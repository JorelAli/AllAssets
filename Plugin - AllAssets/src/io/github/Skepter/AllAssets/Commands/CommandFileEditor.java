/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.YesNoConversation;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.conversations.BooleanPrompt;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;

public class CommandFileEditor {

	public CommandFileEditor(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "fileeditor", aliases = { "fe" }, permission = "fileeditor", description = "Edits files", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		switch (args.getArgs().length) {
		case 0:
		case 1:
		case 2:
			player.sendMessage("Syntax");
			player.sendMessage("/fe <FILE> <SETTING NAME> <DATA>");
			player.sendMessage("File: folder/folder/folder/fileName.yml");
			player.sendMessage("e.g.: plugins/AllAssets/config.yml");
			return;
		case 3:
			File dataFile = new File("." + args.getArgs()[0].replace("/", File.separator));
			YamlConfiguration config = new YamlConfiguration();
			try {
				config.load(dataFile);
			} catch (Exception e) {
				ErrorUtils.error(player, "That file could not be read!");
				return;
			}
			new YesNoConversation(player, new EditFilePrompt(dataFile, config, args.getArgs()[1], args.getArgs()[2]), "Are you sure you want to change " + args.getArgs()[1] + " to " + args.getArgs()[2] + " - this cannot be undone!");
			return;
		}

	}
}

class EditFilePrompt extends BooleanPrompt {
	
	File file;
	YamlConfiguration config;
	String setting;
	String value;

	public EditFilePrompt(File file, YamlConfiguration config, String setting, String value) {
		this.file = file;
		this.config = config;
		this.setting = setting;
		this.value = value;
	}

	@Override
	public String getPromptText(ConversationContext context) {
		return YesNoConversation.getPromptText();
	}

	@Override
	protected Prompt acceptValidatedInput(ConversationContext context, boolean b) {
		if (b) {
			config.set(setting, value);
			try {
				config.save(file);
			} catch (IOException e) {
				context.getForWhom().sendRawMessage(AllAssets.error + "There was an error whilst changing that value");
				return Prompt.END_OF_CONVERSATION;
			}
			context.getForWhom().sendRawMessage(AllAssets.title + "Changed " + setting + " to " + value);
		}	
		return Prompt.END_OF_CONVERSATION;
	}
}
