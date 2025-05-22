import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.*;

/* =============================================
 *      Airport Runway Management System
 * =============================================
 * 
 * This program simulates an airport with 10 airplanes trying to land and take off
 * using 5 runways. The system make sure:
 * - no two planes use the same runway at once
 * - without starvation
 * - prevent deadlocks
 * 
 * Dining Philosophers problem were used, where runways are shared
 * resources that planes must acquire safely.
 */
public class AirportRunwayManagerSystem {
    private static final int NUM_RUNWAYS = 5;    // # runways
    private static final int NUM_AIRPLANES = 10; // # airplanes
    
    public static void main(String[] args) {
        // create the control tower object to manage runway allocation
        ControlTower controlTower = new ControlTower(NUM_RUNWAYS);
        
        // create airplane threads
        List<Thread> airplanes = new ArrayList<>();
        for (int i = 1; i <= NUM_AIRPLANES; i++) {
            boolean isLanding = new Random().nextBoolean();
            Airplane airplane = new Airplane(i, isLanding, controlTower);
            Thread airplaneThread = new Thread(airplane);
            airplanes.add(airplaneThread);
            airplaneThread.start();
        }
        
        // wait for all airplanes to complete their operations
        for (Thread airplane : airplanes) {
            try {
                airplane.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted while waiting for airplanes");
            }
        }
        
        System.out.println("All airplane operations completed. Airport closing.");
    }
}

/**
 * The Control Tower manages airplanes, safely and preventing deadlocks.
 */
class ControlTower {
    private final int totalRunways;
    private final boolean[] runwaysInUse;
    private final Lock lock;
    private final Condition runwayAvailable;
    

    public ControlTower(int totalRunways) {
        this.totalRunways = totalRunways;
        this.runwaysInUse = new boolean[totalRunways];
        this.lock = new ReentrantLock(true); // Fair lock to prevent starvation
        this.runwayAvailable = lock.newCondition();
    }
    
    public int requestRunway(int airplaneId) throws InterruptedException {
        /*
         * requests a runway for an airplane.
         */
        lock.lock();
        try {
            System.out.println("Airplane " + airplaneId + " is requesting a runway...");
            
            while (true) {
                // Check for available runways
                for (int i = 0; i < totalRunways; i++) {
                    if (!runwaysInUse[i]) {
                        runwaysInUse[i] = true;
                        System.out.println("Airplane " + airplaneId + " has been assigned runway " + (i+1));
                        return i;
                    }
                }
                
                // If no runway available, wait
                System.out.println("Airplane " + airplaneId + " is waiting for a runway...");
                runwayAvailable.await();
            }
        } finally {
            lock.unlock();
        }
    }
    
    public void releaseRunway(int runwayIndex, int airplaneId) {
        /*
         * releases a runway after an airplane is done using it.
         */
        lock.lock();
        try {
            runwaysInUse[runwayIndex] = false;
            System.out.println("Airplane " + airplaneId + " has released runway " + (runwayIndex+1));
            runwayAvailable.signalAll(); // Notify all waiting airplanes
        } finally {
            lock.unlock();
        }
    }
}

class Airplane implements Runnable {
    private final int id;
    private final boolean isLanding;
    private final ControlTower controlTower;
    
    public Airplane(int id, boolean isLanding, ControlTower controlTower) {
        this.id = id;
        this.isLanding = isLanding;
        this.controlTower = controlTower;
    }
    
    @Override
    public void run() {
        try {
            // request a runway
            int runway = controlTower.requestRunway(id);
            
            // simulate the time taken for landing/takeoff
            String operation = isLanding ? "landing" : "taking off";
            System.out.println("Airplane " + id + " is " + operation + " on runway " + (runway+1));
            Thread.sleep(new Random().nextInt(3000) + 1000); // 1-4 seconds
            
            // release the runway
            controlTower.releaseRunway(runway, id);
            
            System.out.println("Airplane " + id + " has completed " + operation);
        } catch (InterruptedException e) {
            System.out.println("Airplane " + id + " was interrupted during operation");
            Thread.currentThread().interrupt();
        }
    }
}