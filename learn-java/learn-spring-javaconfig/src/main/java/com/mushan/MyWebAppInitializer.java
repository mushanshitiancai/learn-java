package com.mushan;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by Administrator on 2017/5/15 0015.
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MVCConfiguration.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
