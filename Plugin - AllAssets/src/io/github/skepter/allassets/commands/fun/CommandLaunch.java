/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter 
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
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
package io.github.skepter.allassets.commands.fun;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.CommandFramework.Completer;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.WitherSkull;

public class CommandLaunch {

	public CommandLaunch(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "launch", permission = "launch", description = "Launches a projectile")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			switch (args.getArgs().length) {
				case 0:
				default:
					printHelp(player);
					break;
				case 1:
					switch (args.getArgs()[0].toLowerCase()) {
						case "arrow":
							player.launchProjectile(Arrow.class);
							break;
						case "egg":
							player.launchProjectile(Egg.class);
							break;
						case "enderpearl":
							player.launchProjectile(EnderPearl.class);
							break;
						case "fireball":
							player.launchProjectile(Fireball.class);
							break;
						case "largefireball":
							player.launchProjectile(LargeFireball.class);
							break;
						case "smallfireball":
							player.launchProjectile(SmallFireball.class);
							break;
						case "snowball":
							player.launchProjectile(Snowball.class);
							break;
						case "thrownexpbottle":
							player.launchProjectile(ThrownExpBottle.class);
							break;
						case "thrownpotion":
							player.launchProjectile(ThrownPotion.class);
							break;
						case "witherskull":
							player.launchProjectile(WitherSkull.class);
							break;
						case "bluewitherskull":
							player.launchProjectile(WitherSkull.class).setCharged(true);
							break;
						default:
							break;
					}
					break;
				case 2:
					if (TextUtils.isInteger(args.getArgs()[1]) || TextUtils.isFloat(args.getArgs()[1])) {
						final float m = Float.parseFloat(args.getArgs()[1]);
						switch (args.getArgs()[0].toLowerCase()) {
							case "arrow":
								final Entity arrow = player.launchProjectile(Arrow.class);
								arrow.setVelocity(arrow.getVelocity().multiply(m));
								break;
							case "egg":
								final Entity egg = player.launchProjectile(Egg.class);
								egg.setVelocity(egg.getVelocity().multiply(m));
								break;
							case "enderpearl":
								final Entity enderpearl = player.launchProjectile(EnderPearl.class);
								enderpearl.setVelocity(enderpearl.getVelocity().multiply(m));
								break;
							case "fireball":
								final Fireball fireball = player.launchProjectile(Fireball.class);
								fireball.setYield(m);
								break;
							case "largefireball":
								final LargeFireball largeFireball = player.launchProjectile(LargeFireball.class);
								largeFireball.setYield(m);
								break;
							case "smallfireball":
								final SmallFireball smallFireball = player.launchProjectile(SmallFireball.class);
								smallFireball.setYield(m);
								break;
							case "snowball":
								final Entity snowball = player.launchProjectile(Snowball.class);
								snowball.setVelocity(snowball.getVelocity().multiply(m));
								break;
							case "thrownexpbottle":
								final Entity xpBottle = player.launchProjectile(ThrownExpBottle.class);
								xpBottle.setVelocity(xpBottle.getVelocity().multiply(m));
								break;
							case "thrownpotion":
								final Entity potion = player.launchProjectile(ThrownPotion.class);
								potion.setVelocity(potion.getVelocity().multiply(m));
								break;
							case "witherskull":
								player.launchProjectile(WitherSkull.class);
								break;
							case "bluewitherskull":
								player.launchProjectile(WitherSkull.class).setCharged(true);
								break;
							default:
								break;
						}
					}
					break;
			}
	}

	@Completer(name = "launch")
	public List<String> testCompleter(final CommandArgs args) {
		final List<String> list = new ArrayList<String>();
		list.add("arrow");
		list.add("egg");
		list.add("enderpearl");
		list.add("fireball");
		list.add("largefireball");
		list.add("smallfireball");
		list.add("snowball");
		list.add("thrownexpbottle");
		list.add("thrownpotion");
		list.add("witherskull");
		list.add("bluewitherskull");
		return list;
	}

	@Help(name = "Launch")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Launch", "/launch <projectile> - launches a projectile", "/launch <projectile> <value> - launches a projectile. If possible, the value will set the explosion size (fireball), or set the speed (arrow/snowball)");
	}

}
