package io.github.Skepter.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LogEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private final String log;
	private final LogType logType;

	public LogEvent(final String s, final LogType type) {
		log = s;
		logType = type;
	}

	public String getMessage() {
		return log;
	}

	public LogType getLogType() {
		return logType;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public static enum LogType {
		CHAT, ERROR, OTHER
	};
}