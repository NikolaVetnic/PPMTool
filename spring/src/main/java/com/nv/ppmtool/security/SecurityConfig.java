package com.nv.ppmtool.security;

import com.nv.ppmtool.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.nv.ppmtool.security.SecurityConstants.H2_URL;
import static com.nv.ppmtool.security.SecurityConstants.SIGN_UP_URLS;

@Configuration
@EnableGlobalMethodSecurity(
        securedEnabled = true,  // if in the future method-level security is to be added
        jsr250Enabled = true,
        prePostEnabled = true
)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*
     * Basically a class that implement WebSecurityConfigurer interfac-
     * e. What do we get out of this? This provides default security c-
     * onfigurations, and by extending it this gives an option to cust-
     * omize this by reimplementing the methods. First thing we are go-
     * ing to work on is configure(HttpSecurity)...
     *
     * When JWTs are used they are passed back and forth and as a resu-
     * lt the server doesn't have to save sessions, and that is why the
     * SessionCreationPolicy.STATELESS is used. In our case here the R-
     * edux is taking care of the session.
     */

    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired private JWTAuthenticationEntryPoint unauthorizedHandler;
    @Autowired private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().frameOptions().sameOrigin()        // to enable H2 database
                .and().authorizeRequests()
                .antMatchers(
                        "/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()                                       // in case admin console is present in future
                .antMatchers(SIGN_UP_URLS).permitAll()
                .antMatchers(H2_URL).permitAll()
                .anyRequest().authenticated();
    }
}
