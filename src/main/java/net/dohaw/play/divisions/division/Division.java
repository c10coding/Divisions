package net.dohaw.play.divisions.division;

import lombok.Getter;
import lombok.Setter;
import net.dohaw.play.divisions.DivisionsPlugin;
import net.dohaw.play.divisions.events.custom.NewMemberEvent;
import net.dohaw.play.divisions.PlayerData;
import net.dohaw.play.divisions.rank.Permission;
import net.dohaw.play.divisions.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.UUID;

public class Division {

    final private String NAME;
    final private FileConfiguration CONFIG;
    final private PlayerData LEADER;

    private DivisionStatus status;
    @Getter @Setter private String bankName, motd;
    private double power, heartsDestroyed;
    private Location garrisonLocation;
    private List<UUID> players;
    private EnumMap<Rank, EnumMap<Permission, Object>> rankPermissions = new EnumMap<>(Rank.class);
    private int kills, casualties, shrinesConquered, numMembers;

    public Division(final String DIVISION_NAME, final FileConfiguration DIVISION_CONFIG, final PlayerData LEADER){
        this.NAME = DIVISION_NAME;
        this.CONFIG = DIVISION_CONFIG;
        this.LEADER = LEADER;
        this.players = new ArrayList<>();
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public void setPlayers(List<UUID> players) {
        this.players = players;
    }

    public void addPlayer(PlayerData data){
        UUID uuid = data.getPLAYER_UUID();
        players.add(uuid);
        NewMemberEvent nme = new NewMemberEvent(this, uuid);
        Bukkit.getPluginManager().callEvent(nme);
    }

    /*
        Lazy right now but just make the parameter the player UUID instead of Player data
     */
    public void removePlayer(PlayerData data){
        players.removeIf(uuid -> data.getPLAYER_UUID().equals(uuid));
    }

    public Location getGarrisonLocation() {
        return garrisonLocation;
    }

    public void setGarrisonLocation(Location garrisonLocation) {
        this.garrisonLocation = garrisonLocation;
    }

    public int getCasualties() {
        return casualties;
    }

    public void setCasualties(int casualties) {
        this.casualties = casualties;
    }

    public double getHeartsDestroyed() {
        return heartsDestroyed;
    }

    public void setHeartsDestroyed(double heartsDestroyed) {
        this.heartsDestroyed = heartsDestroyed;
    }

    public double getGoldAmount() {
        return DivisionsPlugin.getEconomy().bankBalance(bankName).balance;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public int getShrinesConquered() {
        return shrinesConquered;
    }

    public void setShrinesConquered(int shrinesConquered) {
        this.shrinesConquered = shrinesConquered;
    }

    public String getName() {
        return NAME;
    }

    public FileConfiguration getConfig() {
        return CONFIG;
    }

    public PlayerData getLeader() {
        return LEADER;
    }

    public EnumMap<Rank, EnumMap<Permission, Object>> getRankPermissions() {
        return rankPermissions;
    }

    public void setRankPermissions(EnumMap<Rank, EnumMap<Permission, Object>> rankPermissions) {
        this.rankPermissions = rankPermissions;
    }

    public void setRankPermission(Rank rank, Permission perm, Object value){
        rankPermissions.get(rank).replace(perm, value);
    }

    /*
    public void updatePlayerData(PlayerData newData){

        int indexOfData = -1;
        for(UUID pd : players){
            if(pd.getPLAYER_UUID().equals(newData.getPLAYER_UUID())){
                indexOfData = players.indexOf(pd);
            }
        }

        if(indexOfData != -1){
            players.set(indexOfData, newData);
        }

    }*/

    public DivisionStatus getStatus() {
        return status;
    }

    public void setStatus(DivisionStatus status) {
        this.status = status;
    }
}
