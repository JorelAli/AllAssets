package io.github.Skepter.Libs;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SimpleScoreboard {

	private final Scoreboard scoreboard;
	private Objective obj;

	private String title;
	private final Map<String, Integer> scores;
	private final List<Team> teams;

	public static Map<UUID, SimpleScoreboard> scoreboardMap = new HashMap<UUID, SimpleScoreboard>();

	public SimpleScoreboard(final String title) {
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		this.title = title;
		this.scores = Maps.newLinkedHashMap();
		this.teams = Lists.newArrayList();
	}

	public void blankLine() {
		add(" ");
	}

	public void add(final String text) {
		add(text, null);
	}

	public void add(String text, final Integer score) {
		Preconditions.checkArgument(text.length() < 48, "text cannot be over 48 characters in length");
		text = fixDuplicates(text);
		scores.put(text, score);
	}

	private String fixDuplicates(String text) {
		while (scores.containsKey(text))
			text += "�r";
		if (text.length() > 48)
			text = text.substring(0, 47);
		return text;
	}

	private Map.Entry<Team, String> createTeam(final String text) {
		String result = "";
		if (text.length() <= 16)
			return new AbstractMap.SimpleEntry<>(null, text);
		final Team team = scoreboard.registerNewTeam("text-" + scoreboard.getTeams().size());
		final Iterator<String> iterator = Splitter.fixedLength(16).split(text).iterator();
		team.setPrefix(iterator.next());
		result = iterator.next();
		if (text.length() > 32)
			team.setSuffix(iterator.next());
		teams.add(team);
		return new AbstractMap.SimpleEntry<>(team, result);
	}

	@SuppressWarnings("deprecation")
	public void build() {
		obj = scoreboard.registerNewObjective((title.length() > 16 ? title.substring(0, 15) : title), "dummy");
		obj.setDisplayName(title);
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		int index = scores.size();

		for (final Map.Entry<String, Integer> text : scores.entrySet()) {
			final Map.Entry<Team, String> team = createTeam(text.getKey());
			final Integer score = text.getValue() != null ? text.getValue() : index;
			final OfflinePlayer player = Bukkit.getOfflinePlayer(team.getValue());
			if (team.getKey() != null)
				team.getKey().addPlayer(player);
			obj.getScore(player).setScore(score);
			index -= 1;
		}
	}

	public void reset() {
		title = null;
		scores.clear();
		for (final Team t : teams)
			t.unregister();
		teams.clear();
	}

	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	public void send(final Player... players) {
		for (final Player p : players)
			p.setScoreboard(scoreboard);
	}

	@SuppressWarnings("deprecation")
	public void update(final String text, final Integer score) {
		if (scores.containsKey(text)) {
			scores.put(text, score);
			for (final Team t : teams)
				if (t.getName() == text)
					t.unregister();

			final Map.Entry<Team, String> team = createTeam(text);
			final OfflinePlayer player = Bukkit.getOfflinePlayer(team.getValue());
			if (team.getKey() != null)
				team.getKey().addPlayer(player);
			obj.getScore(player).setScore(score);

		}
	}

	/** Custom update method that updates it for a specific player
	 * 
	 * @param player - the player to update the board to
	 * @param text - the text to update
	 * @param score - the int to set the score as */
	public static void update(final String text, final Integer score, final Player... players) {
		for (final Player player : players) {
			final SimpleScoreboard board = scoreboardMap.get(player.getUniqueId());
			board.update(text, score);
			board.send(player);
		}
	}

}