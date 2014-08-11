package _;

import io.github.Skepter.Users.User;

import java.net.InetAddress;
import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;


public class SLP_MOTD implements Listener{

	@EventHandler
    public void ping(final ServerListPingEvent event) {
        //String search = compileInetAddress(event.getAddress());
//        if (getConfig().contains(search)) { //search for all of the files....
//            String pName = getConfig().getString(search+".name");
//            int joins = getConfig().getInt(search+".joins");
//            event.setMotd(ChatColor.translateAlternateColorCodes('&', "&c&oWelcome: &4" + pName + " &c&oto the server! You have joined the server: &4"+joins+" &c&otimes!"));
//        }
    }
 
    @EventHandler
    public void login(final AsyncPlayerPreLoginEvent event) {
        final String player = event.getName();
        final String save = compileInetAddress(event.getAddress());
        final User user = new User(player);
        final ArrayList<String> s = new ArrayList<String>();
        s.add(save);
        user.setIPs(s);
    }
 
    private String compileInetAddress(final InetAddress address) {
        return address.toString().substring(1).replace(".", "_");
    }
}
