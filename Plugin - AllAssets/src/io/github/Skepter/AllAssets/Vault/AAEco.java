package io.github.Skepter.AllAssets.Vault;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Reflection.VaultReflection;

import java.util.List;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;

public class AAEco implements Economy {

	private static final Logger log = Bukkit.getLogger();

	private final String name = "AAEco";
	private AllAssets allAssetsPlugin;

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
		return null;
	}

	@Override
	public EconomyResponse bankDeposit(String paramString, double paramDouble) {
		return null;
	}

	@Override
	public EconomyResponse bankHas(String paramString, double paramDouble) {
		return null;
	}

	@Override
	public EconomyResponse bankWithdraw(String paramString, double paramDouble) {
		return null;
	}

	@Override
	public EconomyResponse createBank(String paramString1, String paramString2) {
		return null;
	}

	@Override
	public boolean createPlayerAccount(String paramString) {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createPlayerAccount(String paramString1, String paramString2) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(String paramString, double paramDouble) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(String paramString1, String paramString2, double paramDouble) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String format(double paramDouble) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int fractionalDigits() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBalance(String paramString) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBalance(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getBanks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return name;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EconomyResponse isBankMember(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		return Bukkit.getPluginManager().getPlugin("AllAssets").isEnabled() ? true : false;
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

	public class EconomyServerListener implements Listener {
		AAEco economy = null;

		public EconomyServerListener(AAEco aaeco) {
			this.economy = aaeco;
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

		@EventHandler(priority = EventPriority.MONITOR)
		public void onPluginDisable(PluginDisableEvent event) {
			if ((this.economy.allAssetsPlugin == null) || (!(event.getPlugin().getDescription().getName().equals("AllAssets"))))
				return;
			economy.allAssetsPlugin = null;
			AAEco.log.info(String.format("[%s][Economy] %s unhooked.", new Object[] { "AA", name }));
		}
	}

}
