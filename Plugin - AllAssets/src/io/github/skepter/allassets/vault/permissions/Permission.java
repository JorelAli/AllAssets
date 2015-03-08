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
package io.github.skepter.allassets.vault.permissions;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.api.utils.PlayerMap;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class Permission {

	private static PlayerMap<PermissionAttachment> map = new PlayerMap<PermissionAttachment>(AllAssets.instance());

	public static void registerPlayer(final Player player) {
		map.put(player, player.addAttachment(AllAssets.instance()));
	}

	public static void unregisterPlayer(final Player player) {
		player.removeAttachment(map.get(player));
	}

	/*
	 * Finally! After figuring out how to use the VaultAPI, I can begin working on a permission section :D
	 * 
	 * Command list (things to put into this section)
	 * 
	 * A command to see the heirarchy
	 * World permissions
	 * Listings of everything (users etc.)
	 * - List user will show their permissions, prefix/suffix
	 * A way of adding perms for a user IN A CERTAIN WORLD
	 * """""""""""""""""""""""""""""""" GLOBALLY
	 * A way of undoing what I just said (removing it)
	 * 
	 * Creating groups
	 * Group world perms
	 * Group prefixes
	 * Inheritence - perhaps use a GUI for this? Seems cleaner
	 * 
	 * A simple promote/demote command - a way of setting heiarachy
	 * 
	 * Checking stuff (if <player> has perm)
	 * 
	 * save/load - store in .yml for easy cheesy management
	 */
}
