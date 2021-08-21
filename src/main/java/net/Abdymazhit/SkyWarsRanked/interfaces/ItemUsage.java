package net.Abdymazhit.SkyWarsRanked.interfaces;

import org.bukkit.entity.Player;

/**
 * Отвечает за использование предмета
 *
 * @version   21.08.2021
 * @author    Islam Abdymazhit
 */
@FunctionalInterface
public interface ItemUsage {

    /**
     * Событие использования предмета игроком
     * @param player Игрок
     */
    void use(Player player);
}