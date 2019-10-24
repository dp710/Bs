package com.softeem.db;

import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
//连续池工厂类.酷(使用饿汉单例模式)
public class MyDataSourceFactory {
	
	public static DataSource dataSource ;//数据源只能一个.但是从数据源中可以得到很多的连接对象(连续池)
	
	private MyDataSourceFactory(){}//设置构造方法私有化
	
	static{
		try{
		   //得到dbcp配置文件输入流
	       InputStream in = MyDataSourceFactory.class.getClassLoader().
	                     					getResourceAsStream("dbcp.properties");
	       Properties prop = new Properties();
	       prop.load(in);//加载dbcp配置文件
	       BasicDataSourceFactory factory = new BasicDataSourceFactory();
	       dataSource = factory.createDataSource(prop);
	       
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DataSource getDataSource(){//提供一个公共静态方法
		return dataSource ;
	}
}
