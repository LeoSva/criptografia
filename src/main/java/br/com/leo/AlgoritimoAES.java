package br.com.leo;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *Simples exemplo de criptografia com agoritimo AES 
 */
public class AlgoritimoAES {

	static String texto = "palavra para criptogragar";
	static String chaveParaGerarSecretKey = "chave123546";

	public static void main(String[] args) {

		try {

			System.out.println("Texto Puro    : " + texto);

			byte[] textoCriptografado = criptografar(texto, chaveParaGerarSecretKey);

			System.out.print("Texto Encriptado: ");

			for (int i=0; i<textoCriptografado.length; i++) {
				System.out.print(new Integer(textoCriptografado[i])+" ");
			}

			System.out.println("");

			String textoDecriptado = descriptografar(textoCriptografado, chaveParaGerarSecretKey);

			System.out.println("Texto Decriptado: " + textoDecriptado);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] criptografar(String textoPuro, String chave) throws Exception {
		Cipher encripta = Cipher.getInstance("AES");
		SecretKeySpec secretKey = new SecretKeySpec(chave.getBytes("UTF-8"), "AES");
		encripta.init(Cipher.ENCRYPT_MODE, secretKey);
		return encripta.doFinal(textoPuro.getBytes("UTF-8")); 
	}

	public static String descriptografar(byte[] textoCriptografado, String chave) throws Exception{
		Cipher decripta = Cipher.getInstance("AES");
		SecretKeySpec secretKey = new SecretKeySpec(chave.getBytes("UTF-8"), "AES");
		decripta.init(Cipher.DECRYPT_MODE, secretKey);
		return new String(decripta.doFinal(textoCriptografado),"UTF-8");
	}

}

