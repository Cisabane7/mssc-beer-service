package guru.springframework.msscbeerservice.config;

import feign.auth.BasicAuthRequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
@Slf4j
public class FeignClientConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(@Value("${sfg.brewery.inventory-user}") String inventoryUser,
                                                                   @Value("${sfg.brewery.inventory-pwd}") String inventoryPwd) {
        if (Objects.isNull(inventoryUser) || Objects.isNull(inventoryPwd)) {
            log.warn("Please provide credentials to access inventory service");
        }
        return new BasicAuthRequestInterceptor(inventoryUser, inventoryPwd);
    }
}
