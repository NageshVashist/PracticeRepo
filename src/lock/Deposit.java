package lock;

public class Deposit implements Runnable {

	private Account account;
	private String name;

	public Deposit(String name, Account account) {
		this.name = name;
		this.account = account;

	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(name +" executing"); 
			account.depositeSum(10);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
