package io.github.Skepter.AllAssets.Reflection;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Vault.Chat.AAChat;
import io.github.Skepter.AllAssets.Vault.Economy.AAEco;
import io.github.Skepter.AllAssets.Vault.Permissions.AAPerms;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.milkbowl.vault.Vault;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

/** Class to reflect into Vault's API (Until I actually launch the plugin and get
 * it approved by Vault)
 * 
 * @author Skepter */
public class VaultReflection {

	//Adjust to io.github.Skepter.AllAssets.Vault.<Economy/Permissions/Chat>.AA<Eco/PermsChat>
	public static final String economy = "io.github.Skepter.AllAssets.Vault.Economy.AAEco";
	public static final String permissions = "io.github.Skepter.AllAssets.Vault.Permissions.AAPerms";
	public static final String chat = "io.github.Skepter.AllAssets.Vault.Chat.AAChat";
	
	private final Vault vault;
	private final ServicesManager sm;

	public VaultReflection() {
		if (!AllAssets.instance().hasVault) {
			vault = null;
			sm = null;
			ErrorUtils.printErrorToConsole("Could not implement Vault hooks. Running in vault-independant mode");
			//Run in vault-independant mode xD
		} else {
			vault = (Vault) Bukkit.getPluginManager().getPlugin("Vault");
			sm = Bukkit.getServer().getServicesManager();
		}
	}

	private enum VaultType {
		ECONOMY, PERMISSION, CHAT;
	}

	private void load(VaultType type) {
		try {
			switch (type) {
			case ECONOMY:
				Method vaultEconomy = vault.getClass().getDeclaredMethod("hookEconomy", String.class, Class.class, ServicePriority.class, String[].class);
				vaultEconomy.setAccessible(true);
				vaultEconomy.invoke(vault, "AAEco", AAEco.class, ServicePriority.Highest, new String[] { economy });
				break;
			case PERMISSION:
				Permission aaPerms = (Permission) AAPerms.class.getConstructor(new Class[] { Plugin.class }).newInstance(new Object[] { vault });
				sm.register(Permission.class, aaPerms, vault, ServicePriority.Highest);

				Field permsField = vault.getClass().getDeclaredField("perms");
				permsField.setAccessible(true);
				permsField.set(vault, ((Permission) sm.getRegistration(Permission.class).getProvider()));
				break;
			case CHAT:
				Method vaultChat = vault.getClass().getDeclaredMethod("hookChat", String.class, Class.class, ServicePriority.class, String[].class);
				vaultChat.setAccessible(true);
				vaultChat.invoke(vault, "AAChat", AAChat.class, ServicePriority.Highest, new String[] { chat });
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadAAEco() {
		load(VaultType.ECONOMY);
	}
	
	public void loadAAPerms() {
		load(VaultType.PERMISSION);
	}
	
	public void loadAAChat() {
		load(VaultType.CHAT);
	}
}
