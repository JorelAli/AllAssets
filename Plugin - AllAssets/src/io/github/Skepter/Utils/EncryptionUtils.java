package io.github.Skepter.Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtils {

	final private String IV = "SqkZQJd3xO6z5vc4";
	private String encryptionKey = "8czoa6Ytk0uPYP6G";
	private String s = "";
	public byte[] cipherText = null;;

	public EncryptionUtils(final String key, final String stringToEncrypt, final byte[] bytesToDecrypt) {
		if (encryptionKey != null)
			encryptionKey = key;
		s = stringToEncrypt;
		cipherText = bytesToDecrypt;
	}

	public byte[] encrypt() throws Exception {
		if (!((s.length() % 16) == 0)) {
			final int amountToAdd = (16 - (s.length() % 16));
			String spaces = "";
			for (int i = 0; i < amountToAdd; i++)
				spaces = spaces + " ";
			s = s + spaces;
		}
		final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		final SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		cipherText = cipher.doFinal(s.getBytes("UTF-8"));
		return cipherText;
	}

	public String decrypt() throws Exception {
		final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		final SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		return new String(cipher.doFinal(cipherText), "UTF-8").trim();
	}
}