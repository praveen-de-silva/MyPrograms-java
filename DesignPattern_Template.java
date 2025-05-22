abstract class Beverage {
    // The template method
    public void prepareBeverage() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    // Concrete methods with a default implementation
    public void boilWater() {
        System.out.println("Boiling water");
    }

    public void pourInCup() {
        System.out.println("Pouring into cup");
    }

    // Abstract methods to be implemented by subclasses
    abstract void brew();
    abstract void addCondiments();
}
	
// concrete class 01
class Tea extends Beverage {
    @Override
    void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding lemon");
    }
}

// concrete class 02
class Coffee extends Beverage {
    @Override
    void brew() {
        System.out.println("Dripping coffee through filter");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
}

public class BeveragePreparation {
    public static void main(String[] args) {
        Tea myTea = new Tea();
        System.out.println("Preparing tea:");
        myTea.prepareBeverage();
        System.out.println("---");
        Coffee myCoffee = new Coffee();
        System.out.println("Preparing coffee:");
        myCoffee.prepareBeverage();
    }
}

