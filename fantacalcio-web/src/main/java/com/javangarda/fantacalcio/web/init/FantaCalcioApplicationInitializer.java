package com.javangarda.fantacalcio.web.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.javangarda.fantacalcio.football.application.FootballContext;
import com.javangarda.fantacalcio.util.contexts.RootApplicationProfilesContext;
import com.javangarda.fantacalcio.web.contexts.WebApplicationContext;

public class FantaCalcioApplicationInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.register(FootballContext.class, RootApplicationProfilesContext.class);
		
	    servletContext.addListener(new ContextLoaderListener(appContext));
	    
	    AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
	    dispatcherServlet.register(WebApplicationContext.class);
	    
	    ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
         
	}

}
