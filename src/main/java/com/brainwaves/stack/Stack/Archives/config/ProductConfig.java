package com.brainwaves.stack.Stack.Archives.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.brainwaves.stack.Stack.Archives.service.StackArchivesService;
import com.brainwaves.stack.Stack.Archives.service.StackArchivesServiceImpl;

/**
 * ProductConfig class is a config class of Stock Archives
 * 
 * @author Saravanan Perumal
 *
 */
@Configuration
public class ProductConfig {

	@Bean
	public StackArchivesService stackArchivesService() {
		return new StackArchivesServiceImpl();
	}

}
