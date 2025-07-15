package com.nic.master.config;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI openApi(){
		
		return new OpenAPI()
				.info(new Info()
				.title("MST Service")
				.description("Api for managing Mst Service")
				.version("1.0.0"));
	}

}
