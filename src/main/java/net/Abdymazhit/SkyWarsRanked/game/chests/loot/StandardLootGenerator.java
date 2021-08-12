package net.Abdymazhit.SkyWarsRanked.game.chests.loot;

import net.Abdymazhit.SkyWarsRanked.game.chests.Registry;
import net.Abdymazhit.SkyWarsRanked.utils.Random;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Отвечает за генерацию стандартного лута
 *
 * @version   12.08.2021
 * @author    Islam Abdymazhit
 */
public class StandardLootGenerator extends LootGenerator {

    /** Список сгенерированных шлемов */
    private static final List<ItemStack> HELMETS;

    /** Список сгенерированных нагрудников */
    private static final List<ItemStack> CHESTPLATES;

    /** Список сгенерированных штанов */
    private static final List<ItemStack> LEGGINGS;

    /** Список сгенерированных ботинков */
    private static final List<ItemStack> BOOTS;

    /** Список сгенерированных зельев */
    private static final List<Supplier<ItemStack>> POTIONS;

    /**
     * Генерирует список лута базовых сундуков
     * @return Сгенерированный список лута базовых сундуков
     */
    @Override
    public List<ItemStack> basic() {
        List<ItemStack> items = new ArrayList<>(32);
        
        items.add(Random.of(StandardLootGenerator.HELMETS));
        items.add(Random.of(StandardLootGenerator.CHESTPLATES));
        items.add(Random.of(StandardLootGenerator.LEGGINGS));
        items.add(Random.of(StandardLootGenerator.BOOTS));
        
        if(Random.random.nextDouble() < 0.05) {
            items.remove(Random.random.nextInt(4));
        }
        
        if(Random.random.nextDouble() < 0.2) {
            items.add((ItemStack) Random.of(new List[] { 
                    StandardLootGenerator.HELMETS, 
                    StandardLootGenerator.CHESTPLATES, 
                    StandardLootGenerator.LEGGINGS, 
                    StandardLootGenerator.BOOTS 
            }));
        }
        
        for(ItemStack item : items) {
            double random = Random.random.nextDouble();
            Material type = item.getType();
            
            if(isDiamond(type)) {
                if(random < 0.1) {
                    item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                }
            } else if(isIron(type)) {
                if(random < 0.03) {
                    item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                } else if(random < 0.1) {
                    item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                }
            } else if(random < 0.02) {
                item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
            } else if(random < 0.05) {
                item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
            } else if(random < 0.15) {
                item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
            }
            
            if(Random.random.nextDouble() < 0.05) {
                item.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
            }
        }

        double random = Random.random.nextDouble();
        ItemStack sword;

        if(random < 0.3) {
            sword = new ItemStack(Material.DIAMOND_SWORD);

            if(Random.random.nextDouble() < 0.1) {
                sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
            }
        } else if(random < 0.6) {
            sword = new ItemStack(Material.STONE_SWORD);

            if(Random.random.nextDouble() < 0.15) {
                sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
            }

            if(Random.random.nextDouble() < 0.04) {
                sword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
            }
        } else {
            sword = new ItemStack(Random.of(Material.STONE_SWORD, Material.GOLD_SWORD));

            random = Random.random.nextDouble();
            if(random < 0.05) {
                sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
            } else if(random < 0.4) {
                sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
            }

            if(Random.random.nextDouble() < 0.04) {
                sword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
            }
        }
        items.add(sword);

        if(Random.random.nextDouble() < 0.1) {
            items.add(new ItemStack(Material.BOW));
        }

        items.add(Random.of(Registry.PICKAXES));

        if(Random.random.nextDouble() < 0.4) {
            items.add(Random.of(Registry.AXES));
        }

        if(Random.random.nextDouble() < 0.3) {
            items.add(Random.of(Registry.SPADES));
        }

        if(Random.random.nextDouble() < 0.15) {
            items.add(new ItemStack(Material.FISHING_ROD));
        }

        if(Random.random.nextDouble() < 0.3) {
            items.add(new ItemStack(Material.GOLDEN_APPLE, Random.random.nextInt(2, 5)));
        }

        if(Random.random.nextDouble() < 0.3) {
            items.add(new ItemStack(Material.FLINT_AND_STEEL));
        }

        if(Random.random.nextDouble() < 0.4) {
            items.add(new ItemStack(Material.TNT, Random.random.nextInt(5, 15)));
        }

        if(Random.random.nextDouble() < 0.01) {
            ItemStack item = name(Material.SLIME_BALL, "§aСлизь богов");
            item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
            items.add(item);
        }

        if(Random.random.nextDouble() < 0.05) {
            items.add(name(Material.COMPASS, "§eGPS трекер", "§7Укажет вам на ближайшего врага", "VimeWorld.ru"));
        }

        if(Random.random.nextDouble() < 0.2) {
            items.add(new ItemStack(Material.EGG, Random.random.nextInt(4, 8)));
        }

        if(Random.random.nextDouble() < 0.2) {
            items.add(new ItemStack(Material.SNOW_BALL, Random.random.nextInt(4, 8)));
        }

        if(Random.random.nextDouble() < 0.3) {
            items.add(new ItemStack(Material.ENCHANTMENT_TABLE));
        }

        items.add((Random.of(Registry.FOODS)).get());
        items.add(new ItemStack(Material.ARROW, Random.random.nextInt(16, 32)));
        items.add(new ItemStack(Material.EXP_BOTTLE, Random.random.nextInt(8, 24)));
        items.add(new ItemStack(Material.WATER_BUCKET));
        items.add(new ItemStack(Material.LAVA_BUCKET));

        switch (Random.random.nextInt(4)) {
            case 0: {
                items.add((Random.of(Registry.BLOCKS)).get());
            }
            case 1:
            case 2: {
                items.add((Random.of(Registry.BLOCKS)).get());
            }
            case 3: {
                items.add((Random.of(Registry.BLOCKS)).get());
                break;
            }
        }

        items.add((Random.of(StandardLootGenerator.POTIONS)).get());

        if(Random.random.nextDouble() < 0.1) {
            items.add((Random.of(StandardLootGenerator.POTIONS)).get());
        }

        if(rotation > 0) {
            if(Random.random.nextDouble() < 0.2) {
                items.add(new ItemStack(Material.ENDER_PEARL));
            }

            if(Random.random.nextDouble() < 0.4) {
                items.add(new ItemStack(Material.GOLDEN_APPLE, Random.random.nextInt(2, 4)));
            }
        }

        return items;
    }

    /**
     * Генерирует список лута центральных сундуков
     * @return Сгенерированный список лута центральных сундуков
     */
    @Override
    public List<ItemStack> middle() {
        List<ItemStack> items = new ArrayList<>(32);
        for(int num = Random.random.nextInt(1, 3), i = 0; i < num; ++i) {
            ItemStack item = (ItemStack)Random.of(new List[] { StandardLootGenerator.HELMETS, StandardLootGenerator.CHESTPLATES, StandardLootGenerator.LEGGINGS, StandardLootGenerator.BOOTS });
            double random = Random.random.nextDouble();

            if(random < 0.04) {
                item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
            } else if(random < 0.08) {
                item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
            } else if(random < 0.2) {
                item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
            }

            random = Random.random.nextDouble();

            if(random < 0.03) {
                item.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
            } else if(random < 0.06) {
                item.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
            } else if(random < 0.1) {
                item.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
            }

            if(Random.random.nextDouble() < 0.05) {
                item.addUnsafeEnchantment(Enchantment.THORNS, 1);
            }

            items.add(item);
        }
        if(Random.random.nextDouble() < 0.2) {
            ItemStack item2 = new ItemStack(Material.BOW);
            double random = Random.random.nextDouble();

            if(random < 0.05) {
                item2.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 3);
            } else if(random < 0.1) {
                item2.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
            } else if(random < 0.2) {
                item2.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
            }

            if(Random.random.nextDouble() < 0.02) {
                item2.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
            }

            items.add(item2);
        }
        if(Random.random.nextDouble() < 0.5) {
            ItemStack item2 = new ItemStack(Random.of(Material.DIAMOND_SWORD, Material.IRON_SWORD));
            double random = Random.random.nextDouble();

            if(random < 0.04) {
                item2.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
            } else if(random < 0.1) {
                item2.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
            } else if(random < 0.2) {
                item2.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
            }

            random = Random.random.nextDouble();

            if(random < 0.02) {
                item2.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
            } else if(random < 0.07) {
                item2.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
            }

            items.add(item2);
        }
        if(Random.random.nextDouble() < 0.3) {
            items.add(new ItemStack(Material.GOLDEN_APPLE, Random.random.nextInt(1, 4)));
        }

        items.add((Random.of(Registry.FOODS)).get());

        if(Random.random.nextDouble() < 0.1) {
            items.add(new ItemStack(Material.STICK, Random.random.nextInt(2, 10)));
        }

        if(Random.random.nextDouble() < 0.3) {
            items.add(new ItemStack(Material.FLINT_AND_STEEL));
        }

        if(Random.random.nextDouble() < 0.5) {
            items.add((Random.of(Registry.BLOCKS)).get());
        }

        if(Random.random.nextDouble() < 0.2) {
            items.add(new ItemStack(Material.BROWN_MUSHROOM));
        }

        if(Random.random.nextDouble() < 0.2) {
            items.add(new ItemStack(Material.RED_MUSHROOM));
        }

        if(Random.random.nextDouble() < 0.3) {
            items.add(new ItemStack(Material.ARROW, Random.random.nextInt(12, 32)));
        }

        if(Random.random.nextDouble() < 0.3) {
            items.add(new ItemStack(Material.WATER_BUCKET));
        }

        if(Random.random.nextDouble() < 0.1) {
            items.add(new ItemStack(Material.LAVA_BUCKET));
        }

        if(Random.random.nextDouble() < 0.2) {
            items.add(new ItemStack(Material.EXP_BOTTLE, Random.random.nextInt(8, 24)));
        }

        if(Random.random.nextDouble() < 0.1) {
            items.add(name(Material.COMPASS, "§eGPS трекер", "§7Укажет вам на ближайшего врага", "VimeWorld.ru"));
        }

        if(Random.random.nextDouble() < 0.5) {
            items.add((Random.of(StandardLootGenerator.POTIONS)).get());
        }

        return items;
    }

    /**
     * Генерирует список лута мистического сундука
     * @return Сгенерированный список лута мистического сундука
     */
    @Override
    public List<ItemStack> mystic() {
        List<ItemStack> items = new ArrayList<>(25);
        if(Random.random.nextDouble() < 0.1) {
            ItemStack item = name(Material.SLIME_BALL, "§aСлизь богов");
            item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
            items.add(item);
        }
        
        for(int amount = Random.random.nextInt(1, 3), i = 0; i < amount; ++i) {
            ItemStack is = (Random.of(Registry.DIAMOND_ITEMS)).clone();
            switch (is.getType()) {
                case DIAMOND_BOOTS:
                case DIAMOND_CHESTPLATE:
                case DIAMOND_HELMET:
                case DIAMOND_LEGGINGS: {
                    double random = Random.random.nextDouble();

                    if(random < 0.2) {
                        is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                    } else if(random < 0.8) {
                        is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                    }

                    if(Random.random.nextDouble() < 0.3) {
                        is.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, Random.random.nextInt(1, 2));
                    }

                    if(Random.random.nextDouble() < 0.3) {
                        is.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, Random.random.nextInt(1, 2));
                    }

                    if(Random.random.nextDouble() < 0.2) {
                        is.addUnsafeEnchantment(Enchantment.THORNS, 1);
                        break;
                    }

                    break;
                }
                case DIAMOND_SWORD: {
                    double random = Random.random.nextDouble();

                    if(random < 0.2) {
                        is.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
                    } else if(random < 0.7) {
                        is.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
                    }

                    random = Random.random.nextDouble();

                    if(random < 0.1) {
                        is.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
                    } else if(random < 0.3) {
                        is.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
                    }

                    random = Random.random.nextDouble();

                    if(random < 0.05) {
                        is.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
                        break;
                    }

                    if(random < 0.2) {
                        is.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
                        break;
                    }

                    break;
                }
                case DIAMOND_PICKAXE: {
                    is.addUnsafeEnchantment(Enchantment.DIG_SPEED, Random.random.nextInt(2, 4));

                    if(Random.random.nextDouble() < 0.1) {
                        is.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
                        break;
                    }

                    break;
                }
                case DIAMOND_AXE: {
                    is.addUnsafeEnchantment(Enchantment.DIG_SPEED, Random.random.nextInt(2, 4));
                    is.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
                    break;
                }
                default: {
                    continue;
                }
            }
            items.add(is);
        }

        ItemStack bow = new ItemStack(Material.BOW);
        double random = Random.random.nextDouble();

        if(random < 0.05) {
            bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 3);
        } else if(random < 0.3) {
            bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
        } else if(random < 0.8) {
            bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
        }

        random = Random.random.nextDouble();

        if(random < 0.05) {
            bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
        } else if(random < 0.3) {
            bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
        }

        random = Random.random.nextDouble();

        if(random < 0.05) {
            bow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 2);
        } else if(random < 0.3) {
            bow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
        }

        if(Random.random.nextDouble() < 0.05) {
            bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
        }

        items.add(bow);
        items.add(new ItemStack(Material.ARROW, Random.random.nextInt(24, 48)));

        if(Random.random.nextDouble() < 0.2) {
            items.add(new ItemStack(Material.ENDER_PEARL));
        }

        if(Random.random.nextDouble() < 0.2) {
            items.add(new ItemStack(Material.ENDER_PEARL));
        }

        items.add(new ItemStack(Material.GOLDEN_APPLE, Random.random.nextInt(2, 6)));
        items.add((Random.of(Registry.FOODS)).get());
        if(Random.random.nextDouble() < 0.8) {
            items.add(name(Material.COMPASS, "§eGPS трекер", "§7Укажет вам на ближайшего врага", "VimeWorld.ru"));
        }
        return items;
    }

    static {
        HELMETS = Arrays.asList(new ItemStack(Material.DIAMOND_HELMET), new ItemStack(Material.DIAMOND_HELMET), new ItemStack(Material.GOLD_HELMET), new ItemStack(Material.IRON_HELMET), new ItemStack(Material.IRON_HELMET));
        CHESTPLATES = Arrays.asList(new ItemStack(Material.DIAMOND_CHESTPLATE), new ItemStack(Material.DIAMOND_CHESTPLATE), new ItemStack(Material.GOLD_CHESTPLATE), new ItemStack(Material.IRON_CHESTPLATE), new ItemStack(Material.IRON_CHESTPLATE));
        LEGGINGS = Arrays.asList(new ItemStack(Material.DIAMOND_LEGGINGS), new ItemStack(Material.DIAMOND_LEGGINGS), new ItemStack(Material.GOLD_LEGGINGS), new ItemStack(Material.IRON_LEGGINGS), new ItemStack(Material.IRON_LEGGINGS));
        BOOTS = Arrays.asList(new ItemStack(Material.DIAMOND_BOOTS), new ItemStack(Material.DIAMOND_BOOTS), new ItemStack(Material.GOLD_BOOTS), new ItemStack(Material.IRON_BOOTS), new ItemStack(Material.IRON_BOOTS));

        POTIONS = Arrays.asList(() -> {
            boolean low = Random.random.nextBoolean();
            Potion p = new Potion(PotionType.SPEED);
            p.setSplash(true);
            ItemStack potion = p.toItemStack(1);
            PotionMeta meta = (PotionMeta) potion.getItemMeta();
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED, (low ? 60 : 30) * 20, (low ? 0 : 1));
            meta.addCustomEffect(potionEffect, true);
            potion.setItemMeta(meta);
            return potion;
        }, () -> {
            boolean low = Random.random.nextBoolean();
            Potion p = new Potion(PotionType.REGEN);
            p.setSplash(true);
            ItemStack potion = p.toItemStack(1);
            PotionMeta meta = (PotionMeta) potion.getItemMeta();
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.REGENERATION, (low ? 33 : 16) * 20, (low ? 0 : 1));
            meta.addCustomEffect(potionEffect, true);
            potion.setItemMeta(meta);
            return potion;
        }, () -> new ItemStack(Material.POTION, 1, (short)8261));
    }
}