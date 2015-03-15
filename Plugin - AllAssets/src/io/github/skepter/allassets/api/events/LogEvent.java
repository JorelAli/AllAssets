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
package io.github.skepter.allassets.api.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/** @author Skepter */
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
