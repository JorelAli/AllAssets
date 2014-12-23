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
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands.Administration;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
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
