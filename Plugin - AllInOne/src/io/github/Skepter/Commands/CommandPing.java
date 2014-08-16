package io.github.Skepter.Commands;

import io.github.Skepter.AllInOne;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Users.User;

public class CommandPing {

	public CommandPing(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "ping", permission = "AllInOne.ping", description = "Shows your ping", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final User user = new User(args.getPlayer());
		user.getPlayer().sendMessage(AllInOne.instance().title + "Your ping is " + user.getPing());
		return;
	}
}
