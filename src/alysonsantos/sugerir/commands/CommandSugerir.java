package alysonsantos.sugerir.commands;

import alysonsantos.sugerir.entity.PlayerDiscord;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static alysonsantos.sugerir.Main.*;

public class CommandSugerir implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lb, String[] args) {

        if (!(s instanceof Player)) {
            return true;
        }

        Player p = (Player) s;

        if (getPlayerManager().getPlayer(p.getName()) != null && getPlayerManager().getPlayer(p.getName()).isSugerindo()) {
            p.sendMessage(new String[]{"", "§c Você não pode executar este comando agora.", ""});
            return true;
        }

        if (args.length == 0) {
            getPlayerManager().createPlayer(new PlayerDiscord(p.getName()));
            getPlayerManager().getPlayer(p.getName()).setPlayer(p.getName());
            getPlayerManager().getPlayer(p.getName()).setSugerindo(true);
            p.sendMessage(new String[]{"", "§b Digite no chat a sua sugestão no chat!", "§7 Caso queira ser mencionado: Após o comando, digite ´§f/sugestao (id do seu discord)§´. §7 Para cancelar a ação, digite ´§fcancelar§7´.", ""});
            return true;
        }


        // Caso o jogador tenha definido o seu ID do discord.
        if (args.length == 1) {
            // Aqui estamos verificando se o ID do discord que o jogador citou, é válido!
            if (getDiscordManager().existsUser(args[0])) {
                // Aqui estamos criando o jogador, setando o ID, e iniciando o processo de sugestão.
                getPlayerManager().createPlayer(new PlayerDiscord(p.getName()));
                getPlayerManager().getPlayer(p.getName()).setDiscordId(args[0]);
                getPlayerManager().getPlayer(p.getName()).setSugerindo(true);

                p.sendMessage(new String[]{"", "§b Digite no chat a sua sugestão no chat!", "§7 Para cancelar a ação, digite ´§fcancelar§7´.", ""});
            } else {
                p.sendMessage("§cO ID é inválido!");
            }
            return true;
        }
        return false;
    }
}
