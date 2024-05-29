package chatop.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class ConfigurationSecurityApplication {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return
                httpSecurity
                        .cors(Customizer.withDefaults())
                        .csrf(Customizer.withDefaults())
                        .authorizeHttpRequests(
                                authorize -> authorize.requestMatchers(POST,"/auth/register").permitAll()
                                        .anyRequest().authenticated()
                        ).build();
    }
}
