package io.github.Skepter.Commands;

import io.github.Skepter.AllInOne;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.TextUtils;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.UnknownDependencyException;

public class CommandAllInOne {

	public CommandAllInOne(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "allinone", aliases = { "aio" }, permission = "AllInOne.allinone", description = "Shows help & stuff", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		args.getSender().sendMessage("/allinone commands - shows a list of commands");
		args.getSender().sendMessage("/allinone reload - reloads the entire plugin");
		return;
	}

	@CommandHandler(name = "allinone.commands", aliases = { "aio.cmds", "allinone.cmds", "aio.commands" }, permission = "AllInOne.allinone", description = "Views plugin commands", usage = "Use <command>")
	public void commands(final CommandArgs args) {
		int arg = 1;
		if (!(args.getArgs().length == 0)) {
			arg = Integer.parseInt(args.getArgs()[0]);
		}

		TextUtils.paginate(args.getSender(), CommandFramework.pluginCommands, 10, arg);
		return;
	}

	@CommandHandler(name = "allinone.reload", aliases = { "aio.reload" }, permission = "AllInOne.allinone", description = "Reloads entire plugin", usage = "Use <command>")
	public void reload(final CommandArgs args) {
		args.getSender().sendMessage(AllInOne.instance().ttlc + "Reloading...");
		final Timer timer = new Timer();
		final File pluginFile = new File(AllInOne.instance().getDataFolder().getParent() + File.separator + "AllInOne-" + AllInOne.instance().getDescription().getVersion() + ".jar");
		final String cachedTitle = AllInOne.instance().ttlc;
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				try {
					args.getSender().sendMessage(cachedTitle + "AllInOne successfully reloaded");
					Bukkit.getPluginManager().loadPlugin(pluginFile);
					//Bukkit.getPluginManager().enablePlugin(AllInOne.instance());
				} catch (final UnknownDependencyException | InvalidPluginException | InvalidDescriptionException e) {
					e.printStackTrace();
				}
			}

		}, 3000L);
		Bukkit.getPluginManager().disablePlugin(AllInOne.instance());
		return;
	}
}
