package com.erp.controle.financeiro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //metodo para especificar caminhos que seram permitidos
    private static final String[] PUBLIC_MATCHERS = {
            //para liberar acesso a paginacao e metodos pos caminho /usuario , digite **
            "/usuarios/**",
            "/fornecedores/**",
    };

    private static final String[] PUBLIC_MATCHERS_GET = {

    };

    //sobrescrevendo um metodo do framework para conceder permissoes
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //desabilite cors, csrf que armazena sessão. pois como nosso sistema é stateless não armazena sessão, então não é necessário
        http.cors().and().csrf().disable();
        //concede permissão para todos caminhos contidos no putodo PUBLIC_MATCHRS
        http.authorizeHttpRequests()
                //permissão somente para metodos get
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                //permissão total
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                //para o restante exige autenticação
                .anyRequest().authenticated();
        //configuração para assegurar que o sistema não vai criar sessão
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    //libera acesso cors de multiplas fontes. Implemente no metodo configure
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.applyPermitDefaultValues();
        config.addAllowedMethod(HttpMethod.PUT);  // Adiciona suporte para o método PUT
        source.registerCorsConfiguration("/**", config);
        return source;
    }


    //metodo para encriptar senhas no sistema
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}