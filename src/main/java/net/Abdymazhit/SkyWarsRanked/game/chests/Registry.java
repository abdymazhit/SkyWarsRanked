package net.Abdymazhit.SkyWarsRanked.game.chests;

import net.Abdymazhit.SkyWarsRanked.utils.Random;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Реестр предметов
 *
 * @version   11.08.2021
 * @author    Islam Abdymazhit
 */
public class Registry {

    /** Список алмазных предметов */
    public static final List<ItemStack> DIAMOND_ITEMS;

    /** Список типов эффектов */
    public static final List<PotionEffectType> EFFECT_TYPES;

    /** Список предметов железной руды */
    public static final List<Supplier<ItemStack>> IRON_ORE_ITEMS;

    /** Список еды */
    public static final List<Supplier<ItemStack>> FOODS;

    /** Список блоков */
    public static final List<Supplier<ItemStack>> BLOCKS;

    /** Список кирок */
    public static final List<ItemStack> PICKAXES;

    /** Список топоров */
    public static final List<ItemStack> AXES;

    /** Список мечей */
    public static final List<ItemStack> SWORDS;

    /** Список лопат */
    public static final List<ItemStack> SPADES;

    /** Список шлемов */
    public static final List<ItemStack> HELMETS;

    /** Список нагрудников */
    public static final List<ItemStack> CHESTPLATES;

    /** Список штанов */
    public static final List<ItemStack> LEGGINGS;

    /** Список ботинков */
    public static final List<ItemStack> BOOTS;

    static {
        DIAMOND_ITEMS = Arrays.asList(new ItemStack(Material.DIAMOND_SWORD), new ItemStack(Material.DIAMOND_PICKAXE), new ItemStack(Material.DIAMOND_AXE), new ItemStack(Material.DIAMOND_HELMET), new ItemStack(Material.DIAMOND_CHESTPLATE), new ItemStack(Material.DIAMOND_LEGGINGS), new ItemStack(Material.DIAMOND_BOOTS));
        EFFECT_TYPES = Arrays.asList(PotionEffectType.SPEED, PotionEffectType.SLOW, PotionEffectType.FAST_DIGGING, PotionEffectType.SLOW_DIGGING, PotionEffectType.INCREASE_DAMAGE, PotionEffectType.JUMP, PotionEffectType.CONFUSION, PotionEffectType.REGENERATION, PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.FIRE_RESISTANCE, PotionEffectType.INVISIBILITY, PotionEffectType.BLINDNESS, PotionEffectType.NIGHT_VISION, PotionEffectType.HUNGER, PotionEffectType.WEAKNESS, PotionEffectType.POISON, PotionEffectType.WITHER, PotionEffectType.HEALTH_BOOST, PotionEffectType.ABSORPTION, PotionEffectType.SATURATION);
        IRON_ORE_ITEMS = Arrays.asList(() -> new ItemStack(Material.ARROW, Random.random.nextInt(4, 10)), () -> new ItemStack(Material.STICK, Random.random.nextInt(2, 4)), () -> new ItemStack(Material.FLINT, Random.random.nextInt(2, 4)), () -> new ItemStack(Material.STRING, Random.random.nextInt(3, 4)), () -> new ItemStack(Material.FEATHER, Random.random.nextInt(2, 4)), () -> new ItemStack(Material.COAL, Random.random.nextInt(3, 4)), () -> new ItemStack(Material.DIAMOND, Random.random.nextInt(1, 2)), () -> new ItemStack(Material.DIAMOND, Random.random.nextInt(1, 2)), () -> new ItemStack(Material.IRON_INGOT, Random.random.nextInt(1, 3)), () -> new ItemStack(Material.IRON_INGOT, Random.random.nextInt(1, 3)));
        FOODS = Arrays.asList(() -> new ItemStack(Material.CARROT_ITEM, Random.random.nextInt(8, 32)), () -> new ItemStack(Material.APPLE, Random.random.nextInt(8, 32)), () -> new ItemStack(Material.BREAD, Random.random.nextInt(8, 32)), () -> new ItemStack(Material.BAKED_POTATO, Random.random.nextInt(8, 32)), () -> new ItemStack(Material.COOKED_FISH, Random.random.nextInt(8, 32)), () -> new ItemStack(Material.COOKED_BEEF, Random.random.nextInt(8, 32)), () -> new ItemStack(Material.COOKED_CHICKEN, Random.random.nextInt(8, 32)));
        BLOCKS = Arrays.asList(() -> new ItemStack(Material.STONE, Random.random.nextInt(20, 40)), () -> new ItemStack(Material.WOOD, Random.random.nextInt(20, 40)), () -> new ItemStack(Material.BRICK, Random.random.nextInt(20, 40)), () -> new ItemStack(Material.COBBLESTONE, Random.random.nextInt(20, 40)), () -> new ItemStack(Material.STONE, Random.random.nextInt(20, 40)), () -> new ItemStack(Material.DIRT, Random.random.nextInt(20, 40)));
        PICKAXES = Arrays.asList(new ItemStack(Material.STONE_PICKAXE), new ItemStack(Material.IRON_PICKAXE), new ItemStack(Material.GOLD_PICKAXE), new ItemStack(Material.DIAMOND_PICKAXE));
        AXES = Arrays.asList(new ItemStack(Material.STONE_AXE), new ItemStack(Material.IRON_AXE), new ItemStack(Material.GOLD_AXE), new ItemStack(Material.DIAMOND_AXE));
        SWORDS = Arrays.asList(new ItemStack(Material.STONE_SWORD), new ItemStack(Material.IRON_SWORD), new ItemStack(Material.GOLD_SWORD), new ItemStack(Material.DIAMOND_SWORD));
        SPADES = Arrays.asList(new ItemStack(Material.STONE_SPADE), new ItemStack(Material.IRON_SPADE), new ItemStack(Material.GOLD_SPADE), new ItemStack(Material.DIAMOND_SPADE));
        HELMETS = Arrays.asList(new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.CHAINMAIL_HELMET), new ItemStack(Material.DIAMOND_HELMET), new ItemStack(Material.GOLD_HELMET), new ItemStack(Material.IRON_HELMET));
        CHESTPLATES = Arrays.asList(new ItemStack(Material.LEATHER_CHESTPLATE), new ItemStack(Material.CHAINMAIL_CHESTPLATE), new ItemStack(Material.DIAMOND_CHESTPLATE), new ItemStack(Material.GOLD_CHESTPLATE), new ItemStack(Material.IRON_CHESTPLATE));
        LEGGINGS = Arrays.asList(new ItemStack(Material.LEATHER_LEGGINGS), new ItemStack(Material.CHAINMAIL_LEGGINGS), new ItemStack(Material.DIAMOND_LEGGINGS), new ItemStack(Material.GOLD_LEGGINGS), new ItemStack(Material.IRON_LEGGINGS));
        BOOTS = Arrays.asList(new ItemStack(Material.LEATHER_BOOTS), new ItemStack(Material.CHAINMAIL_BOOTS), new ItemStack(Material.DIAMOND_BOOTS), new ItemStack(Material.GOLD_BOOTS), new ItemStack(Material.IRON_BOOTS));
    }
}