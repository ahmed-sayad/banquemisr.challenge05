package banquemisr.challenge05.task_mangment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity(debug = true)
public class SecurityConfig {
	
	
	@Autowired
    private UserDetailsServicesImpl userDetailsService;
	
	@Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }
	
	@Bean
    public AuthTokenFilter authenticationJwTokenFilter() {
        return new AuthTokenFilter();
    }
	
	 @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

	        authProvider.setUserDetailsService(userDetailsService);
	        authProvider.setPasswordEncoder(passwordEncoder());

	        return authProvider;
	    }
	
	 @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	 
	 @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
	 
	 @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
                 .csrf(csrf -> csrf.disable())
                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .authorizeHttpRequests(auth ->{
                         auth.requestMatchers("/").permitAll();
                         auth.requestMatchers("tasks/").permitAll();
                         auth.requestMatchers("tasks/all").permitAll();       //could be authenticated to ADMIN
                         auth.requestMatchers("tasks/add").permitAll();
                         auth.requestMatchers("tasks/delete/{id}").permitAll();
                         auth.requestMatchers("tasks/update/{id}").permitAll();
                         auth.requestMatchers("tasks/update_title/{id}").permitAll();
                         auth.requestMatchers("tasks/update_description/{id}").permitAll();
                         auth.requestMatchers("tasks/update_status/{id}").permitAll();
                         auth.requestMatchers("tasks/update_priority/{id}").permitAll();
                         auth.requestMatchers("tasks/update_dueAt/{id}").permitAll();
                         auth.requestMatchers("user/login").permitAll();
                         auth.requestMatchers("user/signup").permitAll();
                         auth.requestMatchers("history/all").permitAll();
                         auth.requestMatchers("history/add").permitAll();
                         auth.requestMatchers("history/delete/{id}").hasAnyRole("ADMIN"); 
                         auth.requestMatchers("notifiy/all").permitAll(); 
                         auth.requestMatchers("notifiy/add").hasAnyRole("ADMIN"); 
                         auth.requestMatchers("tasks/search**").permitAll();
                         auth.requestMatchers("history/search**").permitAll();
                         auth.requestMatchers("notifiy/search**").permitAll();
                         auth.requestMatchers("notifiy/send-email**").hasAnyRole("ADMIN"); 
                         auth.requestMatchers("/h2-console/**").permitAll(); 
                         auth.requestMatchers("/h2-console/login.do**").permitAll();
                         

                   
                 }
                 )
                 .authenticationProvider(authenticationProvider())
                 .addFilterBefore(authenticationJwTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        
    return http.build();
    }
	 	 
}
