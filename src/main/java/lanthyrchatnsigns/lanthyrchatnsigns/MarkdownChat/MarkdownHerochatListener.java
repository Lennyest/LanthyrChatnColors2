package lanthyrchatnsigns.lanthyrchatnsigns.MarkdownChat;

import com.dthielke.herochat.ChannelChatEvent;
import com.dthielke.herochat.Chatter;
import com.dthielke.herochat.Herochat;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.Objects;

public class MarkdownHerochatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void etest(ChannelChatEvent event) {
        event.setResult(Chatter.Result.BANNED);

        for(Chatter chatter : event.getChannel().getMembers()) {
            if (event.getChannel().getDistance() <= 0 || event.getSender().isInRange(chatter, event.getChannel().getDistance())) {
                String[] uffixes = event.getFormat().split("\\{msg}");
                for (int i = 0; i < uffixes.length; i++) {
                    String string = uffixes[i];
                    String group = Arrays.stream(Objects.requireNonNull(Herochat.getChatService()).getGroups())
                            .filter(g -> Herochat.getChatService().playerInGroup(event.getSender().getPlayer(), g))
                            .findFirst().orElse(null);
                    string = string.replaceAll("\\{default}", event.getChannel().getFormatSupplier().getStandardFormat())
                            .replaceAll("\\{name}", event.getChannel().getName())
                            .replaceAll("\\{nick}", event.getChannel().getNick() != null ? event.getChannel().getNick() : "null")
                            .replaceAll("\\{color}", event.getChannel().getColor().toString())
                            .replaceAll("\\{sender}", event.getSender().getName())
                            .replaceAll("\\{prefix}", group != null ?
                                    Herochat.getChatService().getGroupPrefix(event.getSender().getPlayer().getWorld(), group) : "")
                            .replaceAll("\\{suffix}", group != null ?
                                    Herochat.getChatService().getGroupPrefix(event.getSender().getPlayer().getWorld(), group) : "");

                    string = ChatColor.translateAlternateColorCodes('&', string);
                    uffixes[i] = string;
                }
                chatter.getPlayer().spigot().sendMessage(ChatMessageType.CHAT, Markdown.methodUsingPattern(event.getMessage(), uffixes));
            }
        }
    }
}
