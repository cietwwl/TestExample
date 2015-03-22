package cn.dyz.arithmetic;

import java.util.LinkedList;

/**
 * 可以获取栈中最小值得栈
 * @author dyz
 *
 */
public class StackWithMin {

	private Integer min;
	
	private LinkedList<Integer> masterStack = new LinkedList<Integer>();
	private LinkedList<Integer> slaveStack = new LinkedList<Integer>();
	
	public void push(Integer obj){
		if(min==null){
			min=obj;
			slaveStack.push(obj);
		}else{
			if(obj<min){
				slaveStack.push(obj);
				min=obj;
			}else{
				slaveStack.push(min);
			}
		}
		masterStack.push(obj);
		
	}
	
	public Integer pop(){
		slaveStack.pop();
		min=slaveStack.peek();
		return masterStack.pop();
	}
	
	public Integer min(){
		return min;
	}
	
}
