package com.makarova.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//config показывает как поднимать приложения и что использовать при этом
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.makarova"})
public class WebMvcConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(WebMvcConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        ServletRegistration.Dynamic dynamic = servletContext
                .addServlet("dispatcher", new DispatcherServlet(webContext));
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");
    }
}
