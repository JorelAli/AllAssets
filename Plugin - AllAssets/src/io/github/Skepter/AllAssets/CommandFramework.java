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
package io.github.Skepter.AllAssets;

import io.github.Skepter.AllAssets.Config.ConfigHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.help.GenericCommandHelpTopic;
import org.bukkit.help.HelpTopic;
import org.bukkit.help.HelpTopicComparator;
import org.bukkit.help.IndexHelpTopic;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;

/** Command Framework - CommandFramework <br>
 * The main command framework class used for controlling the framework.
 * 
 * @author minnymin3
 * 
 * Some features have been added/modified for the use of AllAssets However,
 * minnymin3 deserves pretty much all of the credit since he (or she) wrote the
 * majority of the class. */
public class CommandFramework {

	private final Map<String, Entry<Method, Object>> commandMap = new HashMap<String, Entry<Method, Object>>();
	private CommandMap map;
	private final Plugin plugin;
	private static final String noPerm = AllAssets.error + "You do not have permission to perform that action";
	public static Set<CommandHandler> pluginCommands = new HashSet<CommandHandler>();
	private Set<String> cmds = new HashSet<String>();

	/** Initializes the command framework and sets up the command maps
	 * 
	 * @param plugin */
	public CommandFramework(final Plugin plugin) {
		this.plugin = plugin;
		if (plugin.getServer().getPluginManager() instanceof SimplePluginManager) {
			final SimplePluginManager manager = (SimplePluginManager) plugin.getServer().getPluginManager();
			try {
				final Field field = SimplePluginManager.class.getDeclaredField("commandMap");
				field.setAccessible(true);
				map = (CommandMap) field.get(manager);
			} catch (IllegalArgumentException | NoSuchFieldException
					| IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	/** Handles commands. Used in the onCommand method in your JavaPlugin class
	 * 
	 * @param sender The {@link org.bukkit.command.CommandSender} parsed from
	 * onCommand
	 * @param label The label parsed from onCommand
	 * @param cmd The {@link org.bukkit.command.Command} parsed from onCommand
	 * @param args The arguments parsed from onCommand
	 * @return Always returns true for simplicity's sake in onCommand */
	public boolean handleCommand(final CommandSender sender, final String label, final org.bukkit.command.Command cmd, final String[] args) {
		for (int i = args.length; i >= 0; i--) {
			final StringBuilder buffer = new StringBuilder();
			buffer.append(label.toLowerCase());
			for (int x = 0; x < i; x++)
				buffer.append(".").append(args[x].toLowerCase());
			final String cmdLabel = buffer.toString();
			if (commandMap.containsKey(cmdLabel)) {
				final Entry<Method, Object> entry = commandMap.get(cmdLabel);
				final CommandHandler command = entry.getKey().getAnnotation(CommandHandler.class);
				if (!sender.hasPermission("AllAssets." + command.permission().toLowerCase())) { //Nav
					sender.sendMessage(noPerm);
					return true;
				}
				try {
					long before = System.currentTimeMillis();
					entry.getKey().invoke(entry.getValue(), new CommandArgs(sender, cmd, label, args, cmdLabel.split("\\.").length - 1));
					if (ConfigHandler.instance().config().getBoolean("debugMode") || AllAssets.masterSwitch)
						sender.sendMessage(AllAssets.title + "Command took " + (System.currentTimeMillis() - before) + " milliseconds to execute");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
			}
		}

		sender.sendMessage("There was an error whilst handling the " + label + " command");
		return true;
	}

	/** Registers all command and completer methods inside of the object. Similar
	 * to Bukkit's registerEvents method.
	 * 
	 * @param obj The object to register the commands of */
	public void registerCommands(final Object obj) {
		for (final Method m : obj.getClass().getMethods())
			if (m.getAnnotation(CommandHandler.class) != null) {
				final CommandHandler command = m.getAnnotation(CommandHandler.class);
				if ((m.getParameterTypes().length > 1) || (m.getParameterTypes()[0] != CommandArgs.class)) {
					System.out.println("Unable to register command " + m.getName() + ". Unexpected method arguments");
					continue;
				}
				registerCommand(command, command.name(), m, obj);
				for (final String alias : command.aliases())
					registerCommand(command, alias, m, obj);
			} else if (m.getAnnotation(Completer.class) != null) {
				final Completer comp = m.getAnnotation(Completer.class);
				if ((m.getParameterTypes().length > 1) || (m.getParameterTypes().length == 0) || (m.getParameterTypes()[0] != CommandArgs.class)) {
					System.out.println("Unable to register tab completer " + m.getName() + ". Unexpected method arguments");
					continue;
				}
				if (m.getReturnType() != List.class) {
					System.out.println("Unable to register tab completer " + m.getName() + ". Unexpected return type");
					continue;
				}
				registerCompleter(comp.name(), m, obj);
				for (final String alias : comp.aliases())
					registerCompleter(alias, m, obj);
			}
	}

	/** Registers all the commands under the plugin's help */
	public void registerHelp() {
		final Set<HelpTopic> help = new TreeSet<HelpTopic>(HelpTopicComparator.helpTopicComparatorInstance());
		for (final String s : commandMap.keySet())
			if (!s.contains(".")) {
				final org.bukkit.command.Command cmd = map.getCommand(s);
				final HelpTopic topic = new GenericCommandHelpTopic(cmd);
				help.add(topic);
			}
		final IndexHelpTopic topic = new IndexHelpTopic(plugin.getName(), "All commands for " + plugin.getName(), null, help, "Below is a list of all " + plugin.getName() + " commands:");
		Bukkit.getServer().getHelpMap().addTopic(topic);
	}

	private void registerCommand(final CommandHandler command, final String label, final Method m, final Object obj) {
		final Entry<Method, Object> entry = new AbstractMap.SimpleEntry<Method, Object>(m, obj);
		commandMap.put(label.toLowerCase(), entry);
		if (AllAssets.masterSwitch && !cmds.contains(command.name())) {
			cmds.add(command.name());
			Bukkit.getLogger().info(AllAssets.shortTitleNoColor + "Added command: /" + command.name().replace(".", " "));
		}
		if (command.isListed()) {
			pluginCommands.add(command);
		}
		final String cmdLabel = label.replace(".", ",").split(",")[0].toLowerCase();
		if (map.getCommand(cmdLabel) == null) {
			final org.bukkit.command.Command cmd = new BukkitCommand(cmdLabel, plugin);
			map.register(plugin.getName(), cmd);
		}
		if (!command.description().equalsIgnoreCase("") && (cmdLabel == label))
			map.getCommand(cmdLabel).setDescription(command.description());
		if (!command.usage().equalsIgnoreCase("") && (cmdLabel == label))
			map.getCommand(cmdLabel).setUsage(command.usage());
	}

	private void registerCompleter(final String label, final Method m, final Object obj) {
		final String cmdLabel = label.replace(".", ",").split(",")[0].toLowerCase();
		if (map.getCommand(cmdLabel) == null) {
			final org.bukkit.command.Command command = new BukkitCommand(cmdLabel, plugin);
			map.register(plugin.getName(), command);
		}
		if (map.getCommand(cmdLabel) instanceof BukkitCommand) {
			final BukkitCommand command = (BukkitCommand) map.getCommand(cmdLabel);
			if (command.completer == null)
				command.completer = new BukkitCompleter();
			command.completer.addCompleter(label, m, obj);
		} else if (map.getCommand(cmdLabel) instanceof PluginCommand)
			try {
				final Object command = map.getCommand(cmdLabel);
				final Field field = command.getClass().getDeclaredField("completer");
				field.setAccessible(true);
				if (field.get(command) == null) {
					final BukkitCompleter completer = new BukkitCompleter();
					completer.addCompleter(label, m, obj);
					field.set(command, completer);
				} else if (field.get(command) instanceof BukkitCompleter) {
					final BukkitCompleter completer = (BukkitCompleter) field.get(command);
					completer.addCompleter(label, m, obj);
				} else
					System.out.println("Unable to register tab completer " + m.getName() + ". A tab completer is already registered for that command!");
			} catch (final Exception ex) {
				ex.printStackTrace();
			}
	}

	/** Command Framework - Command <br>
	 * The command annotation used to designate methods as commands. All methods
	 * should have a single CommandArgs argument
	 * 
	 * Things you can put in: name - name of the command permission - command
	 * permissions noPerm - message to send if you don't have the permission
	 * aliases - command aliases description - command description usage -
	 * command usage
	 * 
	 * @author minnymin3 */
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface CommandHandler {

		/** The name of the command. If it is a sub command then its values would
		 * be separated by periods. ie. a command that would be a subcommand of
		 * test would be 'test.subcommandname'
		 * 
		 * @return */
		public String name();

		/** Gets the required permission of the command
		 * 
		 * @return */
		public String permission() default "";

		/** A list of alternate names that the command is executed under. See
		 * name() for details on how names work
		 * 
		 * @return */
		public String[] aliases() default {};

		/** The description that will appear in /help of the command
		 * 
		 * @return */
		public String description() default "";

		/** The usage that will appear in /help (commandname)
		 * 
		 * @return */
		public String usage() default "";

		/** A custom annotation that decides weather or not the command is listed
		 * in /aio commands or not
		 * 
		 * @return */
		public boolean isListed() default true; //Nav
	}

	/** Command Framework - Completer <br>
	 * The completer annotation used to designate methods as command completers.
	 * All methods should have a single CommandArgs argument and return a String
	 * List object
	 * 
	 * @author minnymin3 */
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Completer {

		/** The command that this completer completes. If it is a sub command
		 * then its values would be separated by periods. ie. a command that
		 * would be a subcommand of test would be 'test.subcommandname'
		 * 
		 * @return */
		String name();

		/** A list of alternate names that the completer is executed under. See
		 * name() for details on how names work
		 * 
		 * @return */
		String[] aliases() default {};

	}

	/** Command Framework - BukkitCommand <br>
	 * An implementation of Bukkit's Command class allowing for registering of
	 * commands without plugin.yml
	 * 
	 * @author minnymin3 */
	class BukkitCommand extends org.bukkit.command.Command {

		private final Plugin owningPlugin;
		protected BukkitCompleter completer;
		private final CommandExecutor executor;

		/** A slimmed down PluginCommand
		 * 
		 * @param label
		 * @param owner */
		protected BukkitCommand(final String label, final Plugin owner) {
			super(label);
			this.executor = owner;
			this.owningPlugin = owner;
			this.usageMessage = "";
		}

		@Override
		public boolean execute(final CommandSender sender, final String commandLabel, final String[] args) {
			boolean success = false;

			if (!owningPlugin.isEnabled())
				return false;

			if (!testPermission(sender))
				return true;

			try {
				success = executor.onCommand(sender, this, commandLabel, args);
			} catch (final Throwable ex) {
				//Nav - try and sort this part out :)
				Bukkit.getLogger().severe("Unhandled exception executing command '" + commandLabel + "' in plugin " + owningPlugin.getDescription().getFullName() + " - " + ex.getCause().getMessage());
				ErrorUtils.generalCommandError(sender);
				return true;
			}

			if (!success && (usageMessage.length() > 0))
				for (final String line : usageMessage.replace("<command>", commandLabel).split("\n"))
					sender.sendMessage(line);

			return success;
		}

		@Override
		public List<String> tabComplete(final CommandSender sender, final String alias, final String[] args) throws CommandException, IllegalArgumentException {
			Validate.notNull(sender, "Sender cannot be null");
			Validate.notNull(args, "Arguments cannot be null");
			Validate.notNull(alias, "Alias cannot be null");

			List<String> completions = null;
			try {
				if (completer != null)
					completions = completer.onTabComplete(sender, this, alias, args);
				if ((completions == null) && (executor instanceof TabCompleter))
					completions = ((TabCompleter) executor).onTabComplete(sender, this, alias, args);
			} catch (final Throwable ex) {
				final StringBuilder message = new StringBuilder();
				message.append("Unhandled exception during tab completion for command '/").append(alias).append(' ');
				for (final String arg : args)
					message.append(arg).append(' ');
				message.deleteCharAt(message.length() - 1).append("' in plugin ").append(owningPlugin.getDescription().getFullName());
				throw new CommandException(message.toString(), ex);
			}

			if (completions == null)
				return super.tabComplete(sender, alias, args);
			return completions;
		}

	}

	/** Command Framework - BukkitCompleter <br>
	 * An implementation of the TabCompleter class allowing for multiple tab
	 * completers per command
	 * 
	 * @author minnymin3 */
	class BukkitCompleter implements TabCompleter {

		private final Map<String, Entry<Method, Object>> completers = new HashMap<String, Entry<Method, Object>>();

		public void addCompleter(final String label, final Method m, final Object obj) {
			completers.put(label, new AbstractMap.SimpleEntry<Method, Object>(m, obj));
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<String> onTabComplete(final CommandSender sender, final org.bukkit.command.Command command, final String label, final String[] args) {
			for (int i = args.length; i >= 0; i--) {
				final StringBuilder buffer = new StringBuilder();
				buffer.append(label.toLowerCase());
				for (int x = 0; x < i; x++)
					if (!args[x].equals("") && !args[x].equals(" "))
						buffer.append(".").append(args[x].toLowerCase());
				final String cmdLabel = buffer.toString();
				if (completers.containsKey(cmdLabel)) {
					final Entry<Method, Object> entry = completers.get(cmdLabel);
					try {
						return (List<String>) entry.getKey().invoke(entry.getValue(), new CommandArgs(sender, command, label, args, cmdLabel.split("\\.").length - 1));
					} catch (IllegalArgumentException | IllegalAccessException
							| InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			return null;
		}
	}

	/** Command Framework - CommandArgs <br>
	 * This class is passed to the command methods and contains various
	 * utilities as well as the command info.
	 * 
	 * @author minnymin3 */
	public class CommandArgs {

		private final CommandSender sender;
		private final org.bukkit.command.Command command;
		private final String label;
		private final String[] args;

		protected CommandArgs(final CommandSender sender, final org.bukkit.command.Command command, final String label, final String[] args, final int subCommand) {
			final String[] modArgs = new String[args.length - subCommand];
			System.arraycopy(args, 0 + subCommand, modArgs, 0, args.length - subCommand);

			final StringBuilder buffer = new StringBuilder();
			buffer.append(label);
			for (int x = 0; x < subCommand; x++)
				buffer.append(".").append(args[x]);
			final String cmdLabel = buffer.toString();
			this.sender = sender;
			this.command = command;
			this.label = cmdLabel;
			this.args = modArgs;
		}

		/** Gets the command sender
		 * 
		 * @return sender */
		public CommandSender getSender() {
			return sender;
		}

		/** Gets the original command object
		 * 
		 * @return */
		public org.bukkit.command.Command getCommand() {
			return command;
		}

		/** Gets the label including sub command labels of this command
		 * 
		 * @return Something like 'test.subcommand' */
		public String getLabel() {
			return label;
		}

		/** Gets all the arguments after the command's label. ie. if the command
		 * label was test.subcommand and the arguments were subcommand foo foo,
		 * it would only return 'foo foo' because 'subcommand' is part of the
		 * command
		 * 
		 * @return */
		public String[] getArgs() {
			return args;
		}

		public boolean isPlayer() {
			return sender instanceof Player;
		}

		public Player getPlayer() throws Exception {
			if (sender instanceof Player)
				return (Player) sender;
			else
				throw new NullPointerException();
		}
	}
}