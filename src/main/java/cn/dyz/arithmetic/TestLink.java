package cn.dyz.arithmetic;

public class TestLink {

	public static void main(String[] args) {
		LinkNode a1 = new LinkNode(1);
		LinkNode a2 = new LinkNode(2);
		LinkNode a3 = new LinkNode(7);
		LinkNode a4 = new LinkNode(8);
		LinkNode a5 = new LinkNode(9);
		a1.setNext(a2);
		a2.setNext(a3);
		a3.setNext(a4);
		a4.setNext(a5);
		
		LinkNode b1 = new LinkNode(2);
		LinkNode b2 = new LinkNode(3);
		LinkNode b3 = new LinkNode(4);
		LinkNode b4 = new LinkNode(5);
		LinkNode b5 = new LinkNode(7);
		b1.setNext(b2);
		b2.setNext(b3);
		b3.setNext(b4);
		b4.setNext(b5);
		
		//printLink(a1);
		
		LinkNode result = mergeLinkUseWhile(a1,b1);
		
		printLink(result);
	}
	
	
	public static LinkNode mergeLinkUseWhile(LinkNode link1,LinkNode link2){
		LinkNode newLink = null;
		LinkNode curr = null;
		
		while(link1!=null && link2!=null){
			if(link1.getValue()<link2.getValue()){
				if(newLink==null){
					newLink=link1;
					curr=link1;
				}else{
					curr.setNext(link1);
					curr=curr.getNextNode();
				}
				
				link1=link1.getNextNode();
			}else{
				if(newLink==null){
					newLink=link2;
					curr=link2;
				}else{
					curr.setNext(link2);
					curr=curr.getNextNode();
				}
				link2=link2.getNextNode();
			}
			
		}
		if(link1==null)
			curr.setNext(link2);
		if(link2==null)
			curr.setNext(link1);
		return newLink;
	}
	
	/**
	 * 合并链表
	 * @param link1
	 * @param link2
	 * @return
	 */
	public static LinkNode mergeLink(LinkNode link1,LinkNode link2){
		if(link1==null){
			return link2;
		}
		if(link2==null){
			return link1;
		}
		LinkNode newRoot = null;
		if(link1.getValue()<link2.getValue()){
			newRoot=link1;
			newRoot.setNext(mergeLink(link1.getNextNode(), link2));
		}else{
			newRoot=link2;
			newRoot.setNext(mergeLink(link1, link2.getNextNode()));
		}
		return newRoot;
	}

	/**
	 * 翻转链表
	 * @param root
	 */
	public static LinkNode reverseLink(LinkNode root){
		if(root==null)
			throw new IllegalArgumentException("parameter ill");
		
		LinkNode p_tail = root;
		LinkNode p_curr = root;
		LinkNode p_head;
		
		p_curr=p_curr.getNextNode();
		
		 while(p_curr!=null){
			 p_head=p_curr.getNextNode();
			 p_curr.setNext(p_tail);
			 p_tail=p_curr;
			 p_curr=p_head;
			 
		 }
		 root.setNext(null);
		 return p_tail;
	}
	
	/**
	 * 获取倒数第k个节点
	 * @param k
	 */
	public static LinkNode getLastKNode(LinkNode root, int k){
		if(root==null || k<1)
			throw new IllegalArgumentException("parameter ill");
		
		LinkNode p_back=root;
		LinkNode p_fore=root;
		for(int i=0;i<k-1;i++){
			p_fore=p_fore.getNextNode();
			if(p_fore==null)
				return null;
		}
		while(p_fore.getNextNode()!=null){
			p_back=p_back.getNextNode();
			p_fore=p_fore.getNextNode();
		}
		return p_back;
	}
	
	public static void printLink(LinkNode root){
		StringBuilder sb = new StringBuilder();
		while(root!=null){
			sb.append(root.getValue());
			root = root.getNextNode();
		}
		System.out.println(sb.toString());
	}
}
