package com.app.minhaescolaapp.config;


import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/usuario/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/professor/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/secretario/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/tesoureiro/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/funcionario/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/disciplina/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/sala/**").addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}
	
	@Override
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
		super.addArgumentResolvers(argumentResolvers);
	}
	
}
