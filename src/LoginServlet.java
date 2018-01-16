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
	
	//ʵ���� ��ʵ��
	public LoginServlet() {
		System.out.println("���췽�����ã�ʵ����");
	}
	
	//��ʼ��
	public void init(ServletConfig config){
		System.out.println("init(servletconfig) ��ʼ��");
	}
	
	//����
	public void destroy() {
		System.out.println("destroy");
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		//���մ�������յ�������
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//�����ķ�ʽ��ȡ
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//����request�ķ���
		System.out.println("�������������ʱ������URL������Э�� ������ �˿�(�����): " + request.getRequestURL());
        System.out.println("����������������Դ�����֣�ȥ����Э���������: " + request.getRequestURI());
        System.out.println("�������еĲ�������: " + request.getQueryString());
        System.out.println("����������ڵĿͻ�����IP��ַ: " + request.getRemoteAddr());
        System.out.println("����������ڵĿͻ�����������: " + request.getRemoteHost());
        System.out.println("����������ڵĿͻ���ʹ�õ�����˿�: " + request.getRemotePort());
        System.out.println("��������IP��ַ: " + request.getLocalAddr());
        System.out.println("��������������: " + request.getLocalName());
        System.out.println("�õ��ͻ�������ʽ: " + request.getMethod());
        //��ȡͷ��Ϣ
        Enumeration<String> headerNames= request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            String value = request.getHeader(header);
            System.out.printf("%s\t%s%n",header,value);
        }
		//����Ϊ����ԭҳ������ķ�ʽ
//		String html = null;
//		
//		if("admin".equals(name)&&"123".equals(password)) {
//			html = "<div style='color:green'>success</div>";
//		}else {
//			html = "<div style='color:red'>failed</div>";
//		}
//		//�����ݷ��͵������
//		PrintWriter pw;
//		try {
//			response.setContentType("text/html; charset=UTF-8");
//			pw = response.getWriter();
//			pw.println(html);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//����Ϊ������ת�ķ�ʽ
		response.setContentType("text/html; charset=UTF-8");
		if("admin".equals(name)&&"123".equals(password)) {//�ɹ����õķ������ת,ҳ���ַ����
			//����½����Ϣname�洢��session��
			request.getSession().setAttribute("userName", name);
			request.getRequestDispatcher("success.html").forward(request,response);
		}else {//ʧ�ܲ��õĿͻ�����ת��ҳ���ַ�ᷢ���仯
			response.sendRedirect("fail.html");
		}
		
		System.out.println("name:" + name);
		System.out.println("password:" + password);
	}
}
