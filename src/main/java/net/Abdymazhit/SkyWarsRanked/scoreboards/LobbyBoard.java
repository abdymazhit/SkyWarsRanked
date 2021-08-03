package net.Abdymazhit.SkyWarsRanked.scoreboards;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

/**
 * Отвечает за scoreboard лобби
 *
 * @version   03.08.2021
 * @author    Islam Abdymazhit
 */
public class LobbyBoard {

    /** Scoreboard лобби */
    private final Scoreboard scoreboard;

    /**
     * Инициализирует scoreboard лобби
     */
    public LobbyBoard() {
        ScoreboardManager scoreboardManager = SkyWarsRanked.getInstance().getServer().getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("sw1", "sw2");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore("§fКарта: §l" + Config.mapName).setScore(6);
        objective.getScore("  ").setScore(5);

        Team players = scoreboard.registerNewTeam("players");
        players.addEntry("§f§lИгроков: §a");
        objective.getScore("§f§lИгроков: §a").setScore(4);

        Team status = scoreboard.registerNewTeam("status");
        status.addEntry("§f§l");
        objective.getScore("§f§l").setScore(3);

        objective.getScore("").setScore(2);
        objective.getScore("§a§lVimeWorld.ru").setScore(1);

        objective.setDisplayName("§b§lSkyWars Ranked");
    }

    /**
     * Установить всем игрокам статус scoreboard'а лобби на WAITING
     */
    public void setWaitingStatus() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.getScoreboard().getTeam("status").setSuffix("Ожидание игроков...");
        }
    }

    /**
     * Установить игроку статус scoreboard'а лобби на WAITING
     * @param player Игрок
     */
    public void setWaitingStatus(Player player) {
        player.getScoreboard().getTeam("status").setSuffix("Ожидание игроков...");
    }

    /**
     * Установить всем игрокам статус scoreboard'а лобби на начало с обратным отсчетом
     * @param text Текст обратного отсчета
     */
    public void setStartingStatus(String text) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.getScoreboard().getTeam("status").setSuffix(text);
        }
    }

    /**
     * Обновить количество игроков в scoreboard'е лобби
     */
    public void updatePlayersCount() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.getScoreboard().getTeam("players").setSuffix(SkyWarsRanked.getGameManager().getPlayers().size() + "/" + Config.islands.size());
        }
    }

    /**
     * Установить игроку scoreboard лобби
     * @param player Игрок
     */
    public void setScoreboard(Player player) {
        player.setScoreboard(scoreboard);
    }
}