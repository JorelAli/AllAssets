package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.API.LogEvent.LogType;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;
import io.github.Skepter.Utils.TextUtils;
import io.github.Skepter.Utils.YesNoConversation;

import org.bukkit.conversations.BooleanPrompt;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;

public class CommandGrief {

	public CommandGrief(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "grief", aliases = { "griefreport", "gr" }, permission = "grief", description = "Report a grief incident", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		new YesNoConversation(player, new GriefPrompt(TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length)));
		return;
	}

	private class GriefPrompt extends BooleanPrompt {

		private String message;

		private GriefPrompt(String message) {
			this.message = message;
		}

		@Override
		public Prompt acceptInput(ConversationContext context, String string) {
			if (context.getForWhom() instanceof Player) {
				Player player = (Player) context.getForWhom();
				String location = "(" +  player.getLocation().getX() + ", " + player.getLocation().getY() + ", " + player.getLocation().getZ() + ")" ;
				CommandLog.addLog("Player: " + player.getName() + ", Location: " + location + ", Message: " + message, LogType.GRIEF);
				player.sendMessage(AllAssets.title + "Successfully sent grief report");
			}
			return null;
		}

		@Override
		public String getPromptText(ConversationContext context) {
			context.getForWhom().sendRawMessage(YesNoConversation.getPromptText());
			return null;
		}

		@Override
		protected Prompt acceptValidatedInput(ConversationContext arg0, boolean arg1) {
			return null;
		}

	}

}
