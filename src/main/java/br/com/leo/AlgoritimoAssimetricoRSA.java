package br.com.leo;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class AlgoritimoAssimetricoRSA {

	/**
	 * Criptografa o texto puro usando chave pública.
	 */
	public static byte[] criptografa(String texto, PublicKey chave) {
		byte[] cipherText = null;

		try {
			final Cipher cipher = Cipher.getInstance("RSA");
			// Criptografa o texto puro usando a chave Púlica
			cipher.init(Cipher.ENCRYPT_MODE, chave);
			cipherText = cipher.doFinal(texto.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cipherText;
	}

	/**
	 * Decriptografa o texto puro usando chave privada.
	 */
	public static String decriptografa(byte[] texto, PrivateKey chave) {
		byte[] dectyptedText = null;

		try {
			final Cipher cipher = Cipher.getInstance("RSA");
			// Decriptografa o texto puro usando a chave Privada
			cipher.init(Cipher.DECRYPT_MODE, chave);
			dectyptedText = cipher.doFinal(texto);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new String(dectyptedText);
	}

	/**
	 * Testa o Algoritmo
	 */
	public static void main(String[] args) {
		executarComChaveExistente();
	}

	/**
	 * Faz o processo de criptografia usando chaves geradas e armazenadas em Base64
	 */
	private static void executarComChaveExistente() {
		try {
			String publicKeyBase64 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApuds0U1gGN0CD901LiFmv9iow+0e0fDriX8rPe0eu7otPyAmS2p/SEXS35fUMD33ByBkIJzOOvMtmvY6zv9lmKCnCXGC+otS74uFig0EQqafOXgaZHzc1CzIZXdgBhzDxYpDSWszxIuDwwqYArZYfH4M42wkBcv+3dhLQBF6v4OjDHxkkWlD/dud8e0lgqI9A3AUll+3sFG4VzdkNKD0I6ImUMDgJ8xIGaXNfsLpSmnOEPvBK2Be/6xbZqrRWaWSSbhusIlyRmHKzMKzDsOzbmYj6O2OlZcPB6U4PkwCZ5fP3PS/Wnl9HnRoWlZ+hbHpGsvx6VeYNq9b0sEsQ/9QeQIDAQAB";
			String privateKeyBase64 = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCm52zRTWAY3QIP3TUuIWa/2KjD7R7R8OuJfys97R67ui0/ICZLan9IRdLfl9QwPfcHIGQgnM468y2a9jrO/2WYoKcJcYL6i1Lvi4WKDQRCpp85eBpkfNzULMhld2AGHMPFikNJazPEi4PDCpgCtlh8fgzjbCQFy/7d2EtAEXq/g6MMfGSRaUP9253x7SWCoj0DcBSWX7ewUbhXN2Q0oPQjoiZQwOAnzEgZpc1+wulKac4Q+8ErYF7/rFtmqtFZpZJJuG6wiXJGYcrMwrMOw7NuZiPo7Y6Vlw8HpTg+TAJnl8/c9L9aeX0edGhaVn6Fsekay/HpV5g2r1vSwSxD/1B5AgMBAAECggEBAInUqcd613YQ5UgGjIlI6hpaKJyMMBx9kXVdwG8GZ4/t4rmzqgwzqWMczlT5hDBGq76F4EoSVDjYY3wKBWy1+sd9PBQPWnPfJKFPnfrwXulPwkPqTBlaB99sEB3C5FiH3qA+WuzBi+GuuSQTlo7+2LoOOkSBJIpo+VNo8FO7W5vVGBoeD1GWuyvP2OYad8fV5UeKByjpxUR2zExYcVOtGbnQBrZQTREZroizqPvuT1aoYupGRuYUKvIsjsvcgD8cJdVtY0bAsOIHhmj/7esxismLwoHU/M+GdWuiO/HLckfkr5Mw9VnoxPCguxKxWSCzUpDh6B6dm48X2/W6GC3WEkUCgYEA7DI7ClX/Tu6dWYCP+3HwQgW1R6tiizAURPwAYaFulGBV1xqgMiweW2NEUZWxjTXCzQZ9Wtad82MeG7C+KyimkRCeXL79YZiV5+b2/+qyMkHPlbYevhEjvYnKIaigLvywfvSGATsBKu2QsPGsymrbadLfbOz3SfVke4ezJmjkl+MCgYEAtOXkArFpahhJhFodERREnUGvqZMYAwVuWhi/hzn9L7vCaLvSAFoW6DstuTvhudPXXMFcMMZVpo/KljHPZcEnn23sGp4yjIeOWFg2JH0bPA1conbeFv+xandhwxfAwtdxCpcZmi8WnuXWga6QHUPeppP32SC7vQ4oXWI8qGSxjPMCgYAg6W7opcsJiJ5MVmm5s8qyqHD2J593nZ91bVfwQrpR2oqbio4/733bZh2i8KTo9FDMmNbmzXj87RbI7PvSr7tmA1bNvZ849jYNNlW8m0txTVJLnjDycxjz1DtiLUkEwxkk15XEniAvhyLeRQ66teEVAdk4KFzc5WC3BdWkyIDx2QKBgQCzFDoKaHMAspZkzYik1bmvK82oQ5Oruf6qdoF0bvW0AV3NrNGRw52VvK/hBeTJv2sdydVn+4/i2colsZ2tD/AXpgvrtdw4viEuV/URXkBNzLR9qMrnXazWxuQi7CrJN9oSL88Kkb8FAma97n+AWSXbJfRgIEV6PL+84nlGApi5/QKBgGe5Ej1bsPG3f3tOKQfYGx+VQrSXwcuDBG+od+Wc5TVNUA05g8d2m+UO0hpWtgJD5jxUGgLVWDn1e7NgyluQQpjXTEoxwlweRXxjrY10+IpZDt1FBQOzxPXHs/AJG+w5y8FhtDmLwsJ5hu58StxDh6E4W5/PDKWk1g4SnoJ6pYO1";

			PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyBase64.getBytes(StandardCharsets.UTF_8))));
			PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyBase64)));

			final String msgOriginal = "Exemplo de mensagem";

			// Criptografa a Mensagem usando a Chave Pública
			final byte[] textoCriptografado = criptografa(msgOriginal, publicKey);

			// Decriptografa a Mensagem usando a Chave Pirvada
			final String textoPuro = decriptografa(textoCriptografado, privateKey);

			System.out.println("Mensagem Original: " + msgOriginal);
			System.out.println("Mensagem Criptografada: " + new String(Base64.getEncoder().encode(textoCriptografado), StandardCharsets.UTF_8));
			System.out.println("Tamanho da Mensagem Criptografada: " + textoCriptografado.length);
			System.out.println("Mensagem Decriptografada: " + textoPuro);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *Gera chave publica e privada usando algoritimo RSA 
	 * @throws NoSuchAlgorithmException 
	 */
	private static void gerarChave() throws NoSuchAlgorithmException {
		final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(2048);

		final KeyPair key = keyGen.generateKeyPair();

		PrivateKey privateKeyOrig = key.getPrivate();
		PublicKey publicKeyOrig = key.getPublic();

		String publicKeyAsBase64 = Base64.getEncoder().encodeToString(publicKeyOrig.getEncoded());
		String privateKeyAsBase64 = Base64.getEncoder().encodeToString(privateKeyOrig.getEncoded());

		System.out.println( "chave publica: " + publicKeyAsBase64);
		System.out.println( "chave privada: " + privateKeyAsBase64);
	}

	/**
	 * Gera a chave que contém um par de chave Privada e Pública usando 1025 bytes.
	 * Armazena o conjunto de chaves nos arquivos private.key e public.key
	 */
	public static void gerarChaveParaArquivo() {
		try {
			final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(2048);
			final KeyPair key = keyGen.generateKeyPair();

			File chavePrivadaFile = new File("/home/s2it_lsilva/privateKey.txt");
			File chavePublicaFile = new File("/home/s2it_lsilva/publicKey.txt");

			// Cria os arquivos para armazenar a chave Privada e a chave Publica
			if (chavePrivadaFile.getParentFile() != null) {
				chavePrivadaFile.getParentFile().mkdirs();
			}

			chavePrivadaFile.createNewFile();

			if (chavePublicaFile.getParentFile() != null) {
				chavePublicaFile.getParentFile().mkdirs();
			}

			chavePublicaFile.createNewFile();

			FileOutputStream chavePublicaOS = new FileOutputStream(chavePublicaFile);
			chavePublicaOS.write(Base64.getEncoder().encode(key.getPublic().getEncoded()));
			chavePublicaOS.flush();
			chavePublicaOS.close();

			FileOutputStream chavePrivadaOS = new FileOutputStream(chavePrivadaFile);
			chavePrivadaOS.write(Base64.getEncoder().encode(key.getPrivate().getEncoded()));
			chavePrivadaOS.flush();
			chavePrivadaOS.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}