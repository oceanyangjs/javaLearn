import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Hero;
import net.sf.json.JSONSerializer;

public class GetManyServlet extends HttpServlet{
	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<Hero> heros = new ArrayList<>();
		for(int i = 0;i<10;i++) {
			Hero hero = new Hero();
			hero.setName("name" + i);
			hero.setHp(900);
			heros.add(hero);
		}
		
		String result = JSONSerializer.toJSON(heros).toString();
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(result);
	}
}
