/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
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
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			switch (args.getArgs().length) {
			case 0:
			default:
				printHelp(player);
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
			case 2:
				if (TextUtils.isInteger(args.getArgs()[1])) {
					int m = Integer.parseInt(args.getArgs()[1]);
					switch (args.getArgs()[0].toLowerCase()) {
					case "arrow":
						Entity arrow = player.launchProjectile(Arrow.class);
						arrow.setVelocity(arrow.getVelocity().multiply(m));
						break;
					case "egg":
						Entity egg = player.launchProjectile(Egg.class);
						egg.setVelocity(egg.getVelocity().multiply(m));
						break;
					case "enderpearl":
						Entity enderpearl = player.launchProjectile(EnderPearl.class);
						enderpearl.setVelocity(enderpearl.getVelocity().multiply(m));
						break;
					case "fireball":
						Fireball fireball = player.launchProjectile(Fireball.class);
						fireball.setYield(m);
						break;
					case "largefireball":
						LargeFireball largeFireball = player.launchProjectile(LargeFireball.class);
						largeFireball.setYield(m);
						break;
					case "smallfireball":
						SmallFireball smallFireball = player.launchProjectile(SmallFireball.class);
						smallFireball.setYield(m);
						break;
					case "snowball":
						Entity snowball = player.launchProjectile(Snowball.class);
						snowball.setVelocity(snowball.getVelocity().multiply(m));
						break;
					case "thrownexpbottle":
						Entity xpBottle = player.launchProjectile(ThrownExpBottle.class);
						xpBottle.setVelocity(xpBottle.getVelocity().multiply(m));
						break;
					case "thrownpotion":
						Entity potion = player.launchProjectile(ThrownPotion.class);
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

			}
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
	
	@Help(name="Launch")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Launch", "/launch <projectile> - launches a projectile", "/launch <projectile> <value> - launches a projectile. If possible, the value will set the explosion size (fireball), or set the speed (arrow/snowball)");
	}

}
