package com.sbedu.demo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class DemoApplication {

  public static void main(String[] args) throws LifecycleException {
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8080);

    Context context = tomcat.addContext("/", "/");

    HttpServlet servlet = new HttpServlet() {
      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title>");
        writer.println("Hello, Tomcat");
        writer.println("</title></head>");
        writer.println("<body><h1>Hello, World!</h1></body>");
        writer.println("</html>");
      }
    };
    String servletName = "helloServlet";
    tomcat.addServlet("/", servletName, servlet);
    context.addServletMappingDecoded("/hello", servletName);

    tomcat.start();
    tomcat.getServer().await();
  }
}
