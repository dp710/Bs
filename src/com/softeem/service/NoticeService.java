package com.softeem.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softeem.bean.Dormitory;
import com.softeem.bean.Notice;
import com.softeem.bean.Water;
import com.softeem.dao.NoticeDao;


public class NoticeService {
	private NoticeDao  noticedao= new NoticeDao(Notice.class);
	public int addnotice(Notice notice) throws SQLException{ 
		String sql = "insert into t_notice values(0,?,?,?,?)";
		Object [] params =
		{
          notice.getTitle(),
          notice.getPublishTime(),
          notice.getUserId(),
          notice.getContent()
	};
		return noticedao.save(sql, params);
	}
	
	public Map findNotice(Integer pageNumber) throws SQLException{
		List<Notice> noticelist=noticedao.findNotice(pageNumber);
		
		int maxPage = noticedao.findNoticeMaxpage();
		String pageCode = NoticeDao.genPagination("NoticeServlet.action", maxPage, pageNumber, "flag=findNotice");
		
		HashMap map = new HashMap();
		map.put("noticelist", noticelist);
		map.put("pageCode", pageCode);
		
		return map;
		}
	
	 public void deleteById(Integer noticeId) throws SQLException{
			

			noticedao.deleteById("delete from t_notice where id=?", noticeId);
			
		}
	 
	 public Notice findNoticeById(Integer noticeId) throws SQLException{
			

			return noticedao.findById("select * from t_notice where id=?",noticeId);
			
		}
	 public int updatenotice(Notice notice) throws SQLException{
			return noticedao.updatenotice(notice);
		}
	 
	 public Map showNoticeAll( Integer userId,Integer pageNumber) throws SQLException{
			
			List<Notice> noticelist = noticedao.findNoticeUser(userId, pageNumber);
			
			int maxPage = noticedao.findNoticeUserMaxPage(userId);
			String pageCode = noticedao.genPagination("NoticeServlet.action", maxPage, pageNumber, "flag=list&userId="+((userId==null)?"":userId));
			
			HashMap map = new HashMap();
			map.put("noticelist", noticelist);
			map.put("pageCode", pageCode);
			
			return map;
		}
}

