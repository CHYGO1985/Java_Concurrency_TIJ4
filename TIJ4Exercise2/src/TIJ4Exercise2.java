import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * Exercise 2 & Exercise 4. TIJ4 Concurrency.
 * 
 * @author jingjiejiang
 * @history
 * 1. Jul 14, 2017
 */
public class TIJ4Exercise2 {
	
	public static class FibonacciTask implements Runnable {

		private int count;
		
		public FibonacciTask() {
			count = 0;
		}
		
		public FibonacciTask(int count) {
			this.count = count;
		}
		
		@Override
		public void run() {
			Fibonacci num = new Fibonacci();
			for (int i = 0; i < count; i ++) {
				System.out.print(num.fib(i) + "\t");
				if (i > 0  && i % 4 == 0) System.out.println();
			}
			// System.out.println();
		}
		
		public class Fibonacci {
			private int fib(int n) {
				if(n < 2) return 1;
				return fib(n-2) + fib(n-1);
			}
		}
	}

	public static void main(String[] args) {
		/*
		for (int i = 0; i < 8; i ++) {
			Thread t = new Thread(new FibonacciTask(i));
			t.start();
			//System.out.println(t + "finished. **********");
		}
		*/
		
		// Exercise 4
		ExecutorService exec = Executors.newFixedThreadPool(8);
		for (int i = 0; i < 8; i ++) {
			exec.execute(new FibonacciTask(i));
		}
		exec.shutdown();
	}
}
