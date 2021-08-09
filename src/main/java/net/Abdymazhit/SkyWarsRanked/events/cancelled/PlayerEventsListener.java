package net.Abdymazhit.SkyWarsRanked.events.cancelled;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.enums.GameStage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

/**
 * Отменяет события связанные с игроком
 *
 * @version   09.08.2021
 * @author    Islam Abdymazhit
 */
public class PlayerEventsListener implements Listener {

    /**
     * Событие получения достижении
     */
    @EventHandler
    public void onPlayerAchievementAwarded(PlayerAchievementAwardedEvent event) {
        event.setCancelled(true);
    }

    /**
     * Событие взаимодействия с подставкой для брони
     */
    @EventHandler
    public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWarsRanked.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие взаимодействия с кроватью
     */
    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        event.setCancelled(true);
    }

    /**
     * Событие выкидывания предмета
     */
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWarsRanked.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие изменения опыта
     */
    @EventHandler
    public void onPlayerExpChange(PlayerExpChangeEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setAmount(0);
        }

        if(SkyWarsRanked.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setAmount(0);
        }
    }

    /**
     * Событие снижения прочности предмета
     */
    @EventHandler
    public void PlayerItemDamage(PlayerItemDamageEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWarsRanked.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие поднятия предмета
     */
    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWarsRanked.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие телепорта через портал
     */
    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        event.setCancelled(true);
    }

    /**
     * Событие увеличения статистики игрока
     */
    @EventHandler
    public void PlayerStatisticIncrementEvent(PlayerStatisticIncrementEvent event) {
        event.setCancelled(true);
    }
}