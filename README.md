# smart-sql
<h3>快捷，方便，轻量</h3>
重新造一个操作数据库的好轮子,好像也没别的了,喜欢的可以一起加入开发哦。
<h3>花一分钟了解下？</h3>
文件名:test.$,内容
<pre>
#select
select * from person where id = ?
#count
select count(*) from 
person
</pre>
Java POJO
<pre>
public class Person
@Column("id")
private int id;
@Column("uname")
private String uname;
@Column("age")
private int age;
</pre>
<pre>
$ s = $.init(setup(),"src/main/resources");//setup() return instance of DataSource
Person p=s.select("test#select", Person.class, 2);
System.out.println(p.getAge());


OR

A target=A.file("test").sql("select");
Person p2=s.select(target, Person.class, 1);
System.out.println(p2.getUname());

OR

target=A.file("test").sql("select").pojo(Person.class);
p2=s.select(target,1);
System.out.println(p2.getUname());
</pre>

介绍告一段落，当然还提供另一种方式,接着看<br/>
<pre>
$(S.init(setup(),"src/main/resources"));
String rst=$(select).done("test#count");//select 为内置枚举,insert,delete,update 均支持
Person p=$(select).done(A.file("test").sql("select"),Person.class,2);
System.out.println(p.getAge());
</pre>
<h3>
OVER
</h3>
