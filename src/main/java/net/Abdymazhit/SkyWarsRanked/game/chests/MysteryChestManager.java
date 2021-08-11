package net.Abdymazhit.SkyWarsRanked.game.chests;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

/**
 * Менеджер мистического сундука, отвечает за мистический сундук
 *
 * @version   11.08.2021
 * @author    Islam Abdymazhit
 */
public class MysteryChestManager {

    /** Инвентарь мистического сундука */
    private final Inventory inventory;

    /** Значение, открыт ли мистический сундук */
    private boolean isOpen;

    /**
     * Инициализирует мистический сундук
     */
    public MysteryChestManager() {
        isOpen = false;
        inventory = Bukkit.createInventory(null, 27, "Мистический сундук");
    }

    /**
     * Открывает мистический сундук
     */
    public void open() {
        SkyWarsRanked.getGameManager().getChestManager().refillMysteryChest(inventory);
        isOpen = true;
    }

    /**
     * Закрывает мистический сундук
     */
    public void close() {
        inventory.clear();
        isOpen = false;
    }

    /**
     * Получает инвентарь мистического сундука
     * @return Инвентарь мистического сундука
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Получает значение, открыт ли мистический сундук
     * @return Значение, открыт ли мистический сундук
     */
    public boolean isOpen() {
        return isOpen;
    }
}
