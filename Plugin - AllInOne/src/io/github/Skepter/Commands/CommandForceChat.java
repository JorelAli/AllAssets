package io.github.Skepter.Commands;

import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;
import io.github.Skepter.Utils.PlayerUtils;
import io.github.Skepter.Utils.TextUtils;

import org.bukkit.entity.Player;

public class CommandForceChat {

	public CommandForceChat(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "forcechat", aliases = { "fc" }, permission = "AllInOne.forcechat", description = "Force a player to say something", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		if(args.getArgs().length > 0) {
			final Player target = PlayerUtils.getPlayerFromString(args.getArgs()[0]);
			final String s = TextUtils.join(TextUtils.getMsgFromArgs(args.getArgs(), 1, args.getArgs().length), " ");
			target.chat(s);
		} else
			ErrorUtils.notEnoughArguments(args.getPlayer());
		return;
	}

}
