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

//回调函数,dbutil(反射),
//连接池:https://www.cnblogs.com/zhchoutai/p/7080991.html
//http://commons.apache.org/proper/commons-dbutils/examples.html什么情况下需要手动关闭
//抽象类父类
public class BaseDao<E> {

	public Integer pageCount = 5;// 每页显示5页
	public List<E> list; // 用于保存实体对象的集合
	public E entity; // 实体
	public QueryRunner query;
	public DataSource dataSource;// 数据源对象
	public Class claxx; // 实例类对象类型
	public Connection conn ;

	// 无参构造器
	public BaseDao(Class claxx) {
		dataSource = MyDataSourceFactory.getDataSource();// 为dataSource初始化
		query = new QueryRunner();
		this.claxx = claxx;
	}

	/**
	 * 查询所有数据
	 * 
	 * @param sql
	 *            查询语句
	 * @param claxx
	 *            要查询的表对应的实例对象
	 * @return 返回查询出来的list集合
	 * @author xxx
	 * @throws SQLException 
	 */
	public List<E> findAll(String sql) throws SQLException {
		conn = ConnectionContext.getInstance().getConnection();
		
			list = query.query(conn , sql, new BeanListHandler<E>(claxx));
		
		return list;
	}

	/**
	 * 根据主键id进行查询
	 * 
	 * @param sql
	 *            要查询的sql语句
	 * @param id
	 *            查询条件(主键id)
	 * @return 返回一条记录(一个实体对象)
	 * @author xxx
	 * @throws SQLException 
	 */
	public E findById(String sql, Integer id) throws SQLException {
		conn = ConnectionContext.getInstance().getConnection();
		
			entity = query.query(conn, sql, new BeanHandler<E>(claxx), id);
		
		return entity;
	}

	/**
	 * 分页查询方法
	 * 
	 * @param sql
	 *            分页查询语句
	 * @param pageNumber
	 *            要查询的页数
	 * @return 分页查询的结果集
	 * @author xxx
	 * @throws SQLException 
	 */
	public List<E> paging(String sql, Integer pageNumber) throws SQLException {
		conn = ConnectionContext.getInstance().getConnection();
		Object[] params = { (pageNumber - 1) * pageCount, pageCount };// 定义数组sql语句所需要的参数
		
			list = query.query(conn , sql, params, new BeanListHandler<E>(claxx));
		
		return list;
	}

	/**
	 * 添加数据
	 * 
	 * @param sql
	 *            插入语句(insert)
	 * @param params
	 *            查询添加到数据库表中的值
	 * @return 返回插入数据后面生成的主键id
	 * @author xxx
	 * @throws SQLException 
	 */
	public int save( String sql, Object[] params) throws SQLException {
		conn = ConnectionContext.getInstance().getConnection();
		Long id = 0L;
		
			id = query.insert(conn , sql, new ScalarHandler<Long>(), params);// 添加方法
		
		return id.intValue();
	}

	/**
	 * 根据主键id进行修改
	 * 
	 * @param sql
	 *            修改语句(update)
	 * @param params
	 *            要修改的字段值.必须要有主键id不要忘记
	 * @return 返回影响行数
	 * @author xxx
	 * @throws SQLException 
	 */
	public int updateById(String sql, Object[] params) throws SQLException {
		conn = ConnectionContext.getInstance().getConnection();
		int count = 0;
		
			count = query.update(conn, sql, params);// 更新,添加,删除
		
		return count;
	}

	/**
	 * 根据主键id进行删除
	 * 
	 * @param sql
	 *            删除语句 (delete)
	 * @param id
	 *            主键id
	 * @return 返回删除的影响行数
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
	 * 查询最大页
	 * 
	 * @param sql
	 *            查询所有记录数的sql语句
	 * @return 返回总页数
	 * @author xxx
	 * @throws SQLException 
	 */
	public int maxPage( String sql ) throws SQLException {
		conn = ConnectionContext.getInstance().getConnection();
		long count = 0;
		
			count = query.query(conn , sql, new ScalarHandler<Long>());// 返回那种只有一行一列的数据
		
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
			return "未查询到数据";
		} else {

			if (currentPage == 1) {

				pageCode.append("<li class='disabled'><a>首页</a></li>");
			} else {
				pageCode.append("<li><a href='" + targetUrl + "?pageNumber=1&"
						+ param + "'>首页</a></li>");
			}

			if (currentPage == 1) {
				pageCode.append("<li class='disabled'><a>上一页</a></li>");
			} else {
				pageCode.append("<li><a href='" + targetUrl + "?pageNumber="
						+ (currentPage - 1) + "&" + param + "'>上一页</a></li>");
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
				pageCode.append("<li class='disabled'><a>下一页</a></li>");
			} else {
				pageCode.append("<li><a href='" + targetUrl + "?pageNumber="
						+ (currentPage + 1) + "&" + param + "'>下一页</a></li>");
			}

			if (currentPage == totalPage) {
				pageCode.append("<li class='disabled'><a>尾页</a></li>");
			} else {
				pageCode.append("<li><a href='" + targetUrl + "?pageNumber="
						+ totalPage + "&" + param + "'>尾页</a></li>");
			}
		}
		return pageCode.toString();
	}
}
