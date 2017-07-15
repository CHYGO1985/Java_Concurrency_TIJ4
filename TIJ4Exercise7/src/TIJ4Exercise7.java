import java.util.concurrent.TimeUnit;

/**
 * 
 * Exercise 7 - deamon thread. TIJ4 Concurency.
 * 
 * @author jingjiejiang
 * @history 
 * 1. Jul 15, 2017
 *
 */
class Daemon implements Runnable {
	private Thread[] t = new Thread[10];

	public void run() {
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread(new DaemonSpawn());
			t[i].start();
			System.out.println("DaemonSpawn " + i + " started, ");
		}
		for (int i = 0; i < t.length; i++)
			System.out.println("t[" + i + "].isDaemon() = " + t[i].isDaemon() + ", ");
		while (true)
			Thread.yield();
	}
}

class DaemonSpawn implements Runnable {
	public void run() {
		while (true)
			Thread.yield();
	}
}

public class TIJ4Exercise7 {

	public static void main(String[] args) throws InterruptedException {
		Thread d = new Thread(new Daemon());
		// set d as a deamon thread
		d.setDaemon(true);
		d.start();
		System.out.println("d.isDaemon() = " + d.isDaemon() + ", ");
		// Allow the daemon threads to
		// finish their startup processes:
		TimeUnit.MILLISECONDS.sleep(1);
	}
}
