package com.irisdevelopment.scoreboard;

import org.bukkit.entity.Player;

import java.util.List;

public interface ScoreboardEntryProvider {

	List<ScoreboardEntry> getLines(Player player);

}
