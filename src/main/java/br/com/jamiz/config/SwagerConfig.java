package br.com.jamiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwagerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false) //
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.jamiz.rest.controller")) //todas as apis que estiverem dentro deste pacote, serao escaneadas
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
     }

    private ApiInfo apiInfo(){ //vai pra dentro do Docket
        return new ApiInfoBuilder()
                .title("Vendas API")
                .description("Api do projeto de vendas")
                .version("1.0")
                .contact(contact())
                .build();
    }

    @Bean
    public Contact contact(){
        return new Contact("Joao", "https://github.com/JamizM", "joao.antonnio.m2005@gmail.com");
    }
}
