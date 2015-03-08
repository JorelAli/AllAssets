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
package io.github.skepter.allassets.utils;

import io.github.skepter.allassets.AllAssets;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.BooleanPrompt;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationFactory;

public class YesNoConversation {

	private static String customText;

	public YesNoConversation(final CommandSender sender, final BooleanPrompt prompt, final String text) {
		if (customText != null)
			customText = text;
		if (sender instanceof Conversable) {
			final ConversationFactory conversationFactory = new ConversationFactory(AllAssets.instance()).withModality(true).withFirstPrompt(prompt).withEscapeSequence("/quit").withTimeout(10);
			conversationFactory.buildConversation((Conversable) sender).begin();
		}
	}

	public static String getPromptText() {
		if (customText == null)
			return Strings.TITLE + Strings.HOUSE_STYLE_COLOR + "Are you sure you want to do that? Say " + ChatColor.GREEN + "yes " + Strings.HOUSE_STYLE_COLOR + "to continue or " + ChatColor.RED + "no " + Strings.HOUSE_STYLE_COLOR + "to cancel";
		else
			return Strings.TITLE + Strings.HOUSE_STYLE_COLOR + customText + "? Say " + ChatColor.GREEN + "yes " + Strings.HOUSE_STYLE_COLOR + "to continue or " + ChatColor.RED + "no " + Strings.HOUSE_STYLE_COLOR + "to cancel";

	}

	/* Example of a BooleanPrompt*/

	//	private class YesNoPrompt extends BooleanPrompt {
	//
	//		@Override
	//		public String getPromptText(ConversationContext context) {
	//			return YesNoConversation.getPromptText();
	//		}
	//
	//		@Override
	//		protected Prompt acceptValidatedInput(ConversationContext context, boolean b) {
	//			/* Do action here :) */
	//			return Prompt.END_OF_CONVERSATION;
	//		}
	//	}
}
