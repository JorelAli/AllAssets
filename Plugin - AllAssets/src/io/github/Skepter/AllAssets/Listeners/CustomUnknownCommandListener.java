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
package io.github.Skepter.AllAssets.Listeners;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Libs.FancyMessage;
import io.github.Skepter.AllAssets.Utils.TextUtils;

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
				new FancyMessage(AllAssets.TITLE + "Unknown command. Did you mean: ").then(AllAssets.HOUSE_STYLE_COLOR + searchWithTuncater(msg)).tooltip(AllAssets.HOUSE_STYLE_COLOR + "Click to execute " + searchWithTuncater(msg)).command(searchWithTuncater(msg)).send(e.getPlayer());
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
