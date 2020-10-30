package org.sid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableHystrix
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}
	
//	@Bean
	//RouteLocator staticRoutes(RouteLocatorBuilder builder) {
	//	return builder.routes()
	//			.route(r->r.path("/customers/**").uri("lb://CUSTOMER-SERVICE").id("r1"))
	//			.route(r->r.path("/products/**").uri("lb://INVENTORY-SERVICE").id("r2"))
	//			.build();
	//}
	
	@Bean
	RouteLocator staticRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r->r
					.path("/publicCountries/**")
					.filters(f->f
							.addRequestHeader("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
							.addRequestHeader("x-rapidapi-key", "1533b9188bmsh9905c03fb4b5688p1d40b7jsne1b8b164b90d")
							.rewritePath("/publicCountries/(?<segment>.*)", "/${segment}")
							.hystrix(h->h.setName("countries").setFallbackUri("forward:/defaultCountries"))
							)
					.uri("https://restcountries-v1.p.rapidapi.com").id("r1"))
				.build();
	} 

 	//@Bean
	DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc,DiscoveryLocatorProperties dlp) {
		return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
	}
}
