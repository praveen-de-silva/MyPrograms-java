package proj_250301;

public class Main {
    public static void main(String[] args) {
		Person p1 = new Person("Amal", 22);
		
		
		System.out.println(p1.getName());
		System.out.println(p1.getAge());
		
		p1.setName("Bimal");
		p1.setAge(24);
		
		System.out.println(p1.getName());
		System.out.println(p1.getAge());
		
		Person w1 = new Person("Akmi", 24);
		
		p1.setGF(w1);
		
		Person p2 = p1.sendInstance();
		
		System.out.println("--------- p2 ---------");
		System.out.println(p2.getName());
		System.out.println(p2.getAge());
		
		// Person p3 = new Person("Chamara", 24);
		
		System.out.println("--------- population ---------");
		System.out.println(Person.population);
	}
}
