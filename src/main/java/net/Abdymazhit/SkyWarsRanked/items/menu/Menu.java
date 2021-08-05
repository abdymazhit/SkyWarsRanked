package net.Abdymazhit.SkyWarsRanked.items.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Представляет собой меню
 *
 * @version   05.08.2021
 * @author    Islam Abdymazhit
 */
public class Menu {

    /** Инвентарь меню */
    private Inventory inventory;

    /**
     * Устанавливает инвентарь меню
     * @param inventory Инвентарь меню
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Получает инвентарь меню
     * @return Инвентарь меню
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Открывает меню для игрока
     * @param player Игрок
     */
    public void open(Player player) {
        player.openInventory(inventory);
    }

    /**
     * Обрабатывает событие клика по слоту
     * @param player Игрок
     * @param slot Слот
     */
    public void clickSlot(Player player, int slot) {

    }
}