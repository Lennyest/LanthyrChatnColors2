package lanthyrchatnsigns.lanthyrchatnsigns.MarkdownChat;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MarkdownVanillaListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public static void boldMarkDown(AsyncPlayerChatEvent event) {
        for (Player player : event.getRecipients()) {
            player.spigot().sendMessage(ChatMessageType.CHAT, Markdown.methodUsingPattern(
                    event.getMessage(), event.getFormat().replaceAll("%1\\$s", event.getPlayer().getDisplayName()).split("%2\\$s")));
        }
        event.setCancelled(true);
    }
}
