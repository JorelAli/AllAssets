package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;

import org.bukkit.entity.Player;

/** Batch command - designed to run a specific command multiple times.
 * Technically a 'for loop emulator' for Minecraft, but designed to be more
 * advanced
 * 
 * @author Skepter */
public class CommandBatch {

	public CommandBatch(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "batch", permission = "batch", description = "Run a command multiple times", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		if (args.getArgs().length == 0) {
			player.sendMessage("[i] = the number in which the time is being run at (i.e. the iteration number)");
			player.sendMessage("[i=NUMBER] = the number to start from, cannot use [i] in same command");
			player.sendMessage("[i=NUMBER:INCREMENT] = the number to start from and the amount to increment it by");
			return;
			// massive tut
		}
		int amount = 1;
		try {
			amount = Integer.parseInt(args.getArgs()[0]);
		} catch (final NumberFormatException e) {
			ErrorUtils.notAnInteger(player);
			return;
		}
		if (amount > 500) {
			ErrorUtils.error(player, "Amount cannot be larger than 500!");
			return;
		}
		final String s = TextUtils.join(TextUtils.getMsgFromArgs(args.getArgs(), 1, args.getArgs().length), " ");
		
		/* If it doesn't contain [i] or [i=#] */
		if (!(s.contains("[i]")) && !(s.contains("[i="))) {
			for (int i = 1; i < (amount + 1); i++)
				player.performCommand(s);
			return;
		}
		
		/* If it only contains [i] */
		if (s.contains("[i]") && !(s.contains("[i="))) {
			for (int i = 1; i < (amount + 1); i++)
				player.performCommand(s.replace("[i]", String.valueOf(i)));
			return;
		}

		/* If it contains [i=#] but doesn't contain [i] */
		if ((s.contains("[i=") && s.contains("]")) && !(s.contains("[i]")))
			//**** Amount has to be proportional to a different value. It cannot be amount since
			//**** int i = 1, i < [i=x] - x will not be directly proportional to i, so x must
			//**** be changed, or modify the entire syntax.
			for (int i = 1; i < amount; i++) {
				//				List<String> tagValues = new ArrayList<String>();
				int beginInt = 1;
				int increment = 1;
				player.sendMessage("beginInt: " + beginInt);
				player.sendMessage("increment: " + increment);
				//you wanat to replace the str...
				for (final String str : TextUtils.multipleStringBetween(s, "[i=", "]")) {
					if (str.contains(":")) {
						final String[] arr = str.split(":");
						beginInt = Math.abs(Integer.parseInt(arr[0]));
						increment = Integer.parseInt(arr[1]);
					} else
						try {
							beginInt = Math.abs(Integer.parseInt(str));
						} catch (final NumberFormatException e) {
							beginInt = 1;
						}
					player.performCommand(s.replace("[i=" + str + "]", String.valueOf(((i - 1) * increment) + beginInt)));
					//					tagValues.add("[i=" + str + "]");
				}

				//				for(String str : tagValues) {
				//					//can't do that since it has to be dumped into the playerPerformCommand (calculated there)
				//					//parse it into the 's'
				//				}
				//				player.performCommand(s.replace("[i]", String.valueOf(i)));
			}

		//		if ((s.contains("[i=") && s.contains("]")) || s.contains("[i]")) {
		//			final String between = TextUtils.stringBetween(s, "[i=", "]");
		//			int timesToRun = 1;
		//			int increment = 1;
		//			if (between.contains(":")) {
		//				final String[] arr = between.split(":");
		//				timesToRun = Math.abs(Integer.parseInt(arr[0]));
		//				increment = Integer.parseInt(arr[1]);
		//			} else {
		//				try {
		//					timesToRun = Math.abs(Integer.parseInt(between));
		//				} catch (final NumberFormatException e) {
		//					timesToRun = 1;
		//				}
		//			}
		//			int count = timesToRun;
		//			for (int i = timesToRun; i < amount * increment; i += increment) {
		//				String cmd = "";
		//				final String cmdToRun = s.replace("[i=" + between + "]", String.valueOf(i)); // Nav
		//				if (s.contains("[i]") && s.contains("[i=") && s.contains(":")) {
		//					cmd = cmd + cmdToRun.replace("[i]", String.valueOf(count++));
		//				} else if (s.contains("[i]")) {
		//					cmd = cmd + cmdToRun.replace("[i]", String.valueOf(i));
		//				} else {
		//					cmd = cmd + cmdToRun;
		//				}
		//				player.performCommand(cmd);
		//			}
		//			return;
		//		} else {
		//			for (int i = 1; i < amount + 1; i++) {
		//				player.performCommand(s);
		//			}
		//			return;
		//		}
	}
}
