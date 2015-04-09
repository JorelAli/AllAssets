package io.github.skepter.allassets.utils.Tools1;

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
