package mx.arribajim.exchange.rate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@EnableAsync
@Slf4j
@Service
public class ScheduledExchangeRate {
	@Autowired
	private ProcessJsonBO procJson;
	
	@Async
	@Scheduled(cron = "${cron.expression.exrate}")
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        log.info("Fixed rate task async - " + System.currentTimeMillis() / 1000);
        try {
			procJson.saveFixerLatest();
		} catch (Exception e) {
			log.error("cannot run save info exception", e);
		}
        
    }
}
