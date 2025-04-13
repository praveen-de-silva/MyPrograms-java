import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("5 secs to enter ur name.");

        for (int i=1; i<=5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }

            if (i==5) {
                System.out.println("Times up");
            }
        }

        System.out.print("Enter ur name : ");
        String name = scan.nextLine();
        System.out.println("Hello " + name);
        scan.close();
    }
}