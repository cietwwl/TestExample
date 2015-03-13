package cn.dyz.zookeeper;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.Stat;

/**
 * 基于zookeeper的分布式锁
 * 使用方式
 * TestLock lock = new TestLock("127.0.0.1:4180",5000);
 * lock.lock();
 *  do something...
 *  lock.unlock();
 * @author  daiyongzhi
 * @date 2015年3月12日 下午3:03:43
 * @version V1.0
 */
public class TestLock {

	private String groupNodePath="/locks";
	private String lockNodeName="lock";
	
	private String thisNodeName;
	private String preNodePath;
	
	private CountDownLatch connectLatch = new CountDownLatch(1);
	private CountDownLatch lockLatch;
	private ZooKeeper zk;
	
	public TestLock(String hosts,int timeout){
		this(hosts,timeout,"/locks","lockNode");
	}
	public TestLock(String hosts,int timeout,String groupNodePath){
		this(hosts,timeout,groupNodePath,"lockNode");
	}
	private TestLock(String hosts,int timeout,String groupNodePath,String lockNodeName){
		try {
			this.groupNodePath = groupNodePath;
			this.lockNodeName = lockNodeName;
			zk = new ZooKeeper(hosts,timeout,new TestWatcher());
			connectLatch.await();
			zk.create(groupNodePath, null,Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}catch(KeeperException.NodeExistsException e){//node已经存在了，
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void lock() throws Exception{
		String thisPath = zk.create(groupNodePath+"/"+this.lockNodeName, null, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		List<String> locks = zk.getChildren(groupNodePath, false);
		if(locks.size()==1){//就自己申请锁
			return;
		}else{
			Collections.sort(locks);
			thisNodeName = thisPath.substring((groupNodePath + "/").length());
			int index = locks.indexOf(thisNodeName);
			if(index==0){
				return;
			}else{
				preNodePath = groupNodePath+"/"+locks.get(index-1);
				try {
					zk.getData(preNodePath, true, new Stat());
				} catch (KeeperException.NoNodeException e) {//之前的节点已经没了,不用再阻塞了
					return;
				}
				lockLatch = new CountDownLatch(1);
				lockLatch.await();
			}
		}
	}
	
	public void unlock() throws Exception{
		//zk.delete(groupNodePath+"/"+this.lockNodeName,-1);
		zk.close();
	}
	
	
	private class TestWatcher implements Watcher{

		public void process(WatchedEvent event) {
			if(event.getState()==KeeperState.SyncConnected){
				connectLatch.countDown();
			}
			if(event.getType()==EventType.NodeDeleted && event.getPath().equals(preNodePath)){
				try {
					List<String> locks = zk.getChildren(groupNodePath, false);
					Collections.sort(locks);
					int index = locks.indexOf(thisNodeName);
					if(index==0){
						lockLatch.countDown();
					}else{
						preNodePath = groupNodePath+"/"+locks.get(index-1);
						try {
							zk.getData(preNodePath, true, new Stat());
						} catch (KeeperException.NoNodeException e) {//之前的节点已经没了,不用再阻塞了
							return;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	
}
