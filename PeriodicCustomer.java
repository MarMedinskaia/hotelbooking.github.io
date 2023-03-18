/**
 * An automated class that contains Customer constructor, and run method that runs indefinitely 
 * until the thread is interrupted. It calls either the method to make a reservation for randomly 
 * generated dates, or the method to cancel reservations. One of these is chosen based on randomly 
 * generated integer 1 or 2. Each reservation update is printed in the console, as well as the fact of thread interruption.
 * 
 * @author Margarita Medinskaia
 *
 */

public class PeriodicCustomer implements Runnable {
	Hotel hotel;
	private String name;
	private static final int DELAY = 1000;
	String status, update;
	boolean success;

	public PeriodicCustomer(String name, Hotel hotel) {
		this.name = name;
		this.hotel = hotel;
	}

	@Override
	public void run() {
		try {
			while(true) {
				int r = (int)Math.ceil(Math.random()*2);
				if (r==1) {
					int d = (int)Math.ceil(Math.random()*31);
					int d2 = (int)Math.ceil(Math.random()*31);
					if (d<d2) {
						success=hotel.requestReservation(this.name, d, d2);
						if (success) {
							status="made";
						} else {
							status="unsuccessful";
						}
						update = "Reservation "+status+": "+this.name+" from "+d+" through "+d2;
						System.out.println(update);	
						Thread.sleep(DELAY);
					}
				} else {
					success = hotel.cancelReservation(this.name);
					if (success) {
						update = "Reservations successfully cancelled for "+this.name;
					} else {
						update = "Reservations not cancelled for "+this.name+", no current reservation";
					}
					System.out.println(update);
					Thread.sleep(DELAY);
				}
			}
		} catch (InterruptedException e) {	
			System.out.println("Periodic Test Customer "+this.name+" Shutting Down");
		} 
	}
}


