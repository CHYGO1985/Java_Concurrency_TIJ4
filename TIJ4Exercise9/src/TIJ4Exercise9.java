import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class SimplePriorities implements Runnable {
	private int countDown = 5;
	private volatile double d; // No optimization
	//private int priority;

	public SimplePriorities(int priority) {
		//this.priority = priority;
	}

	public String toString() {
		return Thread.currentThread() + ": " + countDown;
	}

	public void run() {
		//Thread.currentThread().setPriority(priority);
		while (true) {
			// An expensive, interruptable operation:
			for (int i = 1; i < 100000; i++) {
				d += (Math.PI + Math.E) / (double) i;
				if (i % 1000 == 0)
					Thread.yield();
			}
			System.out.println(this);
			if (--countDown == 0)
				return;
		}
	}
}

class PriorityThreadFac implements ThreadFactory {
	
	private int priority;
	
	public PriorityThreadFac(int priority) {
		this.priority = priority;
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setPriority(priority);
		return thread;
	}
	
}

public class TIJ4Exercise9 {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new PriorityThreadFac(Thread.MIN_PRIORITY));
	    for(int i = 0; i < 5; i++)
	      exec.execute(
	        new SimplePriorities(Thread.MIN_PRIORITY));
	    exec.execute(
	        new SimplePriorities(Thread.MAX_PRIORITY));
	    exec.shutdown();
	}
}
