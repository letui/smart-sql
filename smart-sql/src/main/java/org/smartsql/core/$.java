package org.smartsql.core;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.smartsql.ex.SQL;
import org.smartsql.inf.Column;
import org.smartsql.inf.Table;
import org.smartsql.inf.Wrap;

public class $ {
	public static final String mysql_driver = "com.mysql.jdbc.Driver";
	private R r;
	private M m=M.autoCommit(true);
	private DataSource dbsource;
	private ThreadLocal<Connection> local=new ThreadLocal<Connection>();
	private $(DataSource dbsource, String sqlPath) {
		this.dbsource = dbsource;
		try {
			this.r = new R(sqlPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T select(A target, Object... args) {
		return (T) select(target.key, target.pojo, args);
	}
	@SuppressWarnings("hiding")
	public <T> T select(A target, Class<T> pojo, Object... args) {
		return select(target.key, pojo, args);
	}

	@SuppressWarnings("hiding")
	public <T> T select(String target, Class<T> pojo, Object... args) {
		String sql = r.get(target);
		PreparedStatement prp = prepare(sql);
		try {
			if (args != null && args.length > 0) {
				for (int i = 0; i < args.length; i++) {
					prp.setObject(1 + i, args[i]);
				}
			}
			ResultSet rst = prp.executeQuery();
			Table table = pojo.getAnnotation(Table.class);
			if (table != null) {
				T rt = pojo.newInstance();
				Field[] entryFields = pojo.getDeclaredFields();
				if (rst.next()) {
					for (int i = 0; i < entryFields.length; i++) {
						Column ano = entryFields[i].getAnnotation(Column.class);
						if (ano != null) {
							Object dbValue = rst.getObject(ano.value());
							entryFields[i].setAccessible(true);
							entryFields[i].set(rt, dbValue);
						}
					}
				}
				return rt;
			}
			Wrap wrap = pojo.getAnnotation(Wrap.class);
			if (wrap != null) {
				T rt = pojo.newInstance();
				Field[] subtables = pojo.getDeclaredFields();
				int sub = 0;
				if (rst.next()) {
					Table tbl = subtables[sub].getAnnotation(Table.class);
					do {
						Class<?> subObjClass = subtables[sub].getType();
						Field[] entryFields = subObjClass.getDeclaredFields();
						Object subObj = subObjClass.newInstance();
						for (int i = 0; i < entryFields.length; i++) {
							Column ano = entryFields[i].getAnnotation(Column.class);
							if (ano != null) {
								Object dbValue = rst.getObject(ano.value());
								entryFields[i].setAccessible(true);
								entryFields[i].set(subObj, dbValue);
							}
						}
						
						subtables[sub].setAccessible(true);
						subtables[sub].set(rt, subObj);
					} while (++sub < subtables.length && tbl != null);
				}
				return rt;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String select(String target, Object... args) {
		String sql = r.get(target);
		return selectDirectly(sql, args);
	}
	private String selectDirectly(String sql, Object... args) {
		PreparedStatement prp = prepare(sql);
		try {
			if (args != null && args.length > 0) {
				for (int i = 0; i < args.length; i++) {
					prp.setObject(1 + i, args[i]);
				}
			}
			ResultSet rst = prp.executeQuery();
			int columnCount = rst.getMetaData().getColumnCount();
			List<Object> rowsList = new ArrayList<Object>();
			while (rst.next()) {
				List<Object> row = new ArrayList<Object>();
				for (int i = 0; i < columnCount; i++) {
					row.add(rst.getObject(i + 1));
				}
				rowsList.add(row);
			}
			return rowsList.toString();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String select(String target) {
		return select(target,new Object[0]);
	}
	public String select(SQL sql) {
		return selectDirectly(sql.toString(),new Object[0]);
	}

	public boolean delete(String target) {
		String sql=r.get(target);
		return exe(sql);
	}
	public boolean delete(String target,Object...params) {
		String sql=r.get(target);
		return exe(sql);
	}

	public boolean update(String target) {
		String sql=r.get(target);
		return exe(sql);
	}
	public boolean update(String target,Object...params) {
		String sql=r.get(target);
		return exe(sql);
	}

	public boolean insert(String target) {
		String sql=r.get(target);
		return exe(sql);
	}
	
	public boolean insert(String target,Object...params) {
		String sql=r.get(target);
		return exe(sql);
	}

	public boolean exe(String sql) {
		try {
			statement().execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public void commit() {
		try {
			if(!local.get().isClosed()) {
				local.get().commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollback() {
		try {
			if(!local.get().isClosed()) {
				local.get().rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public static $ init(DataSource dbs, String sqlPath) {
		return new $(dbs, sqlPath);
	}
	
	public static $ init(DataSource dbs, String sqlPath,M config) {
		return init(dbs,sqlPath).config(config);
	}
	
	public $ config(M config) {
		this.m=config;
		return this;
	}
	
	public void sync(T to) {
		if(to.syncType==T.TABLE_TO_MODEL) {
			PreparedStatement prp=	this.prepare("show tables");
			try {
				ResultSet rst=prp.executeQuery();
				while(rst.next()) {
					System.out.println(rst.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public Connection connect() {
		try {
			
			if(local.get()==null || local.get().isClosed()){
				Connection tmp=this.dbsource.getConnection();
				tmp.setAutoCommit(m.autoCommit);
				local.set(tmp);
			}
			//System.out.println(local.get().hashCode());
			return local.get();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Statement statement() {
		try {
			return connect().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public PreparedStatement prepare(String sql) {
		try {
			return connect().prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
