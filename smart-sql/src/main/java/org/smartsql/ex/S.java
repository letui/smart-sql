package org.smartsql.ex;

import javax.sql.DataSource;

public class S {
	protected String url;
	protected String username;
	protected String password;
	protected String driverClass;
	protected String sqlPath;
	protected DataSource dbs;
	
	public static S init(DataSource dbs,String sqlPath) {
		S s=new S();
		s.dbs=dbs;
		s.sqlPath=sqlPath;
		return s;
	}
	
	public static S url(String url) {
		S s=new S();
		return s;
	}
	
	public S user(String username) {
		return this;
	}
	public S pwd(String pwd) {
		return this;
	}
	public S driver(String driver) {
		return this;
	}
	public S sql_path(String sqlPath) {
		return this;
	}
}
