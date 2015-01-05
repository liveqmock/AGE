package com.age.core.orm;

public class OrmUtil {

	/**
	 * 转换delete sql语句，排除全表删除
	 * @param sql
	 * @return
	 */
	public static String convertSql(String sql) {
		sql = sql.trim();
		String retSql = "";//返回sql; 
		if (isSimilar("delete", sql)) {//判断是否为delete语句
			//判断是否带where条件
			String lowerSql = sql.trim().toLowerCase();
			int whereIndex = lowerSql.indexOf("where");
			if (whereIndex > 0) {//带where条件 
				String sqlWhere = lowerSql.substring(whereIndex + 5, sql.length());
				//判断sqlWhere是否为1=1
				sqlWhere = sqlWhere.replaceAll(" ", "");
				if (sqlWhere.equals("1=1")) {
					retSql += sql + " and 1=0 ";
				} else {
					retSql = sql;
				}
			} else {//不带where条件
				retSql += sql + " where 1=0 ";
			}
		} else {
			retSql = sql;
		}
		return retSql;
	}

	public static Boolean isSimilar(String one, String anotherString) {

		int length = one.length();
		if (length > anotherString.length()) {
			//如果被期待为开头的字符串的长度大于anotherString的长度  
			return false;
		}
		if (one.equalsIgnoreCase(anotherString.substring(0, length))) {
			return true;
		} else {
			return false;
		}
	}
}
