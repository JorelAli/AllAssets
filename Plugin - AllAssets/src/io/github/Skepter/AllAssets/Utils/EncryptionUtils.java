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

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtils {

	final private String IV = "SqkZQJd3xO6z5vc4";
	private String encryptionKey = "8czoa6Ytk0uPYP6G";
	private String s = "";
	public byte[] cipherText = null;

	public EncryptionUtils(final String key, final String stringToEncrypt, final byte[] bytesToDecrypt) {
		if (encryptionKey == null)
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