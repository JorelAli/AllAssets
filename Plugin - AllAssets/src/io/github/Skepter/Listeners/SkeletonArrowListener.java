package io.github.Skepter.Listeners;

import java.lang.reflect.Field;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

/* May rename to MobListener */
public class SkeletonArrowListener implements Listener {

	@EventHandler
	public void onProjectileHit(final ProjectileHitEvent event) {
		final Entity entity = event.getEntity();
		if (!(entity instanceof Arrow))
			return;
		final Arrow arrow = (Arrow) entity;
		try {
			Object object = arrow.getClass().getMethod("getHandle").invoke(arrow);
			Field field = object.getClass().getField("fromPlayer");
			field.setInt(object, 1);
		} catch (Exception e) {
			return;
		}
	}

}
