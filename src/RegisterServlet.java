import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response) {
		//ע���� ������ϰ���ڲ����Ļ�ȡ
		System.out.println("name is : " + request.getParameter("name"));
		
		String[] hobits = request.getParameterValues("hobits");
		System.out.println("��ȡ���ж�ֵ�Ĳ���hobits:" + Arrays.asList(hobits));
		
		System.out.println("ͨ��getparameterMap�������еĲ�����");
		Map<String, String[]> parameters = request.getParameterMap();
		
		Set<String> paramNames = parameters.keySet();
		for(String param:paramNames) {
			String[] value = parameters.get(param);
			System.out.println(param + ":" + Arrays.asList(value));
		}
	}
}
