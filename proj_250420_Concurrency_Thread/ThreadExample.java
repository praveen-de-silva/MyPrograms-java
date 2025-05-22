public class ThreadExample {
    public static void main(String[] args) {
        // Create a new thread
        Thread anotherThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Another thread starts execution");
                
                // This represents the call stack: doMore() → go() → doStuff() → run()
                doMore();
                
                System.out.println("Another thread completes execution");
            }
            
            private void doMore() {
                System.out.println("  Another thread: in doMore()");
                go();
            }
            
            private void go() {
                System.out.println("    Another thread: in go()");
                doStuff();
            }
            
            private void doStuff() {
                System.out.println("      Another thread: in doStuff()");
                // Simulate some work
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted");
                }
            }
        });

        // Start the anotherThread FIRST to see concurrency
        anotherThread.start();
        
        // Main thread's call stack: x.baz() → y.bar() → x.foo() → main()
        System.out.println("Main thread starts execution");
        X.baz();  // Changed to uppercase X
        System.out.println("Main thread completes execution");
        
        try {
            // Main thread waits for anotherThread to complete
            anotherThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted while waiting");
        }
        
        System.out.println("All threads have completed execution");
    }
}