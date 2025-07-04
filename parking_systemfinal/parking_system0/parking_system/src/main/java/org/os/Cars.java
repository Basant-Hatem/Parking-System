package org.os;

public class Cars extends Thread {
    private final int carId;
    private final int arriveTime;
    private final int parkingDuration;
    private final SetUpParkingLot parkingLot;
    private final semaphore parkingSpotsLeft;
    private final SynchronizedCounter parkedCarsCounter;
    private final Gates gates;



    public Cars(int carId, int arriveTime, int parkingDuration, SetUpParkingLot parkingLot, semaphore parkingSpotsLeft , SynchronizedCounter parkedCarsCounter, Gates gates) {
        this.carId = carId;
        this.arriveTime = arriveTime;
        this.parkingDuration = parkingDuration;
        this.parkingLot = parkingLot;
        this.parkingSpotsLeft = parkingSpotsLeft;
        this.parkedCarsCounter = parkedCarsCounter;
        this.gates = gates;
    }

    @Override
    public void run(){
        try{
            Thread.sleep(arriveTime * 1000L); //simulate arrival time
            System.out.println(" Car " + carId + " from " + gates.getGateName() + " arrived at the time " + arriveTime);

            //parkingLot.TrackCarParking(carId, parkingDuration);
            parkingSpotsLeft.P(carId, gates.getGateName());
            parkedCarsCounter.increment();
            System.out.println(" Car " + carId + " from " + gates.getGateName() + " parked. (parking status: " +  parkedCarsCounter.value() + " spots occupied) ");

            Thread.sleep(parkingDuration * 1000L);

            //parkingLot.TrackCarParking(carId , parkingDuration);
            parkingSpotsLeft.V();
            parkedCarsCounter.decrement();
            System.out.println(" Car " + carId + " from " + gates.getGateName() + " left after " + parkingDuration + " units of time. (Parking Status: " + parkedCarsCounter.value() + " spots occupied) ");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
