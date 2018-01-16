import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Hero;
import dao.HeroDao;

public class HeroListMvcServlet extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html; charset=UTF-8");
		String userName = (String)request.getSession().getAttribute("userName");
		if(null == userName) {
			response.sendRedirect("login.html");
		}
		int start = 0;
		int count = 5;
		try {
			start = Integer.parseInt(request.getParameter("start"));
		}catch(NumberFormatException e) {
			
		}
		int next = start + count;
		int pre = start - count;
		int last = 0;
		int total = new HeroDao().getTotal();
		if(0 == total % count) {
			last = total - count;
		}else {
			last = total - total % count;
		}
		
		pre = pre < 0? 0:pre;
		next = next>last? last:next;
		List<Hero> heros = new HeroDao().list(start,count);
		request.setAttribute("heros", heros);
		request.setAttribute("pre", pre);
		request.setAttribute("next", next);
		request.setAttribute("last", last);
		request.getRequestDispatcher("listHero.jsp").forward(request, response);
	}
}
