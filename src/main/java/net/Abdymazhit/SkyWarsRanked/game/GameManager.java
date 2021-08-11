package net.Abdymazhit.SkyWarsRanked.game;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.customs.PlayerInfo;
import net.Abdymazhit.SkyWarsRanked.enums.GameStage;
import net.Abdymazhit.SkyWarsRanked.game.chests.ChestManager;
import net.Abdymazhit.SkyWarsRanked.game.chests.MysteryChestManager;
import net.Abdymazhit.SkyWarsRanked.game.events.*;
import net.Abdymazhit.SkyWarsRanked.game.events.cancelled.*;
import net.Abdymazhit.SkyWarsRanked.game.items.GameItems;
import net.Abdymazhit.SkyWarsRanked.game.scoreboards.GameBoard;
import net.Abdymazhit.SkyWarsRanked.game.scoreboards.LobbyBoard;
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
 * @version   11.08.2021
 * @author    Islam Abdymazhit
 */
public class GameManager {

    /** Менеджер стадии игры, отвечает за стадии игры */
    private final GameStageManager gameStageManager;

    /** Менеджер сундуков игры, отвечает за сундуки */
    private final ChestManager chestManager;

    /** Менеджер мистического сундука, отвечает за мистический сундук */
    private final MysteryChestManager mysteryChestManager;

    /** Объект, отвечающий за scoreboard лобби */
    private final LobbyBoard lobbyBoard;

    /** Объект, отвечающий за scoreboard игры */
    private final GameBoard gameBoard;

    /** Объект, отвечающий за игровые предметы */
    private final GameItems gameItems;

    /** {@link GameStage Стадия игры} */
    private GameStage gameStage;

    /** Список игроков игры */
    private final List<Player> players;

    /** Список зрителей игры */
    private final List<Player> spectators;

    /** Хранит {@link PlayerInfo информацию} о игроке */
    private final Map<Player, PlayerInfo> playersInfo;

    /** Отвечает за параметр включения PvP */
    private boolean isEnabledPvP;

    /**
     * Инициализирует нужные объекты
     */
    public GameManager() {
        gameStageManager = new GameStageManager();
        chestManager = new ChestManager();
        mysteryChestManager = new MysteryChestManager();
        lobbyBoard = new LobbyBoard();
        gameBoard = new GameBoard();
        gameItems = new GameItems();
        
        gameStage = GameStage.WAITING;
        players = new ArrayList<>();
        spectators = new ArrayList<>();
        playersInfo = new HashMap<>();
        isEnabledPvP = false;

        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(), SkyWarsRanked.getInstance());
        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new BlockBreakListener(), SkyWarsRanked.getInstance());
        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new BlockPlaceListener(), SkyWarsRanked.getInstance());
        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), SkyWarsRanked.getInstance());
        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new EntityDamageListener(), SkyWarsRanked.getInstance());
        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new EntityShootBowListener(), SkyWarsRanked.getInstance());
        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new InventoryClickListener(), SkyWarsRanked.getInstance());
        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new PlayerDeathListener(), SkyWarsRanked.getInstance());
        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new PlayerInteractListener(), SkyWarsRanked.getInstance());
        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new PlayerJoinListener(), SkyWarsRanked.getInstance());
        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new PlayerLoginListener(), SkyWarsRanked.getInstance());
        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new PlayerQuitListener(), SkyWarsRanked.getInstance());

        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new BlockEventsListener(), SkyWarsRanked.getInstance());
        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new EntityEventsListener(), SkyWarsRanked.getInstance());
        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new InventoryEventsListener(), SkyWarsRanked.getInstance());
        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new PaintingEventsListener(), SkyWarsRanked.getInstance());
        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new PlayerEventsListener(), SkyWarsRanked.getInstance());
        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new WeatherEventsListener(), SkyWarsRanked.getInstance());
        SkyWarsRanked.getInstance().getServer().getPluginManager().registerEvents(new WorldEventsListener(), SkyWarsRanked.getInstance());

        // Сбросить зону
        WorldBorder worldBorder = Bukkit.getWorld("world").getWorldBorder();
        worldBorder.reset();
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
        player.setFlySpeed(0.1f);
        player.setLevel(0);
        player.setExp(0);

        // Выдать игроку предметы лобби
        gameItems.giveLobbyItems(player);

        player.setGameMode(GameMode.ADVENTURE);

        player.teleport(Config.lobbyLocation);

        // Получить данные игрока с базы данных
        SkyWarsRanked.getMySQL().getPlayerKit(player);
        SkyWarsRanked.getMySQL().getPlayerKits(player);
        SkyWarsRanked.getMySQL().getPlayerUpgrades(player);

        spectators.remove(player);
        players.add(player);

        // Попытаться начать игру
        gameStageManager.tryStartStartingStage();
    }

    /**
     * Удаляет игрока из игры
     * @param player Игрок
     */
    public void removePlayer(Player player) {
        players.remove(player);

        // Обновить меню телепортации к игрокам
        gameItems.getTeleportMenu().update();
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
        player.setFlySpeed(0.1f);
        player.setLevel(0);
        player.setExp(0);

        // Выдать зрителю предметы зрителя
        gameItems.giveSpectatorItems(player);

        player.setGameMode(GameMode.ADVENTURE);

        player.setAllowFlight(true);
        player.setFlying(true);

        for(Player p : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(player);
        }

        // Добавить меню настроек зрителя для зрителя
        gameItems.addSpectatorSettingsMenu(player);

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
        gameBoard.updateLivePlayersCount();

        // Добавить игрока в список зрителей и обновить количество зрителей в scoreboard'е игры
        addSpectator(player);
        gameBoard.updateSpectatorsCount();

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
            gameStageManager.startEndingStage();
        }
    }

    /**
     * Получает менеджер сундуков игры
     * @return Менеджер сундуков игры
     */
    public ChestManager getChestManager() {
        return chestManager;
    }

    /**
     * Получает менеджер мистического сундука
     * @return Менеджер мистического сундука
     */
    public MysteryChestManager getMysteryChestManager() {
        return mysteryChestManager;
    }

    /**
     * Получает объект, отвечающий за scoreboard лобби
     * @return Объект, отвечающий за scoreboard лобби
     */
    public LobbyBoard getLobbyBoard() {
        return lobbyBoard;
    }

    /**
     * Получает объект, отвечающий за scoreboard игры
     * @return Объект, отвечающий за scoreboard игры
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Возвращает объект, отвечающий за игровые предметы
     * @return Объект, отвечающий за игровые предметы
     */
    public GameItems getGameItems() {
        return gameItems;
    }

    /**
     * Устанавливает параметр, включен ли PvP
     * @param enabledPvP Включен ли PvP
     */
    public void setEnabledPvP(boolean enabledPvP) {
        isEnabledPvP = enabledPvP;
    }

    /**
     * Получает параметр, включен ли PvP
     * @return Параметр, включен ли PvP
     */
    public boolean isEnabledPvP() {
        return isEnabledPvP;
    }
}