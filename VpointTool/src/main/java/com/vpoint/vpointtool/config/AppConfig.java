package com.vpoint.vpointtool.config;


import com.vpoint.vpointtool.config.custom.CustomAccessDeniedHandler;
import com.vpoint.vpointtool.config.custom.JwtAuthenticationFilter;
import com.vpoint.vpointtool.config.custom.RestAuthenticationEntryPoint;
import com.vpoint.vpointtool.services.appUser.AppUserService;
import com.vpoint.vpointtool.services.appUser.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {


    private IAppUserService appUserService;

    @Autowired
    public void CircularDependencyA(@Lazy AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }


    @Bean
    public PasswordEncoder passwordEncoder() { //bean mã hóa pass
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //lấy user từ DB
        auth.userDetailsService(appUserService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/**");
        http.httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint());
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .and().authorizeRequests().antMatchers("/register").hasAnyRole("ADMIN")
                .and().authorizeRequests().antMatchers("/api/mark/**").authenticated()
                .and().authorizeRequests().antMatchers("/getAllRole").hasAnyRole("ADMIN")
                .and().authorizeRequests().antMatchers("/getAllUser").hasAnyRole("ADMIN")
                .and().authorizeRequests().antMatchers("/getAllDepartment").hasAnyRole("ADMIN")
                .and().authorizeRequests().antMatchers("/update/**").hasAnyRole("ADMIN")
                .and().authorizeRequests().antMatchers("/myVpoint**").hasAnyRole("ADMIN")
                .and().authorizeRequests().antMatchers("/api/mark/**").hasAnyRole("ADMIN")
                .and().authorizeRequests().antMatchers("/userChangePassword/**").authenticated()
                .and().authorizeRequests().antMatchers("/adminChangePassword/**").hasAnyRole("ADMIN")
                .and().authorizeRequests().antMatchers("/api/mark/**").authenticated()
                .and().csrf().disable();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
    }
}
