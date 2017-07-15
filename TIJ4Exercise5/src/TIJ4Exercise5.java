import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * Exercise 5 -- Use thread to return values. TIJ4 Concurrency.
 * 
 * @author jingjiejiang
 * @history
 * 1. Jul 15, 2017
 */
public class TIJ4Exercise5 {

	public static class FibonacciTask implements Callable<Integer> {

		private int count;
		
		public FibonacciTask() {
			count = 0;
		}
		
		public FibonacciTask(int count) {
			this.count = count;
		}
		
		@Override
		public Integer call() throws Exception {
			
			Fibonacci fibNum = new Fibonacci();
			return fibNum.fib(count);
		}
		
		public class Fibonacci {
			private int fib(int n) {
				if(n < 2) return 1;
				return fib(n-2) + fib(n-1);
			}
		}
	}
	
	public static void main(String[] args) {

		ExecutorService exec = Executors.newFixedThreadPool(5);
		List<Future<Integer>> list = new ArrayList<>();
		for (int i = 0; i < 5; i ++)
			list.add(exec.submit(new FibonacciTask(i)));
		for (Future<Integer> fi: list) {
			try {
				System.out.print(fi.get() + " ");
			}
			catch (InterruptedException e) {
				System.out.println(e);
				// *** interrupted means the exec is no more exist, so just 
				// return, do not execute the rest of the for loop
				return ;
			}
			catch (ExecutionException e) {
				System.out.println(e);
			}
			finally {
				exec.shutdown();
			}
		}
	}

}
