package com.zx.Controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class SpringAdapter extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SelfInterceptor())
			.addPathPatterns("/getAllArticel")
			.addPathPatterns("/deleteArt")
			.addPathPatterns("/getArticelByPage")
			.addPathPatterns("/getAllArtModel")
			.addPathPatterns("/getOneLevelModel")
			.addPathPatterns("/getTwoLevelModel/**")
			.addPathPatterns("/saveArticel")
			.addPathPatterns("/updateArticel")
			.addPathPatterns("/deleteBanner")
			.addPathPatterns("/uploadBanner")
			.addPathPatterns("/getUserorder")
			;
		super.addInterceptors(registry);
	}
}
