package test;


import static org.smartsql.ex.L.select;
import static org.smartsql.ex.Q.$;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.smartsql.core.$;
import org.smartsql.core.A;
import org.smartsql.core.M;
import org.smartsql.core.T;
import org.smartsql.ex.S;
import org.smartsql.ex.SQL;

public class Boot {
	public static void main(String[] args) {
		$ s = $.init(setup(),"src/main/resources");
		ArrayList<Person> plist=s.select(Person.class,"test#all");
		System.out.println(plist);
	}
	
	
	public static void main3(String[] args) {
		//$(S.url("").user("").pwd("").driver("").sql_path(""));	
		$(S.init(setup(),"src/main/resources"));
		
		
		String rst=$(select).done("test#count");
		
		System.out.println(rst);
		
			rst=$(select).done("test#select",2);
		
		System.out.println(rst);
		
		Person p=$(select).done(A.file("test").sql("select"),Person.class,2);
		
		System.out.println(p.getAge());
		
		 p=$(select).done(A.file("test").sql("select").pojo(Person.class),1);
		 System.out.println(p.getUname());
	}
	public static void main2(String[] args) {
		$ s = $.init(setup(),"src/main/resources");
		
		String rst=s.select("test#count");
		System.out.println(rst);
		
		Person p=s.select("test#select", Person.class, 2);
		System.out.println(p.getAge());
		
		A target=A.file("test").sql("select");
		Person p2=s.select(target, Person.class, 1);
		System.out.println(p2.getUname());
		
		target=A.file("test").sql("select").pojo(Person.class);
		p2=s.select(target,1);
		System.out.println(p2.getUname());
		
		target=A.file("test").sql("select").pojo(WrapPerson.class);
		WrapPerson wrp=s.select(target,1);
		System.out.println(wrp.getP().getUname());
		
		
		
		TestThread ts=new TestThread();
		ts.s=s;
		ts.start();
		
		
		$ smart = $.init(setup(),"src/main/resources").config(M.autoCommit(false));
		
		SQL sql=SQL.select().from("person").where().andEqual("id", "1");
		System.out.println(smart.select(sql));
		
		
		
		smart.sync(T.tableToModel("test"));
	}

	public static DataSource setup() {
		String url="jdbc:mysql://localhost:3306/test?user=root&password=&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
		BasicDataSource rst=new BasicDataSource();
		rst.setDriverClassName($.mysql_driver);
		rst.setUrl(url);
		return rst;
	}
}
