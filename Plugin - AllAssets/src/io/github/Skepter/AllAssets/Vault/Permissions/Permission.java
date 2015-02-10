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
package io.github.Skepter.AllAssets.Vault.Permissions;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.API.PlayerMap;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class Permission {

	private static PlayerMap<UUID, PermissionAttachment> map = new PlayerMap<UUID, PermissionAttachment>(AllAssets.instance());

	public static void registerPlayer(final Player player) {
		map.put(player, player.addAttachment(AllAssets.instance()));
	}

	public static void unregisterPlayer(final Player player) {
		player.removeAttachment(map.get(player));
	}
	
	//You know, is this really needed? One can use PEX or GroupManager or whatever nowadays...
	//Probably. PEX and GM are fantastic permission plugins, but are sometimes hard to use

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
	
	/* Sample format:
	 * 
	 * Worlds:
	 *   <WorldName>:
	 *     Groups:
	 *       <GroupName>:
	 *         Permissions:
	 *           - permsList
	 *         Prefix: [prefix]
	 *         Suffix: [suffix]
	 *       <GroupName>:
	 *         Permissions:
	 *           - permsList
	 *         Prefix: [prefix]
	 *         Suffix: [suffix]
	 *     Players:
	 *     	 <PlayerName>:
	 *         Permissions:
	 *           - permsList
	 *         Prefix: [prefix]
	 *         Suffix: [suffix]
	 *       <PlayerName>:
	 *         Permissions:
	 *           - permsList
	 *         Prefix: [prefix]
	 *         Suffix: [suffix]
	 * Global:
	 *   Groups:
	 *     <GroupName>:
	 *       Permissions:
	 *         - permsList
	 *       Prefix: [prefix]
	 *       Suffix: [suffix]
	 *     <GroupName>:
	 *       Permissions:
	 *         - permsList
	 *       Prefix: [prefix]
	 *       Suffix: [suffix]
	 *   Players:
	 *     <PlayerName>:
	 *       Permissions:
	 *       - permsList
	 *       Prefix: [prefix]
	 *       Suffix: [suffix]
	 *     <PlayerName>:
	 *       Permissions:
	 *         - permsList
	 *       Prefix: [prefix]
	 *       Suffix: [suffix]     
	 */

}