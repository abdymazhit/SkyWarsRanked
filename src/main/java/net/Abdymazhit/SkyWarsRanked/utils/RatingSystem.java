package net.Abdymazhit.SkyWarsRanked.utils;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.customs.PlayerInfo;
import net.Abdymazhit.SkyWarsRanked.kits.Kit;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * Отвечает за рейтинговую систему
 *
 * @version   20.08.2021
 * @author    Islam Abdymazhit
 */
public class RatingSystem {

    /** Хранилище рейтинга форматов игры */
    private final RatingRepository ratingRepository;

    /**
     * Инициализирует объекты
     */
    public RatingSystem() {
        ratingRepository = new RatingRepository();
    }

    /**
     * Устанавливает игроку новый рейтинг
     * @param player Игрок
     * @param place Место
     */
    public void setNewRating(Player player, int place) {
        Map<String, Map<Integer, Integer>> islandRatings = ratingRepository.getIslandRatings();

        if(islandRatings != null) {
            PlayerInfo playerInfo = SkyWarsRanked.getGameManager().getPlayerInfo(player);

            Map<Kit, Integer> kitsRatings = playerInfo.getKitsRatings();
            Kit kit = playerInfo.getKit();

            int overallRating = playerInfo.getOverallRating();
            int kitRating = kitsRatings.get(kit);

            int ratingDifference = getRatingDifference(islandRatings, kitRating, place);

            int newKitRating = kitRating + ratingDifference;
            int newOverallRating = overallRating + ratingDifference;

            kitsRatings.put(kit, newKitRating);
            playerInfo.setKitsRatings(kitsRatings);
            playerInfo.setOverallRating(newOverallRating);

            if(ratingDifference >= 0) {
                player.sendMessage("§7[§b§lSkyWars Ranked§7] §7Новый общий рейтинг: §e" + newOverallRating + " §7(§a+" + ratingDifference + "§7)");
                player.sendMessage("§7[§b§lSkyWars Ranked§7] §7Новый рейтинг за набор: §e" + newKitRating + " §7(§a+" + ratingDifference + "§7)");
            } else {
                player.sendMessage("§7[§b§lSkyWars Ranked§7] §7Новый общий рейтинг: §e" + newOverallRating + " §7(§c" + ratingDifference + "§7)");
                player.sendMessage("§7[§b§lSkyWars Ranked§7] §7Новый рейтинг за набор: §e" + newKitRating + " §7(§c" + ratingDifference + "§7)");
            }
        }
    }

    /**
     * Получает разница в рейтинге
     * @param islandRatings Рейтинги формата игры
     * @param playerRating Текущий рейтинг игрока
     * @param playerPlace Место игрока
     * @return Разница в рейтинге
     */
    private int getRatingDifference(Map<String, Map<Integer, Integer>> islandRatings, int playerRating, int playerPlace) {
        for(String ratingRange : islandRatings.keySet()) {
            if(ratingRange.contains("-")) {
                String[] ratingRangeArray = ratingRange.split("-");
                if(ratingRangeArray.length == 2) {
                    int minRange = Integer.parseInt(ratingRangeArray[0]);
                    int maxRange = Integer.parseInt(ratingRangeArray[1]);

                    if(playerRating >= minRange && playerRating <= maxRange) {
                        Map<Integer, Integer> placeRating = islandRatings.get(ratingRange);
                        return placeRating.get(playerPlace);
                    }
                }
            } else if(ratingRange.contains("+")) {
                String[] ratingRangeArray = ratingRange.split("\\+");
                if(ratingRangeArray.length == 1) {
                    int minRange = Integer.parseInt(ratingRangeArray[0]);

                    if(playerRating >= minRange) {
                        Map<Integer, Integer> placeRating = islandRatings.get(ratingRange);
                        return placeRating.get(playerPlace);
                    }
                }
            }
        }

        return 0;
    }
}