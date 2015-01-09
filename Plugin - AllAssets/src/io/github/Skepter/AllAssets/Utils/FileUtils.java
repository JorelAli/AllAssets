/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
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
package io.github.Skepter.AllAssets.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class FileUtils {

	/** Saves an object to a file */
	public static void save(final Object obj, final File file) throws Exception {
		final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true));
		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}

	/** Loads an object from a file */
	public static Object load(final File file) throws Exception {
		final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		final Object result = ois.readObject();
		ois.close();
		return result;
	}

	/** Saves an object to a file */
	public static void saveBytesSecurely(final byte[] bytes, final File file) throws Exception {
		final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true));
		char[] data = Base64Coder.encode(bytes);
		oos.writeObject(data);
		oos.flush();
		oos.close();
	}

	/** Loads an object from a file */
	public static byte[] loadBytesSecurely(final File file) throws Exception {
		final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		final Object result = ois.readObject();
		ois.close();
		byte[] data = null;
		if (result instanceof char[])
			data = Base64Coder.decode((char[]) result);
		return data;
	}

	/** Saves an object to a file */
	public static void saveStringSecurely(final String string, final File file) throws Exception {
		final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true));
		String data = Base64Coder.encodeString(string);
		oos.writeObject(data);
		oos.flush();
		oos.close();
	}

	/** Loads an object from a file */
	public static String loadStringSecurely(final File file) throws Exception {
		final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		final Object result = ois.readObject();
		ois.close();
		return Base64Coder.decodeString((String) result);
	}

}
