package cn.dyz.arithmetic;

/**
 * 快速排序算法
 *
 * @author  daiyongzhi
 * @date 2015年3月20日 上午10:17:10
 * @version V1.0
 */
public class MyQuickSort {

	public static void main(String[] args) {
		int[] data = {2,234,4,2,3,45,56,43,2,3,45,56,7,45,4,78,7,6};
		quickSort(data, 0, data.length-1);
		for(int d:data){
			System.out.print(d+" ");
		}
	}
	
	public static void quickSort(int[] data,int startIndex,int endIndex){
		if(startIndex<endIndex){
			int middle = getMiddleIndex(data, startIndex, endIndex);
			quickSort(data, startIndex, middle-1);
			quickSort(data, middle+1, endIndex);
		}
	}
	
	private static int getMiddleIndex(int[] data,int startIndex,int endIndex){
		
		int referValue = data[startIndex];
		
		while(startIndex < endIndex){
			while(endIndex>startIndex && data[endIndex]>=referValue)
				endIndex--;
			data[startIndex] = data[endIndex];
			while(startIndex<endIndex && data[startIndex]<=referValue)
				startIndex++;
			data[endIndex] = data[startIndex];
		}
		data[startIndex] =referValue;
		return startIndex;
	}

}
