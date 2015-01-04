/*******************************************************************************
 * Skepter's licence
 * Copyright 2014 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. I've just made life easier for you :)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Utils;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtils {

	private String IV = "SqkZQJd3xO6z5vc4";
	private String encryptionKey = "8czoa6Ytk0uPYP6G";

	/** Creates a new Encryption utility instance. The key is predefined. */
	public EncryptionUtils() {
		new EncryptionUtils(encryptionKey, IV);
	}

	/** Creates a new Encryption utility instance using a custom key.
	 * 
	 * @param key - The key to use */
	public EncryptionUtils(final String key) {
		new EncryptionUtils(key, IV);
	}

	/** Creates a new Encryption utility instance using a custom key and IV.
	 * 
	 * @param key - The key to use
	 * @param IV - The Initialisation Vector to use */
	public EncryptionUtils(final String key, final String IV) {
		if (key != null)
			encryptionKey = key;
		if (IV != null)
			this.IV = IV;
	}

	/** Generates a new random key.
	 * 
	 * @return The new random key */
	public String generateNewKey() {
		encryptionKey = generateNewRandomString();
		return encryptionKey;
	}

	/** Generates a new Initialisation Vector. Not to be used often!
	 * 
	 * @return The new Initialisation Vector */
	public String generateNewIV() {
		IV = generateNewRandomString();
		return IV;
	}

	private String generateNewRandomString() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 16; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	/** Encrypts a String into a byte[]
	 * 
	 * @param stringToEncrypt - The string to encrypt
	 * @return byte[] with encrypted data */
	public byte[] encrypt(String stringToEncrypt) {
		if (!((stringToEncrypt.length() % 16) == 0)) {
			final int amountToAdd = (16 - (stringToEncrypt.length() % 16));
			String spaces = "";
			for (int i = 0; i < amountToAdd; i++)
				spaces = spaces + " ";
			stringToEncrypt = stringToEncrypt + spaces;
		}
		try {
			final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
			final SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
			return cipher.doFinal(stringToEncrypt.getBytes("UTF-8"));
		} catch (Exception e) {
			return stringToEncrypt.getBytes();
		}
	}

	/**
	 * Decrypts a byte[] into its original string form
	 * @param bytesToDecrypt - The byte[] with the encrypted data
	 * @return String with decrypted data
	 */
	public String decrypt(byte[] bytesToDecrypt) {
		try {
			final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
			final SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
			return new String(cipher.doFinal(bytesToDecrypt), "UTF-8").trim();
		} catch (Exception e) {
			return new String(bytesToDecrypt);
		}
	}
}