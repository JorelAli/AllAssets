/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Listeners;

import io.github.Skepter.AllAssets.Config.ConfigHandler;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class BlockPoweredListener implements Listener {

	@EventHandler
	public void onBlockRedstoneChange(BlockRedstoneEvent event) {
		Block block = event.getBlock();
		if (event.getNewCurrent() > 0 && block.getType().equals(Material.OBSIDIAN)) {
			if (ConfigHandler.instance().features().getBoolean("PoweredBlocks.Glowstone"))
				block.setType(Material.GLOWSTONE);
		}
	}

}
