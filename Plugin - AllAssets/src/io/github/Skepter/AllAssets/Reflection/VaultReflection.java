package io.github.Skepter.AllAssets.Reflection;

import io.github.Skepter.AllAssets.Vault.AAChat;
import io.github.Skepter.AllAssets.Vault.AAEco;
import io.github.Skepter.AllAssets.Vault.AAPerms;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.milkbowl.vault.Vault;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

/** Class to reflect into Vault's API (Until I actually launch the plugin and get
 * it approved by Vault) TODO: Remove me!
 * 
 * @author Skepter */
public class VaultReflection {

	public static final String economy = "io.github.Skepter.AllAssets.Vault.AAEco";
	public static final String permissions = "io.github.Skepter.AllAssets.Vault.AAPerms";
	public static final String chat = "io.github.Skepter.AllAssets.Vault.AAChat";

	public static void hookIntoVaultEconomy() {
		Vault vault = (Vault) Bukkit.getPluginManager().getPlugin("Vault");
		try {
			Method method = vault.getClass().getDeclaredMethod("hookEconomy", String.class, Class.class, ServicePriority.class, String[].class);
			method.setAccessible(true);
			method.invoke(vault, "AAEco", AAEco.class, ServicePriority.Highest, new String[] { economy });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void hookIntoVaultPermission() {
		Vault vault = (Vault) Bukkit.getPluginManager().getPlugin("Vault");
		try {
			ServicesManager sm = Bukkit.getServer().getServicesManager();
			Permission aaPerms = (Permission) AAPerms.class.getConstructor(new Class[] { Plugin.class }).newInstance(new Object[] { vault });
			sm.register(Permission.class, aaPerms, vault, ServicePriority.Highest);

			Field permsField = vault.getClass().getDeclaredField("perms");
			permsField.setAccessible(true);
			permsField.set(vault, ((Permission) sm.getRegistration(Permission.class).getProvider()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void hookIntoVaultChat() {
		Vault vault = (Vault) Bukkit.getPluginManager().getPlugin("Vault");
		try {
			Method method = vault.getClass().getDeclaredMethod("hookChat", String.class, Class.class, ServicePriority.class, String[].class);
			method.setAccessible(true);
			method.invoke(vault, "AAChat", AAChat.class, ServicePriority.Highest, new String[] { chat });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
