package org.smartsql.ex;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class S {
	protected String url;
	protected String username;
	protected String password;
	public String sqlpath;
	public DataSource dbs;
	
	public static S init(DataSource dbs,String sqlPath) {
		S s=new S();
		s.dbs=dbs;
		s.sqlpath=sqlPath;
		return s;
	}
	
	public static S url(String url) {
		S s=new S();
		s.url=url;
		return s;
	}
	
	public S user(String username) {
		this.username=username;
		init();
		return this;
	}
	public S pwd(String pwd) {
		this.password=pwd;
		init();
		return this;
	}
	public S sqlpath(String sqlpath) {
		this.sqlpath=sqlpath;
		init();
		return this;
	}
	private void init() {
		if(dbs!=null) {
			return;
		}else if(url!=null&&username!=null&&password!=null&&sqlpath!=null){
			MysqlConnectionPoolDataSource dbsMysql=new MysqlConnectionPoolDataSource();
			dbsMysql.setURL(url);
			dbsMysql.setUser(username);
			dbsMysql.setPassword(password);
			dbsMysql.setAutoReconnect(true);
			dbs=dbsMysql;
		}
	}
}
