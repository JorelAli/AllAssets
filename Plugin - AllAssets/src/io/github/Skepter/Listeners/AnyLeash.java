package io.github.Skepter.Listeners;

import io.github.Skepter.AllAssets;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class AnyLeash implements Listener {

	@EventHandler
	public void logError(final PlayerInteractEntityEvent event) {
		if (event.getPlayer().getItemInHand().getType().equals(Material.LEASH) && event.getPlayer().hasPermission("AllAssets.anyleash") && event.getRightClicked() instanceof LivingEntity)
			new BukkitRunnable() {
				@Override
				public void run() {
					((LivingEntity) event.getRightClicked()).setLeashHolder(event.getPlayer());
				}
			}.runTaskLater(AllAssets.instance(), 10L);

	}
}
