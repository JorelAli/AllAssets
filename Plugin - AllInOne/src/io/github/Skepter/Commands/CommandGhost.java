package io.github.Skepter.Commands;

import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;

public class CommandGhost {

	public CommandGhost(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "ghost", aliases = { "semivanish" }, permission = "AllInOne.ghost", description = "Allows you to turn into a ghost", usage = "Use <command>")
	public void command(final CommandArgs args) {
//		Player player = args.getPlayer();
//		if (args.getArgs().length == 0) {
//			if (AllInOne.instance().ghostUtils.isGhost(player)) {
//				AllInOne.instance().ghostUtils.setGhost(player, false);
//				player.sendMessage(AllInOne.ttlc + "Ghost mode disabled");
//				return;
//			} else {
//				AllInOne.instance().ghostUtils.setGhost(player, true);
//				player.sendMessage(AllInOne.ttlc + "Ghost mode enabled");
//				return;
//			}
//		} else if (args.getArgs().length == 1) {
//			Player target = TextUtils.getOnlinePlayerFromString(args.getArgs()[0]);
//			if (AllInOne.instance().ghostUtils.isGhost(target)) {
//				AllInOne.instance().ghostUtils.setGhost(target, false);
//				target.sendMessage(AllInOne.ttlc + "Ghost mode disabled");
//				return;
//			} else {
//				AllInOne.instance().ghostUtils.setGhost(target, true);
//				target.sendMessage(AllInOne.ttlc + "Ghost mode enabled");
//				return;
//			}
//		} else {
//			ErrorUtils.tooManyArguments(player);
//			return;
//		}
	}
}
