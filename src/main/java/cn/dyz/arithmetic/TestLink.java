package cn.dyz.arithmetic;

public class TestLink {

	public static void main(String[] args) {
		LinkNode a1 = new LinkNode(1);
		LinkNode a2 = new LinkNode(2);
		LinkNode a3 = new LinkNode(3);
		LinkNode a4 = new LinkNode(4);
		LinkNode a5 = new LinkNode(5);
		a1.setNext(a2);
		a2.setNext(a3);
		a3.setNext(a4);
		a4.setNext(a5);
		
		printLink(a1);
		
		LinkNode result = reverseLink(a1);
		
		printLink(result);
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
