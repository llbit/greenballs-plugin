package hudson.plugins.thisisfine;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.ServletException;

import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import hudson.Plugin;
import hudson.util.PluginServletFilter;

public class ThisIsFinePlugin extends Plugin {

  @Override
  public void start() throws Exception {
    super.start();
    load();
    PluginServletFilter.addFilter(new ThisIsFineFilter());
  }

  @Override
  public void doDynamic(StaplerRequest req, StaplerResponse rsp) throws IOException, ServletException {
    rsp.setHeader("Cache-Control", "public, s-maxage=86400");
    super.doDynamic(req, rsp);
  }

}
