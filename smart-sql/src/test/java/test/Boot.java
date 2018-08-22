package test;


import static org.smartsql.ex.L.select;
import static org.smartsql.ex.Q.$;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.smartsql.core.$;
import org.smartsql.core.A;
import org.smartsql.core.M;
import org.smartsql.core.T;
import org.smartsql.ex.S;
import org.smartsql.ex.SQL;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class Boot {
	public static void main(String[] args) {
		$ s = $.init(setup(),"src/main/resources");
		ArrayList<Person> plist=s.select(Person.class,"test#all");
		System.out.println(plist);
		
		String url="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
		s=$.init(S.url(url).user("root").pwd("").sqlpath("src/main/resources"));
		System.out.println(s.select("test#all"));
		
	}
	
	
	public static void main3(String[] args) {
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
		
		String rst=s.select("test#count").toString();
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
		
		
		
		
		
		$ smart = $.init(setup(),"src/main/resources").config(M.autoCommit(false));
		
		SQL sql=SQL.select().from("person").where().andEqual("id", "1");
		System.out.println(smart.select(sql));
		
		
		
		smart.sync(T.tableToModel("test"));
	}

	public static DataSource setup() {
		String url="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
		MysqlConnectionPoolDataSource poolDbs=new MysqlConnectionPoolDataSource();
		poolDbs.setUser("root");
		poolDbs.setPassword("");
		poolDbs.setUrl(url);
		return poolDbs;
	}
}
