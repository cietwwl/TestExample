package cn.dyz.arithmetic;
 
public class QSort 
{
 
    /**
     * @param args
     */
    public static void main(String[] args) 
    {
        // TODO 自动生成方法存根
        quicksort qs = new quicksort();
        int data[] = { 5, 3, 6, 2, 7, 1, 9, 4, 8}; 
        qs.data = data;
        qs.sort(0, qs.data.length-1);
        qs.display();
    }
 
}
 
 
class quicksort
{
    public int data[];
     
    private int partition(int sortArray[],int low,int hight)
    {
        int key = sortArray[low];
         
        while(low<hight)
        {
            while(low<hight && sortArray[hight]>=key)
                hight--;
            sortArray[low] = sortArray[hight];
             
            while(low<hight && sortArray[low]<=key)
                low++;
            sortArray[hight] = sortArray[low];
        }
        sortArray[low] = key;
        return low;
    }
     
    public void sort(int low,int hight)
    {
        if(low<hight)
        {
            int result = partition(data,low,hight);
            sort(low,result-1);
            sort(result+1,hight);
        }
         
    }
     
    public void display()
    {
        for(int i=0;i<data.length;i++)
        {
            System.out.print(data[i]);
            System.out.print(" ");
        }
    }
}