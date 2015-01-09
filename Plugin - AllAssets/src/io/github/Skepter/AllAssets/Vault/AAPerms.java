package io.github.Skepter.AllAssets.Vault;

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

	public AAPerms(Plugin plugin) {
		if (this.allAssetsPlugin == null) {
			Plugin allAssets = plugin.getServer().getPluginManager().getPlugin("AllAssets");
			if ((allAssets != null) && (allAssets.isEnabled())) {
				this.allAssetsPlugin = ((AllAssets) allAssets);
				Bukkit.getServer().getPluginManager().registerEvents(new PermissionServerListener(this), plugin);
				log.info(String.format("[%s][Permissions] %s hooked.", new Object[] { "AA", name }));
			}
		}
	}

	public class PermissionServerListener implements Listener {
		AAPerms permissions = null;

		public PermissionServerListener(AAPerms aaeco) {
			this.permissions = aaeco;
		}

		@EventHandler(priority = EventPriority.MONITOR)
		public void onPluginEnable(PluginEnableEvent event) {
			if (this.permissions.allAssetsPlugin == null) {
				Plugin perms = event.getPlugin();
				if ((perms.getDescription().getName().equals("AllAssets")) && (perms.getClass().getName().equals(VaultReflection.economy))) {
					permissions.allAssetsPlugin = (AllAssets) perms;
					AAPerms.log.info(String.format("[%s][Permissions] %s hooked.", new Object[] { "AA", "AAPerms" }));
				}
			}
		}

		@EventHandler(priority = EventPriority.MONITOR)
		public void onPluginDisable(PluginDisableEvent event) {
			if ((this.permissions.allAssetsPlugin == null) || (!(event.getPlugin().getDescription().getName().equals("AllAssets"))))
				return;
			permissions.allAssetsPlugin = null;
			AAPerms.log.info(String.format("[%s][Permissions] %s unhooked.", new Object[] { "AA", "AAPerms" }));
		}
	}

	@Override
	public String[] getGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getPlayerGroups(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrimaryGroup(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean groupAdd(String paramString1, String paramString2, String paramString3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean groupHas(String paramString1, String paramString2, String paramString3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean groupRemove(String paramString1, String paramString2, String paramString3) {
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
	public boolean playerAdd(String paramString1, String paramString2, String paramString3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerAddGroup(String paramString1, String paramString2, String paramString3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerHas(String paramString1, String paramString2, String paramString3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerInGroup(String paramString1, String paramString2, String paramString3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerRemove(String paramString1, String paramString2, String paramString3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerRemoveGroup(String paramString1, String paramString2, String paramString3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		return Bukkit.getPluginManager().getPlugin("AllAssets").isEnabled() ? true : false;
	}

}
