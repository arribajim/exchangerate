package mx.arribajim.exchange.rate.data;

import java.math.BigInteger;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;


@Repository
public class JdbcExchangeRateRepository implements ExchangeRateRepository {
	
	private SimpleJdbcInsert inserter;
	private SimpleJdbcInsert inserterResult;
	private JdbcTemplate jdbc;
	private ObjectMapper objectMapper;

	@Autowired
	public JdbcExchangeRateRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
		this.inserter = new SimpleJdbcInsert(jdbc).withTableName("mx_exchange_rate_set").usingGeneratedKeyColumns("id");
		this.inserterResult = new SimpleJdbcInsert(jdbc).withTableName("mx_exchange_rate");
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public void save(ExchangeRateSet ent) {	
			@SuppressWarnings("unchecked")
			Map<String, Object> values = objectMapper.convertValue(ent, Map.class);						
			//values.put("id", ent.getId());
			values.put("timestamp_qry", ent.getTimestampQry());
			values.put("base", ent.getBase());
			values.put("date_latest", ent.getDateLatest());
			
			Number idExchangeRateSet= inserter.executeAndReturnKey(values);
			
			
			for(ExchangeRate er:ent.getExchangeRateList()) {				
				er.setIdExchangeRate(BigInteger.valueOf(idExchangeRateSet.longValue()));
				save(er);
			}
			
			

	}

	private void save(ExchangeRate er) {
		@SuppressWarnings("unchecked")
		Map<String, Object> valuesResult = objectMapper.convertValue(er, Map.class);
		
		valuesResult.put("currency", er.getCurrency());
		valuesResult.put("rate_currency", er.rateCurrency);
		valuesResult.put("id_exchange_rate",er.getIdExchangeRate());
		
		inserterResult.execute(valuesResult);
		
	}

	
}
