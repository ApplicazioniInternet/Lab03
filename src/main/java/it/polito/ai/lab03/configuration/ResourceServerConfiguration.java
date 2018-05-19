package it.polito.ai.lab03.configuration;

import it.polito.ai.lab03.utils.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(Constants.SIMMETRIC_KEY);
        converter.setVerifierKey(Constants.SIMMETRIC_KEY);
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
        http.requestMatchers()
                .and().authorizeRequests()
                .antMatchers(Constants.SECURED_USER_PATTERN)
                .hasAnyAuthority(Constants.ROLE_USER, Constants.ROLE_CUSTOMER, Constants.ROLE_ADMIN)
                .antMatchers(Constants.SECURED_CUSTOMER_PATTERN)
                .hasAnyAuthority(Constants.ROLE_CUSTOMER, Constants.ROLE_ADMIN)
                .antMatchers(Constants.SECURED_ADMIN_PATTERN)
                .hasAuthority(Constants.ROLE_ADMIN);
    }
}
