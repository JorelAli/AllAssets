package io.github.Skepter.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignListener implements Listener{

	@EventHandler
	public void onInteract(final PlayerInteractEvent event) {
		//buy signs etc.
	}
	
	@EventHandler
	public void onSwear(final SignChangeEvent event) {
		//swear signs etc.
	}
	
	@EventHandler
	public void onCreate(final SignChangeEvent event) {
		//buy signs etc.
	}
	
	@EventHandler
	public void color(final SignChangeEvent event) {
		if(event.getPlayer().hasPermission("AllAssets.signColor"))
			for(int i = 0; i < 3; i++) {
				final String s = event.getLine(i);
				final String str = ChatColor.translateAlternateColorCodes('&', s);
				event.setLine(i, str);
			}
	}
	
}
