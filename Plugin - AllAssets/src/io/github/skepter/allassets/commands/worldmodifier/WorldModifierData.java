package io.github.skepter.allassets.commands.worldmodifier;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class WorldModifierData {
	
	private Location pos1;
	private Location pos2;
	private List<Block> previousAction;
	
	public Location getPos1() {
		return pos1;
	}
	
	public Location getPos2() {
		return pos2;
	}
	
	public List<Block> getPreviousAction() {
		return previousAction;
	}
	
	public void setPos1(Location loc) {
		pos1 = loc;
	}
	
	public void setPos2(Location loc) {
		pos2 = loc;
	}
	
	public void setPreviousAction(List<Block> blocks) {
		previousAction = blocks;
	}
	
}
