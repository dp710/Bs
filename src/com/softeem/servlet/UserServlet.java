package com.softeem.servlet;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.softeem.bean.User;
import com.softeem.dao.UserDao;
import com.softeem.service.DormitoryService;
import com.softeem.service.UserService;
import com.softeem.util.BaseServlet;

public class UserServlet extends BaseServlet {

	// �ϴ��ļ��洢Ŀ¼
    private static final String UPLOAD_DIRECTORY = "upload";
    // �ϴ�����
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    
    
    protected void upload(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, SQLException {

	// ����Ƿ�Ϊ��ý���ϴ�
    if (!ServletFileUpload.isMultipartContent(request)) {
        // ���������ֹͣ
        PrintWriter out = response.getWriter();
        out.println("Error: ��������� enctype=multipart/form-data");
        out.flush();
        return;
    }
    
    //�����ϴ�����
    DiskFileItemFactory factory = new DiskFileItemFactory();
    // �����ڴ��ٽ�ֵ 
    factory.setSizeThreshold(MEMORY_THRESHOLD);//3MB
    // ������ʱ�洢Ŀ¼ �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
    factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
    //����һ���ļ��ϴ���servlet����,���ҽ��ղ����õ����Դ��뵽�˶���
    ServletFileUpload upload = new ServletFileUpload(factory);
    
    // ��������ļ��ϴ�ֵ
    upload.setFileSizeMax(MAX_FILE_SIZE);//40MB
    // �����������ֵ (�����ļ��ͱ�����)
    upload.setSizeMax(MAX_REQUEST_SIZE);//50MB
    // ���Ĵ���
    upload.setHeaderEncoding("UTF-8"); 
    
    User user = new User();//����һ��userbean����
    UserDao userDao = new UserDao(User.class);
    
    // ������ʱ·�����洢�ϴ����ļ�
    // ���·����Ե�ǰӦ�õ�Ŀ¼
    ServletContext application = this.getServletContext();
    String uploadPath = application.getRealPath(File.separator) + UPLOAD_DIRECTORY;
    File temp = new File(uploadPath);
    if(!temp.exists()){
    	temp.mkdirs();//������Ŀ¼
    }
    
    
    try {
    	//��request�������ת����һ���ļ��б��������������Ҫ�ϴ����ļ���Ϣ���ı���Ϣ
		List<FileItem> formItems = upload.parseRequest(request);
		
		if( formItems!=null && formItems.size() > 0 ){
			for (FileItem item : formItems) {
				//System.out.println("name:"+ item.getName() );//�ϴ��ļ���
				//System.out.println("string:"+ item.getString() );//����
				//System.out.println("fieldname:"+ item.getFieldName() );//�ֶ���
				String value = new String(item.getString().getBytes("iso-8859-1"),"utf-8");
				if(item.isFormField()){
					if("id".equals(item.getFieldName())){
						user.setId(Integer.parseInt(value));
					}
					else if("nickName".equals(item.getFieldName())){
						user.setNickName(value);
					}
					else if("sex".equals(item.getFieldName())){
						user.setSex(value);
					}
					else if("faculty".equals(item.getFieldName())){
						user.setFaculty(value);
					}
					else if("grade".equals(item.getFieldName())){
						user.setGrade(value);
					}
					else if("tel".equals(item.getFieldName())){
						user.setTel(value);
					}
					else if("password".equals(item.getFieldName())){
						user.setPassword(value);
					}
					else if("email".equals(item.getFieldName())){
						user.setEmail(value);
					}
					else if("type".equals(item.getFieldName())){
						user.setType(Integer.parseInt(value));
					}
					else if("dormId".equals(item.getFieldName())){
						user.setDormId(Integer.parseInt(value));
					}
					else if("trueName".equals(item.getFieldName())){
						user.setTrueName(value);
					}
				}else{
					
					if(!"".equals(item.getName()) && item.getSize()>0){
						
					    String suffix = item.getName().substring(item.getName().lastIndexOf("."));
					    String fileName = Math.random()+suffix; //�ļ���
					    String filePath = uploadPath + File.separator + fileName;
					    File storeFile = new File(filePath);
					    item.write(storeFile);//���ϴ����ļ�д�뵽ָ����Ŀ¼��ȥ
					    user.setFace(UPLOAD_DIRECTORY+File.separator+fileName);
					}
				}
			}
		}
	} catch (FileUploadException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	UserService userService = new UserService() ;
	userService.saveUser(user);
	//���浽session���þ�����
	session.setAttribute("user", user);
	response.sendRedirect("introduce.jsp");
}
    
    protected void updatepassword(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, SQLException {
    	String id = request.getParameter("id");
    	String password=request.getParameter("passWord");
    	String password1=request.getParameter("passWord1");
    	String password2=request.getParameter("passWord2");
    	UserService userservice=new UserService();
    	User user=userservice.findPassWord(Integer.parseInt(id));
    	String passWord=user.getPassword();
    	PrintWriter pw=response.getWriter();
    	if(password.equals(passWord)){
    		if(password1.equals(password2)){
    			userservice.updatePassword(password1, Integer.parseInt(id));
    			request.setAttribute("msg", "�޸ĳɹ�");
    			request.getRequestDispatcher("introduce.jsp").forward(request, response);
    			
    		}else{
    			request.setAttribute("msg", "�������벻һ�£��������޸�");
    			request.getRequestDispatcher("updatePassword.jsp").forward(request, response);
    			
    		}
    	}else{
    		request.setAttribute("msg", "������˺Ŵ�������������");
    		request.getRequestDispatcher("updatePassword.jsp").forward(request, response);
    	}
    	
    }
    protected void updateuser(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, SQLException {
    	String id = request.getParameter("id");
    	String nickName = request.getParameter("nickName");
    	String sex=request.getParameter("sex");
    	String tel=request.getParameter("tel");
    	String email=request.getParameter("email");
    	
    	
    	User user=new User(Integer.parseInt(id),nickName,sex,tel,email);
    	
    	UserService userservice=new UserService();
    	userservice.updateuser(user);
    	PrintWriter pw=response.getWriter();
    	pw.write("<script language='javascript'>alert('��Ϣ�޸ĳɹ��������µ�¼�鿴��')</script>");
    	request.getRequestDispatcher("introduce.jsp").forward(request, response);
    	
    }
	
	
	protected void checkid(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {
	
		String id = request.getParameter("id");//����û�id
		//�����û���ҵ������
		UserService userService = new UserService() ;
		
		boolean exist = userService.checkid(Integer.parseInt(id));
		//���true��false
		out.print(exist);
	}
	
	protected void checkname(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, SQLException {

	String nickName = request.getParameter("nickName");//����û��ǳ�
	//�����û���ҵ������
	UserService userService = new UserService() ;
	
	boolean exist = userService.checknickName("nickName");
	//���true��false
	out.print(exist);
}
	
	protected void login(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {
		
		String id = request.getParameter("id").trim();//ȥ���ո�
		String password = request.getParameter("password").trim();
		String imageCode = request.getParameter("imageCode").trim();
		
		if(!session.getAttribute("sRand").equals(imageCode)){
			request.setAttribute("id", id);
			request.setAttribute("password", password);
			request.setAttribute("error", "��֤���������");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			UserService userService = new UserService();
			User user = new User();
			user.setId(Integer.parseInt(id));
			user.setPassword(password);
			user = userService.login(user);
			if(user == null){//user����null��ʽ�û�����������������
				request.setAttribute("id", id);
				request.setAttribute("password", password);
				request.setAttribute("error", "�˺Ż������������");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else{
				//�ɹ���ת����ҳ
				session.setAttribute("user", user);
				String url = request.getParameter("url");
				if(url != null && !"".equals(url)){
					response.sendRedirect(url);
				}else{
					if(user.getType()==2){
					response.sendRedirect("index.jsp");
					}else{
						response.sendRedirect("index1.jsp");
					}
				}
			}
		}
		
	}
	
	
	 protected void list(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException,
				NumberFormatException, SQLException {
			
			
			String pageNumber = request.getParameter("pageNumber");
			String grade = request.getParameter("grade");
			String id = request.getParameter("id");
			
			UserService userService = new UserService();
			Map map = userService.showUserAll(
					grade==null||"".equals(grade)? null:Integer.parseInt(grade),
					id==null||"".equals(id)? null:Integer.parseInt(id), 
					Integer.parseInt(pageNumber));
			
			request.setAttribute("userlist", map.get("userlist"));
			request.setAttribute("pageCode", map.get("pageCode"));
			request.setAttribute("mainPage", "dormitory.jsp");
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("grade", grade);
			request.setAttribute("id", id);
			
			//System.out.println(((List)map.get("dormitoryList")).size());
			
			//request.getRequestDispatcher("DormitoryServlet.action?flag=findDormitory&pageNumber=1").forward(request,response);
			request.getRequestDispatcher("findstudent.jsp").forward(request,response);
			
		}
	
}
