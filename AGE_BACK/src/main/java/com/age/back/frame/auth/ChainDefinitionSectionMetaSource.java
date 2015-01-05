package com.age.back.frame.auth;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.age.back.sysmng.model.SysResource;
import com.age.back.sysmng.service.ISysResourceService;

public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {

	@Autowired
	protected ISysResourceService sysResourceService;

	//shiro默认的链接定义
	private String filterChainDefinitions;

	/**
	 * 通过filterChainDefinitions对默认的链接过滤定义
	 * 
	 * @param filterChainDefinitions 默认的接过滤定义
	 */
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	@Override
	public Section getObject() throws BeansException {
		Ini ini = new Ini();
		//加载默认的url
		ini.load(filterChainDefinitions);
		Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
		if (CollectionUtils.isEmpty(section)) {
			section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}
		//循环数据库资源的url
		for (SysResource resource : sysResourceService.findSysResources()) {
			if (StringUtils.isNotEmpty(resource.getResNo()) && StringUtils.isNotEmpty(resource.getUrl())) {
				section.put(resource.getUrl(), getPremission(resource.getResNo()));
			}
		}
		return section;
	}

	@Override
	public Class<?> getObjectType() {
		return Section.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public static String getPremission(String pres) {
		StringBuilder sb = new StringBuilder();
		sb.append("perms[");
		sb.append(pres.replace("/", ":").substring(1));
		sb.append("]");

		return sb.toString();
	}

}