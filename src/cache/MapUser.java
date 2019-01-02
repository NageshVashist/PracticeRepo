package cache;

public class MapUser {
	public static void main(String[] args) {
		MyCache<String, String> cache = new MyCache<>(16);
		Thread th1 = new Thread(new WriterThread(cache, "Writer 1"));
		Thread th2 = new Thread(new ReaderThread(cache, "Writer 1"));
		Thread th3 = new Thread(new WriterThread(cache, "Writer 2"));
		Thread th4 = new Thread(new ReaderThread(cache, "Writer 2"));

		th1.start();
		try {
			th1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		th2.start();
		th3.start();
		th4.start();

	}
}
