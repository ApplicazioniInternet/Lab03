package it.polito.ai.lab03.configuration;

import it.polito.ai.lab03.utils.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "resource-server-rest-api";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
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
