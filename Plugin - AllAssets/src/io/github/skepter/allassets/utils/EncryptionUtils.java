/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter 
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.utils;

import io.github.skepter.allassets.utils.Files.Directory;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.FileUtils;

import java.io.File;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtils {

	private String IV;
	private String encryptionKey;

	/** Creates a new Encryption utility instance using a custom key.
	 *
	 * @param key - The key to use */
	public EncryptionUtils(final String key) {
		encryptionKey = makeCompatible(key);
		final File file = new File(Files.getDirectory(Directory.STORAGE), "Encryption.bin");
		if (file.exists())
			try {
				IV = String.valueOf(FileUtils.loadStringSecurely(file));
				return;
			} catch (final Exception e) {
				ErrorUtils.printErrorToConsole("Error loading Encryption system");
			}
		else
			try {
				file.createNewFile();
				IV = generateNewRandomString();
				FileUtils.saveStringSecurely(IV, file);
			} catch (final Exception e) {
				ErrorUtils.printErrorToConsole("Error saving Encryption system");
			}
	}

	public String getKey() {
		return encryptionKey;
	}

	private String generateNewRandomString() {
		final char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		final StringBuilder sb = new StringBuilder();
		final Random random = new Random();
		for (int i = 0; i < 16; i++) {
			final char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	/** Encrypts a String into a byte[]
	 *
	 * @param stringToEncrypt - The string to encrypt
	 * @return byte[] with encrypted data */
	public byte[] encrypt(String stringToEncrypt) {
		stringToEncrypt = makeCompatible(stringToEncrypt);
		try {
			final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
			final SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
			return cipher.doFinal(stringToEncrypt.getBytes("UTF-8"));
		} catch (final Exception e) {
			e.printStackTrace();
			return stringToEncrypt.getBytes();
		}
	}

	public String makeCompatible(String string) {
		if (!((string.length() % 16) == 0)) {
			final int amountToAdd = (16 - (string.length() % 16));
			String spaces = "";
			for (int i = 0; i < amountToAdd; i++)
				spaces = spaces + " ";
			string = string + spaces;
		}
		return string;
	}

	/** Decrypts a byte[] into its original string form
	 *
	 * @param bytesToDecrypt - The byte[] with the encrypted data
	 * @return String with decrypted data */
	public String decrypt(final byte[] bytesToDecrypt) {
		try {
			final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
			final SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
			return new String(cipher.doFinal(bytesToDecrypt), "UTF-8").trim();
		} catch (final Exception e) {
			return new String(bytesToDecrypt);
		}
	}
}
