/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 * 
 * AllAssets, created by Skepter and Tundra
 * 
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 * 
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 * 
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.libs;

import org.bukkit.Bukkit;

/** @author fillpant, Blame him! */
public enum EntityDisguise {
	ZOMBIE("EntityZombie"), WITHER_SKELETON("EntitySkeleton"), SKELETON("EntitySkeleton"), ZOMBIEPIG("EntityPigZombie"), BLAZE("EntityBlaze"), ENDERMAN("EntityEnderman"), CREEPER("EntityCreeper"), SPIDER("EntitySpider"), WITCH("EntityWitch"), WITHER_BOSS("EntityWither"), GHAST("EntityGhast"), GIANT("EntityGiantZombie"), SLIME("EntitySlime"), CAVE_SPIDER("EntityCaveSpider"), SILVERFISH("EntitySilverfish"), MAGMA_CUBE("EntityMagmaCube"), BAT("EntityBat"), PIG("EntityPig"), SHEEP("EntitySheep"), COW("EntityCow"), CHICKEN("EntityChicken"), SQUID("EntitySquid"), WOOLF("EntityWolf"), OCELOT("EntityOcelot"), HORSE("EntityHorse"), VILLAGER("EntityVillager"), IRON_GOLEM("EntityIronGolem"), SNOWMAN("EntitySnowman"), ENDER_DRAGON("EntityEnderDragon"), MOOSHROOM("EntityMushroomCow");
	private final String cls;

	EntityDisguise(final String cls) {
		this.cls = cls;
	}

	/** <b><u>FORGET THIS! DONT USE IT!</u></b>
	 *
	 * @return */
	public String getClassName() {
		return "net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().substring(23) + "." + cls;
	}
}
