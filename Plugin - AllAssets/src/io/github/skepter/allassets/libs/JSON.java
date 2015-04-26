package io.github.skepter.allassets.libs;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;

public class JSON {

	/** @author werter318
	 * @param message
	 * @return String */
	public static String getJSON(String message) {
		char colorChar = '&';
		message = parse(colorChar, message);
		//char colorChar = ChatColor.COLOR_CHAR;

		String template = "{text:\"TEXT\",color:COLOR,bold:BOLD,underlined:UNDERLINED,italic:ITALIC,strikethrough:STRIKETHROUGH,obfuscated:OBFUSCATED,extra:[EXTRA]}";
		String json = "";

		List<String> parts = new ArrayList<String>();

		int first = 0;
		int last = 0;

		while ((first = message.indexOf(colorChar, last)) != -1) {
			int offset = 2;
			while ((last = message.indexOf(colorChar, first + offset)) - 2 == first) {
				offset += 2;
			}

			if (last == -1) {
				parts.add(message.substring(first));
				break;
			} else {
				parts.add(message.substring(first, last));
			}
		}

		if (parts.isEmpty()) {
			parts.add(message);
		}

		Pattern colorFinder = Pattern.compile("(" + colorChar + "([a-f0-9]))");
		for (String part : parts) {
			json = (json.isEmpty() ? template : json.replace("EXTRA", template));

			Matcher matcher = colorFinder.matcher(part);
			ChatColor color = (matcher.find() ? ChatColor.getByChar(String.valueOf(matcher.group().charAt(1))) : ChatColor.WHITE);

			json = json.replace("COLOR", color.name().toLowerCase());
			json = json.replace("BOLD", String.valueOf(part.contains(ChatColor.BOLD.toString())));
			json = json.replace("ITALIC", String.valueOf(part.contains(ChatColor.ITALIC.toString())));
			json = json.replace("UNDERLINED", String.valueOf(part.contains(ChatColor.UNDERLINE.toString())));
			json = json.replace("STRIKETHROUGH", String.valueOf(part.contains(ChatColor.STRIKETHROUGH.toString())));
			json = json.replace("OBFUSCATED", String.valueOf(part.contains(ChatColor.MAGIC.toString())));

			json = json.replace("TEXT", part.replaceAll("(" + colorChar + "([a-z0-9]))", ""));
		}

		json = json.replace(",extra:[EXTRA]", "");

		return json;
	}

	private static String parse(char code, String message) {
		String out = message;
//		Pattern colorFinder = Pattern.compile("(" + code + "([a-f0-9]))");
//		Matcher matcher = colorFinder.matcher(out);
//		while (!matcher.hitEnd()) {
//			if (matcher.find()) {
//				System.out.println(matcher.group());
//				ChatColor color = ChatColor.getByChar(String.valueOf(matcher.group().charAt(1)));
//				System.out.println(color.name());
//				System.out.println(color.toString());
//				out = out.replaceAll("(" + code + "([a-z0-9]))", color.toString());
//			}
//		}
//		System.out.println("Out: " + out);
		return out;
	}
}
