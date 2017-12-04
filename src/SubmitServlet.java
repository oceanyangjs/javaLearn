import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Hero;
import net.sf.json.JSONObject;

public class SubmitServlet extends HttpServlet{
	protected void service(HttpServletRequest request,HttpServletResponse response) {
		String data = request.getParameter("data");
		System.out.println("服务器端接收到的数据为" + data);
		
		JSONObject json = JSONObject.fromObject(data);
		System.out.println("转化为json对象之后为" + json);
		
		Hero hero = (Hero)JSONObject.toBean(json,Hero.class);
		System.out.println("转化为hero对象之后为" + hero);
	}
}
