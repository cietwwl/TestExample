package cn.dyz.zookeeper;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.Stat;

public class TestLock {

	private String groupNode = "/locks";
	private String lockNode = "lock";
	private String preNode;
	
	private CountDownLatch connectLatch = new CountDownLatch(1);
	private CountDownLatch lockLatch;
	private ZooKeeper zk;
	
	public TestLock(String hosts,int timeout){
		try {
			zk = new ZooKeeper(hosts,timeout,new TestWatcher());
			connectLatch.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void lock() throws Exception{
		String thisPath = zk.create(groupNode+"/"+lockNode, null, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		List<String> locks = zk.getChildren(groupNode, false);
		if(locks.size()==1){
			return;
		}else{
			Collections.sort(locks);
			int index = locks.indexOf(thisPath);
			if(index==0){
				return;
			}else{
				 preNode = groupNode+"/"+locks.get(index-1);
				zk.getData(preNode, true, new Stat());
				lockLatch = new CountDownLatch(1);
				lockLatch.await();
			}
		}
	}
	
	public void unlock() throws Exception{
		zk.close();
	}
	
	
	private class TestWatcher implements Watcher{

		public void process(WatchedEvent event) {
			if(event.getState()==KeeperState.SyncConnected){
				connectLatch.countDown();
			}
			if(event.getType()==EventType.NodeDeleted && event.getPath().equals(preNode)){
				lockLatch.countDown();
			}
		}
		
		
		
	}
	
}
