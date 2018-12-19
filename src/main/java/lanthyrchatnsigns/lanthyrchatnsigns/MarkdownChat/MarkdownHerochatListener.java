package lanthyrchatnsigns.lanthyrchatnsigns.MarkdownChat;

import com.dthielke.herochat.ChannelChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class MarkdownHerochatListener implements Listener {
    @EventHandler
    public static void herochatMarkdown(ChannelChatEvent event) {
        Player player = event.getSender().getPlayer();
        event.setMessage(Markdown.parseMarkdown(player, event.getMessage(), event.getChannel()));
    }
}