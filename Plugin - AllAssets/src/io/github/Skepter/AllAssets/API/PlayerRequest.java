package io.github.Skepter.AllAssets.API;

import io.github.Skepter.AllAssets.AllAssets;

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
	private Map<UUID, UUID> playerMap;

	/** Use -1 as the timeout for an infinite timeout */
	public PlayerRequest(Player from, Player to, String information, long timeout) {
		hasExpired = false;
		if (timeout != -1) {
			from.sendMessage(AllAssets.title + "Your request will expire in " + (timeout / 1000) + " seconds");
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
			conversationFactory.buildConversation((Conversable) to).begin();
		}
	}

	public PlayerRequest getRequest() {
		return this;
	}

	public static String getPromptText() {
		return AllAssets.title + AllAssets.houseStyleColor + sender + " has requested to " + information + " Say" + ChatColor.GREEN + "yes " + AllAssets.houseStyleColor + "to continue or " + ChatColor.RED + "no " + AllAssets.houseStyleColor + "to cancel";
	}

	public boolean hasAccepted() {
		return hasAccepted;
	}

	/** The event when a player has accepted/declined a request */
	static class PlayerRequestEvent extends Event {

		private static final HandlerList handlers = new HandlerList();
		private PlayerRequest request;
		private Player from;
		private Player to;
		private boolean result;

		public PlayerRequestEvent(PlayerRequest request, Player from, Player to, boolean result) {
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

		public void setFrom(Player from) {
			this.from = from;
		}

		public Player getTo() {
			return to;
		}

		public void setTo(Player to) {
			this.to = to;
		}

		public boolean isResult() {
			return result;
		}

		public void setResult(boolean result) {
			this.result = result;
		}

		public PlayerRequest getRequest() {
			return request;
		}

		public void setRequest(PlayerRequest request) {
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
					from.sendMessage(AllAssets.title + "Your request has expired");
					return Prompt.END_OF_CONVERSATION;
				}
				hasAccepted = true;
				final Event playerRequestEvent = new PlayerRequestEvent(getRequest(), from, player, b);
				Bukkit.getServer().getPluginManager().callEvent(playerRequestEvent);
				if (b) {
					context.getForWhom().sendRawMessage(AllAssets.title + "You have accepted the request");
					from.sendMessage(AllAssets.title + player.getName() + " has accepted your request");
				} else {
					context.getForWhom().sendRawMessage(AllAssets.title + "You have declined the request");
					from.sendMessage(AllAssets.title + player.getName() + " has declined your request");
				}
			}
			return Prompt.END_OF_CONVERSATION;
		}

	}

}
