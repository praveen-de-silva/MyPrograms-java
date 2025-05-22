import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* -----------------
         *      Ex : 01
         * ----------------- 
         */

        // Scanner scan = new Scanner(System.in);

        // MyRunnable myRunnable = new MyRunnable();
        // Thread thread = new Thread(myRunnable);
        // thread.setDaemon(true);
        // thread.start();

        // System.out.println("5 secs to enter ur name.");
        // System.out.print("Enter ur name : ");
        // String name = scan.nextLine();

        // System.out.println("Hello " + name);
        
        // scan.close();
  
        /* -----------------
         *      Ex : 02
         * ----------------- 
         */

        Thread threadMT1 = new Thread(new MultiThreading("Head"));
        Thread threadMT2 = new Thread(new MultiThreading("Tail"));

        System.out.println("Game Start!");
        threadMT1.start();
        threadMT2.start();

        try {
            threadMT1.join();
            threadMT2.join();
        } catch (InterruptedException e) {
            System.out.println("Main method was interupted.");
        }
        System.out.println("Game Over!");
    }
}