package test;

import org.smartsql.inf.Table;
import org.smartsql.inf.Wrap;

@Wrap
public class WrapPerson {
	@Table("person")
	private Person p;

	public Person getP() {
		return p;
	}

	public void setP(Person p) {
		this.p = p;
	}
	
}
