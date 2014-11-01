/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Config;

import io.github.Skepter.AllAssets.AllAssets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SimpleConfig {
	private int comments;
	private final SimpleConfigManager manager;

	private final File file;
	private FileConfiguration config;

	public SimpleConfig(final InputStream configStream, final File configFile, final int comments, final AllAssets plugin) {
		this.comments = comments;
		this.manager = new SimpleConfigManager(plugin);

		this.file = configFile;

		/* Custom check to find tabs in config files */
		try {
			if (Boolean.parseBoolean(checkYamlFiles().split(":")[0])) {
				AllAssets.instance().getLogger().severe(checkYamlFiles().split(":")[1] + " contains a tab " + checkYamlFiles().split(":")[2] + "!");
				Bukkit.getPluginManager().disablePlugin(plugin);
			} else
				loadConfig(configStream);
		} catch (final IOException e) {
			e.printStackTrace();
			//AllAssets.instance().getLogger().warning("There was an error checking data files for errors. Don't worry :)");
			//loadConfig(configStream);
		}
	}

	@SuppressWarnings("deprecation")
	private void loadConfig(final InputStream configStream) {
		this.config = YamlConfiguration.loadConfiguration(configStream);
	}

	/* Used to check for tabs in configuration files */
	private String checkYamlFiles() throws IOException {
		final BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		int count = 0;
		while ((line = reader.readLine()) != null) {
			count++;
			if (line.contains("\t")) {
				reader.close();
				return "true:" + file.getName() + ":" + count;
			}
		}
		reader.close();
		return "false:null:null";
	}

	public Object get(final String path) {
		return this.config.get(path);
	}

	public Object get(final String path, final Object def) {
		return this.config.get(path, def);
	}

	public String getString(final String path) {
		return String.valueOf(get(path));
	}

	public String getSpecialString(final String path) {
		String string = getString(path);
		return string.substring(1, string.length() - 1);
	}

	public String getString(final String path, final String def) {
		return String.valueOf(get(path, def));
	}

	public int getInt(final String path) {
		return Integer.parseInt(getString(path));
	}

	public int getInt(final String path, final int def) {
		return Integer.parseInt(getString(path, String.valueOf(def)));
	}

	public boolean getBoolean(final String path) {
		return Boolean.parseBoolean(getString(path));
	}

	public boolean getBoolean(final String path, final boolean def) {
		return Boolean.parseBoolean(getString(path, String.valueOf(def)));
	}

	public void createSection(final String path) {
		this.config.createSection(path);
	}

	public ConfigurationSection getConfigurationSection(final String path) {
		return this.config.getConfigurationSection(path);
	}

	public double getDouble(final String path) {
		return Double.parseDouble(getString(path));
	}

	public double getDouble(final String path, final double def) {
		return Double.parseDouble(getString(path, String.valueOf(def)));
	}

	public List<?> getList(final String path) {
		return (List<?>) this.config.get(path);
	}

	public List<?> getList(final String path, final List<?> def) {
		return (List<?>) this.config.get(path, def);
	}

	public List<String> getStringList(final String path) {

		final List<?> list = getList(path);

		if (list == null)
			return new ArrayList<String>(0);

		final List<String> result = new ArrayList<String>();

		for (final Object object : list)
			if ((object instanceof String) || (isPrimitiveWrapper(object)))
				result.add(String.valueOf(object));

		return result;

	}

	public List<Integer> getIntegerList(final String path) {

		final List<?> list = getList(path);

		if (list == null)
			return new ArrayList<Integer>(0);

		final List<Integer> result = new ArrayList<Integer>();

		for (final Object object : list)
			if (object instanceof Integer)
				result.add((Integer) object);
			else if (object instanceof String)
				try {

					result.add(Integer.valueOf((String) object));

				} catch (final Exception ex) {

				}
			else if (object instanceof Character)
				result.add((int) ((Character) object).charValue());
			else if (object instanceof Number)
				result.add(((Number) object).intValue());

		return result;

	}

	public List<Boolean> getBooleanList(final String path) {

		final List<?> list = getList(path);

		if (list == null)
			return new ArrayList<Boolean>(0);

		final List<Boolean> result = new ArrayList<Boolean>();

		for (final Object object : list)
			if (object instanceof Boolean)
				result.add((Boolean) object);
			else if (object instanceof String)
				if (Boolean.TRUE.toString().equals(object))
					result.add(true);
				else if (Boolean.FALSE.toString().equals(object))
					result.add(false);

		return result;

	}

	public List<Double> getDoubleList(final String path) {

		final List<?> list = getList(path);

		if (list == null)
			return new ArrayList<Double>(0);

		final List<Double> result = new ArrayList<Double>();

		for (final Object object : list)
			if (object instanceof Double)
				result.add((Double) object);
			else if (object instanceof String)
				try {

					result.add(Double.valueOf((String) object));

				} catch (final Exception ex) {

				}
			else if (object instanceof Character)
				result.add((double) ((Character) object).charValue());
			else if (object instanceof Number)
				result.add(((Number) object).doubleValue());

		return result;

	}

	public List<Float> getFloatList(final String path) {

		final List<?> list = getList(path);

		if (list == null)
			return new ArrayList<Float>(0);

		final List<Float> result = new ArrayList<Float>();

		for (final Object object : list)
			if (object instanceof Float)
				result.add((Float) object);
			else if (object instanceof String)
				try {

					result.add(Float.valueOf((String) object));

				} catch (final Exception ex) {

				}
			else if (object instanceof Character)
				result.add((float) ((Character) object).charValue());
			else if (object instanceof Number)
				result.add(((Number) object).floatValue());

		return result;

	}

	public List<Long> getLongList(final String path) {

		final List<?> list = getList(path);

		if (list == null)
			return new ArrayList<Long>(0);

		final List<Long> result = new ArrayList<Long>();

		for (final Object object : list)
			if (object instanceof Long)
				result.add((Long) object);
			else if (object instanceof String)
				try {

					result.add(Long.valueOf((String) object));

				} catch (final Exception ex) {

				}
			else if (object instanceof Character)
				result.add((long) ((Character) object).charValue());
			else if (object instanceof Number)
				result.add(((Number) object).longValue());

		return result;

	}

	public List<Byte> getByteList(final String path) {

		final List<?> list = getList(path);

		if (list == null)
			return new ArrayList<Byte>(0);

		final List<Byte> result = new ArrayList<Byte>();

		for (final Object object : list)
			if (object instanceof Byte)
				result.add((Byte) object);
			else if (object instanceof String)
				try {

					result.add(Byte.valueOf((String) object));

				} catch (final Exception ex) {

				}
			else if (object instanceof Character)
				result.add((byte) ((Character) object).charValue());
			else if (object instanceof Number)
				result.add(((Number) object).byteValue());

		return result;

	}

	public List<Character> getCharacterList(final String path) {

		final List<?> list = getList(path);

		if (list == null)
			return new ArrayList<Character>(0);

		final List<Character> result = new ArrayList<Character>();

		for (final Object object : list)
			if (object instanceof Character)
				result.add((Character) object);
			else if (object instanceof String) {

				final String str = (String) object;

				if (str.length() == 1)
					result.add(str.charAt(0));

			} else if (object instanceof Number)
				result.add((char) ((Number) object).intValue());

		return result;

	}

	public List<Short> getShortList(final String path) {

		final List<?> list = getList(path);

		if (list == null)
			return new ArrayList<Short>(0);

		final List<Short> result = new ArrayList<Short>();

		for (final Object object : list)
			if (object instanceof Short)
				result.add((Short) object);
			else if (object instanceof String)
				try {

					result.add(Short.valueOf((String) object));

				} catch (final Exception ex) {

				}
			else if (object instanceof Character)
				result.add((short) ((Character) object).charValue());
			else if (object instanceof Number)
				result.add(((Number) object).shortValue());

		return result;

	}

	public List<Map<?, ?>> getMapList(final String path) {

		final List<?> list = getList(path);

		final List<Map<?, ?>> result = new ArrayList<Map<?, ?>>();

		if (list == null)
			return result;

		for (final Object object : list)
			if (object instanceof Map)
				result.add((Map<?, ?>) object);

		return result;

	}

	public boolean contains(final String path) {
		return this.config.contains(path);
	}

	public void removeKey(final String path) {
		this.config.set(path, null);
	}

	public void set(final String path, final Object value) {
		this.config.set(path, value);
	}

	public void set(final String path, final Object value, final String comment) {
		if (config == null)
			return;
		if (!this.config.contains(path)) {
			this.config.set(manager.getPluginName() + "_COMMENT_" + comments, " " + comment);
			comments++;
		}
		this.config.set(path, value);

	}

	public void set(final String path, final Object value, final String[] comment) {

		for (final String comm : comment)
			if (!this.config.contains(path)) {
				this.config.set(manager.getPluginName() + "_COMMENT_" + comments, " " + comm);
				comments++;
			}

		this.config.set(path, value);

	}

	public void setHeader(final String[] header) {
		manager.setHeader(this.file, header);
		this.comments = header.length + 2;
		this.reloadConfig();
	}

	@SuppressWarnings("deprecation")
	public void reloadConfig() {
		this.config = YamlConfiguration.loadConfiguration(manager.getConfigContent(file));
	}

	public void saveConfig() {
		if (config == null)
			return;
		final String config = this.config.saveToString();
		manager.saveConfig(config, this.file);

	}

	public Set<String> getKeys() {
		return this.config.getKeys(false);
	}

	protected boolean isPrimitiveWrapper(final Object input) {

		return (input instanceof Integer) || (input instanceof Boolean) || (input instanceof Character) || (input instanceof Byte) || (input instanceof Short) || (input instanceof Double) || (input instanceof Long) || (input instanceof Float);

	}

}