package br.com.phc.citiesapi.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.phc.citiesapi.filter.TransactionFilter;

@Configuration
public class TransactionConfig {

	@Bean
	public FilterRegistrationBean<TransactionFilter> loggingFilter() {
		FilterRegistrationBean<TransactionFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new TransactionFilter());
		registrationBean.addUrlPatterns("/cities/*");
		registrationBean.addUrlPatterns("/states/*");
		registrationBean.addUrlPatterns("/countries/*");
		registrationBean.addUrlPatterns("/distances/*");

		return registrationBean;
	}
}
