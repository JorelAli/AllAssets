/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and MCSpartans
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.other;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.api.events.LogEvent.LogType;
import io.github.skepter.allassets.commands.administration.CommandLog;
import io.github.skepter.allassets.misc.NotificationsBoard;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.YesNoConversation;
import io.github.skepter.allassets.utils.utilclasses.MathUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.conversations.BooleanPrompt;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;

public class CommandGrief {

	public CommandGrief(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "grief", aliases = { "griefreport", "gr" }, permission = "grief", description = "Report a grief incident")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			final String message = TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length);
			if (message != null)
				new YesNoConversation(player, new GriefPrompt(message), "Are you sure you want to send a grief report");
		}
		return;
	}

	private class GriefPrompt extends BooleanPrompt {

		private final String message;

		private GriefPrompt(final String message) {
			this.message = message;
		}

		@Override
		public String getPromptText(final ConversationContext context) {
			return YesNoConversation.getPromptText();
		}

		@SuppressWarnings("deprecation")
		@Override
		protected Prompt acceptValidatedInput(final ConversationContext context, final boolean b) {
			if (context.getForWhom() instanceof Player)
				if (b) {
					final Player player = (Player) context.getForWhom();
					final String location = "(" + MathUtils.round(player.getLocation().getX(), 0) + ", " + MathUtils.round(player.getLocation().getY(), 0) + ", " + MathUtils.round(player.getLocation().getZ(), 0) + ")";
					CommandLog.addLog("Player: " + player.getName() + ", Location: " + location + ", Message: " + message, LogType.GRIEF);
					NotificationsBoard.addGriefLog();
					NotificationsBoard.updateAll();
					context.getForWhom().sendRawMessage(Strings.TITLE + "Successfully sent grief report");
				} else
					context.getForWhom().sendRawMessage(Strings.ERROR + "Cancelled grief report");
			return Prompt.END_OF_CONVERSATION;
		}

	}

}
