package com.softeem.util;

import java.sql.Connection;

/**
* @ClassName: ConnectionContext
* @Description:���ݿ�����������
* @author: xxxx
*
*/ 
public class ConnectionContext {

    /**
     * ���췽��˽�л�����ConnectionContext��Ƴɵ���
     */
    private ConnectionContext(){
        
    }
    //����ConnectionContextʵ������
    private static ConnectionContext connectionContext = new ConnectionContext();
    
    /**
    * @Method: getInstance
    * @Description:��ȡConnectionContextʵ������
    * @Anthor:xxxx
    *
    * @return
    */ 
    public static ConnectionContext getInstance(){
        return connectionContext;
    }
    
    /**
    * @Field: connectionThreadLocal
    *         ʹ��ThreadLocal�洢���ݿ����Ӷ���
    */ 
    private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();
    
    /**
    * @Method: bind
    * @Description:����ThreadLocal�ѻ�ȡ���ݿ����Ӷ���Connection�͵�ǰ�̰߳�
    * @Anthor:xxxx
    *
    * @param connection
    */ 
    public void bind(Connection connection){
        connectionThreadLocal.set(connection);
    }
    
    /**
    * @Method: getConnection
    * @Description:�ӵ�ǰ�߳���ȡ��Connection����
    * @Anthor:xxxx
    *
    * @return
    */ 
    public Connection getConnection(){
        return connectionThreadLocal.get();
    }
    
    /**
    * @Method: remove
    * @Description: �����ǰ�߳��ϰ�Connection
    * @Anthor:xxxx
    *
    */ 
    public void remove(){
        connectionThreadLocal.remove();
    }
}