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
package io.github.Skepter.AllAssets.Utils;

import io.github.Skepter.AllAssets.AllAssets;

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
			return AllAssets.title + AllAssets.houseStyleColor + "Are you sure you want to do that? Say " + ChatColor.GREEN + "yes " + AllAssets.houseStyleColor + "to continue or " + ChatColor.RED + "no " + AllAssets.houseStyleColor + "to cancel";
		else
			return AllAssets.title + AllAssets.houseStyleColor + customText + "? Say " + ChatColor.GREEN + "yes " + AllAssets.houseStyleColor + "to continue or " + ChatColor.RED + "no " + AllAssets.houseStyleColor + "to cancel";

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
