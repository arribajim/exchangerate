package mx.arribajim.exchange.rate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class WebCustomPort implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory > {

	@Value("${local.server.port}")
	private String port;
	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		
		factory.setPort(new Integer(port));		
	}

}
