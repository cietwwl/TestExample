package cn.dyz.arithmetic;

public class TestGigData {

	public static void main(String[] args) {
		print1ToMax(5);
	}
	
	public static void print1ToMax(int digit){
		byte[] data = new byte[digit];
		for(int i=0;i<digit;i++){
			data[i]=0;
		}
		while(increatOne(data)){
			printBigData(data);
		}
	}
	
	private static boolean increatOne(byte[] data){
		byte nTokenOver = 0;
		for(int i=data.length-1;i>=0;i--){
			byte num = (byte) (data[i]+nTokenOver);
			if(i==data.length-1)
				num++;
			if(num>=10){//需要进位
				
				if(i==0)
					return false;
				
				nTokenOver=1;
				num=(byte) (num-10);
				data[i]=num;
			}else{
				data[i]=num;
				nTokenOver=0;
				break;
			}
		}
		return true;
	}
	
	private static void printBigData(byte[] data){
		boolean isNotZero = false;
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<data.length;i++){
			if(data[i]!=0){
				isNotZero=true;
			}
			if(isNotZero){
				sb.append(data[i]);
			}
		}
		System.out.println(sb.toString());
	}
	
}
