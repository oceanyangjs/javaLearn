import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response) {
		//注释类 用来练习对于参数的获取
		System.out.println("name is : " + request.getParameter("name"));
		
		String[] hobits = request.getParameterValues("hobits");
		System.out.println("获取具有多值的参数hobits:" + Arrays.asList(hobits));
		
		System.out.println("通过getparameterMap遍历所有的参数：");
		Map<String, String[]> parameters = request.getParameterMap();
		
		Set<String> paramNames = parameters.keySet();
		for(String param:paramNames) {
			String[] value = parameters.get(param);
			System.out.println(param + ":" + Arrays.asList(value));
		}
	}
}
