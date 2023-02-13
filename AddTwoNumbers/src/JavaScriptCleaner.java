import java.io.*;
import javax.servlet.http.*;

public class JavaScriptCleaner extends HttpServletRequestWrapper {

  public JavaScriptCleaner(HttpServletRequest request) {
    super(request);
  }

  @Override
  public String getParameter(String name) {
    String value = super.getParameter(name);
    return removeJavaScript(value);
  }

  @Override
  public String[] getParameterValues(String name) {
    String[] values = super.getParameterValues(name);
    if (values == null) {
      return null;
    }
    for (int i = 0; i < values.length; i++) {
      values[i] = removeJavaScript(values[i]);
    }
    return values;
  }

  private String removeJavaScript(String value) {
	    if (value == null) {
	      return null;
	    }
	    // Remove malicious JavaScript code
	    value = value.replaceAll("<script>", "");
	    value = value.replaceAll("</script>", "");
	    value = value.replaceAll("eval\\((.*)\\)", "");
	    value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
	    value = value.replaceAll("script", "");
	    value = value.replaceAll("expression\\((.*)\\)", "");
	    value = value.replaceAll("javascript\\:", "");
	     
	    value = value.replaceAll("onload(.*)", "");
	    value = value.replaceAll("onfocus(.*)", "");
	    value = value.replaceAll("onclick(.*)", "");
	    value = value.replaceAll("onmouseover(.*)", "");
	    value = value.replaceAll("onmouseout(.*)", "");
	    value = value.replaceAll("prompt\\((.*)\\)", "");
	    value = value.replaceAll("alert\\((.*)\\)", "");
	    return value;
	  }


}
