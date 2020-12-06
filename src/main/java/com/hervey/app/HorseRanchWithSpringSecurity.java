package com.hervey.app;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AbstractAjpProtocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HorseRanchWithSpringSecurity {

	public static void main(String[] args) {
		SpringApplication.run(HorseRanchWithSpringSecurity.class, args);
	}

//	@Bean
//	public TomcatServletWebServerFactory servletContainer() {
//		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//		Connector ajpConnector = new Connector("AJP/1.3");
//		ajpConnector.setPort(9090);
//		ajpConnector.setSecure(false);
//		ajpConnector.setAllowTrace(false);
//		ajpConnector.setScheme("http");
//		
//		final AbstractAjpProtocol protocol = (AbstractAjpProtocol) ajpConnector.getProtocolHandler();
//		ajpConnector.setSecure(true);
//		protocol.setSecret("myAJPSecret-you-cant-guess-this");
//		tomcat.addAdditionalTomcatConnectors(ajpConnector);
//		return tomcat;
//	}

//    @Bean
//    public TomcatServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        Connector ajpConnector = new Connector("AJP/1.3");
//        ajpConnector.setPort(9090);
//        ajpConnector.setSecure(false);
//        ajpConnector.setAllowTrace(false);
//        ajpConnector.setScheme("http");
//        tomcat.addAdditionalTomcatConnectors(ajpConnector);
//        return tomcat;
//    }
//	
	
	
}
