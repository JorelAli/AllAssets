package io.github.Skepter.API;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

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
		CHAT, ERROR, OTHER
	};

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}