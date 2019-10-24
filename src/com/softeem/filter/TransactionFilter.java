package com.softeem.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.softeem.db.MyDataSourceFactory;
import com.softeem.util.ConnectionContext;

/**
* @ClassName: TransactionFilter
* @Description:ThreadLocal + Filter ͳһ�������ݿ�����
* @author: xxxx
*/ 
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        Connection connection = null;
        try {
            //1����ȡ���ݿ����Ӷ���Connection
            connection = MyDataSourceFactory.getDataSource().getConnection();
            //2����������
            connection.setAutoCommit(false);
            //3������ThreadLocal�ѻ�ȡ���ݿ����Ӷ���Connection�͵�ǰ�̰߳�
            ConnectionContext.getInstance().bind(connection);
            //4��������ת����Ŀ��Servlet
            chain.doFilter(request, response);
            //5���ύ����
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //6���ع�����
            try {
            	System.out.println("����ع�");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            //req.setAttribute("errMsg", e.getMessage());
            //req.getRequestDispatcher("/error.jsp").forward(req, res);
            //�����쳣֮����ת������ҳ��
            res.sendRedirect(req.getContextPath()+"/error.jsp");
        }finally{
            //7�������
            ConnectionContext.getInstance().remove();
            //8���ر����ݿ�����
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}