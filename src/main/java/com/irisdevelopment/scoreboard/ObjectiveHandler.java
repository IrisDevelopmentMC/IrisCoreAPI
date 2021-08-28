package com.irisdevelopment.scoreboard;

import javafx.util.Pair;
import lombok.Getter;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class ObjectiveHandler {

	private final Scoreboard scoreboard;
	private final @Getter Objective objective;

	private List<ScoreboardEntry> current = new ArrayList<>();


	public ObjectiveHandler(Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
		this.objective = scoreboard.getBukkitScoreboard().registerNewObjective("handler", "dummy");
		this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	}

	public void updateContents(List<ScoreboardEntry> entries) {
		List<ScoreboardEntry> newCurrent = new ArrayList<>();

		for (Pair<ScoreboardEntry, Integer> filteredEntryPair : filterUpdates(entries)) {
			ScoreboardEntry entry = filteredEntryPair.getKey();

			Team team = scoreboard.getBukkitScoreboard().getTeam(entry.getName());
			if (team == null)
				team = scoreboard.getBukkitScoreboard().registerNewTeam(entry.getName());

			team.setPrefix(entry.getPrefix());
			team.setSuffix(entry.getSuffix());

			if (!team.hasEntry(entry.getName()))
				team.addEntry(entry.getName());

			objective.getScore(entry.getName()).setScore(filteredEntryPair.getValue());
			newCurrent.add(entry);
		}


		current.removeAll(newCurrent);
		for (ScoreboardEntry previous : current) {
			Team team = scoreboard.getBukkitScoreboard().getTeam(previous.getName());
			if (team != null) {
				team.removeEntry(previous.getName());
				scoreboard.getBukkitScoreboard().resetScores(previous.getName());
			}
		}

		current = newCurrent;

		objective.setDisplayName(scoreboard.getTitle());
	}

	private List<Pair<ScoreboardEntry, Integer>> filterUpdates(List<ScoreboardEntry> entries) {
		List<Pair<ScoreboardEntry, Integer>> filteredUpdates = new ArrayList<>();
		if (current.size() == entries.size()) {
			int size = entries.size();
			for (int i = 0; i < size; i++)
				if (!entries.get(i).equals(current.get(i)))
					filteredUpdates.add(new Pair<>(entries.get(i), size - i));
		} else {
			int size = Math.min(16, entries.size());
			for (int i = 0; i < size; i++)
				filteredUpdates.add(new Pair<>(entries.get(i), size - i));
		}

		return filteredUpdates;
	}
}
