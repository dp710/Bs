package com.softeem.util;

import java.sql.Connection;

/**
* @ClassName: ConnectionContext
* @Description:数据库连接上下文
* @author: xxxx
*
*/ 
public class ConnectionContext {

    /**
     * 构造方法私有化，将ConnectionContext设计成单例
     */
    private ConnectionContext(){
        
    }
    //创建ConnectionContext实例对象
    private static ConnectionContext connectionContext = new ConnectionContext();
    
    /**
    * @Method: getInstance
    * @Description:获取ConnectionContext实例对象
    * @Anthor:xxxx
    *
    * @return
    */ 
    public static ConnectionContext getInstance(){
        return connectionContext;
    }
    
    /**
    * @Field: connectionThreadLocal
    *         使用ThreadLocal存储数据库连接对象
    */ 
    private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();
    
    /**
    * @Method: bind
    * @Description:利用ThreadLocal把获取数据库连接对象Connection和当前线程绑定
    * @Anthor:xxxx
    *
    * @param connection
    */ 
    public void bind(Connection connection){
        connectionThreadLocal.set(connection);
    }
    
    /**
    * @Method: getConnection
    * @Description:从当前线程中取出Connection对象
    * @Anthor:xxxx
    *
    * @return
    */ 
    public Connection getConnection(){
        return connectionThreadLocal.get();
    }
    
    /**
    * @Method: remove
    * @Description: 解除当前线程上绑定Connection
    * @Anthor:xxxx
    *
    */ 
    public void remove(){
        connectionThreadLocal.remove();
    }
}