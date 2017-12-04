import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Hero;
import net.sf.json.JSONObject;

public class SubmitServlet extends HttpServlet{
	protected void service(HttpServletRequest request,HttpServletResponse response) {
		String data = request.getParameter("data");
		System.out.println("�������˽��յ�������Ϊ" + data);
		
		JSONObject json = JSONObject.fromObject(data);
		System.out.println("ת��Ϊjson����֮��Ϊ" + json);
		
		Hero hero = (Hero)JSONObject.toBean(json,Hero.class);
		System.out.println("ת��Ϊhero����֮��Ϊ" + hero);
	}
}
