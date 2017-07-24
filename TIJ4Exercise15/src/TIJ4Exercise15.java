import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * Exercise15 - a class has three methods syn on same / dif objects, demons
 * one of the three methods can be executed or the three methods can be
 * executed at the same time. TIJ4 Concurrency.
 * 
 * 1. class Transform:
 * The method contains three methods: addOne, addThree, addFive that sync on
 * same / dif objects.
 * 
 * 2. TransformTaskOne / ... Three/ ... Five
 * Runnable Classes that only use addOne() / addThree() / addFive() 
 * 
 * @author jingjiejiang
 * @history 
 * 1. Jul 23, 2017
 */

// different method syn on different objects
/*
class Transform {
	
	private Object syncObjOne;
	private Object syncObjThree;
	private Object syncObjFive;
	private int num;
		
	public Transform() {
		syncObjOne = new Object();
		syncObjThree = new Object();
		syncObjFive = new Object();
		num = 0;
	}
	
	public void addOne() {
		synchronized (syncObjOne) {
			while (num < 1000) {
				num ++;
				Thread.yield();
				System.out.println("addOne: " + num);
			}
		}
	}
	
	public void addThree() {
		synchronized (syncObjThree) {
			while (num < 1000) {
				num += 3;
				Thread.yield();
				System.out.println("addThree: " + num);
			}
		}
	}
	
	public void addFive() {
		synchronized (syncObjFive) {
			while (num < 1000) {
				num += 5;
				Thread.yield();
				System.out.println("addFive: " + num);
			}
		}
	}

	public synchronized int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
*/

// multi methods sync on an identical object.
// even use yield(), always execure addOne until 10000, as the lock is hold by addOne
class Transform {
	
	private Object syncObj;
	private int num;
		
	public Transform() {
		syncObj = new Object();
		num = 0;
	}
	
	public void addOne() {
		synchronized (syncObj) {
			while (num < 1000) {
				num ++;
				Thread.yield();
				System.out.println("addOne: " + num);
			}
		}
	}
	
	public void addThree() {
		synchronized (syncObj) {
			while (num < 1000) {
				num += 3;
				Thread.yield();
				System.out.println("addThree: " + num);
			}
		}
	}
	
	public void addFive() {
		synchronized (syncObj) {
			
			while (num < 1000) {
				num += 5;
				Thread.yield();
				System.out.println("addFive " + num);
			}
		}
	}

	public synchronized int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}

class TransformTaskOne implements Runnable {

	private Transform trans;
	private int id;
	
	public TransformTaskOne(Transform trans, int id) {
		this.trans = trans;
		this.id = id;
	}
	
	@Override
	public void run() {
		while (trans.getNum() < 729) {
			System.out.println(this.toString() + " id: " + id);
			trans.addOne();
		}
	}
}

class TransformTaskThree implements Runnable {

	private Transform trans;
	private int id;
	
	public TransformTaskThree(Transform trans, int id) {
		this.trans = trans;
		this.id = id;
	}
	
	@Override
	public void run() {
		while (trans.getNum() < 729) {
			System.out.println(this.toString() + " id: " + id);
			trans.addThree();
		}
	}
}

class TransformTaskFive implements Runnable {

	private Transform trans;
	private int id;
	
	public TransformTaskFive(Transform trans, int id) {
		this.trans = trans;
		this.id = id;
	}
	
	@Override
	public void run() {
		while (trans.getNum() < 729) {
			System.out.println(this.toString() + " id: " + id);
			trans.addFive();
		}
	}
}

public class TIJ4Exercise15 {

	public static void main(String[] args) {
		Transform trans = new Transform();
		ExecutorService exec = Executors.newFixedThreadPool(3);
		exec.execute(new TransformTaskOne(trans, 1));
		exec.execute(new TransformTaskThree(trans, 3));
		exec.execute(new TransformTaskFive(trans, 5));
		exec.shutdown();
	}
}
