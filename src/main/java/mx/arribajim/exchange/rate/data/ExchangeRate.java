package mx.arribajim.exchange.rate.data;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ExchangeRate {
	protected BigInteger id;
	protected String currency;
	protected BigDecimal rateCurrency;
	protected BigInteger idExchangeRate;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) throws Exception {
		if(currency!=null && currency.length()==3) {
			this.currency = currency.trim().toUpperCase();
		}else {
			throw new Exception("currency value doesn't apply "+currency);
		}		
	}
	public BigDecimal getRateCurrency() {
		return rateCurrency;
	}
	public void setRateCurrency(BigDecimal rateCurrency) {
		this.rateCurrency = rateCurrency;
	}
	public BigInteger getIdExchangeRate() {
		return idExchangeRate;
	}
	public void setIdExchangeRate(BigInteger idExchangeRate) {
		this.idExchangeRate = idExchangeRate;
	}
	@Override
	public String toString() {
		return "ExchangeRate [id=" + id + ", currency=" + currency + ", rateCurrency=" + rateCurrency
				+ ", idExchangeRate=" + idExchangeRate + "]";
	}
}
