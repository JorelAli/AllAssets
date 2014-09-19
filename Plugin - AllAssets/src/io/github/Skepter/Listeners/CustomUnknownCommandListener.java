package io.github.Skepter.Listeners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class CustomUnknownCommandListener implements Listener {

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if (Bukkit.getHelpMap().getHelpTopic(e.getMessage().split(" ")[0]) == null) {
			e.setCancelled(true);
			handle(e.getMessage());
			//check for possible commands
			e.getPlayer().sendMessage("Example Text For Unkown Command!");
		}
	}

	private void handle(String message) {
		Collection<HelpTopic> topics = Bukkit.getHelpMap().getHelpTopics();
		String newMessage = message.split(" ")[0];
		for (HelpTopic topic : topics) {
			for (int i = 0; i < topic.getName().length(); i++) {
				if(!(topic.getName().charAt(i) == newMessage.charAt(i))) {
					//stop. then check i - 1 get the chars, build the topicName up to that char and we got a command :)
				}
			}
			//check the first letter of this, to the first letter of newMessage
			//then break a label and we're good :)
		}
		for (int i = 0; i < newMessage.length(); i++) {
			List<Character> charList = new ArrayList<Character>();
			for (int j = 0; j < i; j++) {
				charList.add(newMessage.charAt(j));
			}
		}
	}
}
