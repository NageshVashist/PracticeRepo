package lock;

public class Withdraw implements Runnable {

	private Account account;
	private String name;

	public Withdraw(String name,Account account) {
		this.name=name;
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(name +" executing");
			account.withdrawSum(20);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
