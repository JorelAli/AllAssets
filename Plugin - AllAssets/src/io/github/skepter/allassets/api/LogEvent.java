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
package io.github.skepter.allassets.api;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author Skepter
 *
 */
public class LogEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private final String log;
	private final LogType logType;

	/** Call a new LogEvent.
	 * 
	 * @param message - the message to log
	 * @param type - the type of log to log (chat/error/other) */
	public LogEvent(final String message, final LogType type) {
		log = message;
		logType = type;
	}

	/** Gets the message from the log event
	 * 
	 * @return The log message */
	public String getMessage() {
		return log;
	}

	/** Gets the log type from the log event
	 * 
	 * @return The log type */
	public LogType getLogType() {
		return logType;
	}

	public static enum LogType {
		CHAT, ERROR, OTHER, GRIEF
	};

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}