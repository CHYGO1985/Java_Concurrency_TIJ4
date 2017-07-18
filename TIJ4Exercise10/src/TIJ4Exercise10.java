import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * Exercise 10 - use thread in a method of a class. TIJ4 Concurrency.
 * 
 * @author jingjiejiang
 * @history Jul 18, 2017
 *
 */
class ThreadMethod {
	private Callable<Integer> call;
	
	public ThreadMethod() {
		
	}
	
	private int fib(int n) {
		if(n < 2) return 1;
		return fib(n-2) + fib(n-1);
	}

	public Future<Integer> runTask(int num) {
		if (call == null) {
			call = new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					return fib(num);
				}
			};
		}
		
		ExecutorService exec = Executors.newSingleThreadExecutor();
		return exec.submit(call);
	}
}

public class TIJ4Exercise10 {
	
	public static void main(String[] args) {
		
		ThreadMethod threadMethod = new ThreadMethod();
		int res = 0;
		try {
			res = (int) threadMethod.runTask(5).get();
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		catch (ExecutionException e) {
			System.out.println(e);
		}
		
		System.out.println("The result is " + res);

	}

}
