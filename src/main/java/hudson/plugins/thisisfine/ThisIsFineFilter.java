package hudson.plugins.thisisfine;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hudson.init.InitMilestone;
import hudson.model.User;
import jenkins.model.Jenkins;

/**
 * This filter intercept calls to the static ball images and
 * replaces them with requests for the fine/fire images.
 *
 * @author Jesper Öqvist
 * @author Asgeir Storesund Nilsen
 */
public class ThisIsFineFilter implements Filter {

  final String patternStr = "/(\\d{2}x\\d{2})/%s(_anime|)\\.(gif|png)";

  final Pattern patternBlue = Pattern.compile(String.format(patternStr, "blue"));
  final Pattern patternYellow = Pattern.compile(String.format(patternStr, "yellow"));
  final Pattern patternRed = Pattern.compile(String.format(patternStr, "red"));
  final Pattern patternGray = Pattern.compile(String.format(patternStr, "nobuilt"));

  final Logger logger = Logger.getLogger("hudson.plugins.thisisfine");

  @Override
  public void init(FilterConfig config) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
    throws IOException, ServletException {
    if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
      final HttpServletRequest httpServletRequest = (HttpServletRequest) req;
      final HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
      final String uri = httpServletRequest.getRequestURI();
      if (uri.endsWith(".gif") || uri.endsWith(".png")) {
        String newImageUrl = mapImage(uri);
        if (newImageUrl != null) {
          if (logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, "Redirecting {0} to {1}", new Object[] { uri, newImageUrl });
          }
          RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher(newImageUrl);
          dispatcher.forward(httpServletRequest, httpServletResponse);
          return;
        }
      }
    }
    chain.doFilter(req, resp);
  }

  private String mapImage(String uri) {
    // Fix for JENKINS-28422
    Jenkins jenkins = Jenkins.getActiveInstance();
    if (InitMilestone.EXTENSIONS_AUGMENTED.compareTo(jenkins.getInitLevel()) > 0) {
      return null;
    }
    if (uri.contains("plugin/thisisfine/")) {
      return null;
    }
    String basePath = "/static/.../plugin/thisisfine/";
    Matcher m;
    if ((m = patternBlue.matcher(uri)).find()) {
      return basePath + m.group(1) + "/fine" + m.group(2) + "." + m.group(3);
    }
    if ((m = patternYellow.matcher(uri)).find()) {
      return basePath + m.group(1) + "/notfine" + m.group(2) + "." + m.group(3);
    }
    if ((m = patternRed.matcher(uri)).find()) {
      return basePath + m.group(1) + "/fire" + m.group(2) + "." + m.group(3);
    }
    if ((m = patternGray.matcher(uri)).find()) {
      return basePath + m.group(1) + "/gray" + m.group(2) + "." + m.group(3);
    }
    return null;
  }

  @Override
  public void destroy() {
  }
}
