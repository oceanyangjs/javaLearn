import java.io.IOException;
import java.text.Format;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Hero;
import dao.HeroDao;

public class EditHeroServlet extends HttpServlet{
	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		//获取对应的英雄
		Hero hero = new HeroDao().get(id);
		
		StringBuffer sb = new StringBuffer();
		response.setContentType("text/html;charset=UTF-8");
		
		sb.append("<!DOCTYPE html>");
		
		sb.append("<form action='updateHero' method='post'>");
		sb.append("名字：<input type='text' name='name' value='%s'><br>");
		sb.append("血量：<input type='text' name='hp' value='%f'><br>");
		sb.append("伤害：<input type='text' name='damage' value='%d'><br>");
		sb.append("<input type='hidden' name='id' value='%d'>");
		sb.append("<input type='submit' value='更新'>");
		sb.append("</form>");
		
		
		System.out.println(hero.id);
		String html = String.format(sb.toString(),hero.getName(),hero.getHp(),hero.getDamage(),hero.getId());
		
		response.getWriter().write(html);
	}
}
