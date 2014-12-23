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
	public String getPromptText(final ConversationContext context) {
		return YesNoConversation.getPromptText();
	}

	@Override
	protected Prompt acceptValidatedInput(final ConversationContext context, final boolean b) {
		if (b)
			Bukkit.shutdown();
		return Prompt.END_OF_CONVERSATION;
	}
}
