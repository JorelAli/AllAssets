/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.PlayerUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;

import org.bukkit.entity.Player;

public class CommandForceChat {

	public CommandForceChat(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "forcechat", aliases = { "fc" }, permission = "forcechat", description = "Force a player to say something", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		if (args.getArgs().length > 0) {
			try {
				final Player target = PlayerUtils.getPlayerFromString(args.getArgs()[0]);
				final String s = TextUtils.join(TextUtils.getMsgFromArgs(args.getArgs(), 1, args.getArgs().length), " ");
				target.chat(s);
			} catch (Exception e) {
				//TODO post error here!
				return;
			}
		} else
			ErrorUtils.notEnoughArguments(args.getSender());
		return;
	}

}
