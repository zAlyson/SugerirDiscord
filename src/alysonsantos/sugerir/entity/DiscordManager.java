package alysonsantos.sugerir.entity;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;

import static alysonsantos.sugerir.Main.jda;

public class DiscordManager {

    /*
     * Vericando se o usúario está no discord.
     */

    public boolean existsUser(String id) {
        Guild guild = jda.getGuildById("610124508161900552");
        for (Member user : guild.getMembers()) {
            if (user.getUser().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
