package io.github.Skepter.Tasks;

import io.github.Skepter.Utils.UUIDFetch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.bukkit.craftbukkit.libs.com.google.gson.JsonObject;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonParser;

public class UUIDFetchTask implements Runnable {

	private String name;
	private boolean cache;

	public UUIDFetchTask(final String name, final boolean cache) {
		this.name = name;
		this.cache = cache;
	}

	@Override
	public void run() {
		try {
			final URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);

			final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str;
			while ((str = in.readLine()) != null) {
				final JsonParser parser = new JsonParser();
				final Object o = parser.parse(str);
				final JsonObject jObject = (JsonObject) o;

				final String uuid = jObject.get("id").getAsString();
				final String playerName = jObject.get("name").getAsString();
				if (cache)
					UUIDFetch.cacheSetup(uuid, playerName);
				else
					UUIDFetch.setup(uuid, playerName);
			}
			in.close();

		} catch (final MalformedURLException e) {
			//
		} catch (final IOException e) {
			//
		}
	}

}
