package com.searchsuggestions.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	// Default URI
	// http://localhost:8080/swagger-ui.html

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.searchsuggestions.controller"))
				.paths(postPaths()).build().apiInfo(metaData());
	}

	private Predicate<String> postPaths() {
	
		
		  return or(Stream.of(regex("/search_directory.*"), regex("/city_directory.*"))
				  .collect(Collectors.toList()));
		 
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Directory Search", "City Search API", "1.0", "None", "Free", "None", "None");
				
		return apiInfo;
	}

}
