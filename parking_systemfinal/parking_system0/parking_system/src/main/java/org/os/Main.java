package org.os;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    public static void main(String[] args) {
        SetUpParkingLot parkingLot = new SetUpParkingLot(); // Initialize parking lot
        SynchronizedCounter parkedCarsCounter = new SynchronizedCounter();
       /**/ SynchronizedCounter leavedCarsCounter = new SynchronizedCounter();
       /**/ semaphore parkingSpots = new semaphore(4);
        //Gates gates = new Gates(parkingLot, "Gate 1", "osProject.txt");
        //semaphore parkingSpots = new semaphore();
        //SynchronizedCounter parkedCarsCounter;
        //testCarClass(parkingLot, parkingSpots, parkedCarsCounter, gates);
        SynchronizedCounter gate1Counter = new SynchronizedCounter();
        SynchronizedCounter gate2Counter = new SynchronizedCounter();
        SynchronizedCounter gate3Counter = new SynchronizedCounter();


        // Start gates with their respective car arrival files
        /*new Gates(parkingLot, "Gate 1", "osProject.txt").start();
        new Gates(parkingLot, "Gate 2", "osProject.txt").start();
        new Gates(parkingLot, "Gate 3", "osProject.txt").start();*/
// Start gates with their respective car arrival files
/**/new Gates(parkingLot, "Gate 1", "osProject.txt", parkedCarsCounter, leavedCarsCounter, parkingSpots,gate1Counter).start();
    new Gates(parkingLot, "Gate 2", "osProject.txt", parkedCarsCounter, leavedCarsCounter, parkingSpots,gate2Counter).start();
    new Gates(parkingLot, "Gate 3", "osProject.txt", parkedCarsCounter, leavedCarsCounter, parkingSpots,gate3Counter).start();
        // Allow some time for simulation
        try {
            Thread.sleep(20000);
        } catch (InterruptedException error) {
            error.printStackTrace();
        }



        // Display the final status of the parking lot
        //parkingLot.displayStatus();
        parkingLot.displayStatus(gate1Counter, gate2Counter, gate3Counter);
    }
    /*private static void testCarClass(SetUpParkingLot parkingLot, semaphore parkingSpots, SynchronizedCounter parkedCarsCounter, Gates gates) {
        System.out.println("Testing Car class...");

        // Create a few Car instances
        Cars car1 = new Cars(1, 0, 3, parkingLot, parkingSpots, parkedCarsCounter, gates);
        Cars car2 = new Cars(2, 1, 4, parkingLot, parkingSpots, parkedCarsCounter, gates);

        // Start the car threads
        car1.start();
        car2.start();

        // Wait for the car threads to finish
        try {
            car1.join();
            car2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Car class test completed.");
    }*/

}
