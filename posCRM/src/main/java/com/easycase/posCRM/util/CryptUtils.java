package com.easycase.posCRM.util;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;

public class CryptUtils {

	@Value("wifi.pass")
	private String wifi;

	private static StringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("pass");
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		return encryptor;
	}

	public static String encrypt(String text) {
		StringEncryptor textEncryptor = stringEncryptor();
		String encryptedText = textEncryptor.encrypt(text);
		return encryptedText;
	}

	public static String decrypt(String text) {
		StringEncryptor textEncryptor = stringEncryptor();
		String decryptedText = textEncryptor.decrypt(text);
		return decryptedText;
	}

	public static void main(String[] args) {
//		UUID uuid=UUID.randomUUID(); 
//		System.out.println(uuid);
//		System.out.println(uuid.version());
//		System.out.println(uuid.toString());
		System.out.println(encrypt("200000"));
		//System.out.println(decrypt("r4N+aXSqLMkaKL3ePRLS7TfPa7zBNEiyQ6PlRfNqfFbZeaHRAnFTZBk8YD8ru9Y2i8pi2TVfG/A="));
		//stem.out.println(UUID.fromString("6232ca1d-0d2c-4449-a2fa-2865e41b0839").node());
	}

}
