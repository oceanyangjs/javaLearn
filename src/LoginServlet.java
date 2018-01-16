import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	
	//实例化 单实例
	public LoginServlet() {
		System.out.println("构造方法调用，实例化");
	}
	
	//初始化
	public void init(ServletConfig config){
		System.out.println("init(servletconfig) 初始化");
	}
	
	//销毁
	public void destroy() {
		System.out.println("destroy");
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		//接收从浏览器收到的数据
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//以中文方式获取
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//若干request的方法
		System.out.println("浏览器发出请求时的完整URL，包括协议 主机名 端口(如果有): " + request.getRequestURL());
        System.out.println("浏览器发出请求的资源名部分，去掉了协议和主机名: " + request.getRequestURI());
        System.out.println("请求行中的参数部分: " + request.getQueryString());
        System.out.println("浏览器所处于的客户机的IP地址: " + request.getRemoteAddr());
        System.out.println("浏览器所处于的客户机的主机名: " + request.getRemoteHost());
        System.out.println("浏览器所处于的客户机使用的网络端口: " + request.getRemotePort());
        System.out.println("服务器的IP地址: " + request.getLocalAddr());
        System.out.println("服务器的主机名: " + request.getLocalName());
        System.out.println("得到客户机请求方式: " + request.getMethod());
        //获取头信息
        Enumeration<String> headerNames= request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            String value = request.getHeader(header);
            System.out.printf("%s\t%s%n",header,value);
        }
		//以下为采用原页面输出的方式
//		String html = null;
//		
//		if("admin".equals(name)&&"123".equals(password)) {
//			html = "<div style='color:green'>success</div>";
//		}else {
//			html = "<div style='color:red'>failed</div>";
//		}
//		//将数据发送到浏览器
//		PrintWriter pw;
//		try {
//			response.setContentType("text/html; charset=UTF-8");
//			pw = response.getWriter();
//			pw.println(html);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//以下为采用跳转的方式
		response.setContentType("text/html; charset=UTF-8");
		if("admin".equals(name)&&"123".equals(password)) {//成功采用的服务端跳转,页面地址不变
			//将登陆的信息name存储在session中
			request.getSession().setAttribute("userName", name);
			request.getRequestDispatcher("success.html").forward(request,response);
		}else {//失败采用的客户端跳转，页面地址会发生变化
			response.sendRedirect("fail.html");
		}
		
		System.out.println("name:" + name);
		System.out.println("password:" + password);
	}
}
