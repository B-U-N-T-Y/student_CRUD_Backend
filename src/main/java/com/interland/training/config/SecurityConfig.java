package com.interland.training.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.cors(AbstractHttpConfigurer::disable);
		http.csrf(AbstractHttpConfigurer::disable);
		
        http.authorizeHttpRequests((authz) ->
                authz.requestMatchers(HttpMethod.GET, "/student/search").authenticated()
                .requestMatchers(HttpMethod.POST,"/student/getaccesstoken").permitAll()
                .requestMatchers(HttpMethod.POST, "/student/add").authenticated()
                .requestMatchers(HttpMethod.GET,"/student/get").authenticated()
                .requestMatchers(HttpMethod.PUT, "/student/update/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/student/delete").authenticated()
                .anyRequest().permitAll());
        http.sessionManagement(sess -> sess.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));
        http.oauth2ResourceServer(authServer->authServer.jwt(Customizer.withDefaults()));
        return http.build();
    }
}
