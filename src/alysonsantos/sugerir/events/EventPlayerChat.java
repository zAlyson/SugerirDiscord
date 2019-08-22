package alysonsantos.sugerir.events;

import alysonsantos.sugerir.commands.CommandSugerir;
import alysonsantos.sugerir.entity.PlayerDiscord;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static alysonsantos.sugerir.Main.*;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class EventPlayerChat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String mensagem = e.getMessage();
        String playerName = p.getName();

        if (getPlayerManager().getPlayer(p.getName()) != null && getPlayerManager().getPlayer(p.getName()).isSugerindo()) {
            if (mensagem.equals("cancelar")) {
                e.setCancelled(true);

                getPlayerManager().getPlayer(playerName).setSugerindo(false);
                p.sendMessage(new String[]{"", "§c Pronto, ação cancelada!", ""});
                return;
            }
            PlayerDiscord user = getPlayerManager().getPlayer(playerName);
            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle(" <:notify:613468401184276490> | **NOVA SUGESTÃO**:");
            embed.setColor(Color.ORANGE);

            if (user.getDiscordId() != null) {
                embed.setThumbnail(jda.getUserById(user.getDiscordId()).getAvatarUrl());
            } else {
                embed.setThumbnail("https://icon-library.net/images/icon-new/icon-new-9.jpg");
            }

            embed.addField(" Author:", playerName, true);
            embed.addField(" Sugestão:", mensagem, true);

            // Aqui você deve definir o ID do discord em que o seu bot está.
            embed.setFooter("Agradecemos pela sugestão!", jda.getGuildById("610124508161900552").getIconUrl());

            // Aqui você define o ID do canal em que as sugestões serão enviadas.
            TextChannel channel = jda.getTextChannelById("610812244753907713");

            // Construindo o embed, e enviando para o canal.
            channel.sendMessage(embed.build()).queue();
            p.sendMessage("\n §aSugestão enviada com sucesso! \n ");

            // Adicionando o cooldown ao jogador.
            CommandSugerir.cooldowns.put(playerName, System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));
            user.setSugerindo(false);
            e.setCancelled(true);
        }
    }
}
