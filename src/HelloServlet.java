import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//实例化 单实例
	public HelloServlet() {
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
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.getWriter().println("<h1>hello servlet!!!!</h1>");
			response.getWriter().println(new Date().toString());
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
