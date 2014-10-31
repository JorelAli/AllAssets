package io.github.Skepter.AllAssets.Utils;

import io.github.Skepter.AllAssets.AllAssets;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.BooleanPrompt;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationFactory;

public class YesNoConversation {

	public YesNoConversation(final CommandSender sender, final BooleanPrompt prompt) {
		if (sender instanceof Conversable) {
			final ConversationFactory conversationFactory = new ConversationFactory(AllAssets.instance()).withModality(true).withFirstPrompt(prompt).withEscapeSequence("/quit").withTimeout(10);
			conversationFactory.buildConversation((Conversable) sender).begin();
		}
	}

	public static String getPromptText() {
		return AllAssets.title + AllAssets.houseStyleColor + "Are you sure you want to do that? Say " + ChatColor.GREEN + "yes " + AllAssets.houseStyleColor + "to continue or " + ChatColor.RED + "no " + AllAssets.houseStyleColor + "to cancel";
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
