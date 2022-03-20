package ru.arttrif.springPP321.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class DispatcherServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    // указываем класс ConfigSpring
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ConfigSpring.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    // добавим фильтр чтобы он мог читать _method чтобы заработали PATCH и DELETE запросы
    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException {
        FilterRegistration.Dynamic encodingFilter = aServletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");

        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }

    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }
}
