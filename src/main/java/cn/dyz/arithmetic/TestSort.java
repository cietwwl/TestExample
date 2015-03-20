package cn.dyz.arithmetic;

/**
 * 排序算法实现
 *
 * @author daiyongzhi
 * @date 2015年3月17日 上午10:47:15
 * @version V1.0
 */
public class TestSort {

	public static void main(String[] args) {
		int[] data = { 23, 43, 54, 2, 3, 45, 43, 21, 45, 22 };

		quickSort(data, 0, 9);

		print(data);

	}

	private static void print(int[] data) {
		for(int a:data){
			System.out.println(a);
		}
	}

	/**
	 * 冒泡排序
	 * 
	 * @param data
	 */
	public static void maopao(int[] data) {
		for (int j = 0; j < data.length - 1; j++) {
			for (int i = 0; i < data.length - 1 - j; i++) {
				if (data[i] > data[i + 1]) {
					int tmp = data[i];
					data[i] = data[i + 1];
					data[i + 1] = tmp;
				}
			}
		}

	}

	public static void quickSort(int[] data, int start, int end) {
	
		if(start<end){
			int middle=getMiddle(data, start, end);
			quickSort(data, start, middle-1);
			quickSort(data, middle+1, end);
		}
		

	}
	
	public static int getMiddle(int[] data, int start, int end){
		int referVal = data[start];
		
		while(start<end){
			while(end>start && data[end]>=referVal)
				end--;
			data[start]=data[end];
			while(start<end && data[start]<=referVal)
				start++;
			data[end]=data[start];
		}
		data[start]=referVal;
		
		return start;
	}

	
}
