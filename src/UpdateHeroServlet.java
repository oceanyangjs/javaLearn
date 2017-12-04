import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Hero;
import dao.HeroDao;

public class UpdateHeroServlet extends HttpServlet{
	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		Float hp = Float.parseFloat(request.getParameter("hp"));
		int damage = Integer.parseInt(request.getParameter("damage"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		Hero hero = new Hero();
		hero.setId(id);
		hero.setName(name);
		hero.setHp(hp);
		hero.setDamage(damage);
		
		new HeroDao().update(hero);
		
		//更新结束跳转回herolist
		response.sendRedirect("/j2ee/heroList");
	}
}
