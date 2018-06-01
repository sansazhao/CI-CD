package bookstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
//public class CorsConfig extends WebMvcConfigurerAdapter{
    //@Bean
    //CorsConfiguration createConfig(){
      //  CorsConfiguration corsConfiguration = new CorsConfiguration();
       // corsConfiguration.addAllowedHeader("*");
        //corsConfiguration.addAllowedOrigin("*");
       // corsConfiguration.addAllowedMethod("*");
        //corsConfiguration.setAllowCredentials(true);
        //System.out.println("userd cors");
        //return corsConfiguration;
   // }

   // @Bean
    //public CorsFilter corsFilter(){
      //  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       // source.registerCorsConfiguration("/**", createConfig());
        //return new CorsFilter(source);
   // }

//}
