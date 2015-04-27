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
package io.github.skepter.allassets.vault.economy;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.config.ConfigHandler;
import io.github.skepter.allassets.reflection.VaultReflection;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;

public class AAEco implements Economy {

	public class EconomyServerListener implements Listener {
		AAEco economy = null;

		public EconomyServerListener(final AAEco aaeco) {
			this.economy = aaeco;
		}

		@EventHandler(priority = EventPriority.MONITOR)
		public void onPluginDisable(final PluginDisableEvent event) {
			if ((this.economy.allAssetsPlugin == null) || (!(event.getPlugin().getDescription().getName().equals("AllAssets"))))
				return;
			economy.allAssetsPlugin = null;
			AAEco.log.info(String.format("[%s][Economy] %s unhooked.", new Object[] { "AA", name }));
		}

		@EventHandler(priority = EventPriority.MONITOR)
		public void onPluginEnable(final PluginEnableEvent event) {
			if (this.economy.allAssetsPlugin == null) {
				final Plugin ec = event.getPlugin();
				if ((ec.getDescription().getName().equals("AllAssets")) && (ec.getClass().getName().equals(VaultReflection.economy))) {
					economy.allAssetsPlugin = (AllAssets) ec;
					AAEco.log.info(String.format("[%s][Economy] %s hooked.", new Object[] { "AA", name }));
				}
			}
		}
	}

	private final static Logger log = Bukkit.getLogger();
	private AllAssets allAssetsPlugin;

	private final String name = "AAEco";

	private final EconomyResponse bankEconomyResponse = new EconomyResponse(0.0D, 0.0D, ResponseType.NOT_IMPLEMENTED, name + " does not support banks!");

	public AAEco(final Plugin plugin) {
		if (this.allAssetsPlugin == null) {
			final Plugin allAssets = plugin.getServer().getPluginManager().getPlugin("AllAssets");
			if ((allAssets != null) && (allAssets.isEnabled())) {
				this.allAssetsPlugin = ((AllAssets) allAssets);
				Bukkit.getServer().getPluginManager().registerEvents(new EconomyServerListener(this), plugin);
				log.info(String.format("[%s][Economy] %s hooked.", new Object[] { "AA", name }));
			}
		}
	}

	@Override
	public EconomyResponse bankBalance(final String paramString) {
		return bankEconomyResponse;
	}

	@Override
	public EconomyResponse bankDeposit(final String paramString, final double paramDouble) {
		return bankEconomyResponse;
	}

	@Override
	public EconomyResponse bankHas(final String paramString, final double paramDouble) {
		return bankEconomyResponse;
	}

	@Override
	public EconomyResponse bankWithdraw(final String paramString, final double paramDouble) {
		return bankEconomyResponse;
	}

	@Override
	public EconomyResponse createBank(final String arg0, final OfflinePlayer arg1) {
		return bankEconomyResponse;
	}

	@Override
	public EconomyResponse createBank(final String paramString1, final String paramString2) {
		return bankEconomyResponse;
	}

	@Override
	public boolean createPlayerAccount(final OfflinePlayer arg0) {

		return false;
	}

	@Override
	public boolean createPlayerAccount(final OfflinePlayer arg0, final String arg1) {

		return false;
	}

	@Override
	public boolean createPlayerAccount(final String playerName) {

		return false;
	}

	@Override
	public boolean createPlayerAccount(final String playerName, final String worldName) {

		return false;
	}

	@Override
	public String currencyNamePlural() {
		return ConfigHandler.config().getString("currencyName");
	}

	@Override
	public String currencyNameSingular() {
		return ConfigHandler.config().getString("currencyNameSing");
	}

	@Override
	public EconomyResponse deleteBank(final String paramString) {
		return bankEconomyResponse;
	}

	@Override
	public EconomyResponse depositPlayer(final OfflinePlayer arg0, final double arg1) {

		return null;
	}

	@Override
	public EconomyResponse depositPlayer(final OfflinePlayer arg0, final String arg1, final double arg2) {

		return null;
	}

	@Override
	public EconomyResponse depositPlayer(final String playerName, final double value) {

		return null;
	}

	@Override
	public EconomyResponse depositPlayer(final String playerName, final String worldName, final double value) {

		return null;
	}

	@Override
	//format into readable string
	public String format(final double value) {

		return null;
	}

	@Override
	public int fractionalDigits() {
		return -1;
	}

	@Override
	public double getBalance(final OfflinePlayer arg0) {

		return 0;
	}

	@Override
	public double getBalance(final OfflinePlayer arg0, final String arg1) {

		return 0;
	}

	@Override
	public double getBalance(final String playerName) {

		return 0;
	}

	@Override
	public double getBalance(final String playerName, final String worldName) {

		return 0;
	}

	@Override
	public List<String> getBanks() {
		return new ArrayList<String>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean has(final OfflinePlayer player, final double value) {

		return false;
	}

	@Override
	public boolean has(final OfflinePlayer arg0, final String arg1, final double arg2) {

		return false;
	}

	@Override
	public boolean has(final String paramString, final double paramDouble) {

		return false;
	}

	@Override
	public boolean has(final String paramString1, final String paramString2, final double paramDouble) {

		return false;
	}

	@Override
	public boolean hasAccount(final OfflinePlayer arg0) {

		return false;
	}

	@Override
	public boolean hasAccount(final OfflinePlayer arg0, final String arg1) {

		return false;
	}

	@Override
	public boolean hasAccount(final String paramString) {

		return false;
	}

	@Override
	public boolean hasAccount(final String paramString1, final String paramString2) {

		return false;
	}

	@Override
	public boolean hasBankSupport() {
		return false;
	}

	@Override
	public EconomyResponse isBankMember(final String arg0, final OfflinePlayer arg1) {

		return null;
	}

	@Override
	public EconomyResponse isBankMember(final String paramString1, final String paramString2) {
		return bankEconomyResponse;
	}

	@Override
	public EconomyResponse isBankOwner(final String arg0, final OfflinePlayer arg1) {

		return null;
	}

	@Override
	public EconomyResponse isBankOwner(final String paramString1, final String paramString2) {
		return bankEconomyResponse;
	}

	@Override
	public boolean isEnabled() {
		return Bukkit.getPluginManager().getPlugin("AllAssets").isEnabled() ? true : false;
	}

	@Override
	public EconomyResponse withdrawPlayer(final OfflinePlayer arg0, final double arg1) {

		return null;
	}

	@Override
	public EconomyResponse withdrawPlayer(final OfflinePlayer arg0, final String arg1, final double arg2) {

		return null;
	}

	@Override
	public EconomyResponse withdrawPlayer(final String paramString, final double paramDouble) {

		return null;
	}

	@Override
	public EconomyResponse withdrawPlayer(final String paramString1, final String paramString2, final double paramDouble) {

		return null;
	}

}
