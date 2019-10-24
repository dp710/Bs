package com.softeem.servlet;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import com.softeem.bean.User;
import com.softeem.dao.UserDao;
import com.softeem.service.NoticeService;
import com.softeem.service.StudentService;
import com.softeem.service.UserService;
import com.softeem.util.BaseServlet;

public class StudentServlet extends BaseServlet {
	
	
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
	
	
	StudentService studentService = new StudentService() ;
	studentService.updatestudent(user);
	//保存到session作用局域中
	//session.setAttribute("user", user);
	//response.sendRedirect("introduce.jsp");
	request.getRequestDispatcher("StudentServlet.action?flag=findStudent&pageNumber=1").forward(request, response);
}
	
	protected void findStudent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		
	String pageNumber=request.getParameter("pageNumber");
	StudentService studentservice=new StudentService();
	
	Map map= studentservice.findStudent(Integer.parseInt(pageNumber));
	request.setAttribute("userlist", map.get("userlist"));
	request.setAttribute("pageCode", map.get("pageCode"));
	request.getRequestDispatcher("findstudent.jsp").forward(request,response);	
	}
	protected void deleteStudent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NumberFormatException, SQLException {
		
		String userId = request.getParameter("userId");
		
		StudentService studentService = new StudentService() ;
		
		studentService.deleteById(Integer.parseInt(userId));
		
		request.getRequestDispatcher("StudentServlet.action?flag=findStudent&pageNumber=1").forward(request, response);
	}
	
	protected void findStudentById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		
         String userId = request.getParameter("userId");
		
		StudentService studentService = new StudentService() ;
		
		User user=studentService.findStudentById(Integer.parseInt(userId));
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("updatestudent.jsp").forward(request, response);
	}
	
}
