import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

@WebFilter(filterName = "ControllerResponseFilter", urlPatterns = {"/*"})
public class ControllerResponseFilter implements Filter {

  private static final Pattern[] maliciousPatterns = {
    Pattern.compile("<script.*?>.*?</script.*?>", Pattern.CASE_INSENSITIVE),
    Pattern.compile("<.*?javascript:.*?>", Pattern.CASE_INSENSITIVE),
    Pattern.compile("<.*?\\s+on.*?>", Pattern.CASE_INSENSITIVE)
  };

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // No-op implementation
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    // Sanitize response
     HttpServletResponse httpResponse = (HttpServletResponse) response;
      HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(httpResponse) {
      private final PrintWriter out = new PrintWriter(httpResponse.getWriter());
      private StringBuilder sb = new StringBuilder();

      @Override
      public PrintWriter getWriter() throws IOException {
        return out;
      }

      public void write(String s) {
        sb.append(s);
      }

      public void flush() throws IOException {
        String content = sb.toString();
        for (Pattern pattern : maliciousPatterns) {
          content = pattern.matcher(content).replaceAll("");
        }
        out.print(content);
        out.flush();
      }
    };
    chain.doFilter(request, responseWrapper);
    
  }

  @Override
  public void destroy() {
    // No-op implementation
  }
}
