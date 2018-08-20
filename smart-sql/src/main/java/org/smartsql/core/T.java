package org.smartsql.core;

public class T {
	protected static int MODEL_TO_TABLE=1;
	protected static int TABLE_TO_MODEL=0;
	protected  int syncType=-1;
	protected String pkg="";
	public static T modelToTable(String packageName) {
		T to=new T();
		to.syncType=MODEL_TO_TABLE;
		to.pkg=packageName;
		return to;
	}
	
	public static T tableToModel(String packageName) {
		T to=new T();
		to.syncType=TABLE_TO_MODEL;
		to.pkg=packageName;
		return to;
	}
}
