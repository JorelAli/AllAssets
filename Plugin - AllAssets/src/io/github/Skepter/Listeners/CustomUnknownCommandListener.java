package io.github.Skepter.Listeners;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Libs.FancyMessage;
import io.github.Skepter.Utils.TextUtils;

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
	public void onCommand(PlayerCommandPreprocessEvent e) {
		String msg = e.getMessage().toLowerCase().split(" ")[0];
		if (Bukkit.getHelpMap().getHelpTopic(msg) == null && !TextUtils.arrayContains(excludedCommands, msg)) {
			if (searchWithTuncater(msg) != null && !searchWithTuncater(msg).isEmpty()) {
				e.setCancelled(true);
				new FancyMessage(AllAssets.title + "Unknown command. Did you mean: ").then(AllAssets.houseStyleColor + searchWithTuncater(msg)).tooltip(AllAssets.houseStyleColor + "Click to execute " + searchWithTuncater(msg)).command(searchWithTuncater(msg)).send(e.getPlayer());
			}
			return;
		}
	}

	private String searchWithTuncater(String topicString) {
		for (String s : TextUtils.truncater(topicString)) {
			for (HelpTopic topic : Bukkit.getHelpMap().getHelpTopics()) {
				if (topic.getName().contains(s)) {
					return topic.getName();
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unused")
	private List<String> searchListWithTuncater(String topicString) {
		List<String> listOfCommands = new ArrayList<String>();
		for (String s : TextUtils.truncater(topicString)) {
			for (HelpTopic topic : Bukkit.getHelpMap().getHelpTopics()) {
				if (topic.getName().contains(s)) {
					listOfCommands.add(topic.getName());
					break;
				}
			}
		}
		return listOfCommands;
	}
}
