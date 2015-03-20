package cn.dyz.arithmetic;

public class TestSearch {

	public static void main(String[] args) {
		int[] data={1,0,1,1,1,1};
		
		System.out.println(findMin(data));
		
	}
	
	public static int findMin(int[] data){
		int frontIndex=0;
		int tailIndex=data.length-1;
		
		int middleIndex = (frontIndex+tailIndex)/2;
		
		while(frontIndex+1 < tailIndex){
			if(data[middleIndex]>= data[frontIndex]){
				frontIndex=middleIndex;
			}else{
				tailIndex=middleIndex;
			}
			middleIndex=(frontIndex+tailIndex)/2;
		}
		
		return data[tailIndex];
	}

}
