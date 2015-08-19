/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and MCSpartans
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.utils;

import static org.bukkit.ChatColor.AQUA;
import static org.bukkit.ChatColor.BLUE;
import static org.bukkit.ChatColor.DARK_RED;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.WHITE;

public class Strings {

	/* Messages  */
	public final static String TITLE = BLUE + "[" + AQUA + "AllAssets" + BLUE + "]" + WHITE + " ";
	public final static String ERROR = DARK_RED + "[" + RED + "AllAssets" + DARK_RED + "]" + RED + " ";
	public final static String SHORT_TITLE = BLUE + "[" + AQUA + "AA" + BLUE + "]" + WHITE + " ";
	public final static String NO_COLOR_TITLE = "[AllAssets] ";
	public final static String SHORT_NO_COLOR_TITLE = "[AA] ";
	public final static String ACCENT_COLOR = WHITE + "";
	public final static String HOUSE_STYLE_COLOR = AQUA + "";

	public static String customTitle(final String title) {
		return BLUE + "[" + AQUA + title + BLUE + "]" + WHITE + " ";
	}
	
	public static String customError(final String title) {
		return DARK_RED + "[" + RED + title + DARK_RED + "]" + RED + " ";
	}

}
