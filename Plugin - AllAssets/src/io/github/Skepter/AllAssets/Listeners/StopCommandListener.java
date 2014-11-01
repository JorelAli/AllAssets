/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Listeners;

import io.github.Skepter.AllAssets.Utils.YesNoConversation;

import org.bukkit.Bukkit;
import org.bukkit.conversations.BooleanPrompt;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class StopCommandListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(final PlayerCommandPreprocessEvent event) {
		final String cmd = event.getMessage().split(" ")[0].replace("/", "").toLowerCase();
		if (cmd.equals("stop") && event.getPlayer().hasPermission("AllAssets.stop")) {
			event.setCancelled(true);
			new YesNoConversation(event.getPlayer(), new StopPrompt(), "Are you sure you want to stop the server");
		}
	}
}

class StopPrompt extends BooleanPrompt {

	@Override
	public String getPromptText(ConversationContext context) {
		return YesNoConversation.getPromptText();
	}

	@Override
	protected Prompt acceptValidatedInput(ConversationContext context, boolean b) {
		if (b)
			Bukkit.shutdown();
		return Prompt.END_OF_CONVERSATION;
	}
}
