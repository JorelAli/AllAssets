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
package io.github.skepter.allassets.listeners;

import io.github.skepter.allassets.libs.FancyMessage;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class CustomUnknownCommandListener implements Listener {

	final private String[] excludedCommands = new String[] { "/yes", "/no" };

	@EventHandler
	public void onCommand(final PlayerCommandPreprocessEvent e) {
		final String msg = e.getMessage().toLowerCase().split(" ")[0];
		if ((Bukkit.getHelpMap().getHelpTopic(msg) == null) && !TextUtils.arrayContains(excludedCommands, msg)) {
			if ((searchWithTuncater(msg) != null) && !searchWithTuncater(msg).isEmpty()) {
				e.setCancelled(true);
				new FancyMessage(Strings.TITLE + "Unknown command. Did you mean: ").then(Strings.HOUSE_STYLE_COLOR + searchWithTuncater(msg)).tooltip(Strings.HOUSE_STYLE_COLOR + "Click to execute " + searchWithTuncater(msg)).command(searchWithTuncater(msg)).send(e.getPlayer());
			}
			return;
		}
	}

	private String searchWithTuncater(final String topicString) {
		for (final String s : TextUtils.truncater(topicString))
			for (final HelpTopic topic : Bukkit.getHelpMap().getHelpTopics())
				if (topic.getName().contains(s))
					return topic.getName();
		return null;
	}

	@SuppressWarnings("unused")
	private List<String> searchListWithTuncater(final String topicString) {
		final List<String> listOfCommands = new ArrayList<String>();
		for (final String s : TextUtils.truncater(topicString))
			for (final HelpTopic topic : Bukkit.getHelpMap().getHelpTopics())
				if (topic.getName().contains(s)) {
					listOfCommands.add(topic.getName());
					break;
				}
		return listOfCommands;
	}
}
