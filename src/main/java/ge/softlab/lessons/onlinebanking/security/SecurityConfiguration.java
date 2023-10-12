package ge.softlab.lessons.onlinebanking.security;

import ge.softlab.lessons.onlinebanking.repositories.UserRepository;
import ge.softlab.lessons.onlinebanking.repositories.UserRoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserRepository userRepository;
    private final UserRoleRepo userRoleRepo;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //@ formatter:off
		http
            .authorizeHttpRequests(
                (authorize) -> authorize
                    .requestMatchers("/libs/**", "/images/**").permitAll()
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll()
                    .requestMatchers("/files", "/files/**").permitAll()
                    .anyRequest().authenticated()
            )
            .httpBasic(withDefaults())
            .formLogin(withDefaults())
            .exceptionHandling( (c) -> c.authenticationEntryPoint(
                (request, response, authException) -> response.sendError(401, "Forbidden")
            ));

		//@ formatter:on
        http.csrf(AbstractHttpConfigurer::disable);
		return http.build();
	}


    // @formatter:off
    @Bean
    public ApplicationUserManager userDetailsService() {
        return new ApplicationUserManager(userRepository);
    }
    // @formatter:on

}
