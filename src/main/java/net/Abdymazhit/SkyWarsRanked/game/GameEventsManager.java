package net.Abdymazhit.SkyWarsRanked.game;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.customs.Island;
import net.Abdymazhit.SkyWarsRanked.enums.GameState;
import net.Abdymazhit.SkyWarsRanked.game.tasks.BattleStartTask;
import net.Abdymazhit.SkyWarsRanked.game.tasks.GameEndTask;
import net.Abdymazhit.SkyWarsRanked.game.tasks.GameStartTask;
import net.Abdymazhit.SkyWarsRanked.kits.Kit;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.WorldBorder;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Менеджер {@link GameState стадии игры}, отвечает за изменение {@link GameState стадии игры}
 *
 * @version   21.08.2021
 * @author    Islam Abdymazhit
 */
public class GameEventsManager {

    /** Task игрового события */
    public BukkitRunnable task;

    /** Время до заполнения сундуков */
    public int timeBeforeRefillingChests;

    /**
     * Начинает {@link GameState стадию игры} WAITING
     */
    public void startWaitingState() {
        // Установить стадию игры на WAITING
        SkyWarsRanked.getGameManager().setGameState(GameState.WAITING);

        // Установить статус scoreboard'а лобби на WAITING
        SkyWarsRanked.getGameManager().getLobbyBoard().setWaitingStatus();
    }

    /**
     * Попытается начать {@link GameState стадию игры} STARTING
     */
    public void tryStartStartingState() {
        // Начать стадию STARTING, если набрано достаточное количество игроков
        if (SkyWarsRanked.getGameManager().getPlayers().size() == 2) {
            startStartingState();
        }
    }

    /**
     * Начинает {@link GameState стадию игры} STARTING
     */
    private void startStartingState() {
        // Установить стадию игры на STARTING
        SkyWarsRanked.getGameManager().setGameState(GameState.STARTING);

        // Начать обратный отсчет начала игры
        task = new GameStartTask(this);
        task.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);
    }

    /**
     * Начинает {@link GameState стадию игры} GAME
     */
    public void startGameState() {
        // Установить стадию игры на GAME
        SkyWarsRanked.getGameManager().setGameState(GameState.GAME);

        // Установить для игрока остров, если игрок не выбрал остров
        for(Player player : SkyWarsRanked.getGameManager().getPlayers()) {
            boolean hasIsland = false;

            for(Island island : Config.islands) {
                if(!hasIsland) {
                    if(island.getPlayers().contains(player)) {
                        hasIsland = true;
                    }
                }
            }

            if(!hasIsland) {
                boolean islandSelected = false;

                for (Island island : Config.islands) {
                    if (!islandSelected) {
                        if (island.getPlayers().size() < Config.islandPlayers) {
                            island.addPlayer(player);
                            islandSelected = true;
                        }
                    }
                }
            }
        }

        // Перекинуть игроков в игру
        for(Island island : Config.islands) {
            for(Player player : island.getPlayers()) {
                player.setFireTicks(0);
                player.setNoDamageTicks(200);
                player.setMaxHealth(20.0);
                player.setHealth(20.0);
                player.setFoodLevel(20);
                player.setSaturation(10);
                player.setFlySpeed(0.1f);
                player.setLevel(0);
                player.setExp(0);

                for(PotionEffect potionEffect : player.getActivePotionEffects()) {
                    player.removePotionEffect(potionEffect.getType());
                }

                player.setItemOnCursor(null);
                player.closeInventory();

                // Очистить инвентарь игрока
                player.getInventory().setHelmet(null);
                player.getInventory().setChestplate(null);
                player.getInventory().setLeggings(null);
                player.getInventory().setBoots(null);
                player.getInventory().clear();

                // Установить игровой режим на выживание
                player.setGameMode(GameMode.SURVIVAL);

                // Установить игрокам scoreboard игры
                SkyWarsRanked.getGameManager().getGameBoard().setScoreboard(player);

                // Установить игроку количество его убийств в scoreboard'е игры
                SkyWarsRanked.getGameManager().getGameBoard().updateKillsCount(player);

                // Телепортировать игрока в спавн острова
                player.teleport(island.getSpawn());

                // Выдать игроку набор
                Kit.equip(player);

                // Установить игрокам теги
                EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(p != player) {
                        String replacement = "";

                        if (Config.islandPlayers > 1) {
                            replacement = "[" + island.getTag() + "] ";
                        }

                        if(island.getPlayers().contains(p)) {
                            replacement = "§a" + replacement;
                        } else {
                            replacement = "§c" + replacement;
                        }

                        entityPlayer.setCustomName(replacement + entityPlayer.getName());
                        entityPlayer.setCustomNameVisible(true);

                        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.UPDATE_DISPLAY_NAME, entityPlayer);
                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
                    }
                }
            }
        }

        // Перекинуть зрителей в игру
        for(Player player : SkyWarsRanked.getGameManager().getSpectators()) {
            // Установить зрителям scoreboard игры
            SkyWarsRanked.getGameManager().getGameBoard().setScoreboard(player);

            // Установить зрителям количество их убийств в scoreboard'е игры
            SkyWarsRanked.getGameManager().getGameBoard().updateKillsCount(player);
        }

        // Обновить меню телепортации к игрокам
        SkyWarsRanked.getGameManager().getGameItemsManager().getTeleportMenu().update();

        // Обновить количество живых игроков в scoreboard'е игры
        SkyWarsRanked.getGameManager().getGameBoard().updateLivePlayersCount();

        // Обновить количество зрителей в scoreboard'е игры
        SkyWarsRanked.getGameManager().getGameBoard().updateSpectatorsCount();

        // Установить зону
        WorldBorder worldBorder = Config.world.getWorldBorder();
        worldBorder.setCenter(Config.mysteryChest);
        worldBorder.setSize(300);

        // Заполнить сундуки лутом
        SkyWarsRanked.getGameManager().getChestManager().refillIslandChests();
        SkyWarsRanked.getGameManager().getChestManager().refillBasicChests();
        SkyWarsRanked.getGameManager().getChestManager().refillMiddleChests();

        // Начать игровое событие начала битвы
        task = new BattleStartTask(this);
        task.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);
    }

    /**
     * Начинает {@link GameState стадию игры} ENDING
     */
    public void startEndingState() {
        // Отменить таймер предыдущего события
        task.cancel();

        // Установить стадию игры на ENDING
        SkyWarsRanked.getGameManager().setGameState(GameState.ENDING);

        task = new GameEndTask();
        task.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);
    }

    /**
     * Получает время до заполнения сундуков
     * @return Время до заполнения сундуков
     */
    public int getTimeBeforeRefillingChests() {
        return timeBeforeRefillingChests;
    }
}