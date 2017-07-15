import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * Exersice 6 - make a thread sleep. TIJ4 Concurrency.
 * 
 * @author jingjiejiang
 * @history
 * 1. Jul 15, 2017
 */
public class TIJ4Exercise6 {
	
	public static class SleepyTask implements Runnable {

		@Override
		public void run() {
			Random random = new Random();
			int time = random.nextInt(10) * 1000;
			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
			}
			System.out.println(time);
		}
	}

	public static void main(String[] args) {
		
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i ++) {
			exec.execute(new SleepyTask());
		}
		exec.shutdown();
	}

}
