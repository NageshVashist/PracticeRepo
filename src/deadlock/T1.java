package deadlock;

public class T1 implements Runnable {
	private R1 r1;
	private R2 r2;

	public T1(R1 r1, R2 r2) {

		this.r1 = r1;
		this.r2 = r2;
	}

	@Override
	public void run() {
		synchronized (r1) {
			
			
		}

	}
}
