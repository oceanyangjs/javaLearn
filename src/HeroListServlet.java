import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Hero;
import dao.HeroDao;

public class HeroListServlet extends HttpServlet{
	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset = UTF-8");
		
		List<Hero> heros = new HeroDao().list();
		
		StringBuffer sb = new StringBuffer();
		sb.append("<table aligen='center' border='1' cellspacing='0'>\r\n");
		sb.append("<tr><td>id</td><td>name</td><td>hp</td><td>damage</td><td>edit</td><td>delete</td></tr>\r\n");
		
		String trFormat = "<tr><td>%d</td><td>%s</td><td>%f</td><td>%d</td><td><a href='editHero?id=%d'>edit</td><td><a href='deleteHero?id=%d'>delete</td></tr>\r\n";
		
		for(Hero hero : heros) {
			String st = String.format(trFormat, hero.getId(),hero.getName(),hero.getHp(),hero.getDamage(),hero.getId(),hero.getId());
			sb.append(st);
		}
		
		sb.append("</table>");
		
		response.getWriter().println(sb.toString());
	}
}
