package io.github.Skepter.Listeners;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Libs.FancyMessage;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class CustomUnknownCommandListener implements Listener {

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		String msg = e.getMessage().split(" ")[0];
		if (Bukkit.getHelpMap().getHelpTopic(msg) == null) {
			if (searchTopics(msg) != null && !searchTopics(msg).isEmpty()) {
				e.setCancelled(true);
				new FancyMessage(AllAssets.title + "Unknown command. Did you mean: ").then(AllAssets.houseStyleColor + searchTopics(msg).get(0)).tooltip(AllAssets.houseStyleColor + "Click to execute " + searchTopics(msg).get(0)).command(searchTopics(msg).get(0)).send(e.getPlayer());;
			}
			return;
		}
	}

	private List<String> searchTopics(String topicString) {
		List<String> listOfCommands = new ArrayList<String>();
		for (HelpTopic topic : Bukkit.getHelpMap().getHelpTopics())
			if (topic.getName().contains(topicString))
				listOfCommands.add(topic.getName());
		return listOfCommands;
	}
}
