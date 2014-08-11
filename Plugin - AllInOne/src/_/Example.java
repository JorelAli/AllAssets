package _;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;
 
import java.net.InetAddress;
 
public class Example extends JavaPlugin implements Listener {
 //sets username in MOTD (server listing) with the amount of times they've played on that server
    @Override
	public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }
 
    @Override
	public void onDisable() {
        saveDefaultConfig();
    }
 
    @EventHandler
    public void ping(final ServerListPingEvent event) {
        final String search = compileInetAddress(event.getAddress());
        if (getConfig().contains(search)) {
            final String pName = getConfig().getString(search+".name");
            final int joins = getConfig().getInt(search+".joins");
            event.setMotd(ChatColor.translateAlternateColorCodes('&', "&c&oWelcome: &4" + pName + " &c&oto the server! You have joined the server: &4"+joins+" &c&otimes!"));
        }
    }
 
    @EventHandler
    public void login(final AsyncPlayerPreLoginEvent event) {
        final String player = event.getName();
        final String save = compileInetAddress(event.getAddress());
        getConfig().set(save+".name", player);
        getConfig().set(save+".joins", getConfig().contains(save+".joins") ? getConfig().getInt(save+".joins")+1 : 1);
        saveConfig();
        reloadConfig();
    }
 
    private String compileInetAddress(final InetAddress address) {
        return address.toString().substring(1).replace(".", "_");
    }
}