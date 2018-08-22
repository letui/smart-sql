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
	public static SQL update(String table) {
		SQL sql=new SQL();
		sql.buf.append("UPDATE "+table+" ");
		return sql;
	}
	public static SQL delete() {
		SQL sql=new SQL();
		sql.buf.append("DELETE ");
		return sql;
	}
	public static SQL insert(String table) {
		SQL sql=new SQL();
		sql.buf.append("INSERT INTO "+table+" ");
		return sql;
	}
	
	public SQL from(String table) {
		this.buf.append("FROM "+table+" ");
		return this;
	}
	public SQL from(String table,String alias) {
		this.buf.append("FROM "+table+" "+alias+" ");
		return this;
	}
	
	public SQL where() {
		this.buf.append("WHERE 1=1 ");
		return this;
	}
	
	public SQL leftJoin(String table) {
		this.buf.append("LEFT JOIN "+table+" ");
		return this;
	}
	public SQL rightJoin(String table) {
		this.buf.append("RIGHT JOIN "+table+" ");
		return this;
	}
	
	public SQL innerJoin(String table) {
		this.buf.append("INNER JOIN "+table+" ");
		return this;
	}
	public SQL on(String columnSrc,String columnTarget) {
		this.buf.append("ON "+columnSrc+" = "+columnTarget+" ");
		return this;
	}
	
	public SQL orEqual(String src,String target) {
		this.buf.append("OR "+src+" = "+target+" ");
		return this;
	}
	public SQL orLessThan(String src,String target) {
		this.buf.append("OR "+src+" < "+target+" ");
		return this;
	}
	public SQL orGreatThan(String src,String target) {
		this.buf.append("OR "+src+" > "+target+" ");
		return this;
	}
	
	public SQL andEqual(String src,String target) {
		this.buf.append("AND "+src+" = "+target+" ");
		return this;
	}
	public SQL andLessThan(String src,String target) {
		this.buf.append("AND "+src+" < "+target+" ");
		return this;
	}
	public SQL andGreatThan(String src,String target) {
		this.buf.append("AND "+src+" > "+target+" ");
		return this;
	}
	
	public SQL groupBy(String strings) {
		this.buf.append("GROUP BY "+strings+" ");
		return this;
	}
	public SQL orderBy(String strings) {
		this.buf.append("ORDER BY "+strings+" ");
		return this;
	}
	
	public SQL in(String strings) {
		this.buf.append("in "+strings+" ");
		return this;
	}
	@Override
	public String toString() {
		return buf.toString();
	}

}
