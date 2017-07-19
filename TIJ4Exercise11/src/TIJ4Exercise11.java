import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * Exercise 11 - use synchronised keyword to prevent shared resource contention
 * 
 * @author jingjiejiang
 * @history 
 * 1. Jul 20, 2017
 */
class Fighter {
	
	private volatile boolean isDead = false;
	private int hpVal;
	private int magicVal;
	
	public Fighter(int hpVal, int magicVal) {
		this.hpVal = hpVal;
		this.magicVal = magicVal;
	}
	
	public synchronized int convertMagicVal() {
		for (int i = 0; i < 10; i ++) {
			magicVal --;
			hpVal ++;
		}
		
		return hpVal;
	}
	
	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	public int getHpVal() {
		return hpVal;
	}

	public void setHpVal(int hpVal) {
		this.hpVal = hpVal;
	}

	public int getMagicVal() {
		return magicVal;
	}

	public void setMagicVal(int magicVal) {
		this.magicVal = magicVal;
	}
}

class FighterChecker implements Runnable {

	private Fighter fighter;
	
	public FighterChecker(Fighter fighter) {
		this.fighter = fighter;
	}
	
	@Override
	public void run() {
		while (fighter.isDead() == false) {
			// if convertMagicVal does not return value, after the method, use 
			// fighter to get value, it will still get hp vals that % 10 != 0, 
			// as it may get val at any time during another thread visit
			// convertMagicVal()
			int hp = fighter.convertMagicVal();
			if (hp % 10 != 0) {
				System.out.println(" The hp of the Fighter is " + hp + 
						". The fighter " + this + " is dead.");
				fighter.setDead(true);
			}
		}
	}
}

public class TIJ4Exercise11 {

	public static void main(String[] args) {
		Fighter fighter = new Fighter(0, 10 * 200000000);
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i ++)
			exec.execute(new FighterChecker(fighter));
		// exec.shutdown();
	}

}
