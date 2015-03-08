package io.github.skepter.allassets.Vault1.Chat1;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.reflection.VaultReflection;

import java.util.logging.Logger;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;

public class AAChat extends Chat {

	private static final Logger log = Bukkit.getLogger();

	private final String name = "AAChat";
	private AllAssets allAssetsPlugin;

	public AAChat(final Plugin plugin, final Permission perms) {
		super(perms);
		if (this.allAssetsPlugin == null) {
			final Plugin allAssets = plugin.getServer().getPluginManager().getPlugin("AllAssets");
			if ((allAssets != null) && (allAssets.isEnabled())) {
				this.allAssetsPlugin = ((AllAssets) allAssets);
				Bukkit.getServer().getPluginManager().registerEvents(new ChatServerListener(this), plugin);
				log.info(String.format("[%s][Chat] %s hooked.", new Object[] { "AA", name }));
			}
		}
	}

	public class ChatServerListener implements Listener {
		AAChat chat = null;

		public ChatServerListener(final AAChat aaeco) {
			this.chat = aaeco;
		}

		@EventHandler(priority = EventPriority.MONITOR)
		public void onPluginEnable(final PluginEnableEvent event) {
			if (this.chat.allAssetsPlugin == null) {
				final Plugin chatPlugin = event.getPlugin();
				if ((chatPlugin.getDescription().getName().equals("AllAssets")) && (chatPlugin.getClass().getName().equals(VaultReflection.chat))) {
					chat.allAssetsPlugin = (AllAssets) chatPlugin;
					AAChat.log.info(String.format("[%s][Chat] %s hooked.", new Object[] { "AA", name }));
				}
			}
		}

		@EventHandler(priority = EventPriority.MONITOR)
		public void onPluginDisable(final PluginDisableEvent event) {
			if ((this.chat.allAssetsPlugin == null) || (!(event.getPlugin().getDescription().getName().equals("AllAssets"))))
				return;
			chat.allAssetsPlugin = null;
			AAChat.log.info(String.format("[%s][Chat] %s unhooked.", new Object[] { "AA", name }));
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isEnabled() {
		return Bukkit.getPluginManager().getPlugin("AllAssets").isEnabled() ? true : false;
	}

	@Override
	public String getPlayerPrefix(final String paramString1, final String paramString2) {

		return null;
	}

	@Override
	public void setPlayerPrefix(final String paramString1, final String paramString2, final String paramString3) {

	}

	@Override
	public String getPlayerSuffix(final String paramString1, final String paramString2) {

		return null;
	}

	@Override
	public void setPlayerSuffix(final String paramString1, final String paramString2, final String paramString3) {

	}

	@Override
	public String getGroupPrefix(final String paramString1, final String paramString2) {

		return null;
	}

	@Override
	public void setGroupPrefix(final String paramString1, final String paramString2, final String paramString3) {

	}

	@Override
	public String getGroupSuffix(final String paramString1, final String paramString2) {

		return null;
	}

	@Override
	public void setGroupSuffix(final String paramString1, final String paramString2, final String paramString3) {

	}

	@Override
	public int getPlayerInfoInteger(final String paramString1, final String paramString2, final String paramString3, final int paramInt) {

		return 0;
	}

	@Override
	public void setPlayerInfoInteger(final String paramString1, final String paramString2, final String paramString3, final int paramInt) {

	}

	@Override
	public int getGroupInfoInteger(final String paramString1, final String paramString2, final String paramString3, final int paramInt) {

		return 0;
	}

	@Override
	public void setGroupInfoInteger(final String paramString1, final String paramString2, final String paramString3, final int paramInt) {

	}

	@Override
	public double getPlayerInfoDouble(final String paramString1, final String paramString2, final String paramString3, final double paramDouble) {

		return 0;
	}

	@Override
	public void setPlayerInfoDouble(final String paramString1, final String paramString2, final String paramString3, final double paramDouble) {

	}

	@Override
	public double getGroupInfoDouble(final String paramString1, final String paramString2, final String paramString3, final double paramDouble) {

		return 0;
	}

	@Override
	public void setGroupInfoDouble(final String paramString1, final String paramString2, final String paramString3, final double paramDouble) {

	}

	@Override
	public boolean getPlayerInfoBoolean(final String paramString1, final String paramString2, final String paramString3, final boolean paramBoolean) {

		return false;
	}

	@Override
	public void setPlayerInfoBoolean(final String paramString1, final String paramString2, final String paramString3, final boolean paramBoolean) {

	}

	@Override
	public boolean getGroupInfoBoolean(final String paramString1, final String paramString2, final String paramString3, final boolean paramBoolean) {

		return false;
	}

	@Override
	public void setGroupInfoBoolean(final String paramString1, final String paramString2, final String paramString3, final boolean paramBoolean) {

	}

	@Override
	public String getPlayerInfoString(final String paramString1, final String paramString2, final String paramString3, final String paramString4) {

		return null;
	}

	@Override
	public void setPlayerInfoString(final String paramString1, final String paramString2, final String paramString3, final String paramString4) {

	}

	@Override
	public String getGroupInfoString(final String paramString1, final String paramString2, final String paramString3, final String paramString4) {

		return null;
	}

	@Override
	public void setGroupInfoString(final String paramString1, final String paramString2, final String paramString3, final String paramString4) {

	}

}
