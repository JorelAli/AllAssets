package io.github.Skepter.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

public class JavaUtils {

	public static String humanReadableByteCount(final long bytes, final boolean si) {
		final int unit = si ? 1000 : 1024;
		if (bytes < unit)
			return bytes + " B";
		final int exp = (int) (Math.log(bytes) / Math.log(unit));
		final String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

	public static double getProcessCpuLoad() {
		final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = null;
		try {
			name = ObjectName.getInstance("java.lang:type=OperatingSystem");
		} catch (MalformedObjectNameException | NullPointerException e) {
			e.printStackTrace();
		}
		AttributeList list = null;
		try {
			list = mbs.getAttributes(name, new String[] { "ProcessCpuLoad" });
		} catch (InstanceNotFoundException | ReflectionException e) {
			e.printStackTrace();
		}

		if (list.isEmpty())
			return Double.NaN;

		final Attribute att = (Attribute) list.get(0);
		final Double value = (Double) att.getValue();

		if (value == -1.0)
			return Double.NaN; // usually takes a couple of seconds before we
							   // get real values

		return ((int) (value * 1000) / 10.0); // returns a percentage value with
											  // 1 decimal point precision
	}

	public static List<String> getClassNamesInPackage(final String jarName, String packageName) {
		final ArrayList<String> classes = new ArrayList<String>();
		packageName = packageName.replaceAll("\\.", "/");
		try {
			final JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
			JarEntry jarEntry;

			while (true) {
				jarEntry = jarFile.getNextJarEntry();
				if (jarEntry == null)
					break;
				if ((jarEntry.getName().startsWith(packageName)) && (jarEntry.getName().endsWith(".class")))
					classes.add(jarEntry.getName().replaceAll("/", "\\."));
			}
			jarFile.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return classes;
	}

	public static void save(final Object obj, final File file) throws Exception {
		final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true));
		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}

	public static Object load(final File file) throws Exception {
		final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		final Object result = ois.readObject();
		ois.close();
		return result;
	}
}
