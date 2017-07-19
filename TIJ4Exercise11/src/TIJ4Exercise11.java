import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Fighter implements Runnable{
	
	private volatile boolean isDead = false;
	private int hpVal;
	private int magicVal;
	
	public Fighter(int hpVal, int magicVal) {
		this.hpVal = hpVal;
		this.magicVal = magicVal;
	}

	@Override
	public void run() {
		while (isDead == false) {
			convertMagicVal();
			if (magicVal % 10 != 0 || hpVal % 10 != 0) {
				System.out.println("The magic of the Fight is " + magicVal + 
						" The hp of the Fighter is " + hpVal + 
						". The fighter " + this + " is dead.");
				isDead = true;
			}
		}
		
	}
	
	public void convertMagicVal() {
		// if (magicVal < 10) return ;
		magicVal --;
		hpVal ++;
		magicVal --;
		hpVal ++;
		magicVal --;
		hpVal ++;
		magicVal --;
		hpVal ++;
		magicVal --;
		hpVal ++;
		magicVal --;
		hpVal ++;
		magicVal --;
		hpVal ++;
		magicVal --;
		hpVal ++;
		magicVal --;
		hpVal ++;
		magicVal --;
		hpVal ++;
	}
}


public class TIJ4Exercise11 {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(20);
		for (int i = 0; i < 20; i ++)
			exec.execute(new Fighter(0, 10 * 200000000));
		exec.shutdown();
	}

}
