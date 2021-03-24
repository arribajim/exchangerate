package mx.arribajim.exchange.rate;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EncryptUtil {
	@Autowired
	private Environment env;
	
	public String getProperty(String propertyName) {
		String returnValue = "No value";

		String keyValue = env.getProperty(propertyName);

		if (keyValue != null && !keyValue.isEmpty()) {
			if(propertyName.startsWith("cryp.")) {				
				returnValue= stringEncryptor().decrypt(keyValue);
			}else {
				returnValue = keyValue;
			}
			
		}
		return returnValue;
	}

	@Bean(name = "encryptorBean")
	public StringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();		
		config.setPassword(System.getProperty("jasypt.encryptor.password"));
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		return encryptor;
	}

	public void encryptString(String string) {
		String domain = stringEncryptor().encrypt(string);		
		log.info(domain);
		log.info(stringEncryptor().decrypt(domain));		
	}
}
