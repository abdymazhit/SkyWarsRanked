package net.Abdymazhit.SkyWarsRanked.game.chests.loot;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Отвечает за генерацию лута в сундуках
 *
 * @version   11.08.2021
 * @author    Islam Abdymazhit
 */
public abstract class LootGenerator {

    /** Количество перезаполнении сундуков */
    public int rotation;

    /**
     * Инициализация нужных объектов
     */
    public LootGenerator() {
        this.rotation = 0;
    }

    /**
     * Получает сгенерированный список лута базовых сундуков
     * @return Сгенерированный список лута базовых сундуков
     */
    public abstract List<ItemStack> basic();

    /**
     * Получает сгенерированный список лута центральных сундуков
     * @return Сгенерированный список лута центральных сундуков
     */
    public abstract List<ItemStack> middle();

    /**
     * Получает сгенерированный список лута мистического сундука
     * @return Сгенерированный список лута мистического сундука
     */
    public abstract List<ItemStack> mystic();

    /**
     * Проверяет, является ли предмет алмазным
     * @param type Тип предмета
     * @return Значение, является ли предмет алмазным
     */
    protected boolean isDiamond(Material type) {
        switch (type) {
            case DIAMOND_HELMET:
            case DIAMOND_CHESTPLATE:
            case DIAMOND_LEGGINGS:
            case DIAMOND_BOOTS: {
                return true;
            }
            default: {
                return false;
            }
        }
    }

    /**
     * Проверяет, является ли предмет железным
     * @param type Тип предмета
     * @return Значение, является ли предмет железным
     */
    protected boolean isIron(Material type) {
        switch (type) {
            case IRON_HELMET:
            case IRON_CHESTPLATE:
            case IRON_LEGGINGS:
            case IRON_BOOTS: {
                return true;
            }
            default: {
                return false;
            }
        }
    }

    /**
     * Создаёт предмет
     * @param material Материал предмета
     * @param name Название предмета
     * @param lore Описание предмета
     * @return Предмет
     */
    public ItemStack name(Material material, String name, String... lore) {
        return name(new ItemStack(material), name, lore);
    }

    /**
     * Создаёт предмет
     * @param itemStack Предмет
     * @param name Название предмета
     * @param lore Описание предмет
     * @return Предмет
     */
    public ItemStack name(ItemStack itemStack, String name, String... lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        if(lore.length > 0) {
            itemMeta.setLore(Arrays.asList(lore));
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    /**
     * Создаёт предмет
     * @param itemStack Предмет
     * @param name Название предмета
     * @param lore Описание предмета
     * @return Предмет
     */
    public ItemStack name(ItemStack itemStack, String name, List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        if(!lore.isEmpty()) {
            itemMeta.setLore(lore);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}