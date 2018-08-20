package org.smartsql.ex;

public class SQL {
	private StringBuffer buf=new StringBuffer();
	private SQL() {
		
	}
	public static SQL select() {
		SQL sql=new SQL();
		sql.buf.append("SELECT * ");
		return sql;
	}
	public static SQL update() {
		SQL sql=new SQL();
		return sql;
	}
	public static SQL delete() {
		SQL sql=new SQL();
		return sql;
	}
	public static SQL insert() {
		SQL sql=new SQL();
		return sql;
	}
	
	public SQL from(String table) {
		this.buf.append("FROM "+table+" ");
		return this;
	}
	public SQL from(String table,String alias) {
		return this;
	}
	
	public SQL where() {
		this.buf.append("WHERE ");
		return this;
	}
	
	public SQL leftJoin(String table) {
		return this;
	}
	public SQL rightJoin(String table) {
		return this;
	}
	
	public SQL innerJoin(String table) {
		return this;
	}
	public SQL on(String columnSrc,String columnTarget) {
		return this;
	}
	
	public SQL orEqual(String src,String target) {
		return this;
	}
	public SQL orLessThan(String src,String target) {
		return this;
	}
	public SQL orGreatThan(String src,String target) {
		return this;
	}
	
	public SQL andEqual(String src,String target) {
		if(!this.buf.toString().trim().endsWith("WHERE")) {
			this.buf.append("AND ");
		}
		this.buf.append(src+" = "+target);
		return this;
	}
	public SQL andLessThan(String src,String target) {
		return this;
	}
	public SQL andGreatThan(String src,String target) {
		return this;
	}
	
	public SQL groupBy(String strings) {
		return this;
	}
	public SQL orderBy(String strings) {
		return this;
	}
	
	public SQL in(String strings) {
		return this;
	}
	@Override
	public String toString() {
		return buf.toString();
	}

}
