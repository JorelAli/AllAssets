/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
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
package io.github.Skepter.AllAssets.Listeners;

import io.github.Skepter.AllAssets.API.LogEvent.LogType;
import io.github.Skepter.AllAssets.Commands.Administration.CommandLog;
import io.github.Skepter.AllAssets.Config.ConfigHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;

import java.math.BigInteger;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	@EventHandler
	public void playerHyperlinkChatEvent(final AsyncPlayerChatEvent event) {
		if (ConfigHandler.features().getBoolean("AntiHyperlink"))
			if (TextUtils.isHyperlink(event.getMessage()) && !event.getPlayer().hasPermission("AllAssets.hyperlink")) {
				CommandLog.addLog(ChatColor.BLUE + event.getPlayer().getName() + ChatColor.WHITE + " tried to post a link: " + ChatColor.BLUE + event.getMessage(), LogType.CHAT);
				event.getRecipients().clear();
			}
	}

	@EventHandler
	public void playerSwearEvent(final AsyncPlayerChatEvent event) {
		if (ConfigHandler.features().getBoolean("AntiSwear"))
			if ((TextUtils.containsSwear(event.getMessage()) || TextUtils.containsSwearUsingFilter(event.getMessage())) && !event.getPlayer().hasPermission("AllAssets.swear")) {
				CommandLog.addLog(ChatColor.BLUE + event.getPlayer().getName() + ChatColor.WHITE + " tried to swear: " + ChatColor.BLUE + event.getMessage(), LogType.CHAT);
				event.getRecipients().clear();
			}
	}

	@EventHandler
	public void playerUseColorEvent(final AsyncPlayerChatEvent event) {
		if (ConfigHandler.features().getBoolean("ChatColor"))
			if (event.getPlayer().hasPermission("AllAssets.chatColor"))
				event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
	}

	//TODO add to features/config

	@EventHandler
	public void playerSumEvent(final AsyncPlayerChatEvent event) {
		if (event.getMessage().startsWith("sum(") && event.getMessage().endsWith(")")) {
			String sum = event.getMessage().replace("sum(", "").replace(")", "").replace(" ", "");
			String arr[] = null;
			if (sum.contains("+")) {
				sum = sum.replace('+', ':');
				arr = sum.split(":");
				if (TextUtils.isInteger(arr[0]) && TextUtils.isInteger(arr[1])) {
					event.setMessage(event.getMessage() + " = " + (Integer.parseInt(arr[0]) + Integer.parseInt(arr[1])));
				} else {
					ErrorUtils.notAnInteger(event.getPlayer());
				}
			}
			if (sum.contains("-")) {
				sum = sum.replace('-', ':');
				arr = sum.split(":");
				if (TextUtils.isInteger(arr[0]) && TextUtils.isInteger(arr[1])) {
					event.setMessage(event.getMessage() + " = " + (Integer.parseInt(arr[0]) - Integer.parseInt(arr[1])));
				} else {
					ErrorUtils.notAnInteger(event.getPlayer());
				}
			}
			if (sum.contains("*")) {
				sum = sum.replace('*', ':');
				arr = sum.split(":");
				if (TextUtils.isInteger(arr[0]) && TextUtils.isInteger(arr[1])) {
					event.setMessage(event.getMessage() + " = " + (Integer.parseInt(arr[0]) * Integer.parseInt(arr[1])));
				} else {
					ErrorUtils.notAnInteger(event.getPlayer());
				}
			}
			if (sum.contains("/")) {
				sum = sum.replace('/', ':');
				arr = sum.split(":");
				if (TextUtils.isInteger(arr[0]) && TextUtils.isInteger(arr[1])) {
					event.setMessage(event.getMessage() + " = " + (new Double(Integer.parseInt(arr[0])) / new Double(Integer.parseInt(arr[1]))));
				} else {
					ErrorUtils.notAnInteger(event.getPlayer());
				}
			}
			if (sum.contains("^")) {
				sum = sum.replace('^', ':');
				arr = sum.split(":");
				if (TextUtils.isInteger(arr[0]) && TextUtils.isInteger(arr[1])) {
					event.setMessage(event.getMessage() + " = " + new BigInteger(arr[0]).pow(Integer.parseInt(arr[1])).toString());
				} else {
					ErrorUtils.notAnInteger(event.getPlayer());
				}
			}
		}
	}
}
