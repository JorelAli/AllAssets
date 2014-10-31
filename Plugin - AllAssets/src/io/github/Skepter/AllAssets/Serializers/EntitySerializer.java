package io.github.Skepter.AllAssets.Serializers;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public final class EntitySerializer {

	public static String toString(final Entity entity) {
		final YamlConfiguration config = new YamlConfiguration();
		config.set("Entity", entity);
		return Base64Coder.encodeString(config.saveToString());
	}

	public static Entity fromString(final String s) {
		final YamlConfiguration config = new YamlConfiguration();
		try {
			config.loadFromString(Base64Coder.decodeString(s));
			return (Entity) config.get("Entity");
		} catch (final InvalidConfigurationException e) {
			return null;
		}
	}
}