
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieExample")
public class CookieExample extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// print out cookies

		Cookie[] cookies = request.getCookies();

		String name = "";
		String value = "Stranger";
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				name = c.getName();
				value = c.getValue();
			}
		}
		
		out.println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"UTF-8\" />\r\n" + 
				"<title>Cookies Example</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"\r\n" + 
				"	<h1>Cookies Example</h1>\r\n" + 
				"<h3 style=\"color:red\">Welcome Back " + value + "!</h3>"+
				"	<h3>Tell us your name to greet you, the next time you visit our site!</h3>\r\n" + 
				"	\r\n" + 
				"	<form action=\"SetCookies\" method=\"POST\">\r\n" + 
				"		Name: <input type=\"text\" name=\"username\"><br>\r\n" + 
				"		\r\n" + 
				"		<br /><input type=\"submit\" value=\"Send my name!\">\r\n" + 
				"	</form>\r\n" + 
				"	\r\n" + 
				"	\r\n" + 
				"</body>\r\n" + 
				"</html>");

	}
	
}
