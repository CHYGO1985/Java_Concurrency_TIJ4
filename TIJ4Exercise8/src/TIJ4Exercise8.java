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
