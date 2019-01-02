package cache;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyCache<K, V> {

	private int size;
	private KeyPair[] list;
	private Lock rLock;
	private Lock wLock;

	public MyCache(int size) {
		this.size = size;
		list = new KeyPair[size];
		rLock = new ReentrantReadWriteLock().readLock();
		wLock = new ReentrantReadWriteLock().writeLock();
	}

	private int hash(K key) {
		int h;
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h << 16);
	}

	private KeyPair getinternal(K key) {
		int hash = list.length - 1 & hash(key);
		if (hash <= list.length) {
			KeyPair node = list[hash];
			rLock.lock();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (node != null) {
				while (node.node != null) {
					if (node.node.key.equals(key)) {
						node = node.node;
						return node;
					}

					node = node.node;
				}
				if (node.node == null) {
					return node;
				}
			}
			rLock.unlock();
		}
		return null;
	}

	public V get(Object key) {

		KeyPair kp = getinternal((K) key);

		return kp != null ? (V) kp.val : null;
	}

	public V put(K k, V v) {
		int hash = list.length - 1 & hash(k);
		KeyPair keyPair = getinternal(k);

		if (keyPair == null) {
			list[hash] = new KeyPair<K, V>(k, v);
		} else {
			wLock.lock();

			System.out.println("Writing--- " + k);

			if (keyPair.key.equals(k)) {

				V tempV = (V) keyPair.val;
				keyPair.val = v;
				return tempV;
			} else {
				keyPair.node = new KeyPair<K, V>(k, v);
			}
			wLock.unlock();
		}
		return null;
	}

	class KeyPair<K, V> {
		private K key;
		private V val;
		private KeyPair<K, V> node;

		public KeyPair(K key, V val) {
			super();
			this.key = key;
			this.val = val;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getVal() {
			return val;
		}

		public void setVal(V val) {
			this.val = val;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result + ((val == null) ? 0 : val.hashCode());
			return result;
		}

		public KeyPair<K, V> getNode() {
			return node;
		}

		public void setNode(KeyPair<K, V> node) {
			this.node = node;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			KeyPair other = (KeyPair) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			if (val == null) {
				if (other.val != null)
					return false;
			} else if (!val.equals(other.val))
				return false;
			return true;
		}

		private MyCache getOuterType() {
			return MyCache.this;
		}

	}
}
