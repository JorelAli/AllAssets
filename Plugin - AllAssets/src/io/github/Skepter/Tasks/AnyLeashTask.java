package io.github.Skepter.Tasks;

import io.github.Skepter.Utils.ErrorUtils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class AnyLeashTask implements Runnable {

	private final Player player;
	private LivingEntity entity;
	public AnyLeashTask(final Player player, final Entity entity) {
		this.player = player;
		if(entity instanceof LivingEntity)
			this.entity = (LivingEntity) entity;
	}
	
	@Override
	public void run() {
		if (!(entity).setLeashHolder(player))
			ErrorUtils.error(player, "You cannot put a leash on that mob");
	}

}
