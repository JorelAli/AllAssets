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
