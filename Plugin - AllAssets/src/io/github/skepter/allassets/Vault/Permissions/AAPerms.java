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
package io.github.skepter.allassets.Vault.Permissions;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.reflection.VaultReflection;

import java.util.logging.Logger;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;

public class AAPerms extends Permission {

	private static final Logger log = Bukkit.getLogger();

	private final String name = "AAPerms";
	private AllAssets allAssetsPlugin;

	public AAPerms(final Plugin plugin) {
		if (this.allAssetsPlugin == null) {
			final Plugin allAssets = plugin.getServer().getPluginManager().getPlugin("AllAssets");
			if ((allAssets != null) && (allAssets.isEnabled())) {
				this.allAssetsPlugin = ((AllAssets) allAssets);
				Bukkit.getServer().getPluginManager().registerEvents(new PermissionServerListener(this), plugin);
				log.info(String.format("[%s][Permission] %s hooked.", new Object[] { "AA", name }));
			}
		}
	}

	public class PermissionServerListener implements Listener {
		AAPerms permissions = null;

		public PermissionServerListener(final AAPerms aaeco) {
			this.permissions = aaeco;
		}

		@EventHandler(priority = EventPriority.MONITOR)
		public void onPluginEnable(final PluginEnableEvent event) {
			if (this.permissions.allAssetsPlugin == null) {
				final Plugin perms = event.getPlugin();
				if ((perms.getDescription().getName().equals("AllAssets")) && (perms.getClass().getName().equals(VaultReflection.permissions))) {
					permissions.allAssetsPlugin = (AllAssets) perms;
					AAPerms.log.info(String.format("[%s][Permission] %s hooked.", new Object[] { "AA", name }));
				}
			}
		}

		@EventHandler(priority = EventPriority.MONITOR)
		public void onPluginDisable(final PluginDisableEvent event) {
			if ((this.permissions.allAssetsPlugin == null) || (!(event.getPlugin().getDescription().getName().equals("AllAssets"))))
				return;
			permissions.allAssetsPlugin = null;
			AAPerms.log.info(String.format("[%s][Permission] %s unhooked.", new Object[] { "AA", name }));
		}
	}

	@Override
	public String[] getGroups() {

		return null;
	}

	@Override
	public String[] getPlayerGroups(final String world, final String playerName) {

		return null;
	}

	@Override
	public String getPrimaryGroup(final String world, final String playerName) {

		return null;
	}

	@Override
	public boolean groupAdd(final String world, final String group, final String permission) {

		return false;
	}

	@Override
	public boolean groupHas(final String world, final String group, final String permission) {

		return false;
	}

	@Override
	public boolean groupRemove(final String world, final String group, final String permission) {

		return false;
	}

	@Override
	public boolean hasGroupSupport() {
		return true;
	}

	@Override
	public boolean hasSuperPermsCompat() {
		return false;
	}

	@Override
	public boolean playerAdd(final String world, final String player, final String permission) {

		return false;
	}

	@Override
	public boolean playerAddGroup(final String world, final String player, final String group) {

		return false;
	}

	@Override
	public boolean playerHas(final String world, final String player, final String permission) {

		return false;
	}

	@Override
	public boolean playerInGroup(final String world, final String player, final String group) {

		return false;
	}

	@Override
	public boolean playerRemove(final String world, final String player, final String permission) {

		return false;
	}

	@Override
	public boolean playerRemoveGroup(final String world, final String player, final String group) {

		return false;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isEnabled() {
		return Bukkit.getPluginManager().getPlugin("AllAssets").isEnabled() ? true : false;
	}

}
