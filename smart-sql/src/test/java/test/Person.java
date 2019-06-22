package test;

import java.io.Serializable;
import java.util.Date;

import org.smartsql.inf.Column;
import org.smartsql.inf.Table;

@Table("person")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column("id")
	private int id;
	@Column("uname")
	private String uname;
	@Column("age")
	private int age;
	@Column("birth")
	private Date birth;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", uname=" + uname + ", age=" + age + ", birth=" + birth + "]";
	}
}
