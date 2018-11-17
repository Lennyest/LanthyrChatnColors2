package lanthyrchatnsigns.lanthyrchatnsigns.MarkdownChat;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Markdown implements Listener {

    private static final Pattern ASTERISK_PATTERN = Pattern.compile("(\\*+|_)(.*?)(\\*+|_)", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);

    public static BaseComponent[] methodUsingPattern(String testString, String format, String username) {

        Matcher matcher = ASTERISK_PATTERN.matcher(testString);
        format = format.replaceAll("%1\\$s", username);
        String[] uffixes = format.split("%2\\$s");
        ComponentBuilder builder = new ComponentBuilder(uffixes[0]);
        int lastEnd = 0;

        while (matcher.find()) {
            int numberOfAsterisksInBeginning = matcher.group(1).length();
            String theTestWItchAsterisks = matcher.group(2);
            int numberOfAsterisksInEnd = matcher.group(3).length();

            builder.append(testString.substring(lastEnd, matcher.start()), ComponentBuilder.FormatRetention.NONE);
            builder.append(theTestWItchAsterisks, ComponentBuilder.FormatRetention.NONE);
            if (matcher.group(1).charAt(0) == '*' && numberOfAsterisksInBeginning == numberOfAsterisksInEnd) {

                switch (numberOfAsterisksInBeginning) {
                    case 1:
                        builder.italic(true);
                        break;
                    case 2:
                        builder.bold(true);
                        break;
                    case 3:
                        builder.bold(true);
                        builder.italic(true);
                }
            } else if (matcher.group(1).charAt(0) == '_') {
                builder.underlined(true);
            }
            lastEnd = matcher.end();

        }
        builder.append(testString.substring(lastEnd), ComponentBuilder.FormatRetention.NONE);
        if (uffixes.length > 1) builder.append(uffixes[1], ComponentBuilder.FormatRetention.NONE);

        if (builder.create().length == 1) return new TextComponent[]{new TextComponent(testString)};
        return builder.create();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public static void boldMarkDown(AsyncPlayerChatEvent event) {
        for (Player player : event.getRecipients()) {
            player.spigot().sendMessage(ChatMessageType.CHAT, methodUsingPattern(event.getMessage(), event.getFormat(), event.getPlayer().getDisplayName()));
        }
        event.setCancelled(true);
    }
}
