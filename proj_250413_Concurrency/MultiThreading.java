public class MultiThreading implements Runnable {
    String text;

    public MultiThreading(String text) {
        this.text = text;
    }


    @Overriding
    public void run() {
        for (int i=1; i<=5; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(i + " " + this.text);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            
        }
    }
}
