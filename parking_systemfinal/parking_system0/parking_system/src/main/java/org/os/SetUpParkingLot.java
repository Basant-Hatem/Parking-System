package org.os;

public class SetUpParkingLot {
    private final int NumberOfSpots =4;  // Total number of parking spots
    private final semaphore semaphore = new semaphore(NumberOfSpots); // Semaphore to control access without fair ordering
    private final SynchronizedCounter trackParkingCar; // counter that count cars in the parkingg
    private final SynchronizedCounter trackLeavedCar; // counter that count cars that parked in the parking and leave after it finish the duration

    public SetUpParkingLot() {
        this.trackParkingCar = new SynchronizedCounter();
        this.trackLeavedCar = new SynchronizedCounter();
    }

    public void TrackCarParking(int carNum, int duration, String gateName) {
        semaphore.P(carNum, gateName);// it checks if parking has empty spot if it has the car will park if not then the car will wait
        trackParkingCar.increment();
        System.out.println(carNum + " has parked. then " + trackParkingCar.value() + " cars currently parked in parking )");
        try {
            Thread.sleep(duration);
            //Thread.sleep(duration * 1000L);
        } // car parked for specific duration then leave
        catch (InterruptedException e) {
            System.out.println(carNum + "  was interrupted when it parking.");
        }
        trackParkingCar.decrement();// decrease number of cars in parking
        trackLeavedCar.increment();// increase number of cars that leave parking
        System.out.println(carNum + " has left the parking lot. then " + trackParkingCar.value() + " cars currently parked) and Number of cars parked and leave is " + trackLeavedCar.value());

        semaphore.V();// when car leave it notify waiting car in the gate
    }
    /*public void displayStatus() { synchronized (this) { System.out.println("Parking Lot Status:");
        System.out.println("Currently parked cars: " + trackParkingCar.value());
        System.out.println("Total cars served: " + trackLeavedCar.value()); }
    }*/
    public void displayStatus(SynchronizedCounter gate1Counter, SynchronizedCounter gate2Counter, SynchronizedCounter gate3Counter) { synchronized (this)
    {
        int total=gate1Counter.value() +gate2Counter.value()+gate3Counter.value();
        System.out.println("Parking Lot Status:");
        System.out.println("Currently parked cars: " + trackParkingCar.value());
        System.out.println("Total cars served: " + total);
        System.out.println("Details:");
        System.out.println("- Gate 1 served " + gate1Counter.value() + " cars.");
        System.out.println("- Gate 2 served " + gate2Counter.value() + " cars.");
        System.out.println("- Gate 3 served " + gate3Counter.value() + " cars.");
    }
}}


/*package org.os;

public class SetUpParkingLot {
    private final int numberOfSpots = 4; // Total number of parking spots
    private final semaphore semaphore = new semaphore(numberOfSpots); // Semaphore to control parking spot access
    private final SynchronizedCounter trackParkingCar; // Tracks cars currently parked
    private final SynchronizedCounter trackLeavedCar; // Tracks total cars that have parked and left

    // Constructor
    public SetUpParkingLot() {
        this.trackParkingCar = new SynchronizedCounter();
        this.trackLeavedCar = new SynchronizedCounter();
    }

    // Method to handle car parking logic
    public void trackCarParking(int carNum, int duration) {
        System.out.println("Car " + carNum + " is attempting to park...");
        semaphore.P(); // Acquire a parking spot
        synchronized (this) {
            trackParkingCar.increment(); // Increment the count of parked cars
            System.out.println("Car " + carNum + " has parked. (" + trackParkingCar.value() + " cars currently parked)");
        }

        try {
            // Simulate the car staying in the parking lot for the specified duration
            Thread.sleep(duration * 1000L);
        } catch (InterruptedException e) {
            System.out.println("Car " + carNum + " was interrupted while parking.");
        }

        synchronized (this) {
            trackParkingCar.decrement(); // Decrement the count of parked cars
            trackLeavedCar.increment(); // Increment the count of cars that have left
            System.out.println("Car " + carNum + " has left the parking lot. (" + trackParkingCar.value() + " cars currently parked)");
            System.out.println("Total cars served so far: " + trackLeavedCar.value());
        }

        semaphore.V(); // Release the parking spot
    }

    // Display the final parking lot status
    public void displayStatus() {
        synchronized (this) {
            System.out.println("\nFinal Parking Lot Status:");
            System.out.println("Currently parked cars: " + trackParkingCar.value());
            System.out.println("Total cars served: " + trackLeavedCar.value());
        }
    }
}
*/
