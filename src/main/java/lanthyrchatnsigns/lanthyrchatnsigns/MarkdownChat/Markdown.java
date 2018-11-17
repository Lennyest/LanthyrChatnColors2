package lanthyrchatnsigns.lanthyrchatnsigns.MarkdownChat;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Markdown implements Listener {

    private static final Pattern astrikPattern = Pattern.compile("(\\*+|_)(.*?)(\\*+|_)", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
    private static final Pattern prefixPattern = Pattern.compile("");

    public static BaseComponent[] methodUsingPattern(String testString) {

        Matcher matcher = astrikPattern.matcher(testString);
        ComponentBuilder builder = new ComponentBuilder("");
        int lastEnd = 0;
        while (matcher.find()) {
            int numberOfAstricksInBeginning = matcher.group(1).length();
            String theTestWIthAstricks = matcher.group(2);
            int numberOfAstricksInEnd = matcher.group(3).length();

            builder.append(testString.substring(lastEnd, matcher.start()), ComponentBuilder.FormatRetention.NONE);
            builder.append(theTestWIthAstricks, ComponentBuilder.FormatRetention.NONE);
            if (matcher.group(1).charAt(0) == '*' && numberOfAstricksInBeginning == numberOfAstricksInEnd) {

                switch (numberOfAstricksInBeginning) {
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
        if (builder.create().length == 1) return new TextComponent[]{new TextComponent(testString)};
        return builder.create();
    }

    @EventHandler
    public static void boldMarkDown(AsyncPlayerChatEvent event) {
        for (Player player : event.getRecipients()) {
            player.spigot().sendMessage(ChatMessageType.CHAT, methodUsingPattern(event.getMessage()));
        }
        event.setCancelled(true);
    }
}
