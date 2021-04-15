package mx.arribajim.exchange.rate;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.arribajim.exchange.rate.data.ExchangeRate;
import mx.arribajim.exchange.rate.data.ExchangeRateSet;
import mx.arribajim.exchange.rate.data.JdbcExchangeRateRepository;

@Slf4j
@Service
public class ProcessJsonBO {
	@Autowired
	private EncryptUtil encryptUtil;
	@Autowired
	private JdbcExchangeRateRepository repository;

	public void saveFixerLatest() throws Exception, IOException {
		String url = encryptUtil.getProperty("cryp.url.fixer.io");
		JSONObject res = ParceUrl.readJsonFromUrl(url);//todo add timeout and try aiagn
		
		if(res.getBoolean("success")){
			log.info("Saving from fixer with date " + res.getString("date"));
			ExchangeRateSet ers = new ExchangeRateSet();
			ers.setBase(res.getString("base"));
			ers.setTimestampQry(new Timestamp(res.getLong("timestamp")*1000));	//TODO move to miliseconds		
			ers.setDateLatest(Calendar.getInstance().getTime());
			JSONObject rates = res.getJSONObject("rates");
			ArrayList<ExchangeRate> ratesList= new ArrayList<ExchangeRate>();
			rates.toMap().forEach( (k, v) ->{
				log.debug(String.format("key %s, value %s", k,v));							
				try {
					ExchangeRate bean = new ExchangeRate();
					bean.setCurrency(k);
					bean.setRateCurrency(new BigDecimal(v.toString()));
					ratesList.add(bean);
				} catch (Exception e) {log.error("ExchangeRate fail the values are "+String.format("key %s, value %s", k,v), e);}
				
			});
			
			ers.setExchangeRateList(ratesList);
			
			log.debug("Saving Exchange Rate "+ers.toString());			
			repository.save(ers);
		}else {
			throw new Exception("Tag success is false, check this call");
		}	
	}

	

}
