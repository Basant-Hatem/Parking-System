package org.os;

public class semaphore {

        protected int value = 0 ;
        protected semaphore() { value = 0 ; }
        public semaphore(int initial) { value = initial ; }
        public synchronized void P(int carId, String gateName) {
            // acquire
            value--; if (value < 0) { System.out.println("Car " + carId + " from " + gateName + " waiting for a spot.");
                try { wait(); } catch (InterruptedException e) { } } }
        public synchronized void V() {// release
            value++ ; if (value <= 0) notify() ;
        }

}
