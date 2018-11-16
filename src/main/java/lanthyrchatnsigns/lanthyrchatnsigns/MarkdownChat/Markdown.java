package lanthyrchatnsigns.lanthyrchatnsigns.MarkdownChat;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Markdown implements Listener {

    private static final Pattern astrikPattern = Pattern.compile("(.*?)(\\*+ | _)(.*?)(\\*+ | _)(.*?)", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);

    public static String methodUsingPattern(String testString) {

        Matcher matcher = astrikPattern.matcher(testString);
        ComponentBuilder builder = new ComponentBuilder("");
        while (matcher.find()) {
            String head = matcher.group(1);
            int numberOfAstricksInBeginning = matcher.group(2).length();
            String theTestWIthAstricks = matcher.group(3);
            int numberOfAstricksInEnd = matcher.group(4).length();
            String tail = matcher.group(5);

            if (matcher.group(1).charAt(0) == '*' && numberOfAstricksInBeginning == numberOfAstricksInEnd) {
                builder.append(head).append(theTestWIthAstricks);

                switch (numberOfAstricksInBeginning) {
                    case 1:
                        builder.italic(true);
                    case 2:
                        builder.bold(true);
                }
            } else if (matcher.group(1).charAt(0) == '_') {
                builder.underlined(true);
            }
            builder.append(tail);
        }
        if (builder.create().length == 1) return testString;
        String message = Arrays.stream(builder.create())
                .map(c -> c.toLegacyText())
                .collect(Collectors.joining());
        return message;
    }

    @EventHandler
    public static void boldMarkDown(AsyncPlayerChatEvent event) {
            event.setMessage(methodUsingPattern(event.getMessage()));
    }
}
