package guru.springframework.msscbeerservice.config;

import feign.auth.BasicAuthRequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FeignClientConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(@Value("${sfg.brewery.inventory-user}") String inventoryUser,
                                                                   @Value("${sfg.brewery.inventory-pwd}") String inventoryPwd) {
        log.info("Provided credentials to access inventory service, user = {}, pwd = {}", inventoryUser, inventoryPwd);
        return new BasicAuthRequestInterceptor(inventoryUser, inventoryPwd);
    }
}
