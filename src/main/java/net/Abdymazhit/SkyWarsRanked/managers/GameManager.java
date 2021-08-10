package net.Abdymazhit.SkyWarsRanked.managers;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.customs.PlayerInfo;
import net.Abdymazhit.SkyWarsRanked.enums.GameStage;
import net.Abdymazhit.SkyWarsRanked.upgrades.Upgrade;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Менеджер игры, отвечает за работу игры
 *
 * @version   10.08.2021
 * @author    Islam Abdymazhit
 */
public class GameManager {

    /** {@link GameStage Стадия игры} */
    private GameStage gameStage;

    /** Список игроков игры */
    private final List<Player> players;

    /** Список зрителей игры */
    private final List<Player> spectators;

    /** Хранит {@link PlayerInfo информацию} о игроке */
    private final Map<Player, PlayerInfo> playersInfo;

    /**
     * Инициализирует нужные объекты
     */
    public GameManager() {
        gameStage = GameStage.WAITING;
        players = new ArrayList<>();
        spectators = new ArrayList<>();
        playersInfo = new HashMap<>();

        // Установить зону
        WorldBorder worldBorder = Bukkit.getWorld("world").getWorldBorder();
        worldBorder.reset();
        worldBorder.setCenter(Config.mysteryChest);
        worldBorder.setSize(200);
    }

    /**
     * Устанавливает {@link GameStage стадию игры}
     * @param gameStage {@link GameStage Стадия игры}
     */
    public void setGameStage(GameStage gameStage) {
        this.gameStage = gameStage;
    }

    /** Получает {@link GameStage стадию игры}
     * @return {@link GameStage Стадия игры}
     */
    public GameStage getGameStage() {
        return gameStage;
    }

    /**
     * Добавляет игрока в игру
     * @param player Игрок
     */
    public void addPlayer(Player player) {
        player.setFireTicks(0);
        player.setMaxHealth(20.0);
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setSaturation(10);

        // Выдать игроку предметы лобби
        SkyWarsRanked.getGameItems().giveLobbyItems(player);

        player.setGameMode(GameMode.ADVENTURE);

        player.teleport(Config.lobbyLocation);

        // Получить данные игрока с базы данных
        SkyWarsRanked.getMySQL().getPlayerKit(player);
        SkyWarsRanked.getMySQL().getPlayerKits(player);
        SkyWarsRanked.getMySQL().getPlayerUpgrades(player);

        spectators.remove(player);
        players.add(player);

        // Попытаться начать игру
        SkyWarsRanked.getGameStageManager().tryStartStartingStage();
    }

    /**
     * Удаляет игрока из игры
     * @param player Игрок
     */
    public void removePlayer(Player player) {
        players.remove(player);

        // Обновить меню телепортации к игрокам
        SkyWarsRanked.getGameItems().getTeleportMenu().update();
    }

    /** Получает список игроков игры
     * @return Список игроков игры
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Добавляет зрителя в игру
     * @param player Зритель
     */
    public void addSpectator(Player player) {
        player.setFireTicks(0);
        player.setMaxHealth(20.0);
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setSaturation(10);

        // Выдать зрителю предметы зрителя
        SkyWarsRanked.getGameItems().giveSpectatorItems(player);

        player.setGameMode(GameMode.ADVENTURE);

        player.setAllowFlight(true);
        player.setFlying(true);
        player.setFlySpeed(0.1f);

        for(Player p : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(player);
        }

        // Добавить меню настроек зрителя для зрителя
        SkyWarsRanked.getGameItems().addSpectatorSettingsMenu(player);

        players.remove(player);
        spectators.add(player);
    }

    /**
     * Удаляет зрителя из игры
     * @param player Зритель
     */
    public void removeSpectator(Player player) {
        spectators.remove(player);
    }

    /** Получает список зрителей игры
     * @return Список зрителей игры
     */
    public List<Player> getSpectators() {
        return spectators;
    }

    /**
     * Добавляет информацию о игроке
     * @param player Игрок
     * @param playerInfo Информация о игроке
     */
    public void addPlayerInfo(Player player, PlayerInfo playerInfo) {
        playersInfo.put(player, playerInfo);
    }

    /**
     * Удаляет информацию о игроке
     * @param player Игрок
     */
    public void removePlayerInfo(Player player) {
        playersInfo.remove(player);
    }

    /** Получает {@link PlayerInfo информацию} о игроке
     * @return {@link PlayerInfo Информация} о игроке
     */
    public PlayerInfo getPlayerInfo(Player player) {
        return playersInfo.get(player);
    }

    /**
     * Выполняет действия убийства игрока
     * @param player Игрок (умерший)
     */
    public void performKillEvent(Player player) {
        // Установить местоположение смерти игрока
        Location deathLocation = player.getLocation();
        deathLocation.setY(Config.respawnY);
        playersInfo.get(player).setDeathLocation(deathLocation);

        // Телепортировать игрока в местоположение смерти
        player.teleport(playersInfo.get(player).getDeathLocation());

        // Удалить игрока из списка живых игроков и обновить количество живых игроков в scoreboard'е игры
        removePlayer(player);
        SkyWarsRanked.getGameBoard().updateLivePlayersCount();

        // Добавить игрока в список зрителей и обновить количество зрителей в scoreboard'е игры
        addSpectator(player);
        SkyWarsRanked.getGameBoard().updateSpectatorsCount();

        // Получить убийцу игрока (последнего нанесшего урон)
        Player killer = playersInfo.get(player).getLastDamager();

        // Проверить, существует ли последний нанесший урон
        if(killer != null) {
            // Выполнить действия прокачки Джаггернаут
            int improvement = Upgrade.getPlayerUpgradeImprovement(killer, Upgrade.JUGGERNAUT);
            if(improvement > 0) {
                killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, improvement * 20, 0));
            }

            // Отправить сообщения о убийстве
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage("Игрок " + player.getDisplayName() + " убит игроком " + killer.getName());
            }
            player.sendMessage("Вас убил игрок §c" + killer.getDisplayName() + " §fи у него осталось §c" + (killer.getHealth() / 2) + "❤");

            // Добавить коины и опыт проигравшему
            SkyWarsRanked.getMySQL().addCoins(player, 5 + playersInfo.get(player).getKills() * 4);
            SkyWarsRanked.getMySQL().giveExp(player, 5 + playersInfo.get(player).getKills() * 4);

            // Добавить коины и опыт убийце
            SkyWarsRanked.getMySQL().addCoins(killer, 5);
            SkyWarsRanked.getMySQL().giveExp(killer, 5);
        } else {
            // Отправить сообщения о убийстве
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage("Игрок " + player.getName() + " самоубился");
            }
            player.sendMessage("Вы самоубились");
        }

        // Проверить, есть ли победитель игры
        if(players.size() == 1) {
            Player winner = players.get(0);

            // Отправить сообщение о победителе
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage("§7####################################");
                p.sendMessage("§7# §fПобедитель:");
                p.sendMessage("§7#     " + winner.getDisplayName());
                p.sendMessage("§7####################################");
            }

            // Добавить коины и опыт победителю
            SkyWarsRanked.getMySQL().addCoins(winner, 50 + playersInfo.get(winner).getKills() * 5);
            SkyWarsRanked.getMySQL().giveExp(winner , 50 + playersInfo.get(winner).getKills() * 5);

            // Начать стадию конца игры
            SkyWarsRanked.getGameStageManager().startEndingStage();
        }
    }
}