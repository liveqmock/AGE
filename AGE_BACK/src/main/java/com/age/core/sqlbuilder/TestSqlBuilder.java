package com.age.core.sqlbuilder;

public class TestSqlBuilder {

	/**
	 * @param args
	 *            /~ username = {username} ~/ /~ password like '%[password]%' ~/
	 *            /~ birthDate > {startBirthDate} and birthDate < [endBirthDate]
	 *            ~/
	 * 
	 *            将Map filters中的数据类型修饰为另外一种类型 /~ {username} ~/ /~ {age?int} ~/
	 *            /~ {birthDate?timestamp(yyyy年MM月dd日)} ~/
	 * 
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String xsql = "select * from user where 1=1  /~ and username = {username} ~/ /~ and password = {password} ~/ /~ and age = [age] ~/ /~ and sex = [sex] ~/";
		StringBuffer sb = new StringBuffer();
		sb.append("Insert Into t_Sys_User     ");
		sb.append("  (u_Id,                   ");
		sb.append("   g_Id,                   ");
		sb.append("   Loginname,              ");
		sb.append("   Password,               ");
		sb.append("   Username,               ");
		sb.append("   Mobilephone,            ");
		sb.append("   Email,                  ");
		sb.append("   Createtime,             ");
		sb.append("   u_Type,                 ");
		sb.append("   Lastlogintime,          ");
		sb.append("   Logincount,             ");
		sb.append("   Status)                 ");
		sb.append("Values                     ");
		sb.append("  (/~{u_id},~/             ");
		sb.append("   /~{g_id},~/             ");
		sb.append("   /~{loginname},~/        ");
		sb.append("   /~{password},~/         ");
		sb.append("   /~{username},~/         ");
		sb.append("   /~{mobilephone},~/      ");
		sb.append("   /~{email},~/            ");
		sb.append("   /~{createtime},~/       ");
		sb.append("   /~{u_type},~/           ");
		sb.append("   /~{lastlogintime},~/    ");
		sb.append("   /~{logincount},~/       ");
		sb.append("   /~{status}~/  )         ");
		/*
		 * UserVO userVO = new UserVO(); userVO.setG_id("1");
		 * userVO.setUserid("222"); userVO.setEmail("guojf@sohu.com");
		 * userVO.setStatus("1"); XsqlFilterResult result = new
		 * XsqlBuilder().generateHql(sb.toString(), userVO); String sql =
		 * result.getXsql(); System.out.println(sql);
		 */

	}

}
