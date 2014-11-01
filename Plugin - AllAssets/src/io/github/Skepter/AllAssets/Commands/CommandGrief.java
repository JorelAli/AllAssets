/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.API.LogEvent.LogType;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.MathUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;
import io.github.Skepter.AllAssets.Utils.YesNoConversation;

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
		//check if it's null
		new YesNoConversation(player, new GriefPrompt(TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length)), "Are you sure you want to send a grief report");
		return;
	}

	private class GriefPrompt extends BooleanPrompt {

		private String message;

		private GriefPrompt(String message) {
			this.message = message;
		}

		@Override
		public String getPromptText(ConversationContext context) {
			return YesNoConversation.getPromptText();
		}

		@Override
		protected Prompt acceptValidatedInput(ConversationContext context, boolean b) {
			if (context.getForWhom() instanceof Player) {
				if (b) {
					Player player = (Player) context.getForWhom();
					String location = "(" + MathUtils.round(player.getLocation().getX(), 0) + ", " + MathUtils.round(player.getLocation().getY(), 0) + ", " + MathUtils.round(player.getLocation().getZ(), 0) + ")";
					CommandLog.addLog("Player: " + player.getName() + ", Location: " + location + ", Message: " + message, LogType.GRIEF);
					context.getForWhom().sendRawMessage(AllAssets.title + "Successfully sent grief report");
				} else
					context.getForWhom().sendRawMessage(AllAssets.error + "Cancelled grief report");
			}
			return Prompt.END_OF_CONVERSATION;
		}

	}

}
