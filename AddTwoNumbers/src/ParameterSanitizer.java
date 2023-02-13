import java.io.*;
import javax.servlet.http.*;

public class ParameterSanitizer extends HttpServletRequestWrapper {

  public ParameterSanitizer(HttpServletRequest request) {
    super(request);
  }

  @Override
  public String getParameter(String name) {
    String value = super.getParameter(name);
    return sanitize(value);
  }

  @Override
  public String[] getParameterValues(String name) {
    String[] values = super.getParameterValues(name);
    if (values == null) {
      return null;
    }
    for (int i = 0; i < values.length; i++) {
      values[i] = sanitize(values[i]);
    }
    return values;
  }

  private String sanitize(String value) {
    if (value == null) {
      return null;
    }
    // Perform the necessary sanitation operations
    value = value.replaceAll("<", "&lt;");
    value = value.replaceAll(">", "&gt;");
    value = value.replaceAll("&", "&amp;");
    value = value.replaceAll("\"", "&quot;");
    return value;
  }

}
