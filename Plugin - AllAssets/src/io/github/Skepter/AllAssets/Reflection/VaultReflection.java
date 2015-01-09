package io.github.Skepter.AllAssets.Reflection;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Vault.AAEco;
import io.github.Skepter.AllAssets.Vault.AAPerms;

import java.lang.reflect.Method;

import net.milkbowl.vault.Vault;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
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
//			net.milkbowl.vault.permission.Permission perms = new Permission_SuperPerms(vault);

			AAPerms p = new AAPerms(AllAssets.instance());
			sm.register(net.milkbowl.vault.permission.Permission.class, p, AllAssets.instance(), ServicePriority.Highest);

			//sm.unregister(net.milkbowl.vault.permission.Permission.class, perms);

			for (RegisteredServiceProvider<?> registration : sm.getRegistrations(vault)) {
				System.out.println(registration.getProvider());
			}

			net.milkbowl.vault.permission.Permission p1 = (net.milkbowl.vault.permission.Permission) AAPerms.class.getConstructor(new Class[] { Plugin.class }).newInstance(new Object[] { vault });
			sm.register(net.milkbowl.vault.permission.Permission.class, p1, vault, ServicePriority.Highest);
			Bukkit.getLogger().info(String.format("[%s][Permission] %s found: %s", new Object[] { "AA", "AAPerms", (p1.isEnabled()) ? "Loaded" : "Waiting" }));
			//sm.unregister(net.milkbowl.vault.permission.Permission.class, p1);

			for (RegisteredServiceProvider<?> registration : sm.getRegistrations(vault)) {
				System.out.println(registration.getProvider());
			}


			//			vault.perms = ((net.milkbowl.vault.permission.Permission) vault.sm.getRegistration(net.milkbowl.vault.permission.Permission.class).getProvider());

//			Method method = vault.getClass().getDeclaredMethod("hookPermission", String.class, Class.class, ServicePriority.class, String[].class);
//			method.setAccessible(true);
//			method.invoke(vault, "AAPerms", AAEco.class, ServicePriority.Highest, new String[] { permissions });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void hookIntoVaultChat() {
		Vault vault = (Vault) Bukkit.getPluginManager().getPlugin("Vault");
		try {
			Method method = vault.getClass().getDeclaredMethod("hookChat", String.class, Class.class, ServicePriority.class, String[].class);
			method.setAccessible(true);
			method.invoke(vault, "AAChat", AAEco.class, ServicePriority.Highest, new String[] { chat });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
