package ${basepackage}.config;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * web.xml配置
 *
<%include("/java_description.include"){}%>
 */
public class WebApp extends AbstractAnnotationConfigDispatcherServletInitializer {

    public WebApp() {
        super();
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{PersistenceConfig.class, WebMVCConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("shiroFilter", DelegatingFilterProxy
                .class);
        filterRegistration.setInitParameter("targetFilterLifecycle", "true");
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
    }

    //    @Override
//    protected void customizeRegistration(final ServletRegistration.Dynamic registration) {
//        super.customizeRegistration(registration);
//    }

//    @Override
//    protected FilterRegistration.Dynamic registerServletFilter(ServletContext servletContext, Filter filter) {
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//        String filterName = Conventions.getVariableName(characterEncodingFilter);
//        FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, characterEncodingFilter);
//        registration.setAsyncSupported(true);
//        registration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
//        return registration;

//        DelegatingFilterProxy filterProxy = new DelegatingFilterProxy();
//        filterProxy.setTargetFilterLifecycle(true);
//        FilterRegistration.Dynamic registration = servletContext.addFilter("shiroFilter", filterProxy);
//        registration.setAsyncSupported(true);
//        registration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
//        return registration;
//    }

}
