package lock;

public class Atm {
	public static void main(String[] args) {
		Account acc = new Account(100);
		Thread t1 = new Thread(new Deposit("T1",acc));
		Thread t2 = new Thread(new Withdraw("T2",acc));
		Thread t3 = new Thread(new Withdraw("T3",acc));

		t1.start();
		t2.start();
		t3.start();
	}
}
