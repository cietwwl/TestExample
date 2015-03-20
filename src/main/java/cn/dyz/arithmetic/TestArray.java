package cn.dyz.arithmetic;

/**
 
 * @author  daiyongzhi
 * @date 2015年3月16日 上午9:47:09
 * @version V1.0
 */

public class TestArray {

	public static void main(String[] args) {
		/*int[][] array = {{2,4,6,6,9,12},
						 {3,6,7,8,10,13},
						 {6,8,9,11,14,14}
		                };
		System.out.println(findNum(array, 6));*/
		
		int[] a = {1,3,5,7,0,0,0,0,0};
		int[] b = {3,6,8,9};
		mergeSortedArray(a, b);
		for(int tmp:a){
			System.out.println(tmp);
		}
	}
	/**
	 * 合并两个有序的数组，合并完以后也是有序的。a数组有足够的空间存放b
	 * @param a
	 * @param b
	 */
	static void mergeSortedArray(int[] a,int[] b){
		int index = 0;
		int a_point=0;
		while(a[index]!=0){
			index++;
		}
		a_point=index-1;
		int dest_point=index+b.length-1;
		int b_point=b.length-1;
		
		while(a_point!=dest_point){
			if(a[a_point]<=b[b_point]){
				a[dest_point]=b[b_point];
				b_point--;
			}else{
				a[dest_point]=a[a_point];
				a_point--;
			}
			dest_point--;
		}
		
	}
	/**
	 * * 一个二维数组，每行都是从左到右递增，从上到下递增。
	 * 从中查找给定的数字是否存在
	 * @param array
	 * @param target
	 * @return
	 */
	static boolean findNum(int[][] array,int target){
		boolean isFound = false;
		if(array==null || array.length<=0 || array[0].length<=0){
			return isFound;
		}
		int row=0;
		int column=array[0].length-1;
		while(row<array.length && column>=0){
			if(array[row][column]>target){
				column--;
			}else if((array[row][column]<target)){
				row++;
			}else{
				isFound =true;
				break;
			}
		}
		
		return isFound;
	}
	

	
}

