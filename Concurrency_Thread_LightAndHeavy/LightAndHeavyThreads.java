class LightAndHeavyThreads {
    public static void main(String[] args) throws InterruptedException {
        Thread light = new Thread(() -> {
            System.out.println("Light task starts");
            // try { Thread.sleep(500); } catch (Exception e) {}
            System.out.println("Light task ends");
        });
        
        Thread heavy = new Thread(() -> {
            System.out.println("Heavy task starts");
            for (int i = 0; i < 100000000; i++) {
                Math.sqrt(i); // Fake heavy work
            }
            System.out.println("Heavy task ends");
        });
        
        light.start();
        heavy.start();
        
        light.join();
        heavy.join();
        
        System.out.println("Main thread ends");
    }
}