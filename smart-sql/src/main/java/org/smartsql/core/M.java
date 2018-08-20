package org.smartsql.core;

public class M {
	private M() {
		
	}
	protected boolean autoCommit;
	
	public static M autoCommit(boolean value) {
		M m=new M();
		m.autoCommit=value;
		return m;
	}
}
