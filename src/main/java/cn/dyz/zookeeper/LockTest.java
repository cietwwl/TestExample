package cn.dyz.zookeeper;

import java.util.ArrayList;
import java.util.Iterator;

public class LockTest {

	public static void main(String[] args) {
		
		final ArrayList<String>  strs = new ArrayList<String>();
		
		new Thread(new Runnable() {
			
			public void run() {
				int i =0;
				while(true){
					TestLock lock = new TestLock("115.28.50.155:4180", 5000);
					try {
						lock.lock();
						
						strs.add("o-"+i);
						System.out.println("add o-"+i);
						i++;
						
						lock.unlock();
						Thread.sleep(100);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		/*try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}*/
		new Thread(new Runnable() {
			
			public void run() {
				while(true){
					TestLock lock = new TestLock("115.28.50.155:4180", 5000);
					try {
						lock.lock();
						Iterator<String> iter = strs.iterator();
						while(iter.hasNext()){
							String str = iter.next();
							iter.remove();
							System.out.println("remove "+ str);
						}
						lock.unlock();
						Thread.sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
