package io.github.Skepter.Commands;

import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;

public class CommandWorkbench {

	public CommandWorkbench(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "workbench", aliases = { "wb" }, permission = "workbench", description = "Shows your ping", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		args.getPlayer().openWorkbench(null, true);
		return;
	}
}
