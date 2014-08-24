package io.github.Skepter.Utils;

import io.github.Skepter.AllAssets;

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
		return AllAssets.instance().title + "Are you sure you want to do that? Use " + ChatColor.GREEN + "/yes " + AllAssets.instance().houseStyleColor + "to continue or " + ChatColor.RED + "/no " + AllAssets.instance().houseStyleColor + "to cancel";
	}

	/* Example of a BooleanPrompt*/
	
	//	private class YesNoPrompt extends BooleanPrompt {
	//
	//		@Override
	//		public Prompt acceptInput(ConversationContext context, String string) {
	//			/* Action to perform here! */
	//			return null;
	//		}
	//
	//		@Override
	//		public String getPromptText(ConversationContext context) {
	//			context.getForWhom().sendRawMessage(YesNoConversation.getPromptText());
	//			return null;
	//		}
	//
	//		@Override
	//		protected Prompt acceptValidatedInput(ConversationContext context, boolean b) {
	//			return null;
	//		}
	//	}
}
