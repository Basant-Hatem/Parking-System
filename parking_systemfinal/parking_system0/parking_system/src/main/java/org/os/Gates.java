package org.os;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*public class Gates extends Thread {
    private final SetUpParkingLot parkingLot;
    private final String gateName;
    private final String fileName;
    //new
    private final SynchronizedCounter parkedCarsCounter;
    private final SynchronizedCounter leavedCarsCounter;
    private final semaphore parkingSpots;

    public Gates(SetUpParkingLot parkingLot, String gateName, String fileName) {
        this.parkingLot = parkingLot;
        this.gateName = gateName;
        this.fileName = fileName;
        //new
        this.parkedCarsCounter = parkedCarsCounter;
        this.leavedCarsCounter = leavedCarsCounter;
        this.parkingSpots = parkingSpots;
    }

    @Override
    public void run() {
        try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferReader.readLine()) != null) {
                if (line.startsWith(gateName)) {
                    String[] parts = line.split(", ");
                    int carId = Integer.parseInt(parts[1].split(" ")[1]);
                    int arriveTime = Integer.parseInt(parts[2].split(" ")[1]);
                    int parkDuration = Integer.parseInt(parts[3].split(" ")[1]);
                    new Cars(carId, arriveTime, parkDuration, parkingLot, parkingSpots, parkedCarsCounter, this).start();
                }
                /*new Thread(() -> {
                        try {
                            Thread.sleep(arriveTime * 1000L); // Simulate arrival time
                            System.out.println("Car " + carId + " from " + gateName + " arrived at time " + arriveTime);

                            parkingLot.TrackCarParking(carId, (int) (parkDuration * 1000L));
                        }catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getGateName() {
        return this.gateName;
    }
}
*/
class Gates extends Thread {
    private final SetUpParkingLot parkingLot;
    private final String gateName;
    private final String fileName;
    private final SynchronizedCounter parkedCarsCounter;
    private final SynchronizedCounter leavedCarsCounter;
    private final semaphore parkingSpots;
    private SynchronizedCounter gateCounter = null;

    public Gates(SetUpParkingLot parkingLot, String gateName, String fileName, SynchronizedCounter parkedCarsCounter, SynchronizedCounter leavedCarsCounter, semaphore parkingSpots, SynchronizedCounter gateCounter) {
        this.parkingLot = parkingLot;
        this.gateName = gateName;
        this.fileName = fileName;
        this.parkedCarsCounter = parkedCarsCounter;
        this.leavedCarsCounter = leavedCarsCounter;
        this.parkingSpots = parkingSpots;
        this.gateCounter = gateCounter;
    }




    @Override
    public void run() {
        try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferReader.readLine()) != null) {
                if (line.startsWith(gateName)) {
                    String[] parts = line.split(", ");
                    int carId = Integer.parseInt(parts[1].split(" ")[1]);
                    int arriveTime = Integer.parseInt(parts[2].split(" ")[1]);
                    int parkDuration = Integer.parseInt(parts[3].split(" ")[1]);
                    gateCounter.increment();
                    new Cars(carId, arriveTime, parkDuration, parkingLot, parkingSpots, parkedCarsCounter, this).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getGateName() {
        return this.gateName;
    }
}
