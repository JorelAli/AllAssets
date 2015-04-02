package io.github.skepter.allassets.api.events;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BanEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;
	private final CommandSender banner;
	private final Player bannedPlayer;
	private final String reason;

	public BanEvent(final CommandSender banner, final Player bannedPlayer, final String reason) {
		this.banner = banner;
		this.bannedPlayer = bannedPlayer;
		this.reason = reason;
	}

	@Override
	public HandlerList getHandlers() {
		return null;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(final boolean b) {
		cancelled = b;
	}

	public CommandSender getBanner() {
		return banner;
	}

	public Player getBannedPlayer() {
		return bannedPlayer;
	}

	public String getReason() {
		return reason;
	}

}
