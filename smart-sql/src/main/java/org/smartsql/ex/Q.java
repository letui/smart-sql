package org.smartsql.ex;

import org.smartsql.core.$;

//封装$，支持JQUERY风格操作方式
public class Q {
	private ThreadLocal<$> _$ =new ThreadLocal<$>();
	private static ThreadLocal<Q> _Q =new ThreadLocal<Q>();
	private ThreadLocal<L> _L =new ThreadLocal<L>();
	public static Q $(S setup) {
		if(setup!=null) {
			$ smart=$.init(setup.dbs, setup.sqlPath);
			Q q=new Q();
			q._$.set(smart);
			_Q.set(q);
			return _Q.get();
		}
		return null;
	}
	public static Q $(L l) {
		Q q=_Q.get();
		q._L.set(l);
		return q;
	}
	public static Q $(S server,L l) {
		return null;
	}
	
	public String done(String target) {
		if(this._L.get().equals(L.select)) {
			return this._$.get().select(target);
		}
		return null;
	}
}
