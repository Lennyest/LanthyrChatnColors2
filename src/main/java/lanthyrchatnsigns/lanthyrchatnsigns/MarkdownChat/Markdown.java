package lanthyrchatnsigns.lanthyrchatnsigns.MarkdownChat;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.Listener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Markdown implements Listener {

    private static final Pattern ASTERISK_PATTERN = Pattern.compile("(\\*+|_)(.*?)(\\*+|_)", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);

    public static BaseComponent[] methodUsingPattern(String testString, String[] uffixes) {

        Matcher matcher = ASTERISK_PATTERN.matcher(testString);
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
                        builder.bold(true);
                        break;
                    case 2:
                        builder.bold(true);
                        break;
                    case 3:
                        builder.bold(true);
                        builder.italic(true);
                }
            } else if (matcher.group(1).charAt(0) == '_') {
                builder.italic(true);
            }
            lastEnd = matcher.end();
        }
        builder.append(testString.substring(lastEnd), ComponentBuilder.FormatRetention.NONE);
        if (uffixes.length > 1) builder.append(uffixes[1], ComponentBuilder.FormatRetention.NONE);

        if (builder.create().length == 1) return new TextComponent[]{new TextComponent(testString)};
        return builder.create();
    }

    private static final Pattern COLOR_CODE_PATTERN = Pattern.compile("(?i)(&)([0-9a-fk-or])");
}