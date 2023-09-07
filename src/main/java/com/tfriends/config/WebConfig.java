package com.tfriends.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tfriends.interceptor.CmsInterceptor;
import com.tfriends.interceptor.HomeInterceptor;
import com.tfriends.interceptor.SecurityInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    // @Bean
    // CookieSerializer cookie() {
    // DefaultCookieSerializer serial = new DefaultCookieSerializer();
    // serial.setDomainName("tsukimorifriends.xyz");
    // serial.setCookieName("JSESSIONID");
    // serial.setCookiePath("/");

    // return serial;
    // }

    @Bean
    protected HomeInterceptor homeInterceptor() {
        return new HomeInterceptor();
    }

    @Bean
    protected CmsInterceptor boardInterceptor() {
        return new CmsInterceptor();
    }

    @Bean
    protected SecurityInterceptor securityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor()).addPathPatterns("/**").excludePathPatterns("/banned");
        registry.addInterceptor(homeInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(boardInterceptor()).addPathPatterns("/community/**", "/cmsv2/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

}
