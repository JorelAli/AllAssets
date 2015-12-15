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
package io.github.skepter.allassets.tasks;

import io.github.skepter.allassets.utils.UUIDFetch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@SuppressWarnings("deprecation")
public class UUIDFetchTask implements Runnable {

	private final String name;
	private final boolean cache;

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
