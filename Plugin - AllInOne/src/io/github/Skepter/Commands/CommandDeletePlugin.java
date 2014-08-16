package io.github.Skepter.Commands;

import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.YesNoConversation;

import org.bukkit.Bukkit;
import org.bukkit.conversations.BooleanPrompt;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;

public class CommandDeletePlugin {

	public CommandDeletePlugin(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	String pluginName = null;
	
	@CommandHandler(name = "deleteplugin", aliases = { "delete", "delplugin", "delp" }, permission = "AllInOne.deleteplugin", description = "Deletes a plugin", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final Player player = args.getPlayer();
		new YesNoConversation(player, new DeletePluginPrompt());
		return;
	}

	private class DeletePluginPrompt extends BooleanPrompt {

		@Override
		public Prompt acceptInput(ConversationContext context, String string) {
			
			return null;
		}

		@Override
		public String getPromptText(ConversationContext context) {
			context.getForWhom().sendRawMessage(YesNoConversation.getPromptText());
			return null;
		}

		@Override
		protected Prompt acceptValidatedInput(ConversationContext context, boolean b) {
			return null;
		}
	}

}
