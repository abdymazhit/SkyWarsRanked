package net.Abdymazhit.SkyWarsRanked.game.items.menu;

import net.Abdymazhit.SkyWarsRanked.Config;
import net.Abdymazhit.SkyWarsRanked.customs.Island;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Меню выбора острова
 *
 * @version   13.08.2021
 * @author    Islam Abdymazhit
 */
public class IslandSelectMenu extends Menu {

    /** Хранит информацию о id острове и его слоте */
    private final Map<Integer, Integer> islandsIdSlot;

    /**
     * Инициализирует объекты меню
     */
    public IslandSelectMenu() {
        setInventory(Bukkit.createInventory(null, 27, "Выбор острова"));

        islandsIdSlot = new HashMap<>();

        // Добавить слоты для островов по id
        if(Config.islands.size() == 8) {
            islandsIdSlot.put(1, 2);
            islandsIdSlot.put(2, 3);
            islandsIdSlot.put(3, 5);
            islandsIdSlot.put(4, 6);
            islandsIdSlot.put(5, 20);
            islandsIdSlot.put(6, 21);
            islandsIdSlot.put(7, 23);
            islandsIdSlot.put(8, 24);
        } else if(Config.islands.size() == 10) {
            islandsIdSlot.put(1, 1);
            islandsIdSlot.put(2, 2);
            islandsIdSlot.put(3, 3);
            islandsIdSlot.put(4, 5);
            islandsIdSlot.put(5, 6);
            islandsIdSlot.put(6, 7);
            islandsIdSlot.put(7, 10);
            islandsIdSlot.put(8, 11);
            islandsIdSlot.put(9, 15);
            islandsIdSlot.put(10, 16);
        } else if(Config.islands.size() == 12) {
            islandsIdSlot.put(1, 1);
            islandsIdSlot.put(2, 2);
            islandsIdSlot.put(3, 3);
            islandsIdSlot.put(4, 5);
            islandsIdSlot.put(5, 6);
            islandsIdSlot.put(6, 7);
            islandsIdSlot.put(7, 19);
            islandsIdSlot.put(8, 20);
            islandsIdSlot.put(9, 21);
            islandsIdSlot.put(10, 23);
            islandsIdSlot.put(11, 24);
            islandsIdSlot.put(12, 25);
        } else if(Config.islands.size() == 16) {
            islandsIdSlot.put(1, 0);
            islandsIdSlot.put(2, 1);
            islandsIdSlot.put(3, 2);
            islandsIdSlot.put(4, 3);
            islandsIdSlot.put(5, 5);
            islandsIdSlot.put(6, 6);
            islandsIdSlot.put(7, 7);
            islandsIdSlot.put(8, 8);
            islandsIdSlot.put(9, 18);
            islandsIdSlot.put(10, 19);
            islandsIdSlot.put(11, 20);
            islandsIdSlot.put(12, 21);
            islandsIdSlot.put(13, 23);
            islandsIdSlot.put(14, 24);
            islandsIdSlot.put(15, 25);
            islandsIdSlot.put(16, 26);
        } else if(Config.islands.size() == 20) {
            islandsIdSlot.put(1, 0);
            islandsIdSlot.put(2, 1);
            islandsIdSlot.put(3, 2);
            islandsIdSlot.put(4, 3);
            islandsIdSlot.put(5, 5);
            islandsIdSlot.put(6, 6);
            islandsIdSlot.put(7, 7);
            islandsIdSlot.put(8, 8);
            islandsIdSlot.put(9, 9);
            islandsIdSlot.put(10, 10);
            islandsIdSlot.put(11, 16);
            islandsIdSlot.put(12, 17);
            islandsIdSlot.put(13, 18);
            islandsIdSlot.put(14, 19);
            islandsIdSlot.put(15, 20);
            islandsIdSlot.put(16, 21);
            islandsIdSlot.put(17, 23);
            islandsIdSlot.put(18, 24);
            islandsIdSlot.put(19, 25);
            islandsIdSlot.put(20, 26);
        }

        update();
    }

    /**
     * Обновляет меню выбора острова
     */
    public void update() {
        for(Island island : Config.islands) {
            if(island.getPlayer() != null) {
                setIslandLockedItem(island);
            } else {
                setIslandAvailableItem(island);
            }
        }
    }

    /**
     * Устанавливает для острова предмет доступности для выбора
     * @param island Остров
     */
    private void setIslandAvailableItem(Island island) {
        ItemStack islandItem = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta islandItemMeta = islandItem.getItemMeta();
        islandItemMeta.setDisplayName("§rОстров " + island.getId() + " [0/1]");
        islandItemMeta.setLore(Collections.singletonList("§5§oНажмите для выбора"));
        islandItem.setItemMeta(islandItemMeta);
        getInventory().setItem(islandsIdSlot.get(island.getId()), islandItem);
    }

    /**
     * Устанавливает для острова предмет недоступности для выбора
     * @param island Остров
     */
    private void setIslandLockedItem(Island island) {
        ItemStack islandItem = new ItemStack(Material.STAINED_CLAY, 1, (byte) 14);
        ItemMeta islandItemMeta = islandItem.getItemMeta();
        islandItemMeta.setDisplayName("§rОстров " + island.getId() + " [1/1]");
        islandItemMeta.setLore(Collections.singletonList("§r" + island.getPlayer().getDisplayName()));
        islandItem.setItemMeta(islandItemMeta);
        getInventory().setItem(islandsIdSlot.get(island.getId()), islandItem);
    }

    /**
     * Выбирает остров для игрока по слоту
     * @param player Игрок
     * @param slot Слот
     */
    @Override
    public void clickSlot(Player player, int slot) {
        super.clickSlot(player, slot);
        // Проверка нажатого предмета на EMERALD_BLOCK
        // EMERALD_BLOCK означает, что остров доступен для выбора
        if(getInventory().getItem(slot) != null && getInventory().getItem(slot).getType().equals(Material.EMERALD_BLOCK)) {
            // Id острова
            int islandId = 0;

            // Найти id острова по слоту
            for(int id : islandsIdSlot.keySet()) {
                int islandSlot = islandsIdSlot.get(id);
                if(islandSlot == slot) {
                    islandId = id;
                }
            }

            // Проверка, найден ли id острова
            if(islandId != 0) {
                // Выбрать остров для игрока
                select(player, islandId);
            }
        }
    }

    /**
     * Выбирает остров для игрока по id острова
     * @param player Игрок
     * @param islandId Id острова
     */
    private void select(Player player, int islandId) {
        // Удалить старый остров игрока
        for(Island island : Config.islands) {
            if(island.getPlayer() != null && island.getPlayer() == player) {
                island.setPlayer(null);
            }
        }

        // Установить для игрока новый остров
        Config.islands.get(islandId - 1).setPlayer(player);

        // Обновить меню
        update();
    }
}