package io.github.Skepter.AllAssets.Vault.Economy;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Reflection.VaultReflection;

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

		public EconomyServerListener(AAEco aaeco) {
			this.economy = aaeco;
		}

		@EventHandler(priority = EventPriority.MONITOR)
		public void onPluginDisable(PluginDisableEvent event) {
			if ((this.economy.allAssetsPlugin == null) || (!(event.getPlugin().getDescription().getName().equals("AllAssets"))))
				return;
			economy.allAssetsPlugin = null;
			AAEco.log.info(String.format("[%s][Economy] %s unhooked.", new Object[] { "AA", name }));
		}

		@EventHandler(priority = EventPriority.MONITOR)
		public void onPluginEnable(PluginEnableEvent event) {
			if (this.economy.allAssetsPlugin == null) {
				Plugin ec = event.getPlugin();
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
	

	public AAEco(Plugin plugin) {
		if (this.allAssetsPlugin == null) {
			Plugin allAssets = plugin.getServer().getPluginManager().getPlugin("AllAssets");
			if ((allAssets != null) && (allAssets.isEnabled())) {
				this.allAssetsPlugin = ((AllAssets) allAssets);
				Bukkit.getServer().getPluginManager().registerEvents(new EconomyServerListener(this), plugin);
				log.info(String.format("[%s][Economy] %s hooked.", new Object[] { "AA", name }));
			}
		}
	}

	@Override
	public EconomyResponse bankBalance(String paramString) {
		return bankEconomyResponse;
	}

	@Override
	public EconomyResponse bankDeposit(String paramString, double paramDouble) {
		return bankEconomyResponse;
	}

	@Override
	public EconomyResponse bankHas(String paramString, double paramDouble) {
		return bankEconomyResponse;
	}

	@Override
	public EconomyResponse bankWithdraw(String paramString, double paramDouble) {
		return bankEconomyResponse;
	}

	@Override
	public EconomyResponse createBank(String arg0, OfflinePlayer arg1) {
		return bankEconomyResponse;
	}

	@Override
	public EconomyResponse createBank(String paramString1, String paramString2) {
		return bankEconomyResponse;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createPlayerAccount(String playerName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createPlayerAccount(String playerName, String worldName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String currencyNamePlural() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String currencyNameSingular() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse deleteBank(String paramString) {
		return bankEconomyResponse;
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(String playerName, double value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(String playerName, String worldName, double value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//format into readable string
	public String format(double value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int fractionalDigits() {
		return -1;
	}

	@Override
	public double getBalance(OfflinePlayer arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBalance(OfflinePlayer arg0, String arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBalance(String playerName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBalance(String playerName, String worldName) {
		// TODO Auto-generated method stub
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
	public boolean has(OfflinePlayer player, double value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean has(OfflinePlayer arg0, String arg1, double arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean has(String paramString, double paramDouble) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean has(String paramString1, String paramString2, double paramDouble) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasAccount(String paramString) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasAccount(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBankSupport() {
		return false;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse isBankMember(String paramString1, String paramString2) {
		return bankEconomyResponse;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String paramString1, String paramString2) {
		return bankEconomyResponse;
	}

	@Override
	public boolean isEnabled() {
		return Bukkit.getPluginManager().getPlugin("AllAssets").isEnabled() ? true : false;
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse withdrawPlayer(String paramString, double paramDouble) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse withdrawPlayer(String paramString1, String paramString2, double paramDouble) {
		// TODO Auto-generated method stub
		return null;
	}

}
