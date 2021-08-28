package com.irisdevelopment.scoreboard;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

public class Scoreboard {

	private final JavaPlugin plugin;

	private final @Getter org.bukkit.scoreboard.Scoreboard bukkitScoreboard;

	private final @Getter Player owningPlayer;
	private @Getter @Setter String title;
	private @Getter @Setter long updateTickInterval;

	private @Getter ObjectiveHandler objectiveHandler;
	private @Getter ScoreboardEntryProvider entryProvider;

	private int taskID = -1;

	public Scoreboard(JavaPlugin plugin, Player owningPlayer, String title, long updateTickInterval) {
		this.plugin = plugin;
		this.owningPlayer = owningPlayer;
		this.title = title;
		this.updateTickInterval = updateTickInterval;

		this.bukkitScoreboard = plugin.getServer().getScoreboardManager().getNewScoreboard();
		this.objectiveHandler = new ObjectiveHandler(this);

		owningPlayer.setScoreboard(bukkitScoreboard);
	}


	public void destroy() {
		if (objectiveHandler != null) {
			for (Team team : bukkitScoreboard.getTeams())
				team.unregister();

			for (Objective objective : bukkitScoreboard.getObjectives())
				objective.unregister();

			if (taskID != -1)
				plugin.getServer().getScheduler().cancelTask(taskID);

			objectiveHandler = null;
		}
	}

	public void revive() {
		this.objectiveHandler = new ObjectiveHandler(this);

		if (entryProvider != null)
			setEntryProvider(entryProvider);
	}

	public void reset() {
		destroy();
		revive();
	}


	public void setEntryProvider(ScoreboardEntryProvider entryProvider) {
		if (taskID != -1)
			plugin.getServer().getScheduler().cancelTask(taskID);

		this.entryProvider = entryProvider;
		taskID =
				plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, () -> {
					objectiveHandler.updateContents(entryProvider.getLines(owningPlayer));
				}, updateTickInterval, updateTickInterval).getTaskId();
	}

}
