package mx.arribajim.exchange.rate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan({"mx.arribajim.exchange.rate"})
@EnableJpaRepositories({"mx.arribajim.exchange.rate.data"})
@EnableScheduling
@EnableRetry
@SpringBootApplication
public class ExchangerateApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ExchangerateApplication.class, args);
		 
	}
	    
}
