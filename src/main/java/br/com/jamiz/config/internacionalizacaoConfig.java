package br.com.jamiz.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class internacionalizacaoConfig {

    //usado para o spring fazer a leitura do textos no arquivo messages.properties
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); //nao é necessario colocar o .propoerties do arquivo
        messageSource.setDefaultEncoding("ISO-8859-1");//em que codificao esta as nossas mensagems UTF-8
        messageSource.setDefaultLocale(Locale.getDefault());// ira pegar a codificao padrao aonde esta rodando as mensagens, Exemplo no Brasil
        return messageSource;
    }

    //Objeto resolsavel por fazer a interpolacao nas frases
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
