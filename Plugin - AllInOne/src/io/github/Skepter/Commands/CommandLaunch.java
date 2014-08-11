package io.github.Skepter.Commands;

import java.util.ArrayList;
import java.util.List;

import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Commands.CommandFramework.Completer;
import io.github.Skepter.Utils.ErrorUtils;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Fish;
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

	@CommandHandler(name = "launch", permission = "AllInOne.launch", description = "Launches a projectile", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final Player player = args.getPlayer();
		if(!(args.getArgs().length == 1)) {
			ErrorUtils.notEnoughArguments(player);
		}
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
		case "fish":
			player.launchProjectile(Fish.class);
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
		default:
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
		list.add("fish");
		list.add("largefireball");
		list.add("smallfireball");
		list.add("snowball");
		list.add("thrownexpbottle");
		list.add("thrownpotion");
		list.add("witherskull");
		return list;
	}

}
