package net.Abdymazhit.SkyWarsRanked.game;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.utils.NMS;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Отвечает за работу GPS трекера
 *
 * @version   13.08.2021
 * @author    Islam Abdymazhit
 */
public class PlayerTrackerCompass {

    /** Хранит информацию о игроке и его таймере GPS трекера */
    private final Map<Player, BukkitTask> playersTasks;

    /** Хранит информацию о отслеживаемом игроке для владельца GPS трекера */
    private final Map<Player, Player> playersTargets;

    /**
     * Инициализирует объекты
     */
    public PlayerTrackerCompass() {
        playersTasks = new HashMap<>();
        playersTargets = new HashMap<>();
    }

    /**
     * Использует предмет GPS трекера игроку
     * @param player Игрок
     */
    public void useItem(Player player) {
        // Удалить предыдущего отслеживаемого игрока
        if(playersTasks.containsKey(player)) {
            BukkitTask task = playersTasks.get(player);
            task.cancel();
            playersTasks.remove(player);
        }

        // Найти ближайшего игрока
        List<Player> players = new ArrayList<>(SkyWarsRanked.getGameManager().getPlayers());
        players.remove(player);

        Player targetPlayer = null;
        double distanceToTarget = 0;

        for(Player target : players) {
            double distance = player.getLocation().distance(target.getLocation());

            if (targetPlayer == null) {
                distanceToTarget = distance;
                targetPlayer = target;
            } else {
                if (distance < distanceToTarget) {
                    distanceToTarget = distance;
                    targetPlayer = target;
                }
            }
        }

        // Проверка, найден ли ближайший игрок
        if(targetPlayer != null) {
            playersTargets.put(player, targetPlayer);

            BukkitTask task = new BukkitRunnable() {
                @Override
                public void run() {
                    // Проверка, умер ли игрок
                    if(!SkyWarsRanked.getGameManager().getPlayers().contains(player)) {
                        NMS.sendActionBar(player, "");
                        playersTasks.remove(player);
                        playersTargets.remove(player);
                        cancel();
                    }
                    // Проверка, умер ли отслеживаемый игрок
                    else if(!SkyWarsRanked.getGameManager().getPlayers().contains(playersTargets.get(player))) {
                        NMS.sendActionBar(player, "§c§lОтслеживаемый игрок умер!");
                        playersTasks.remove(player);
                        playersTargets.remove(player);
                        cancel();
                    }
                    // Проверка, есть ли у игрока GPS трекер в инвентаре
                    else if(!player.getInventory().contains(Material.COMPASS)) {
                        NMS.sendActionBar(player, "§c§lВы потеряли GPS трекер!");
                        playersTasks.remove(player);
                        playersTargets.remove(player);
                        cancel();
                    }

                    // Получить отслеживаемого игрока
                    Player target = playersTargets.get(player);

                    // Проверка, существует ли отслеживаемый игрок
                    if(target != null) {
                        int distance = (int) player.getLocation().distance(target.getLocation());
                        player.setCompassTarget(target.getLocation());
                        NMS.sendActionBar(player, "§f§lОтслеживаемый: §e§l" + target.getName() + ", §f§lРасстояние: §a§l" + distance + "м");
                    }
                }
            }.runTaskTimer(SkyWarsRanked.getInstance(), 0L, 20L);

            playersTasks.put(player, task);
        } else {
            player.sendMessage("§cБлижайший отслеживаемый игрок не найден!");
        }
    }
}