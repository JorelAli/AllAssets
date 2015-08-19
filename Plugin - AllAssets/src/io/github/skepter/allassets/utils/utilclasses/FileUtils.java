/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and MCSpartans
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.utils.utilclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;

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
		final char[] data = Base64Coder.encode(bytes);
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
		final String data = Base64Coder.encodeString(string);
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

	public static final void copy(final File source, final File destination) throws IOException {
		if (source.isDirectory())
			copyDirectory(source, destination);
		else
			copyFile(source, destination);
	}

	public static final void copyDirectory(final File source, final File destination) throws IOException {
		if (!source.isDirectory())
			throw new IllegalArgumentException("Source (" + source.getPath() + ") must be a directory.");

		if (!source.exists())
			throw new IllegalArgumentException("Source directory (" + source.getPath() + ") doesn't exist.");

		if (destination.exists())
			throw new IllegalArgumentException("Destination (" + destination.getPath() + ") exists.");

		destination.mkdirs();
		final File[] files = source.listFiles();

		for (final File file : files)
			if (file.isDirectory())
				copyDirectory(file, new File(destination, file.getName()));
			else
				copyFile(file, new File(destination, file.getName()));
	}

	@SuppressWarnings("resource")
	public static final void copyFile(final File source, final File destination) throws IOException {
		final FileChannel sourceChannel = new FileInputStream(source).getChannel();
		final FileChannel targetChannel = new FileOutputStream(destination).getChannel();
		sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);
		sourceChannel.close();
		targetChannel.close();
	}

}
