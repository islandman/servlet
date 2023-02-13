
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;


@WebFilter(filterName = "FilterExample", urlPatterns = { "/SetCookies" })
public class FilterExample implements Filter {


	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// set a cookie

		String username = request.getParameter("username");

		if (!"hacker".equalsIgnoreCase(username)) {
			chain.doFilter(request, response);
		} else {

			out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\" />\r\n"
					+ "<title>Filter Example</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n"
					+ "	<h1>Sorry! we don't entertain hackers.. :D</h1>\r\n" + "</body>\r\n" + "</html>");
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
