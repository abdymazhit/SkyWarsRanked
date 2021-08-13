package net.Abdymazhit.SkyWarsRanked.game.events;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.enums.GameStage;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Отвечает за событие взаимодействия игрока с объектом
 *
 * @version   13.08.2021
 * @author    Islam Abdymazhit
 */
public class PlayerInteractListener implements Listener {

    /**
     * Событие взаимодействия игрока с объектом
     */
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Проверка, является ли игрок зрителем или является ли стадия игры WAITING или STARTING
        if(SkyWarsRanked.getGameManager().getSpectators().contains(player) ||
                SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            // Отменить событие, чтобы игрок не мог взаимодействовать с блоками
            event.setCancelled(true);

            // Проверка клика на правый
            if(event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) {
                return;
            }

            // Проверка существует ли предмет
            if(event.getItem() == null) {
                return;
            }

            // Использовать предмет игроку
            SkyWarsRanked.getGameManager().getGameItems().useItem(player, event.getItem());
        }
        // Проверка стадии игры на GAME
        else if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.GAME)) {
            // Проверка клика на правый
            if(event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) {
                return;
            }

            // Проверка на взаимодействие с эндер сундуком
            if (event.hasBlock() && event.getClickedBlock().getType().equals(Material.ENDER_CHEST)) {
                event.setCancelled(true);

                // Проверка на взаимодействие с мистическим сундуком
                if (Config.mysteryChest.equals(event.getClickedBlock().getLocation())) {
                    // Отправить сообщение, что мистический сундук закрыт
                    if (!SkyWarsRanked.getGameManager().getChestManager().getMysteryChest().isOpen()) {
                        player.sendMessage("§cСундук в данный момент закрыт. Приходите позже.");
                    }
                    // Открыть мистический сундук игроку
                    else {
                        player.openInventory(SkyWarsRanked.getGameManager().getChestManager().getMysteryChest().getInventory());
                    }
                }
            }
            // Проверка на взаимодействие с сундуком
            else if (event.hasBlock() && event.getClickedBlock().getType().equals(Material.CHEST)) {
                // Добавить голограмму открытого сундука
                Chest chest = (Chest) event.getClickedBlock().getState();
                SkyWarsRanked.getGameManager().getChestManager().setCurrentOpenedPlayerChest(player, chest);
                SkyWarsRanked.getGameManager().getChestManager().addOpenedChestHologram(chest);
            }
            // Проверка на взаимодействие с GPS трекером
            else if (event.getItem() != null && event.getItem().getType().equals(Material.COMPASS)) {
                // Отменить событие, чтобы игрок не мог взаимодействовать с блоками
                event.setCancelled(true);

                // Проверка клика на правый
                if(event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) {
                    return;
                }

                // Использовать GPS трекер игроку
                SkyWarsRanked.getGameManager().getPlayerTrackerCompass().useItem(player);
            }
        }
    }
}