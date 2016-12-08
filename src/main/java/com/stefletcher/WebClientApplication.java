package com.stefletcher;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
public class WebClientApplication extends SpringBootServletInitializer
{

	@Bean
	public ServletRegistrationBean dummyServlet(CMDBRepository cmdbRepository) {
		ServletRegistrationBean dummyServletRegistrationBean = new ServletRegistrationBean(
				new DummyServlet(cmdbRepository), "/dummy/*");
		return dummyServletRegistrationBean;
	}

//	@Autowired
//	private DummyServlet dummyServlet;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebClientApplication.class);
	}
	@Bean
	public FilterRegistrationBean dummyServlet1Registration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new DummyServletFilter());
		registration.setOrder(1);
		return registration;
	}
	@Bean
	public FilterRegistrationBean dummyServlet2Registration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new DummyServletFilter2());
		registration.setOrder(2);
		return registration;
	}

	@Bean
	CommandLineRunner commandLineRunner(CMDBRepository cmdbRepository){
		return strings -> {
			Stream.of(new ServiceDefinition("serviceA", "online"),
					new ServiceDefinition("serviceB", "branch"))
			.forEach( n-> cmdbRepository.save(n));
			cmdbRepository.findAll().forEach(System.out::println);
		};
	}


	public static void main(String[] args) {
		SpringApplication.run(WebClientApplication.class, args);
	}
}
