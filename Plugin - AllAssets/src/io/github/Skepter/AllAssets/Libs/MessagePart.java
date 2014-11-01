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
package io.github.Skepter.AllAssets.Libs;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.libs.com.google.gson.stream.JsonWriter;

final class MessagePart {

	ChatColor color = ChatColor.WHITE;
	ArrayList<ChatColor> styles = new ArrayList<ChatColor>();
	String clickActionName = null, clickActionData = null,
			hoverActionName = null, hoverActionData = null;
	String text = null;

	MessagePart(final String text) {
		this.text = text;
	}

	MessagePart() {
	}

	boolean hasText() {
		return text != null;
	}

	JsonWriter writeJson(final JsonWriter json) {
		try {
			json.beginObject().name("text").value(text);
			json.name("color").value(color.name().toLowerCase());
			for (final ChatColor style : styles) {
				String styleName;
				switch (style) {
				case MAGIC:
					styleName = "obfuscated";
					break;
				case UNDERLINE:
					styleName = "underlined";
					break;
				default:
					styleName = style.name().toLowerCase();
					break;
				}
				json.name(styleName).value(true);
			}
			if ((clickActionName != null) && (clickActionData != null))
				json.name("clickEvent").beginObject().name("action").value(clickActionName).name("value").value(clickActionData).endObject();
			if ((hoverActionName != null) && (hoverActionData != null))
				json.name("hoverEvent").beginObject().name("action").value(hoverActionName).name("value").value(hoverActionData).endObject();
			return json.endObject();
		} catch (final Exception e) {
			e.printStackTrace();
			return json;
		}
	}

}