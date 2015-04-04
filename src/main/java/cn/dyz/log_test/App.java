package cn.dyz.log_test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger loger = LoggerFactory.getLogger(App.class);
    public static void main( String[] args )
    {
    	int i = 1;
        while(true){
        	
        	try {
				Thread.sleep(1000);
				loger.info("hello-"+i);
				i++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	
        }
    }
}
