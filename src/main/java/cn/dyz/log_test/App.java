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
        loger.info( "Hello World!",3 );
    }
}
