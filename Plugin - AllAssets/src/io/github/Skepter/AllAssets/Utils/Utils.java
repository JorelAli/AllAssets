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
