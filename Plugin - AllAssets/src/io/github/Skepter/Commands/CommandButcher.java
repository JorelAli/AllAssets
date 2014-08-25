package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class CommandButcher {

	public CommandButcher(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "butcher", aliases = { "killall" }, permission = "butcher", description = "Kills mobs", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final Player player = args.getPlayer();
		int count = 0;
		for (Entity entity : player.getWorld().getEntities()) {
			if (entity instanceof LivingEntity) {
				entity.remove();
				count++;
			}
		}
		player.sendMessage(AllAssets.instance().title + count + " entities removed");
		//butcher mobs/animals/npc/villagers/<animalName>
		return;
	}

}
