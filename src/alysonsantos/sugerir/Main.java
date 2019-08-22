package alysonsantos.sugerir;

import alysonsantos.sugerir.commands.CommandSugerir;
import alysonsantos.sugerir.entity.DiscordManager;
import alysonsantos.sugerir.entity.PlayerManager;
import alysonsantos.sugerir.events.EventPlayerChat;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public class Main extends JavaPlugin {
    public static Main plugin;
    public static JDA jda;
    public static PlayerManager playerManager;
    public static DiscordManager discordManager;

    public void onEnable() {
        plugin = this;

        startBot();
        registerCommandsEvents();

        discordManager = new DiscordManager();
        playerManager = new PlayerManager();
    }

    /*
     * Iniciando o bot...
     * Defina o token do bot que está no discord de seu servidor, no ´setToken("")´"
     */

    public static void startBot() {
        try {
            jda = new JDABuilder(AccountType.BOT).setToken("TOKEN AQUI").build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    private void registerCommandsEvents() {
        pm.registerEvents(new EventPlayerChat(), this);
        getCommand("sugestao").setExecutor(new CommandSugerir());
    }

    public static PlayerManager getPlayerManager() {
        return playerManager;
    }

    public static DiscordManager getDiscordManager() {
        return discordManager;
    }

    public ConsoleCommandSender cs = Bukkit.getConsoleSender();
    public PluginManager pm = Bukkit.getPluginManager();

}
