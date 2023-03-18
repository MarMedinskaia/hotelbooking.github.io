/**
 * A test class that creates three threads, each with its customer object.
 * The program runs for approximately 6 seconds and interrupts the threads. Finally, the hotel 
 * reservation summary is shown.
 * 
 * @author Margarita Medinskaia
 *
 */

public class HotelTester {
	
	public static void main(String[] args) throws InterruptedException {
		Hotel hotel = new Hotel();
		PeriodicCustomer pc = new PeriodicCustomer("Olivia",hotel);
		PeriodicCustomer pc2 = new PeriodicCustomer("George",hotel);
		PeriodicCustomer pc3 = new PeriodicCustomer("Christopher",hotel);
		
		Thread t1 = new Thread(pc);
		Thread t2 = new Thread(pc2);
		Thread t3 = new Thread(pc3);
	
		t1.start();
		t2.start();
		t3.start();
		Thread.sleep(6000);
		t1.interrupt();
		t2.interrupt();
		t3.interrupt();
		t1.join();
		t2.join();
		t3.join();
		
		System.out.println(hotel.reservationInformation());
	}
}
