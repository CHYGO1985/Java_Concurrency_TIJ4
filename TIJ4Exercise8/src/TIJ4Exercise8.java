/**
 * 
 * Exercise 8 - use deamon thread. TIJ4 Concurrency.
 * 
 * @author jingjiejiang
 * @history
 * 1. Jul 15, 2017
 */
class LiftOff implements Runnable {
	protected int countDown = 10; // Default
	private static int taskCount = 0;
	private final int id = taskCount++;

	public LiftOff() {
	}

	public LiftOff(int countDown) {
		this.countDown = countDown;
	}

	public String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!")
				+ "), ";
	}

	public void run() {
		while (countDown-- > 0) {
			System.out.print(status());
			Thread.yield();
		}
	}
}

public class TIJ4Exercise8 {

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			Thread temp = new Thread(new LiftOff());
			temp.setDaemon(true);
			temp.start();
		}
		System.out.println("Waiting for LiftOff");
	}
}

/* output:
#1(9), #2(9), Waiting for LiftOff
#4(9), #0(9), #3(9), #1(8), #2(8), #4(8), #0(8), #3(8), #1(7), #2(7), #4(7), #0(7), #3(7), #1(6), 
*/
