package io.github.skepter.allassets.commands.worldmodifier;

import io.github.skepter.allassets.api.utils.Cuboid;
import io.github.skepter.allassets.utils.DoubleMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class WorldModifierData {

	private Location pos1;
	private Location pos2;
	private DoubleMap<Location, Material, Byte> previousAction;
	private boolean wandActive;

	public Location getPos1() {
		return pos1;
	}

	public Location getPos2() {
		return pos2;
	}

	public DoubleMap<Location, Material, Byte> getPreviousAction() {
		return previousAction;
	}

	public void setPos1(Location loc) {
		pos1 = loc;
	}

	public void setPos2(Location loc) {
		pos2 = loc;
	}

	@SuppressWarnings("deprecation")
	public void setPreviousAction(Cuboid cuboid) {
		DoubleMap<Location, Material, Byte> map = new DoubleMap<Location, Material, Byte>();
		for(Block b : cuboid.blocksFromTwoPoints()) {
			map.put(b.getLocation(), b.getType(), b.getData());
		}
		previousAction = map;
	}

	public boolean isWandActive() {
		return wandActive;
	}

	public void setWandActive(boolean wandActive) {
		this.wandActive = wandActive;
	}

	public void toggleWandStatus() {
		if (!wandActive)
			wandActive = true;
		else
			wandActive = false;
	}

}
