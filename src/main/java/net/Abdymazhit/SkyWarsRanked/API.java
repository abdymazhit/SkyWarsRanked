package net.Abdymazhit.SkyWarsRanked;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.Abdymazhit.SkyWarsRanked.customs.PlayerInfo;
import net.Abdymazhit.SkyWarsRanked.customs.PlayerVimeInfo;
import net.Abdymazhit.SkyWarsRanked.customs.Stats;
import net.Abdymazhit.SkyWarsRanked.enums.PlayerRank;
import net.Abdymazhit.SkyWarsRanked.upgrades.Upgrade;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Отвечает за работу с API
 *
 * @version   08.08.2021
 * @author    Islam Abdymazhit
 */
public class API {

    /** Токен API VimeWorld.ru пользователя Abdymazhit */
    private static final String token = "DUDDsGmSjKGfP09Dn6aJy5CRqpxPr7K";

    /**
     * Получает информацию о игроке
     * @param player Игрок
     * @return Информация о игроке
     */
    public PlayerInfo getPlayerInfo(Player player) {
        PlayerVimeInfo playerVimeInfo = getPlayerVimeInfo(player);
        if(playerVimeInfo != null) {
            Stats stats = getPlayerStats(playerVimeInfo);
            if(stats != null) {
                Map<Upgrade, Integer> playerUpgrades = getPlayerUpgrades(player);
                return new PlayerInfo(playerVimeInfo, stats, playerUpgrades);
            }
        }

        return null;
    }

    /**
     * Получает глобальную информацию о игроке
     * @param player Игрок
     * @return Глобальная информация о игроке
     */
    private PlayerVimeInfo getPlayerVimeInfo(Player player) {
        JsonParser parser = new JsonParser();

        String stringInfo = sendGetRequest("https://api.vimeworld.ru/user/name/" + player.getName() + "?token=" + token);

        if(stringInfo != null) {
            JsonArray infoArray = parser.parse(stringInfo).getAsJsonArray();
            JsonObject infoObject = infoArray.get(0).getAsJsonObject();

            int id = infoObject.get("id").getAsInt();
            String rank = infoObject.get("rank").getAsString();


            JsonElement guildElement = infoObject.get("guild");

            String guildTag = null;
            String guildColor = null;
            if(!guildElement.isJsonNull()) {
                JsonObject guildObject = guildElement.getAsJsonObject();
                guildTag = guildObject.get("tag").getAsString();
                guildColor = guildObject.get("color").getAsString();
            }

            return new PlayerVimeInfo(id, PlayerRank.valueOf(rank), guildTag, guildColor);
        }
        return null;
    }

    /**
     * Получает информацию о статистике игрока
     * @param playerVimeInfo Глобальная информация игрока
     * @return Информация о статистике игрока
     */
    private Stats getPlayerStats(PlayerVimeInfo playerVimeInfo) {
        JsonParser parser = new JsonParser();

        String stringStats = sendGetRequest("https://api.vimeworld.ru/user/" + playerVimeInfo.getId() + "/stats?games=sw");

        if(stringStats != null) {
            JsonObject skyWarsObject = parser.parse(stringStats).getAsJsonObject().getAsJsonObject("stats").getAsJsonObject("SW");

            JsonObject globalObject = skyWarsObject.getAsJsonObject("global");
            int wins = globalObject.get("wins").getAsInt();
            int games = globalObject.get("games").getAsInt();
            int kills = globalObject.get("kills").getAsInt();
            int deaths = globalObject.get("deaths").getAsInt();
            int arrowsFired = globalObject.get("arrowsFired").getAsInt();
            int blocksBroken = globalObject.get("blocksBroken").getAsInt();
            int blocksPlaced = globalObject.get("blocksPlaced").getAsInt();
            int currentWinStreak = globalObject.get("currentWinStreak").getAsInt();
            int winStreak = globalObject.get("winStreak").getAsInt();

            JsonObject monthlyObject = skyWarsObject.getAsJsonObject("season").getAsJsonObject("monthly");
            int monthlyWins = monthlyObject.get("wins").getAsInt();
            int monthlyGames = monthlyObject.get("games").getAsInt();
            int monthlyKills = monthlyObject.get("kills").getAsInt();
            int monthlyDeaths = monthlyObject.get("deaths").getAsInt();
            int monthlyArrowsFired = monthlyObject.get("arrowsFired").getAsInt();
            int monthlyBlocksBroken = monthlyObject.get("blocksBroken").getAsInt();
            int monthlyBlocksPlaced = monthlyObject.get("blocksPlaced").getAsInt();

            return new Stats(wins, games, kills, deaths, arrowsFired, blocksBroken, blocksPlaced, currentWinStreak, winStreak,
                    monthlyWins, monthlyGames, monthlyKills, monthlyDeaths, monthlyArrowsFired, monthlyBlocksBroken, monthlyBlocksPlaced);
        }
        return null;
    }

    /**
     * Получает прокачки игрока с их уровнем
     * @return Прокачки игрока с их уровнем
     */
    private Map<Upgrade, Integer> getPlayerUpgrades(Player player) {
        Map<Upgrade, Integer> playerUpgrades = new HashMap<>();
        playerUpgrades.put(Upgrade.BLAZING_ARROWS, 10);
        playerUpgrades.put(Upgrade.JUGGERNAUT, 10);
        return playerUpgrades;
    }

    /**
     * Отправляет GET запрос по URL
     * @param url URL
     * @return Результат запроса в типе String
     */
    private String sendGetRequest(String url) {
        HttpGet request = new HttpGet(url);
        try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}