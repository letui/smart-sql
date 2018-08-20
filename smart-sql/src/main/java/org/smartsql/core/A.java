package org.smartsql.core;

public class A {
	protected String key="";
	protected Class<?> pojo;
	public static A file(String fileName) {
		A x=new A();
		x.key=fileName;
		return x;
	}
	public A sql(String target) {
		this.key+="#"+target;
		return this;
	}
	
	public A pojo(Class<?> cls) {
		this.pojo=cls;
		return this;
	}
}
