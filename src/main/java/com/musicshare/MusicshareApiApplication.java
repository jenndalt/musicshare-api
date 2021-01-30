package com.musicshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MusicshareApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicshareApiApplication.class, args);
	}
	
	  @Bean
	    public Docket musicshareApi() {
	        return new Docket(DocumentationType.SWAGGER_2).select()
	                .apis(RequestHandlerSelectors.basePackage("com.musicshare")).build();
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder().title("Musicshare API Docs")
	                .description("Musicshare API documentation. Musicshare is playlist service where users can create music list")
	                .license("GPLv3")
	                .licenseUrl("https://www.gnu.org/licenses/gpl-3.0.en.html").version("1.0").build();
	    }

}
