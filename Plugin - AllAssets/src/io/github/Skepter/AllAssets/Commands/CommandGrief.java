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
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.API.LogEvent.LogType;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Commands.Administration.CommandLog;
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
		final String message = TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length);
		if (message != null)
			new YesNoConversation(player, new GriefPrompt(message), "Are you sure you want to send a grief report");
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

		@Override
		protected Prompt acceptValidatedInput(final ConversationContext context, final boolean b) {
			if (context.getForWhom() instanceof Player)
				if (b) {
					final Player player = (Player) context.getForWhom();
					final String location = "(" + MathUtils.round(player.getLocation().getX(), 0) + ", " + MathUtils.round(player.getLocation().getY(), 0) + ", " + MathUtils.round(player.getLocation().getZ(), 0) + ")";
					CommandLog.addLog("Player: " + player.getName() + ", Location: " + location + ", Message: " + message, LogType.GRIEF);
					context.getForWhom().sendRawMessage(AllAssets.title + "Successfully sent grief report");
				} else
					context.getForWhom().sendRawMessage(AllAssets.error + "Cancelled grief report");
			return Prompt.END_OF_CONVERSATION;
		}

	}

}
