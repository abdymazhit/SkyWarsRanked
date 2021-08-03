package net.Abdymazhit.SkyWarsRanked.events.cancelled;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.managers.GameStage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;

/**
 * Отменяет события связанные с блоками
 *
 * @version   03.08.2021
 * @author    Islam Abdymazhit
 */
public class BlockEventsListener implements Listener {

    /**
     * Событие ломания блока игроком
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWarsRanked.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие разрушения блока в результате сожжения огнем
     */
    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие получения информации можно ли ставить блок
     */
    @EventHandler
    public void onBlockCanBuild(BlockCanBuildEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setBuildable(false);
        }
    }

    /**
     * Событие повреждения блока игроком
     */
    @EventHandler
    public void onBlockDamage(BlockDamageEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWarsRanked.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие выдачи предмета блоком
     */
    @EventHandler
    public void onBlockDispense(BlockDispenseEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие получения опыта блоком
     */
    @EventHandler
    public void onBlockExp(BlockExpEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setExpToDrop(0);
        }
    }

    /**
     * Событие взрыва блока
     */
    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие исчезания блока мировым условием
     */
    @EventHandler
    public void onBlockFade(BlockFadeEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие формирования блока мировым условием
     */
    @EventHandler
    public void onBlockForm(BlockFormEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие формирования блока природными событиями
     */
    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие роста блока
     */
    @EventHandler
    public void onBlockGrow(BlockGrowEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие воспламенения блока
     */
    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие поставки двойного блока (кровать и т.д.)
     */
    @EventHandler
    public void onBlockMultiPlace(BlockMultiPlaceEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWarsRanked.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие проверки физики блока
     */
    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие расширения поршня
     */
    @EventHandler
    public void onBlockPistonExtend(BlockPistonExtendEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие втягивания поршня
     */
    @EventHandler
    public void onBlockPistonRetract(BlockPistonRetractEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие поставки блока игроком
     */
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWarsRanked.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие изменения тока redstone
     */
    @EventHandler
    public void onBlockRedstone(BlockRedstoneEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setNewCurrent(0);
        }
    }

    /**
     * Событие распространения блока на основе мировых условий
     */
    @EventHandler
    public void onBlockSpread(BlockSpreadEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие формирования блока сущностями
     */
    @EventHandler
    public void onEntityBlockForm(EntityBlockFormEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }
    }

    /**
     * Событие выпадения листьев
     */
    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    /**
     * Событие изменения текста в табличке игроком
     */
    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        if(SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.WAITING) || SkyWarsRanked.getGameManager().getGameStage().equals(GameStage.STARTING)) {
            event.setCancelled(true);
        }

        if(SkyWarsRanked.getGameManager().getSpectators().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}