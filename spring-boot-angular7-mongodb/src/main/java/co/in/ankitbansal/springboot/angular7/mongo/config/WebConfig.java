package co.in.ankitbansal.springboot.angular7.mongo.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.EnumSet;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by ankit on 3/4/17.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "co.in.ankitbansal.springboot.angular7.mongo.controller")
public class WebConfig implements WebMvcConfigurer {

  public static final String API_SERVICES_URL_PATTERN = "/api/services/*";
  public static final String CLASS_PATH = "classpath:/dist/";
  public static final String TEMPLATE_PATH = "classpath:/templates/";
  public static final String TEMPLATE_SUFFIX = ".html";
  @Value("${timezone}")
  private String timeZone;

  @Value("${cors.allowed.origins}")
  private String[] allowedOrigins;

  @Value("${cors.allowed.methods}")
  private String[] allowedMethods;

  @Value("${cors.exposed.headers}")
  private String[] exposedHeaders;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!registry.hasMappingForPattern("/dist/**")) {
      registry.addResourceHandler("/dist/**").addResourceLocations(CLASS_PATH);
    }
    if (!registry.hasMappingForPattern("/**")) {
      registry.addResourceHandler("/**").addResourceLocations(CLASS_PATH);
    }
    if (!registry.hasMappingForPattern("/asset/**")) {
      registry.addResourceHandler("/asset/**").addResourceLocations(CLASS_PATH);
    }

    if (!registry.hasMappingForPattern("/assets/**")) {
      registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/");
    }
  }

  @Bean
  public InternalResourceViewResolver internalViewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/dist/");
    viewResolver.setSuffix(".html");
    viewResolver.setOrder(2);
    return viewResolver;
  }


  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins(allowedOrigins)
        .allowedMethods(allowedMethods)
        .exposedHeaders(exposedHeaders)
        .maxAge(3600);
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters) {
    httpMessageConverters.add(mappingJackson2HttpMessageConverter());
  }

  @Bean
  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
    MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    objectMapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
    objectMapper.setTimeZone(TimeZone.getTimeZone(timeZone));
    objectMapper.registerModule(new JavaTimeModule());
    jsonConverter.setObjectMapper(objectMapper);
    return jsonConverter;
  }

  @Bean
  public FilterRegistrationBean headerFilter() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    // ????This only logs the headers of the request
    Filter dumperFilter = new RequestDumperFilter();
    registration.setFilter(dumperFilter);
    registration.addUrlPatterns(API_SERVICES_URL_PATTERN);
    registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
    return registration;
  }

}