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
package io.github.skepter.allassets.listeners;

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
			final Object object = arrow.getClass().getMethod("getHandle").invoke(arrow);
			final Field field = object.getClass().getField("fromPlayer");
			field.setInt(object, 1);
		} catch (final Exception e) {
			return;
		}
	}

}
