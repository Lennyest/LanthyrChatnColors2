package lanthyrchatnsigns.lanthyrchatnsigns.ChatSecurity;

import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

public class VersionTabCompleteCancel implements Listener {

    @EventHandler
    public void tabCompletePlugins(TabCompleteEvent event) {

        if (!event.getBuffer().startsWith("/")) {
            // No command, not interested.
        }

        CommandSender sender = event.getSender();

        if (sender.hasPermission("lanthyr.version.bypass")) {
            return;
        }

        if (event.getBuffer().startsWith("/ver")) {
            event.setCancelled(true);
        }

        if (event.getBuffer().startsWith("/version")) {
            event.setCancelled(true);
        }

        if (event.getBuffer().startsWith("/about")) {
            event.setCancelled(true);
        }


    }

}
