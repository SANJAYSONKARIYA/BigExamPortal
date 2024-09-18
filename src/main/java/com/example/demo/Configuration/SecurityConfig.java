package com.example.demo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

 @Autowired
 CustomUserDetailsService customUserDetailsService;

 @Bean
 public static PasswordEncoder passwordEncoder() {
  return new BCryptPasswordEncoder();
 }

 @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     http.csrf().disable()
         .authorizeHttpRequests(auth -> auth
             // Permit access to static resources
             .requestMatchers("/assets/css/**", "/assets/img/**", "/assets/js/**", "/assets/vendor/**").permitAll()
             
             // Permit access to certain HTML pages and API endpoints
             .requestMatchers("/exam.html", "/add-question.html", "/Answer.html", "/submit-answers", "/api/questions", "/api/questions/**",
                              "/api", "/submitAnswers", "/api/submit-answers", "/api/answers/**", "/", "/about", "/team", "/contact/**",
                              "/feedback/**", "/events", "/footer", "/partner", "/register", "/home").permitAll()

             // Require authentication for all other requests
             .anyRequest().authenticated()
         )
         .formLogin(form -> form
             .loginPage("/login")
             .loginProcessingUrl("/login")
             .defaultSuccessUrl("/home", true)
             .permitAll()
         )
         .logout(logout -> logout
             .invalidateHttpSession(true)
             .clearAuthentication(true)
             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
             .logoutSuccessUrl("/login?logout")
             .permitAll()
         );
     
     return http.build();
 }

 
@Bean
WebSecurityCustomizer customizeWebSecurity() {
	
	return (web) -> web.ignoring().requestMatchers("/assets/css/**", "/assets/img/**", "/assets/js/**", "/assets/vendor/**");
}
 
 
 
 
 

 @Autowired
 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
  auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

 }
 
 
 
}