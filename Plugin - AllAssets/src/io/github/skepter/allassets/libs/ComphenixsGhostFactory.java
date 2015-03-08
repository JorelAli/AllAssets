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
package io.github.skepter.allassets.libs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ComphenixsGhostFactory {
	/** Team of ghosts and people who can see ghosts. */
	private final String GHOST_TEAM_NAME = "Ghosts";
	private final long UPDATE_DELAY = 20L;

	// No players in the ghost factory
	private final OfflinePlayer[] EMPTY_PLAYERS = new OfflinePlayer[0];
	private Team ghostTeam;

	// Task that must be cleaned up
	private BukkitTask task;
	private boolean closed;

	// Players that are actually ghosts
	private final Set<String> ghosts = new HashSet<String>();

	public ComphenixsGhostFactory(final Plugin plugin) {
		// Initialize
		try {
			createTask(plugin);
		} catch (final IllegalPluginAccessException e) {
			Bukkit.getPluginManager().disablePlugin(plugin);
		}
		createGetTeam();
	}

	private void createGetTeam() {
		final Scoreboard board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();

		ghostTeam = board.getTeam(GHOST_TEAM_NAME);

		// Create a new ghost team if needed
		if (ghostTeam == null)
			ghostTeam = board.registerNewTeam(GHOST_TEAM_NAME);
		// Thanks to Rprrr for noticing a bug here
		ghostTeam.setCanSeeFriendlyInvisibles(true);
	}

	private void createTask(final Plugin plugin) throws IllegalPluginAccessException {
		task = Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
			@Override
			public void run() {
				for (final OfflinePlayer member : getMembers()) {
					final Player player = member.getPlayer();

					if (player != null)
						// Update invisibility effect
						setGhost(player, isGhost(player));
					else {
						ghosts.remove(member.getName());
						ghostTeam.removePlayer(member);
					}
				}
			}
		}, UPDATE_DELAY, UPDATE_DELAY);
	}

	/** Remove all existing player members and ghosts. */
	public void clearMembers() {
		if (ghostTeam != null)
			for (final OfflinePlayer player : getMembers())
				ghostTeam.removePlayer(player);
	}

	/** Add the given player to this ghost manager. This ensures that it can see
	 * ghosts, and later become one.
	 * 
	 * @param player - the player to add to the ghost manager. */
	public void addPlayer(final Player player) {
		validateState();
		if (!ghostTeam.hasPlayer(player)) {
			ghostTeam.addPlayer(player);
			player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 15));
		}
	}

	/** Determine if the given player is tracked by this ghost manager and is a
	 * ghost.
	 * 
	 * @param player - the player to test.
	 * @return TRUE if it is, FALSE otherwise. */
	public boolean isGhost(final Player player) {
		return (player != null) && hasPlayer(player) && ghosts.contains(player.getName());
	}

	/** Determine if the current player is tracked by this ghost manager, or is a
	 * ghost.
	 * 
	 * @param player - the player to check.
	 * @return TRUE if it is, FALSE otherwise. */
	public boolean hasPlayer(final Player player) {
		validateState();
		return ghostTeam.hasPlayer(player);
	}

	/** Set wheter or not a given player is a ghost.
	 * 
	 * @param player - the player to set as a ghost.
	 * @param isGhost - TRUE to make the given player into a ghost, FALSE
	 * otherwise. */
	public void setGhost(final Player player, final boolean isGhost) {
		// Make sure the player is tracked by this manager
		if (!hasPlayer(player))
			addPlayer(player);

		if (isGhost) {
			ghosts.add(player.getName());
			player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 15));
		} else if (!isGhost) {
			ghosts.remove(player.getName());
			player.removePotionEffect(PotionEffectType.INVISIBILITY);
		}
	}

	/** Remove the given player from the manager, turning it back into the living
	 * and making it unable to see ghosts.
	 * 
	 * @param player - the player to remove from the ghost manager. */
	public void removePlayer(final Player player) {
		validateState();
		if (ghostTeam.removePlayer(player))
			player.removePotionEffect(PotionEffectType.INVISIBILITY);
	}

	/** Retrieve every ghost currently tracked by this manager.
	 * 
	 * @return Every tracked ghost. */
	public OfflinePlayer[] getGhosts() {
		validateState();
		final Set<OfflinePlayer> players = new HashSet<OfflinePlayer>(ghostTeam.getPlayers());

		// Remove all non-ghost players
		for (final Iterator<OfflinePlayer> it = players.iterator(); it.hasNext();)
			if (!ghosts.contains(it.next().getName()))
				it.remove();
		return toArray(players);
	}

	/** Retrieve every ghost and every player that can see ghosts.
	 * 
	 * @return Every ghost or every observer. */
	public OfflinePlayer[] getMembers() {
		validateState();
		return toArray(ghostTeam.getPlayers());
	}

	private OfflinePlayer[] toArray(final Set<OfflinePlayer> players) {
		if (players != null)
			return players.toArray(new OfflinePlayer[0]);
		else
			return EMPTY_PLAYERS;
	}

	public void close() {
		if (!closed) {
			task.cancel();
			ghostTeam.unregister();
			closed = true;
		}
	}

	public boolean isClosed() {
		return closed;
	}

	private void validateState() {
		if (closed)
			throw new IllegalStateException("Ghost factory has closed. Cannot reuse instances.");
	}
}
