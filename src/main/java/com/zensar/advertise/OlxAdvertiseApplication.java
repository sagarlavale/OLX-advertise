package com.zensar.advertise;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class OlxAdvertiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxAdvertiseApplication.class, args);
	}
	@Bean
	public Docket get()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.zensar"))
				.build().apiInfo(getApiInfo());
	}
	
	@SuppressWarnings("rawtypes")
	private ApiInfo getApiInfo() 
	{
		return new ApiInfo(
				"OLX Advertisement API Documentation", 
				"OLX Advertise", 
				"1.0.0", 
				"https://zensar.com"
				,new  Contact("Sagar Lavale", "http://gpl.com", "sagar.lavale@zensar.com"),
				"GPL", 
				"http://gpllicense.com",
				new ArrayList<VendorExtension>()
				);
	}
}
