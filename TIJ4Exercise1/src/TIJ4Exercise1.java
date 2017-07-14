import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * Exercise 1 & Exercise 4. Thinking in Java 4 Concurrency.
 * 
 * @author jingjiejiang
 * @history
 * 1.Jul 14, 2014
 */
public class TIJ4Exercise1 {
	
	public static class RepeatThreeTimes implements Runnable {

		public RepeatThreeTimes() {
			System.out.println(this + " thread is constructed.");
		}
		
		@Override
		public void run() {
			int count = 1;
			while (count <= 3) {
				System.out.println(this + " -- Thread Message" + count ++);
				Thread.yield();
			}
			System.out.println(this + " ** is terminated!");
		}
	}

	public static void main(String[] args) {
		
		/*
		for (int i = 0; i < 5; i ++) {
			Thread t = new Thread(new RepeatThreeTimes());
			t.start();
		}
		*/
		
		// Exercise 3: use Executor
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i ++) {
			exec.execute(new RepeatThreeTimes());
		}
		exec.shutdown();
	}
}
