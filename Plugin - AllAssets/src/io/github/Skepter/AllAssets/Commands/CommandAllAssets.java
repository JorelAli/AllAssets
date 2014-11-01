/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Config.ConfigHandler;
import io.github.Skepter.AllAssets.Utils.TextUtils;

public class CommandAllAssets {

	public CommandAllAssets(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "allassets", aliases = { "aa" }, permission = "allassets", description = "Shows help & stuff", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		args.getSender().sendMessage("/allassets commands - shows a list of commands");
		args.getSender().sendMessage("/allassets reload - reloads the entire plugin");
		return;
	}

	@CommandHandler(name = "allassets.commands", aliases = { "aa.cmds", "allassets.cmds", "aa.commands" }, permission = "AllAssets.allassets", description = "Views plugin commands", usage = "Use <command>")
	public void commands(final CommandArgs args) {
		int arg = 1;
		if (args.getArgs().length == 1)
			arg = Integer.parseInt(args.getArgs()[0]);

		TextUtils.paginate(args.getSender(), CommandFramework.pluginCommands, 10, arg);
		return;
	}

	@CommandHandler(name = "allassets.reload", aliases = { "aa.reload" }, permission = "AllAssets.allassets", description = "Reloads entire plugin", usage = "Use <command>")
	public void reload(final CommandArgs args) {
		ConfigHandler.instance().config().reloadConfig();
		ConfigHandler.instance().features().reloadConfig();
		args.getSender().sendMessage(AllAssets.title + "Configuration reloaded");
//		args.getSender().sendMessage(AllAssets.title + "Reloading...");
//		/* We're currently in dev and dev file name isn't the same as the released name */
//		final File devPluginFile = new File(AllAssets.instance().getDataFolder().getParent() + File.separator + "AllAssets.jar");
//		//		final File pluginFile = new File(AllAssets.instance().getDataFolder().getParent() + File.separator + "AllAssets-" + AllAssets.instance().getDescription().getVersion() + ".jar");
//		final String cachedTitle = AllAssets.title;
//		new Timer().schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//				try {
//					args.getSender().sendMessage(cachedTitle + "AllAssets successfully reloaded");
//					Bukkit.getPluginManager().loadPlugin(devPluginFile);
//					//Bukkit.getPluginManager().enablePlugin(AllAssets.instance());
//				} catch (final UnknownDependencyException
//						| InvalidPluginException | InvalidDescriptionException e) {
//					e.printStackTrace();
//				}
//			}
//
//		}, 3000L);
//		Bukkit.getPluginManager().disablePlugin(AllAssets.instance());
//		return;
	}
}
