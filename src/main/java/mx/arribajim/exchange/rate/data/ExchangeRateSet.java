package mx.arribajim.exchange.rate.data;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class ExchangeRateSet {
	protected BigInteger id;
	protected Timestamp timestampQry;
	protected String base;
	protected Date dateLatest;
	
	protected ArrayList<ExchangeRate> exchangeRateList;
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public Timestamp getTimestampQry() {
		return timestampQry;
	}
	public void setTimestampQry(Timestamp timestampQry) {
		this.timestampQry = timestampQry;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) throws Exception {
		if(base!=null && base.length()==3) {
			this.base = base.trim().toUpperCase();
		}else {
			throw new Exception("Base value doesn't apply "+base);
		}
	}
	public Date getDateLatest() {
		return dateLatest;
	}
	public void setDateLatest(Date dateLatest) {
		this.dateLatest = dateLatest;
	}
	
	public ArrayList<ExchangeRate> getExchangeRateList() {
		return exchangeRateList;
	}
	public void setExchangeRateList(ArrayList<ExchangeRate> exchangeRateList) {
		this.exchangeRateList = exchangeRateList;
	}
	@Override
	public String toString() {
		return "ExchangeRateSet [id=" + id + ", timestampQry=" + timestampQry + ", base=" + base + ", dateLatest="
				+ dateLatest + ", exchangeRateList=" + exchangeRateList + "]";
	}
	
}
