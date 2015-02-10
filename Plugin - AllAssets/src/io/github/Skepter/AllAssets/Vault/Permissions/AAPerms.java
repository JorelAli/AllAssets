package io.github.Skepter.AllAssets.Vault.Permissions;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Reflection.VaultReflection;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getPlayerGroups(final String paramString1, final String paramString2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrimaryGroup(final String paramString1, final String paramString2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean groupAdd(final String paramString1, final String paramString2, final String paramString3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean groupHas(final String paramString1, final String paramString2, final String paramString3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean groupRemove(final String paramString1, final String paramString2, final String paramString3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasGroupSupport() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasSuperPermsCompat() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerAdd(final String paramString1, final String paramString2, final String paramString3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerAddGroup(final String paramString1, final String paramString2, final String paramString3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerHas(final String paramString1, final String paramString2, final String paramString3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerInGroup(final String paramString1, final String paramString2, final String paramString3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerRemove(final String paramString1, final String paramString2, final String paramString3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerRemoveGroup(final String paramString1, final String paramString2, final String paramString3) {
		// TODO Auto-generated method stub
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
