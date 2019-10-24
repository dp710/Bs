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

	// 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";
    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    
    
    protected void upload(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, SQLException {

	// 检测是否为多媒体上传
    if (!ServletFileUpload.isMultipartContent(request)) {
        // 如果不是则停止
        PrintWriter out = response.getWriter();
        out.println("Error: 表单必须包含 enctype=multipart/form-data");
        out.flush();
        return;
    }
    
    //配置上传参数
    DiskFileItemFactory factory = new DiskFileItemFactory();
    // 设置内存临界值 
    factory.setSizeThreshold(MEMORY_THRESHOLD);//3MB
    // 设置临时存储目录 超过后将产生临时文件并存储于临时目录中
    factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
    //创建一个文件上传的servlet对象,并且将刚才设置的属性传入到此对象
    ServletFileUpload upload = new ServletFileUpload(factory);
    
    // 设置最大文件上传值
    upload.setFileSizeMax(MAX_FILE_SIZE);//40MB
    // 设置最大请求值 (包含文件和表单数据)
    upload.setSizeMax(MAX_REQUEST_SIZE);//50MB
    // 中文处理
    upload.setHeaderEncoding("UTF-8"); 
    
    User user = new User();//创建一个userbean对象
    UserDao userDao = new UserDao(User.class);
    
    // 构造临时路径来存储上传的文件
    // 这个路径相对当前应用的目录
    ServletContext application = this.getServletContext();
    String uploadPath = application.getRealPath(File.separator) + UPLOAD_DIRECTORY;
    File temp = new File(uploadPath);
    if(!temp.exists()){
    	temp.mkdirs();//创建此目录
    }
    
    
    try {
    	//将request请求对象转换成一个文件列表里面包涵了所有要上传的文件信息与文本信息
		List<FileItem> formItems = upload.parseRequest(request);
		
		if( formItems!=null && formItems.size() > 0 ){
			for (FileItem item : formItems) {
				//System.out.println("name:"+ item.getName() );//上传文件名
				//System.out.println("string:"+ item.getString() );//数据
				//System.out.println("fieldname:"+ item.getFieldName() );//字段名
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
					    String fileName = Math.random()+suffix; //文件名
					    String filePath = uploadPath + File.separator + fileName;
					    File storeFile = new File(filePath);
					    item.write(storeFile);//将上传的文件写入到指定的目录中去
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
	//保存到session作用局域中
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
    			request.setAttribute("msg", "修改成功");
    			request.getRequestDispatcher("introduce.jsp").forward(request, response);
    			
    		}else{
    			request.setAttribute("msg", "两次密码不一致，请重新修改");
    			request.getRequestDispatcher("updatePassword.jsp").forward(request, response);
    			
    		}
    	}else{
    		request.setAttribute("msg", "密码或账号错误，请重新输入");
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
    	pw.write("<script language='javascript'>alert('信息修改成功，请重新登录查看！')</script>");
    	request.getRequestDispatcher("introduce.jsp").forward(request, response);
    	
    }
	
	
	protected void checkid(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {
	
		String id = request.getParameter("id");//获得用户id
		//创建用户名业务层对象
		UserService userService = new UserService() ;
		
		boolean exist = userService.checkid(Integer.parseInt(id));
		//输出true或false
		out.print(exist);
	}
	
	protected void checkname(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, SQLException {

	String nickName = request.getParameter("nickName");//获得用户昵称
	//创建用户名业务层对象
	UserService userService = new UserService() ;
	
	boolean exist = userService.checknickName("nickName");
	//输出true或false
	out.print(exist);
}
	
	protected void login(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {
		
		String id = request.getParameter("id").trim();//去除空格
		String password = request.getParameter("password").trim();
		String imageCode = request.getParameter("imageCode").trim();
		
		if(!session.getAttribute("sRand").equals(imageCode)){
			request.setAttribute("id", id);
			request.setAttribute("password", password);
			request.setAttribute("error", "验证码输入错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			UserService userService = new UserService();
			User user = new User();
			user.setId(Integer.parseInt(id));
			user.setPassword(password);
			user = userService.login(user);
			if(user == null){//user等于null表式用户名或者密码有问题
				request.setAttribute("id", id);
				request.setAttribute("password", password);
				request.setAttribute("error", "账号或密码输入错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else{
				//成功跳转到首页
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
