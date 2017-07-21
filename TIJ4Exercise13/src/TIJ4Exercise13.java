import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * Exercise13 - Fixed the bug of SerialNumberGenerator. TIJ4 Concurrency.
 * 1) regarding to prove the programme is correct: as it is not possible to
 * prove a software is bug free, so either to produce data for limited amount
 * or let the programme run for a limited amount of time.
 * 
 * @author jingjiejiang
 * @history
 * 1. Jul 21, 2017
 */
class CircularSet {
	private int[] array;
	private int len;
	private int index = 0;

	public CircularSet(int size) {
		array = new int[size];
		len = size;
		// Initialize to a value not produced
		// by the SerialNumberGenerator:
		for (int i = 0; i < size; i++)
			array[i] = -1;
	}

	public synchronized void add(int i) {
		array[index] = i;
		// Wrap index and write over old elements:
		index = ++index % len;
	}

	public synchronized boolean contains(int val) {
		for (int i = 0; i < len; i++)
			if (array[i] == val)
				return true;
		return false;
	}
}

class SerialNumberGenerator {
	private static volatile int serialNumber = 0;

	// if the method is not synchronised, it will produce duplicated nums.
	public static synchronized int nextSerialNumber() {
		return serialNumber++; // Not thread-safe
	}
}

public class TIJ4Exercise13 {

	private static final int SIZE = 10;
	private static CircularSet serials = new CircularSet(1000);
	private static ExecutorService exec = Executors.newCachedThreadPool();

	static class SerialChecker implements Runnable {
		public void run() {
			while (true) {
				int serial = SerialNumberGenerator.nextSerialNumber();
				if (serials.contains(serial)) {
					System.out.println("Duplicate: " + serial);
					System.exit(0);
				}
				serials.add(serial);
			}
		}
	}
	  
	public static void main(String[] args) {
		for (int i = 0; i < SIZE; i++)
			exec.execute(new SerialChecker());
		// Stop after n seconds if there's an argument:
		if (args.length > 0) {
			try {
				TimeUnit.SECONDS.sleep(new Integer(args[0]));
			} catch (NumberFormatException | InterruptedException e) {
				System.out.println(e);
			}
			System.out.println("No duplicates detected");
			System.exit(0);
		}
	}
}
