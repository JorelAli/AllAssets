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
package io.github.skepter.allassets.api;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.utils.Strings;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.conversations.BooleanPrompt;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/** A small API to handle a player sending a yes/no request to another player */
public class PlayerRequest {

	private boolean hasAccepted;
	private static String sender;
	private static String information;
	private boolean hasExpired;

	/* to -> from */
	private final Map<UUID, UUID> playerMap;

	/** Use -1 as the timeout for an infinite timeout */
	public PlayerRequest(final Player from, final Player to, final String information, final long timeout) {
		hasExpired = false;
		if (timeout != -1L) {
			from.sendMessage(Strings.TITLE + "Your request will expire in " + (timeout / 1000) + " seconds");
			Bukkit.getScheduler().runTaskLater(AllAssets.instance(), new Runnable() {
				@Override
				public void run() {
					hasExpired = true;
					hasAccepted = false;
				}
			}, timeout);
		}

		playerMap = new HashMap<UUID, UUID>();
		playerMap.put(to.getUniqueId(), from.getUniqueId());
		sender = from.getName();
		PlayerRequest.information = information;
		if (to instanceof Conversable) {
			final ConversationFactory conversationFactory = new ConversationFactory(AllAssets.instance()).withModality(true).withFirstPrompt(new YesNoPrompt()).withEscapeSequence("/quit").withTimeout(10);
			conversationFactory.buildConversation(to).begin();
		}
	}

	public PlayerRequest getRequest() {
		return this;
	}

	public static String getPromptText() {
		return Strings.TITLE + Strings.HOUSE_STYLE_COLOR + sender + " has requested to " + information + " Say" + ChatColor.GREEN + "yes " + Strings.HOUSE_STYLE_COLOR + "to continue or " + ChatColor.RED + "no " + Strings.HOUSE_STYLE_COLOR + "to cancel";
	}

	public boolean hasAccepted() {
		return hasAccepted;
	}

	/** The event when a player has accepted/declined a request */
	public static class PlayerRequestEvent extends Event {

		private static final HandlerList handlers = new HandlerList();
		private PlayerRequest request;
		private Player from;
		private Player to;
		private boolean result;

		public PlayerRequestEvent(final PlayerRequest request, final Player from, final Player to, final boolean result) {
			this.setFrom(from);
			this.setTo(to);
			this.setResult(result);
			this.setRequest(request);
		}

		@Override
		public HandlerList getHandlers() {
			return handlers;
		}

		public static HandlerList getHandlerList() {
			return handlers;
		}

		public Player getFrom() {
			return from;
		}

		public void setFrom(final Player from) {
			this.from = from;
		}

		public Player getTo() {
			return to;
		}

		public void setTo(final Player to) {
			this.to = to;
		}

		public boolean getResult() {
			return result;
		}

		public void setResult(final boolean result) {
			this.result = result;
		}

		public PlayerRequest getRequest() {
			return request;
		}

		public void setRequest(final PlayerRequest request) {
			this.request = request;
		}

	}

	class YesNoPrompt extends BooleanPrompt {

		@Override
		public String getPromptText(final ConversationContext context) {
			return PlayerRequest.getPromptText();
		}

		@Override
		protected Prompt acceptValidatedInput(final ConversationContext context, final boolean b) {
			if (context.getForWhom() instanceof Player) {
				final Player player = (Player) context.getForWhom();
				final Player from = Bukkit.getPlayer(playerMap.get(player.getUniqueId()));

				if (hasExpired) {
					from.sendMessage(Strings.TITLE + "Your request has expired");
					return Prompt.END_OF_CONVERSATION;
				}
				hasAccepted = true;
				final Event playerRequestEvent = new PlayerRequestEvent(getRequest(), from, player, b);
				Bukkit.getServer().getPluginManager().callEvent(playerRequestEvent);
				if (b) {
					context.getForWhom().sendRawMessage(Strings.TITLE + "You have accepted the request");
					from.sendMessage(Strings.TITLE + player.getName() + " has accepted your request");
				} else {
					context.getForWhom().sendRawMessage(Strings.TITLE + "You have declined the request");
					from.sendMessage(Strings.TITLE + player.getName() + " has declined your request");
				}
			}
			return Prompt.END_OF_CONVERSATION;
		}

	}

}
