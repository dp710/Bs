package com.softeem.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import com.softeem.db.MyDataSourceFactory;

//�ص�����,dbutil(����),
//���ӳ�:https://www.cnblogs.com/zhchoutai/p/7080991.html
//http://commons.apache.org/proper/commons-dbutils/examples.htmlʲô�������Ҫ�ֶ��ر�
//�����ุ��
public class BaseDao<E> {

	public Integer pageCount = 5;// ÿҳ��ʾ5ҳ
	public List<E> list; // ���ڱ���ʵ�����ļ���
	public E entity; // ʵ��
	public QueryRunner query;
	public DataSource dataSource;// ����Դ����
	public Class claxx; // ʵ�����������
	public Connection conn ;

	// �޲ι�����
	public BaseDao(Class claxx) {
		dataSource = MyDataSourceFactory.getDataSource();// ΪdataSource��ʼ��
		query = new QueryRunner();
		this.claxx = claxx;
	}

	/**
	 * ��ѯ��������
	 * 
	 * @param sql
	 *            ��ѯ���
	 * @param claxx
	 *            Ҫ��ѯ�ı��Ӧ��ʵ������
	 * @return ���ز�ѯ������list����
	 * @author xxx
	 * @throws SQLException 
	 */
	public List<E> findAll(String sql) throws SQLException {
		conn = ConnectionContext.getInstance().getConnection();
		
			list = query.query(conn , sql, new BeanListHandler<E>(claxx));
		
		return list;
	}

	/**
	 * ��������id���в�ѯ
	 * 
	 * @param sql
	 *            Ҫ��ѯ��sql���
	 * @param id
	 *            ��ѯ����(����id)
	 * @return ����һ����¼(һ��ʵ�����)
	 * @author xxx
	 * @throws SQLException 
	 */
	public E findById(String sql, Integer id) throws SQLException {
		conn = ConnectionContext.getInstance().getConnection();
		
			entity = query.query(conn, sql, new BeanHandler<E>(claxx), id);
		
		return entity;
	}

	/**
	 * ��ҳ��ѯ����
	 * 
	 * @param sql
	 *            ��ҳ��ѯ���
	 * @param pageNumber
	 *            Ҫ��ѯ��ҳ��
	 * @return ��ҳ��ѯ�Ľ����
	 * @author xxx
	 * @throws SQLException 
	 */
	public List<E> paging(String sql, Integer pageNumber) throws SQLException {
		conn = ConnectionContext.getInstance().getConnection();
		Object[] params = { (pageNumber - 1) * pageCount, pageCount };// ��������sql�������Ҫ�Ĳ���
		
			list = query.query(conn , sql, params, new BeanListHandler<E>(claxx));
		
		return list;
	}

	/**
	 * �������
	 * 
	 * @param sql
	 *            �������(insert)
	 * @param params
	 *            ��ѯ��ӵ����ݿ���е�ֵ
	 * @return ���ز������ݺ������ɵ�����id
	 * @author xxx
	 * @throws SQLException 
	 */
	public int save( String sql, Object[] params) throws SQLException {
		conn = ConnectionContext.getInstance().getConnection();
		Long id = 0L;
		
			id = query.insert(conn , sql, new ScalarHandler<Long>(), params);// ��ӷ���
		
		return id.intValue();
	}

	/**
	 * ��������id�����޸�
	 * 
	 * @param sql
	 *            �޸����(update)
	 * @param params
	 *            Ҫ�޸ĵ��ֶ�ֵ.����Ҫ������id��Ҫ����
	 * @return ����Ӱ������
	 * @author xxx
	 * @throws SQLException 
	 */
	public int updateById(String sql, Object[] params) throws SQLException {
		conn = ConnectionContext.getInstance().getConnection();
		int count = 0;
		
			count = query.update(conn, sql, params);// ����,���,ɾ��
		
		return count;
	}

	/**
	 * ��������id����ɾ��
	 * 
	 * @param sql
	 *            ɾ����� (delete)
	 * @param id
	 *            ����id
	 * @return ����ɾ����Ӱ������
	 * @author xxx
	 * @throws SQLException 
	 */
	public int deleteById( String sql, Integer id) throws SQLException {
		conn = ConnectionContext.getInstance().getConnection();
		int count = 0;
		
			count = query.update(conn , sql, id);
		
		return count;
	}

	/**
	 * ��ѯ���ҳ
	 * 
	 * @param sql
	 *            ��ѯ���м�¼����sql���
	 * @return ������ҳ��
	 * @author xxx
	 * @throws SQLException 
	 */
	public int maxPage( String sql ) throws SQLException {
		conn = ConnectionContext.getInstance().getConnection();
		long count = 0;
		
			count = query.query(conn , sql, new ScalarHandler<Long>());// ��������ֻ��һ��һ�е�����
		
		return (int) Math.ceil(((double) count) / pageCount);
	}

	public Date parseDate(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return new Date(sdf.parse(time).getTime());
		} catch (ParseException e) {
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return new Date(sdf2.parse(time).getTime());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	public static String genPagination(String targetUrl, long totalPage,int currentPage, String param) {
		
		StringBuilder pageCode = new StringBuilder();
		if (totalPage == 0) {
			return "δ��ѯ������";
		} else {

			if (currentPage == 1) {

				pageCode.append("<li class='disabled'><a>��ҳ</a></li>");
			} else {
				pageCode.append("<li><a href='" + targetUrl + "?pageNumber=1&"
						+ param + "'>��ҳ</a></li>");
			}

			if (currentPage == 1) {
				pageCode.append("<li class='disabled'><a>��һҳ</a></li>");
			} else {
				pageCode.append("<li><a href='" + targetUrl + "?pageNumber="
						+ (currentPage - 1) + "&" + param + "'>��һҳ</a></li>");
			}

			for (int i = currentPage - 2; i <= currentPage + 2; i++) {
				if (i < 1 || i > totalPage) {
					continue;
				}
				if (i == currentPage) {
					pageCode.append("<li class='active'><a>" + i + "</a></li>");
				} else {
					pageCode.append("<li><a href='" + targetUrl + "?pageNumber=" + i
							+ "&" + param + "'>" + i + "</a></li>");
				}
			}

			if (currentPage == totalPage) {
				pageCode.append("<li class='disabled'><a>��һҳ</a></li>");
			} else {
				pageCode.append("<li><a href='" + targetUrl + "?pageNumber="
						+ (currentPage + 1) + "&" + param + "'>��һҳ</a></li>");
			}

			if (currentPage == totalPage) {
				pageCode.append("<li class='disabled'><a>βҳ</a></li>");
			} else {
				pageCode.append("<li><a href='" + targetUrl + "?pageNumber="
						+ totalPage + "&" + param + "'>βҳ</a></li>");
			}
		}
		return pageCode.toString();
	}
}
