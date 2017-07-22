import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Transform {
	
	private Object syncObj;
	private int num;
		
	public Transform() {
		syncObj = new Object();
		num = 0;
	}
	
	public void addOne() {
		synchronized (syncObj) {
			num ++;
			Thread.yield();
		}
	}
	
	public void addThree() {
		synchronized (syncObj) {
			for (int i = 0; i < 3; i++) num ++;
			Thread.yield();
		}
	}
	
	public void addFive() {
		synchronized (syncObj) {
			for (int i = 0; i < 5; i++) num ++;
			Thread.yield();
		}
	}

	public synchronized int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}

class TransformTask implements Runnable {

	private Transform trans;
	
	public TransformTask(Transform trans) {
		this.trans = trans;
	}
	
	@Override
	public void run() {
		while (trans.getNum() <= 729) {
			System.out.println(trans.getNum());
			trans.addOne();
			System.out.println(trans.getNum());
			trans.addThree();
			System.out.println(trans.getNum());
			trans.addFive();
		}
		System.out.println(trans.getNum());
	}
	
}

public class TIJ4Exercise15 {

	public static void main(String[] args) {
		Transform trans = new Transform();
		ExecutorService exec = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 3; i ++) {
			exec.execute(new TransformTask(trans));
		}
		exec.shutdown();
	}

}
