package io.github.Skepter.AllAssets.Vault.Chat;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Reflection.VaultReflection;

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

	public AAChat(Plugin plugin, Permission perms) {
		super(perms);
		if (this.allAssetsPlugin == null) {
			Plugin allAssets = plugin.getServer().getPluginManager().getPlugin("AllAssets");
			if ((allAssets != null) && (allAssets.isEnabled())) {
				this.allAssetsPlugin = ((AllAssets) allAssets);
				Bukkit.getServer().getPluginManager().registerEvents(new ChatServerListener(this), plugin);
				log.info(String.format("[%s][Chat] %s hooked.", new Object[] { "AA", name }));
			}
		}
	}

	public class ChatServerListener implements Listener {
		AAChat chat = null;

		public ChatServerListener(AAChat aaeco) {
			this.chat = aaeco;
		}

		@EventHandler(priority = EventPriority.MONITOR)
		public void onPluginEnable(PluginEnableEvent event) {
			if (this.chat.allAssetsPlugin == null) {
				Plugin chatPlugin = event.getPlugin();
				if ((chatPlugin.getDescription().getName().equals("AllAssets")) && (chatPlugin.getClass().getName().equals(VaultReflection.chat))) {
					chat.allAssetsPlugin = (AllAssets) chatPlugin;
					AAChat.log.info(String.format("[%s][Chat] %s hooked.", new Object[] { "AA", name }));
				}
			}
		}

		@EventHandler(priority = EventPriority.MONITOR)
		public void onPluginDisable(PluginDisableEvent event) {
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
	public String getPlayerPrefix(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPlayerPrefix(String paramString1, String paramString2, String paramString3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPlayerSuffix(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPlayerSuffix(String paramString1, String paramString2, String paramString3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getGroupPrefix(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGroupPrefix(String paramString1, String paramString2, String paramString3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getGroupSuffix(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGroupSuffix(String paramString1, String paramString2, String paramString3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPlayerInfoInteger(String paramString1, String paramString2, String paramString3, int paramInt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPlayerInfoInteger(String paramString1, String paramString2, String paramString3, int paramInt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getGroupInfoInteger(String paramString1, String paramString2, String paramString3, int paramInt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setGroupInfoInteger(String paramString1, String paramString2, String paramString3, int paramInt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getPlayerInfoDouble(String paramString1, String paramString2, String paramString3, double paramDouble) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPlayerInfoDouble(String paramString1, String paramString2, String paramString3, double paramDouble) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getGroupInfoDouble(String paramString1, String paramString2, String paramString3, double paramDouble) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setGroupInfoDouble(String paramString1, String paramString2, String paramString3, double paramDouble) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getPlayerInfoBoolean(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPlayerInfoBoolean(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getGroupInfoBoolean(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setGroupInfoBoolean(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPlayerInfoString(String paramString1, String paramString2, String paramString3, String paramString4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPlayerInfoString(String paramString1, String paramString2, String paramString3, String paramString4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getGroupInfoString(String paramString1, String paramString2, String paramString3, String paramString4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGroupInfoString(String paramString1, String paramString2, String paramString3, String paramString4) {
		// TODO Auto-generated method stub
		
	}

}
