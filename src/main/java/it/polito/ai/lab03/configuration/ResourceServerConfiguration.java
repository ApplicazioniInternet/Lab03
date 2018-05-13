package it.polito.ai.lab03.configuration;

import it.polito.ai.lab03.utils.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private static final String TAG = "ResourceServerConfiguration";

    //@Autowired
    //private CustomAccessTokenConverter customAccessTokenConverter;

    /*@Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setAccessTokenConverter(customAccessTokenConverter);
    }*/

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenServices(tokenServices());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(Constants.SIMMETRIC_KEY); // La stessa che abbiamo messo per oauth
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .and()
                .requestMatchers()
                .antMatchers(Constants.SECURE_GENERAL) // Tutti i path "/secured"
                .and().authorizeRequests()
                .antMatchers(HttpMethod.POST, Constants.SECURED_USER_PATTERN).access(Constants.SECURED_WRITE_SCOPE_USER)
                .antMatchers(HttpMethod.GET, Constants.SECURED_USER_PATTERN).access(Constants.SECURED_READ_SCOPE_USER)
                .antMatchers(HttpMethod.POST, Constants.SECURED_CUSTOMER_PATTERN).access(Constants.SECURED_WRITE_SCOPE_CUSTOMER)
                .antMatchers(HttpMethod.GET, Constants.SECURED_CUSTOMER_PATTERN).access(Constants.SECURED_READ_SCOPE_CUSTOMER)
                .antMatchers(HttpMethod.POST, Constants.SECURED_ADMIN_PATTERN).access(Constants.SECURED_WRITE_SCOPE_ADMIN)
                .antMatchers(HttpMethod.GET, Constants.SECURED_ADMIN_PATTERN).access(Constants.SECURED_READ_SCOPE_ADMIN)
                .anyRequest()
                .access(Constants.SECURED_READ_SCOPE);
    }
}
