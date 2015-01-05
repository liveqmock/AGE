package com.age.core.orm.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.age.core.orm.OrmUtil;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.sqlbuilder.xsqlbuilder.XsqlBuilder;
import com.age.core.sqlbuilder.xsqlbuilder.XsqlBuilder.XsqlFilterResult;
import com.age.core.utils.BeanUtils;
import com.age.core.utils.JdbcHelper;
import com.age.core.utils.StrUtils;

@SuppressWarnings("unchecked")
public class JdbcDao {

	protected static Logger logger = null;
	protected static String className = null;

	// 构造方法
	public JdbcDao() {
		super();
		className = getClass().getName();
		logger = LoggerFactory.getLogger(className);
	}

	public Long queryForLong(String sql) {
		try {
			return getJdbcOperations().queryForLong(sql);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Long queryForLong(String sql, Object... args) {
		try {
			return getJdbcOperations().queryForLong(sql, args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Long queryForLong(String sql, Object paramObject) {
		try {
			return sjdbcTemplate.queryForLong(sql, getSqlParameterSource(paramObject));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Integer queryForInt(String sql, Object... args) {
		try {
			return getJdbcOperations().queryForInt(sql);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Integer queryForInt(String sql) {
		try {
			return getJdbcOperations().queryForInt(sql);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Integer queryForInt(String sql, Object paramObject) {
		try {
			return sjdbcTemplate.queryForInt(sql, getSqlParameterSource(paramObject));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	// 根据某些条件进行查询，并且以object的类型返回一个对象
	public <X> X queryForObject(String sql, Class<X> clazz, Object... args) {
		try {
			if (isGeneralClass(clazz)) {
				return getJdbcOperations().queryForObject(sql, clazz, args);
			} else {
				return (X) getJdbcOperations().queryForObject(sql, new BeanPropertyRowMapper(clazz), args);
			}

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public <X> X queryForObject(String sql, Class<X> clazz) {
		try {
			if (isGeneralClass(clazz)) {
				return getJdbcOperations().queryForObject(sql, clazz);
			} else {
				return (X) getJdbcOperations().queryForObject(sql, new BeanPropertyRowMapper(clazz));
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public <X> X queryForObject(String sql, Class<X> clazz, Object paramObject) {
		try {
			if (isGeneralClass(clazz)) {
				return getJdbcOperations().queryForObject(sql, clazz);
			} else {
				return (X) sjdbcTemplate.queryForObject(sql, getSqlParameterSource(paramObject), new BeanPropertyRowMapper(clazz));
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public <X> List<X> queryForList(String sql, Class<X> clazz, Object... args) {
		if (isGeneralClass(clazz))
			return getJdbcOperations().queryForList(sql, clazz, args);
		else
			return getJdbcOperations().query(sql, new BeanPropertyRowMapper(clazz), args);
	}

	public <X> List<X> queryForList(String sql, Class<X> clazz) {
		if (isGeneralClass(clazz))
			return getJdbcOperations().queryForList(sql, clazz);
		else
			return getJdbcOperations().query(sql, new BeanPropertyRowMapper(clazz));
	}

	public <X> List<X> queryForList(String sql, Class<X> clazz, Object paramObject) {
		return sjdbcTemplate.query(sql, getSqlParameterSource(paramObject), new BeanPropertyRowMapper(clazz));
	}

	public int execSql(String sql, Object... args) {
		return getJdbcOperations().update(OrmUtil.convertSql(sql), args);
	}

	public int execSql(String sql) {
		return getJdbcOperations().update(OrmUtil.convertSql(sql));
	}

	public int execSql(String sql, Object paramObject) {
		return sjdbcTemplate.update(OrmUtil.convertSql(sql), getSqlParameterSource(paramObject));
	}

	public int[] batchExecSql(String sql, List<Object[]> objs) {
		sql = this.tranSql(sql, objs.get(0));
		return getJdbcOperations().batchUpdate(OrmUtil.convertSql(sql), objs);
	}

	public <X> Page<X> queryForPage(PageRequest pageRequest, String selSql, Class<X> clazz) {
		return queryForPage(pageRequest, selSql, clazz, null, null);
	}

	public <X> Page<X> queryForPage(PageRequest pageRequest, String selSql, Class<X> clazz, Object paramObject) {
		return queryForPage(pageRequest, selSql, clazz, paramObject, null);
	}

	private <X> Page<X> queryForPage(PageRequest pageRequest, String selSql, Class<X> clazz, Object paramObject, String dialect) {
		int pageNo = pageRequest.getPageNo();
		int pageSize = pageRequest.getPageSize();

		selSql = this.tranSql(selSql, paramObject);
		List<X> resultList = null;
		int v_endrownum = pageNo * pageSize;
		int v_startrownum = v_endrownum - pageSize + 1;
		String v_table = "(" + selSql + ") vtb";

		int totalRow = 0;

		if (pageRequest.isCountTotal()) {
			if (pageRequest.isCacheCount()) {
				totalRow = (int) pageRequest.getTotalItems();
			} else {
				String countQueryGoal = pageRequest.getCountQueryGoal();
				String countsql = "";

				if (pageRequest.isUseOwnerCountTotal() && StringUtils.isNotBlank(pageRequest.getOwnerCountTotalSql())) {// 使用自定义计算总数sql
					countsql = pageRequest.getOwnerCountTotalSql();
				} else {

					if (StringUtils.isEmpty(countQueryGoal)) {
						countsql = "SELECT COUNT(1) FROM " + v_table;
					} else {
						countsql = "SELECT " + countQueryGoal + " COUNT(1) FROM " + v_table;
					}
				}

				if (null == paramObject) {
					totalRow = queryForInt(countsql);
				} else {
					totalRow = queryForInt(countsql, paramObject);
				}
			}
		}

		int midCount = totalRow - pageNo * pageSize;
		String selSqlend = "";
		if ((null == dialect) || ("oracle".equalsIgnoreCase(dialect))) {
			selSqlend = " SELECT * FROM (SELECT vtb.*, rownum rn FROM  " + v_table + " WHERE rownum <= to_char(" + v_endrownum + "))  WHERE rn >= to_char(" + v_startrownum + ")";
		} else if ("teradate".equalsIgnoreCase(dialect)) {
			selSqlend = "SELECT TOP  " + pageSize + " * FROM (SELECT TOP " + midCount + "  *	FROM	(" + selSql + " ) vt	ORDER	BY 1 DESC ) AS a ORDER	BY 1 ASC";
		} else if ("hsql".equalsIgnoreCase(dialect)) {// 要重新写
			selSqlend = "SELECT limit " + pageSize + " * FROM (SELECT TOP " + midCount + "  *	FROM	(" + selSql + " ) vt	ORDER	BY 1 DESC ) AS a ORDER	BY 1 ASC";
		} else if ("mysql".equalsIgnoreCase(dialect)) {// 要重新写 limit (3,5)
			selSqlend = "SELECT TOP  " + pageSize + " * FROM (SELECT TOP " + midCount + "  *	FROM	(" + selSql + " ) vt	ORDER	BY 1 DESC ) AS a ORDER	BY 1 ASC";
		} else if ("sqlServer".equalsIgnoreCase(dialect)) {
			selSqlend = "SELECT TOP  " + pageSize + " * FROM (SELECT TOP " + midCount + "  *	FROM	(" + selSql + " ) vt	ORDER	BY 1 ASC ) AS a ORDER	BY 1 DESC";
		} else {
			return null;
		}
		if (null == paramObject) {
			resultList = this.queryForList(selSqlend, clazz);
		} else {
			resultList = this.queryForList(selSqlend, clazz, paramObject);
		}

		Page<X> page = new Page<X>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);

		if (pageRequest.isCountTotal()) {
			page.setTotalItems(totalRow);
		} else {// 如果不需要计算总算分页，用当前页记录数来判断是否显示下一页
			page.setTotalItems(resultList == null ? 0 : resultList.size());
		}

		page.setResult(resultList);
		return page;
	}

	public List execProcForList(String procedureName, Class clazz, Object inputObject, Object outputObject, String cusorName, String dialect) {
		Map parameters = BeanUtils.Obj2Map(inputObject);
		Map outParams = BeanUtils.Obj2Map(outputObject);
		return execProc4List(procedureName, clazz, parameters, outParams, dialect);
	}

	public Map execProcForMap(String procedureName, Object inputObject, Object outputObject, String cusorName, String dialect) {
		Map parameters = BeanUtils.Obj2Map(inputObject);
		Map outParams = BeanUtils.Obj2Map(outputObject);
		outParams.put("cursor", cusorName);

		return execProc4Map(procedureName, parameters, outParams, dialect);
	}

	public <X> Page<X> execProcForPage(String procedureName, Class<X> clazz, Object inputObject, Object outputObject, String cusorName, String dialect) {

		Map parameters = BeanUtils.Obj2Map(inputObject);
		Map outParams = BeanUtils.Obj2Map(outputObject);

		return execProcForPage(procedureName, clazz, parameters, outParams, dialect);
	}

	public Map execProc4Map(String procedureName, Map parameters, Map outParams, String dialect) {

		sjdbcCall = sjdbcCall.withProcedureName(procedureName);

		if (parameters != null && parameters.size() > 0) {
			Set<String> keys = parameters.keySet();
			for (String key : keys) {
				Object value = parameters.get(key);
				sjdbcCall.declareParameters(new SqlParameter(key, JdbcHelper.translateType(value)));
			}
		}

		if (outParams != null && outParams.size() > 0) {
			Set<String> keys = outParams.keySet();
			for (String key : keys) {
				if (!"cusorName".equals(key)) {
					Object value = outParams.get(key);
					sjdbcCall.declareParameters(new SqlOutParameter(key, JdbcHelper.translateType(value)));
				}
			}
		}

		Map<String, Object> resultMaps = sjdbcCall.execute(parameters);
		return resultMaps;
	}

	public List execProc4List(String procedureName, Class clazz, Map parameters, Map outParams, String dialect) {

		Map<String, Object> resultMaps = this.execProc4Map(procedureName, parameters, outParams, dialect);

		List<Map<String, Object>> listmap = (List<Map<String, Object>>) resultMaps.get(outParams.get("cusorName").toString().toUpperCase());

		List retList = new ArrayList();
		Object instance = null;
		try {
			for (Iterator iterator = listmap.iterator(); iterator.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator.next();
				instance = Class.forName(clazz.getName()).newInstance();
				BeanUtils.Map2Obj(map, instance);
				retList.add(instance);
			}
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		return retList;
	}

	public <X> Page<X> execProcForPage(String procedureName, Class<X> clazz, Map parameters, Map outParams, String dialect) {

		Map<String, Object> resultMaps = this.execProc4Map(procedureName, parameters, outParams, dialect);

		List<Map<String, Object>> listmap = (List<Map<String, Object>>) resultMaps.get(outParams.get("cusorName").toString().toUpperCase()); // 在输出游标默认使用
		// cursor为
		// key

		List retList = new ArrayList();
		Object instance;
		try {
			for (Iterator iterator = listmap.iterator(); iterator.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator.next();
				instance = Class.forName(clazz.getName()).newInstance();
				BeanUtils.Map2Obj(map, instance);
				retList.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		int totalRow = Integer.parseInt(resultMaps.get("totalRow").toString());

		Page<X> page = new Page<X>();
		int pageNo = Integer.parseInt(parameters.get("pageNo").toString());
		int pageSize = Integer.parseInt(parameters.get("pageSize").toString());
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalItems(totalRow);
		page.setResult(retList);
		return page;
	}

	public static String builderSql(String xsql, Object obj) {
		String sql = null;
		if (null != obj) {
			XsqlFilterResult result = new XsqlBuilder().generateHql(xsql, obj);
			sql = result.getXsql();
		} else {
			sql = xsql;
		}

		return sql;
	}

	public String tranSql(String sql, Object obj) {
		String retSql = null;
		if (StrUtils.contains(sql, "/~") || StrUtils.contains(sql, "~/")) {
			retSql = builderSql(sql, obj);
		} else {
			retSql = sql;
		}
		return retSql;
	};

	protected ParameterizedBeanPropertyRowMapper resultBeanMapper(Class clazz) {
		return ParameterizedBeanPropertyRowMapper.newInstance(clazz);
	}

	protected BeanPropertySqlParameterSource paramBeanMapper(Object object) {
		return new BeanPropertySqlParameterSource(object);
	}

	private SqlParameterSource getSqlParameterSource(Object paramObject) {
		if (paramObject instanceof HashMap) {
			return new MapSqlParameterSource((HashMap) paramObject);
		} else {
			return new BeanPropertySqlParameterSource(paramObject);
		}
	}

	private boolean isGeneralClass(Class obj) {
		String className = obj.getName();
		return (className.equals("java.lang.String")) || (className.equals("java.lang.Long")) || (className.equals("java.lang.Double")) || (className.equals("java.lang.Boolean")) || (className.equals("java.lang.Integer"));
	}

	private NamedParameterJdbcTemplate sjdbcTemplate;

	private SimpleJdbcCall sjdbcCall;

	public NamedParameterJdbcTemplate getSjdbcTemplate() {
		return sjdbcTemplate;
	}

	public void setSjdbcTemplate(NamedParameterJdbcTemplate sjdbcTemplate) {
		this.sjdbcTemplate = sjdbcTemplate;
	}

	public JdbcOperations getJdbcOperations() {
		return sjdbcTemplate.getJdbcOperations();
	}

	public SimpleJdbcCall getSjdbcCall() {
		return sjdbcCall;
	}

	public void setSjdbcCall(SimpleJdbcCall sjdbcCall) {
		this.sjdbcCall = sjdbcCall;
	}
}
