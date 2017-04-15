package net.shinsoft.config;

import net.shinsoft.shiro.CustomHashedCredentialsMatcher;
import net.shinsoft.shiro.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.apache.shiro.codec.Base64.decode;

/**
 * Created by starhq on 2017/3/25.
 */
@Configuration
public class ShiroConfig {

    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(2592000);
        return cookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(rememberMeCookie());
        rememberMeManager.setCipherKey(decode("5AvVhmFLUs0KTA3Kprsdag=="));
        return rememberMeManager;
    }

    @Bean
    public Realm getUserRealm() {
        UserRealm realm = new UserRealm();
        realm.setCredentialsMatcher(getHashedCredentialsMatcher());
        return realm;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(getUserRealm());
        manager.setRememberMeManager(rememberMeManager());
        return manager;
    }

    @DependsOn("getLifecycleBeanPostProcessor")
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(getDefaultWebSecurityManager());
        return advisor;
    }

    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        CustomHashedCredentialsMatcher matcher = new CustomHashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(2);
        matcher.setStoredCredentialsHexEncoded(true);
        return matcher;
    }


    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(getDefaultWebSecurityManager());
        bean.setLoginUrl("/signin");
//        bean.setSuccessUrl("/back");
        bean.setUnauthorizedUrl("/signin");

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //静态资源文件
        filterChainDefinitionMap.put("/resources/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        //不需要验证的
        filterChainDefinitionMap.put("/signin", "anon");
        filterChainDefinitionMap.put("/default", "anon");
        filterChainDefinitionMap.put("/users/login", "anon");
        filterChainDefinitionMap.put("/admin", "anon");
        //要验证的
        filterChainDefinitionMap.put("/**", "authc");

        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }
}
