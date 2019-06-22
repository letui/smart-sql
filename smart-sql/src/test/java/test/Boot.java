package test;


import static org.smartsql.ex.L.select;
import static org.smartsql.ex.L.update;
import static org.smartsql.ex.L.insert;
import static org.smartsql.ex.L.delete;
import static org.smartsql.ex.Q.$;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.smartsql.core.$;
import org.smartsql.core.A;
import org.smartsql.core.M;
import org.smartsql.core.T;
import org.smartsql.ex.S;
import org.smartsql.ex.SQL;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class Boot {

    public static void main(String[] args) throws IOException {
        //未实现ORM映射关系
        $ smart = $.init(setup(), "src/main/resources");
        smart.sync(T.tableToModel("test.mdl"));
        //grammer_sugar();


    }

    //基本使用示范
    public static void baseUseSample() {

        //初始化Smart
        $ s = $.init(setup(), "src/main/resources");
        //查询POJO集合
        ArrayList<Person> plist = s.select(Person.class, "test#all");
        System.out.println(plist);

        //动态设置数据链接信息
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
        //初始化
        s = $.init(S.url(url).user("root").pwd("123456").sqlpath("src/main/resources"));
        plist = s.select(Person.class, "test#all");
        System.out.println(plist);

        //插入数据
        boolean v = s.insert("test#insert", 5, "testInsert", 33, new Date());
        System.out.println(v);

        //删除数据
        s.delete("test#delete", 5);

        //更新数据
        s.update("test#updateUnameById", "ImUpdate", 4);

        //高能Jquery语法糖
        //初始化
        $(S.init(setup(), "src/main/resources"));

        //查询POJO
        Person p3 = $(select).doneObject("test#select", Person.class, 3);
        System.out.println(p3.getUname());

        //查询POJO List
        ArrayList<Person> plist2 = $(select).doneList(Person.class, "test#all");
        System.out.println(plist2);

        //更新数据
        String updateValue = $(update).done("test#updateUnameById", "IM$", 3);
        System.out.println(updateValue);

        //插入数据
        String inserV = $(insert).done("test#insert", 5, "testInsert", 33, new Date());
        System.out.println(inserV);

        //删除数据
        $(delete).done("test#delete", 5);
    }


    //扩展语法糖
    public static void grammer_sugar() {
        //初始化数据源
        $(S.init(setup(), "src/main/resources"));

        //结果集自动字符化处理
        String rst = $(select).done("test#count");
        System.out.println(rst);

        //行数据展示
        rst = $(select).done("test#select", 2);
        System.out.println(rst);

        //A: aim 瞄准器语法糖使用
        Person p = $(select).doneObject(A.file("test").sql("select"), Person.class, 2);
        System.out.println(p.getAge());

        //瞄准器 适配POJO
        p = $(select).doneObject(A.file("test").sql("select").pojo(Person.class), 1);
        System.out.println(p.getUname());
    }

    //其他扩展
    public static void other() throws IOException {
        $ s = $.init(setup(), "src/main/resources");

        A target = A.file("test").sql("select");
        Person p2 = s.select(target, Person.class, 1);
        System.out.println(p2.getUname());

        target = A.file("test").sql("select").pojo(Person.class);
        p2 = s.select(target, 1);
        System.out.println(p2.getUname());

        //包裹类适配
        target = A.file("test").sql("select").pojo(WrapPerson.class);
        WrapPerson wrp = s.select(target, 1);
        System.out.println(wrp.getP().getUname());

        //是否自动提交
        $ smart = $.init(setup(), "src/main/resources").config(M.autoCommit(false));

        //动态SQL语法糖
        SQL sql = SQL.select().from("person").where().andEqual("id", "1");
        System.out.println(smart.select(sql));


        smart.sync(T.tableToModel("test"));
    }

    public static DataSource setup() {
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
        MysqlConnectionPoolDataSource poolDbs = new MysqlConnectionPoolDataSource();
        poolDbs.setUser("root");
        poolDbs.setPassword("123456");
        poolDbs.setUrl(url);
        return poolDbs;
    }
}
