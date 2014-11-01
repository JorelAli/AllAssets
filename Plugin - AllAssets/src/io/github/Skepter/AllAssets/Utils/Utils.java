/*******************************************************************************
 * Skepter's licence
 * Copyright 2014 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. I've just made life easier for you :)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Utils;

import io.github.Skepter.AllAssets.AllAssets;

import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;

public class Utils {

	public static void setMetadata(Entity entity, String key, Object value) {
		entity.setMetadata(key, new FixedMetadataValue(AllAssets.instance(), value));
	}
	//setMetadata(Entity, "deathDrops", "deathDrops", this);
	//int it = 0;
	//        for(MetadataValue s : ent.getMetadata("tnt")){
	//            if(s.asString().equalsIgnoreCase("tnt")){
	//                it++;
	//                break;
	//            }
	//        }
	//        if((it == 0)) return;
	//                //This item is one of the entities
}
