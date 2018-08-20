package test;

import org.smartsql.core.$;

public class TestThread extends Thread {

	$ s;
	
	@Override
	public void run() {
		s.select("test#count");
	}
	
	public void init($ s) {
		this.s=s;
	}
}
