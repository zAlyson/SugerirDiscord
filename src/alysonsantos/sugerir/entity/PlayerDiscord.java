package alysonsantos.sugerir.entity;

public class PlayerDiscord {

    private String player;
    private String discordId;
    private boolean sugerindo;

    public PlayerDiscord(String player) {
        this.player = player;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public boolean isSugerindo() {
        return sugerindo;
    }

    public void setSugerindo(boolean sugerindo) {
        this.sugerindo = sugerindo;
    }
}
