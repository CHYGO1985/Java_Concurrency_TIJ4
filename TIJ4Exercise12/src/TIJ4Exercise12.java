import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 
 * Exercise12 - learn to use synchronised and use Future to control the running
 * time of a thread.
 * 
 * @author jingjiejiang
 * @history 
 * 1. Jul 21, 2017
 */
class AtomicityTest implements Runnable {
	private int i = 0;

	// if getValue is not synchronised, it may get i at intermediate status of evenIncrement
	public synchronized int getValue() {
		return i;
	}

	private synchronized void evenIncrement() {
		i++;
		i++;
	}

	public void run() {
		while (true)
			evenIncrement();
	}
}

public class TIJ4Exercise12 {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicityTest at = new AtomicityTest();
		/*
		exec.execute(at);
		while (true) {
			int val = at.getValue();
			if (val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
		*/
		
		// use an object of Future to control the running time of a thread.
		Future<Void> future = (Future<Void>) exec.submit(at);
		try {
			future.get(5, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			System.out.println(e);
		}
		
	}
}
