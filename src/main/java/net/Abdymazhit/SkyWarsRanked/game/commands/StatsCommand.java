package net.Abdymazhit.SkyWarsRanked.game.commands;

import net.Abdymazhit.SkyWarsRanked.SkyWarsRanked;
import net.Abdymazhit.SkyWarsRanked.customs.Stats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Отвечает за работу команды /stats
 *
 * @version   21.08.2021
 * @author    Islam Abdymazhit
 */
public class StatsCommand implements CommandExecutor {

    /**
     * Команда /stats
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Проверка, является ли отправитель игроком
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Получить статистику игрока
            Stats stats = SkyWarsRanked.getGameManager().getPlayerInfo(player).getStats();

            // Отправить статистику игрока в виде сообщения
            player.sendMessage(new String[] {
                    "§e---- §fСтатистика §aSkyWars §e----",
                    "§eИгр сыграно: §f" + stats.getGames(),
                    "§eПобед: §f" + stats.getWins(),
                    "§eУбийств: §f" + stats.getKills(),
                    "§eСмертей: §f" + stats.getDeaths(),
                    "§eВыстрелов из лука: §f" + stats.getArrowsFired(),
                    "§eБлоков сломано: §f" + stats.getBlocksBroken(),
                    "§eБлоков поставлено: §f" + stats.getBlocksPlaced(),
                    "§eЛучшая серия побед: §f" + stats.getWinStreak(),
                    "§eТекущая серия побед: §f" + stats.getCurrentWinStreak(),
            });
        } else {
            sender.sendMessage("Вы должны быть игроком, чтобы использовать эту команду!");
        }

        return true;
    }
}