package io.github.Skepter.Commands;

import io.github.Skepter.AllInOne;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CommandSet implements Listener {

	HashMap<UUID, Integer> jump = new HashMap<UUID, Integer>();
	HashMap<UUID, Double> attack = new HashMap<UUID, Double>();

	public CommandSet(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	// - SET <health/hunger/speed/flyspeed/jump (height)/attack
	// (strength)/balance> <data/player> <<data>>

	@CommandHandler(name = "set", permission = "AllInOne.set", description = "Sets data for a player", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final Player player = args.getPlayer();
		if ((args.getArgs().length == 0) || (args.getArgs().length == 1)) {
			ErrorUtils.notEnoughArguments(player);
			return;
		} else if (args.getArgs().length == 2) {
			double arg1 = 0;
			try {
				arg1 = Double.parseDouble(args.getArgs()[1]);
			} catch (final NumberFormatException e) {
				ErrorUtils.notAnInteger(player);
				return;
			}
			double Darg1 = 0;
			try {
				Darg1 = Double.parseDouble(args.getArgs()[1]);
			} catch (final NumberFormatException e) {
				ErrorUtils.notAnInteger(player);
				return;
			}
			switch (args.getArgs()[0]) {
			case "health":
				player.setHealth(arg1);
				return;
			case "hunger":
				player.setFoodLevel((int) arg1);
				return;
			case "speed":
				player.setWalkSpeed((float) arg1);
				return;
			case "flyspeed":
				player.setFlySpeed((float) arg1);
				return;
			case "jump":
				jump.put(player.getUniqueId(), (int) arg1);
				return;
			case "attack":
				attack.put(player.getUniqueId(), Darg1);
				return;
			case "balance":
				if(AllInOne.instance().hasVault)
					AllInOne.instance().economy.depositPlayer(player.getName(), Darg1);
					//send balance
				else
					ErrorUtils.vaultNotFound(player);
				return;
			}
		} else if (args.getArgs().length == 3) {
			// Same, except for another player
		}
		//put tab complete for this!
		return;
	}

//	@EventHandler
//	public void onJump(PlayerMoveEvent event) {
//		Block block, control;
//		Vector dir = event.getPlayer().getVelocity().setY(2);
//		if (event.getTo().getY() > event.getFrom().getY()) {
//			block = event.getPlayer().getWorld().getBlockAt(new Location(event.getPlayer().getWorld(), event.getTo().getX(), event.getTo().getY() + 2, event.getTo().getZ()));
//			control = event.getPlayer().getWorld().getBlockAt(new Location(event.getPlayer().getWorld(), event.getTo().getX(), event.getTo().getY() - 2, event.getTo().getZ()));
//			if (!(block.getType() != Material.AIR || control.getType() == Material.AIR)) {
//				event.getPlayer().setVelocity(dir);
//			}
//		}
//	}

	@EventHandler
	public void onAttack(final EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			final Player player = (Player) event.getDamager();
			if(attack.containsKey(player.getUniqueId()))
				event.setDamage(attack.get(player.getUniqueId()));
		}
	}
}