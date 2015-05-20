package io.github.skepter.allassets.version;

import org.bukkit.entity.Player;

/*
 * See
 * https://github.com/deathmarine/RefactorInterfaceExample/tree/master/src/com/modcrafting/example
 */

public interface NMS {

	int getPing(Player player);
	
	boolean isInvunerable(Player player);
	
	void setInvunerability(Player player, boolean invunerable);
	
	void openAnvil(Player player);
	
//	String getLocale(Player player);
}
