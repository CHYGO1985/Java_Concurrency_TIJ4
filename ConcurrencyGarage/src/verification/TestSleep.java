package verification;

/**
 * 
 * @author jingjiejiang
 * @history Feb 15, 2020 
 */
public class TestSleep {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start");
		new Thread(() -> runMethod()).start();
		System.out.println("Main Thread finished!");
	}
	
	private static void runMethod() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Thread finished!");
    }

}
