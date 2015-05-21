package io.github.skepter.allassets.version.nms;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/*
 * See
 * https://github.com/deathmarine/RefactorInterfaceExample/tree/master/src/com/modcrafting/example
 * 
 * LGPL for setBlock method. Thanks to desht
 */

public interface NMS {

	int getPing(Player player);
	
	boolean isInvunerable(Player player);
	
	void setInvunerability(Player player, boolean invunerable);
	
	void openAnvil(Player player);
	
	void openSign(Player player, Sign sign);
	
	boolean setBlock(Location loc, int blockId, byte data);
	
	String nmsName(ItemStack itemStack);
	
	ItemStack addStringNBT(ItemStack itemStack, String key, String value);
	
	String getStringNBT(ItemStack itemStack, String key);
	
//	String getLocale(Player player);
}
