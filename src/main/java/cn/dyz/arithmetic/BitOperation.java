package cn.dyz.arithmetic;

import java.util.HashMap;
import java.util.LinkedList;

public class BitOperation {
	
	private static HashMap<Integer, Character> map = new HashMap<Integer, Character>();
	
	static{
		char start='A';
		for(int i=1;i<=26;i++){
			
			map.put(i, new Character((char)(start+i-1)));
		}
	}

	public static void main(String[] args) {
		
		//System.out.println(getString(999999999,26));
		//System.out.println(check1num(5));
		
		//System.out.println(isTheOne(16));
		
		//System.out.println(theChangeTimes(5, 3));
		
		System.out.println(testValue(4, 4));
	}
	
	
	/**
	 * 求一个数的n次方  ,只考虑性能，没有考虑特殊情况
	 * @param base
	 * @param exponent
	 * @return
	 */
	public static int testValue(int base,int exponent){
		if(exponent==0){
			return 1;
		}
		if(exponent==1){
			return base;
		}
		
		int result = testValue(base, exponent>>1);
		result*=result;
		if((exponent & 1) == 1){//如果是奇数
			result*=base;
		}
		
		return result;
	}
	
	
	
	public static int theChangeTimes(int a,int b){
		int tmp = a^b;
		int times=0;
		while(tmp>0){
			times++;
			tmp = (tmp-1)&tmp;
		}
		return times;
	}
	
	/**
	 * 判断一个数是否是二的整数次方
	 * @param a
	 * @return
	 */
	public static boolean isTheOne(int a){
		int tmp = (a-1)&a;
		return  tmp == 0;
	}
	
	/**
	 * 获取一个整数二进制位中1的个数
	 * 思路：一个整数减去1再与自身安位与，就相当于出掉了最右边的1
	 * @param a
	 * @return
	 */
	public static int check1num(int a){
		int sum=0;
		while(a>0){
			sum++;
			a = (a-1) & a;//把a对应得二进制位最右边的1消掉
		}
		return sum;
	}
	
	public static String getString(int num,int system){
		if(num<=0)
			throw new IllegalArgumentException("must biger than zero !");
		
		if(num<=26)
			return map.get(num)+"";
		
		LinkedList<Integer> stack = new LinkedList<Integer>();
		
		while(num>0){
			int tmp = num/system;
			stack.push(num%system);
			num=tmp;
		}
		StringBuilder sb = new StringBuilder();
		int max=stack.size();
		for(int i=0;i<max;i++){
			sb.append(map.get(stack.pop()));
		}
		return sb.toString();
		
	}

}
