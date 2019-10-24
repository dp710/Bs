package com.softeem.db;

import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
//�����ع�����.��(ʹ�ö�������ģʽ)
public class MyDataSourceFactory {
	
	public static DataSource dataSource ;//����Դֻ��һ��.���Ǵ�����Դ�п��Եõ��ܶ�����Ӷ���(������)
	
	private MyDataSourceFactory(){}//���ù��췽��˽�л�
	
	static{
		try{
		   //�õ�dbcp�����ļ�������
	       InputStream in = MyDataSourceFactory.class.getClassLoader().
	                     					getResourceAsStream("dbcp.properties");
	       Properties prop = new Properties();
	       prop.load(in);//����dbcp�����ļ�
	       BasicDataSourceFactory factory = new BasicDataSourceFactory();
	       dataSource = factory.createDataSource(prop);
	       
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DataSource getDataSource(){//�ṩһ��������̬����
		return dataSource ;
	}
}
