package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

public class CommandButcher {

	public CommandButcher(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "butcher", aliases = { "killall" }, permission = "butcher", description = "Kills mobs", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		boolean lightning, passive, hostile;
		lightning = passive = hostile = false;
		if (args.getArgs().length > 0) {
			for (String s : args.getArgs()) {
				if (s.contains("-l"))
					lightning = true;
				if (s.contains("-p"))
					passive = true;
				if (s.contains("-h"))
					hostile = true;
			}
		}
		int count = 0;
		if (args.isPlayer()) {
			Player player = null;
			try {
				player = args.getPlayer();
			} catch (Exception e) {
				ErrorUtils.playerOnly(args.getSender());
			}
			Iterator<Entity> iterator = player.getWorld().getEntities().iterator();
			while (iterator.hasNext()) {
				Entity entity = (Entity) iterator.next();
				if (entity instanceof LivingEntity && !(entity instanceof Player)) {
					if (passive)
						if (entity instanceof Monster)
							continue;
					if (hostile)
						if (entity instanceof Animals)
							continue;
					if (lightning)
						entity.getWorld().strikeLightningEffect(entity.getLocation());
					entity.remove();
					count++;
				}
			}
			player.sendMessage(AllAssets.instance().title + count + " entities removed");
		} else {
			for (World world : Bukkit.getWorlds()) {
				Iterator<Entity> iterator = world.getEntities().iterator();
				while (iterator.hasNext()) {
					Entity entity = (Entity) iterator.next();
					if (entity instanceof LivingEntity && !(entity instanceof Player)) {
						if (passive)
							if (entity instanceof Monster)
								continue;
						if (hostile)
							if (entity instanceof Animals)
								continue;
						if (lightning)
							entity.getWorld().strikeLightningEffect(entity.getLocation());
						entity.remove();
						count++;
					}
				}
				args.getSender().sendMessage(AllAssets.instance().title + count + " entities removed from " + world.getName());
			}

		}
		return;
	}

}
