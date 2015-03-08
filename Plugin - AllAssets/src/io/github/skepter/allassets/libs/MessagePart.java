/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 * 
 * AllAssets, created by Skepter and Tundra
 * 
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 * 
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 * 
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.libs;

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
