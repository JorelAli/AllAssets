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
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Tasks;

import io.github.Skepter.AllAssets.Utils.UUIDFetch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.bukkit.craftbukkit.libs.com.google.gson.JsonObject;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonParser;

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
