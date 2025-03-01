package proj_250301;

public class Person {
	static int population = 0;
	private String name;
	private int age;
	private Person gf;
	
	public Person (String aName, int aAge) {
//		this.name = aName;
//		this.age = aAge;
		
		this.setDetails(aName, aAge);
		
		this.population++;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String aName) {
		this.name = aName;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int aAge) {
		this.age = aAge;
	}
	
	public void setDetails(String aName2, int aAge2) {
		this.name = aName2;
		this.age = aAge2;
	}
	
	
	public void setGF(Person person) {
		this.gf = person;
	}
	
	public Person sendInstance() {
		return this;
	}
	
}
