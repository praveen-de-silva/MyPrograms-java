// Methods representing the main thread's call stack
public class X {
    static void baz() {
        System.out.println("  Main thread: in X.baz()");
        Y.bar();
    }
    
    static void foo() {
        System.out.println("    Main thread: in X.foo()");
    }
}



