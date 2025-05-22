public class Y {
    static void bar() {
        System.out.println("    Main thread: in Y.bar()");
        X.foo();
    }
}
