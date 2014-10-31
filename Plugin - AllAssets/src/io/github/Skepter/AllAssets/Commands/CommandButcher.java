package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;

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
		boolean lightning, passive, hostile, explosion;
		explosion = lightning = passive = hostile = false;
		if (args.getArgs().length > 0)
			for (final String s : args.getArgs()) {
				if (s.contains("-l"))
					lightning = true;
				if (s.contains("-p"))
					passive = true;
				if (s.contains("-h"))
					hostile = true;
				if (s.contains("-e"))
					explosion = true;
			}
		int count = 0;
		if (args.isPlayer()) {
			Player player = null;
			try {
				player = args.getPlayer();
			} catch (final Exception e) {
				ErrorUtils.playerOnly(args.getSender());
			}
			final Iterator<Entity> iterator = player.getWorld().getEntities().iterator();
			while (iterator.hasNext()) {
				final Entity entity = iterator.next();
				if ((entity instanceof LivingEntity) && !(entity instanceof Player)) {
					if (passive)
						if (entity instanceof Monster)
							continue;
					if (hostile)
						if (entity instanceof Animals)
							continue;
					if (lightning)
						entity.getWorld().strikeLightningEffect(entity.getLocation());
					if (explosion)
							entity.getWorld().createExplosion(entity.getLocation(), 0.0F);
					entity.remove();
					count++;
				}
			}
			player.sendMessage(AllAssets.title + count + " entities removed");
		} else
			for (final World world : Bukkit.getWorlds()) {
				final Iterator<Entity> iterator = world.getEntities().iterator();
				while (iterator.hasNext()) {
					final Entity entity = iterator.next();
					if ((entity instanceof LivingEntity) && !(entity instanceof Player)) {
						if (passive)
							if (entity instanceof Monster)
								continue;
						if (hostile)
							if (entity instanceof Animals)
								continue;
						if (lightning)
							entity.getWorld().strikeLightningEffect(entity.getLocation());
						if (explosion)
								entity.getWorld().createExplosion(entity.getLocation(), 0.0F);
						entity.remove();
						count++;
					}
				}
				args.getSender().sendMessage(AllAssets.title + count + " entities removed from " + world.getName());
			}
		return;
	}

}
