package cache;

public class WriterThread implements Runnable {

	private MyCache<String, String> cache;
	private String name;

	public WriterThread(MyCache<String, String> cache, String name) {
		super();
		this.cache = cache;
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			cache.put(name + "_key" + i, "v" + i);
		}

	}

}
