package io.github.skepter.allassets.utils.tools;

import org.bukkit.Material;

public class BlockInfo {

	private Material material;
	private byte data;

	public BlockInfo(Material material, byte data) {
		this.material = material;
		this.data = data;
	}

	public Material getMaterial() {
		return material;
	}
	
	public byte getData() {
		return data;
	}

}
