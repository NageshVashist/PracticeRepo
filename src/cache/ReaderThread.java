package cache;

public class ReaderThread implements Runnable {

	private MyCache<String, String> cache;
	private String name;

	public ReaderThread(MyCache<String, String> cache, String name) {
		super();
		this.cache = cache;
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("Reading--" + name + "--  " + cache.get(name + "_key" + i));
		}

	}

}
