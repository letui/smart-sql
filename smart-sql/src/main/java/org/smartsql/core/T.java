package org.smartsql.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	String parseName(String inputName){
		inputName = inputName.toLowerCase();
		final StringBuffer sb = new StringBuffer();
		Pattern p = Pattern.compile("_(\\w)");
		Matcher m = p.matcher(inputName);
		while (m.find()){
			m.appendReplacement(sb,m.group(1).toUpperCase());
		}
		m.appendTail(sb);
		return sb.toString();
	}

	String parseType(String inputType){
		if(inputType.contains("int")){
			return "int";
		}
		if(inputType.contains("float")){
			return "float";
		}
		if(inputType.contains("double")){
			return "double";
		}
		if(inputType.startsWith("long")){
			return "long";
		}
		if(inputType.contains("boolean")){
			return "boolean";
		}
		if(inputType.contains("date")){
			return "Date";
		}
		return "String";
	}
}
