/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and Tundra
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.administration;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.utils.Strings;

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

	@CommandHandler(name = "butcher", aliases = { "killall" }, permission = "butcher", description = "Kills mobs")
	public void onCommand(final CommandArgs args) {
		boolean lightning, passive, hostile, explosion;
		explosion = lightning = passive = hostile = false;
		if (args.getArgs().length > 0)
			for (final String s : args.getArgs()) {
				if (s.contains("-l"))
					lightning = true;
				if (s.contains("-p") || s.contains("-a"))
					passive = true;
				if (s.contains("-h") || s.contains("-m"))
					hostile = true;
				if (s.contains("-e"))
					explosion = true;
			}
		int count = 0;
		if (args.isPlayer()) {
			final Player player = PlayerGetter.getPlayer(args);
			if (player != null) {
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
				player.sendMessage(Strings.TITLE + count + " entities removed");
			}
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
				args.getSender().sendMessage(Strings.TITLE + count + " entities removed from " + world.getName());
			}
		return;
	}

}
