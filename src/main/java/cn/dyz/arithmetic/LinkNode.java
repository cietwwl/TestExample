package cn.dyz.arithmetic;

public class LinkNode {

	private int value;
	private LinkNode nextNode;
	
	public LinkNode(int value){
		this.value=value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public void setNext(LinkNode node){
		this.nextNode = node;
	}
	
	public LinkNode getNextNode(){
		return this.nextNode;
	}
	
}
