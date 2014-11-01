/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Utils;

import io.github.Skepter.AllAssets.AllAssets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class TextUtils {

	/** Capitalises the first letter in a string
	 * 
	 * @param s - The string to capitalise
	 * @return The capitalised string */
	public static String capitalize(final String s) {
		final String f = s.substring(0, 1);
		final String f1 = f.toUpperCase();
		final String s1 = s.substring(1, s.length());
		final String s2 = f1 + s1;
		return s2;
	}

	/** Converts a String to a list of string with specific sizes For example,
	 * "hello" would return "hello", "hell", "hel", "he", "h" */
	public static List<String> truncater(final String string) {
		List<String> list = new ArrayList<String>();
		list.add(string);
		for (int i = 1; i < string.length(); i++)
			list.add(string.substring(0, string.length() - i));
		return list;
	}

	/** Checks if a String[] contains s */
	public static boolean arrayContains(final String[] arr, final String s) {
		for (String str : arr)
			if (str.equals(s))
				return true;
		return false;
	}

	/** Centers text */
	private static String center(final String text) {
		final int spaces = (int) Math.round((80 - (1.4 * ChatColor.stripColor(text).length())) / 2);
		String s = "";
		for (int i = 0; i < spaces; i++)
			s = s + " ";
		return s + text;
	}

	/** Checks if a string contains s
	 * 
	 * @param s
	 * @return */
	public static boolean containsSwear(final String s) {
		final String s00 = ChatColor.stripColor(s);
		final String s0 = s00.toLowerCase();
		for (final String str : swearWords())
			if (s0.contains(str))
				return true;
		return false;
	}

	public static boolean containsSwearUsingFilter(final String text) {
		InputStream is = null;
		try {
			is = new URL("http://www.wdyl.com/profanity?q=" + URLEncoder.encode(text, "UTF-8")).openStream();
		} catch (final MalformedURLException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			return false;
		}
		final BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		final StringBuilder sb = new StringBuilder();
		int cp;
		try {
			while ((cp = rd.read()) != -1)
				sb.append((char) cp);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		final String response = sb.toString();
		if (response.contains("true"))
			return true;
		else
			return false;
	}

	public static String[] getMsgFromArgs(final String[] args, final int indexBegin, final int indexEnd) {
		final List<String> list = new ArrayList<String>();
		for (int i = indexBegin; i < indexEnd; i++)
			list.add(args[i]);
		final String[] array = new String[list.size()];
		int index = 0;
		for (final Object value : list) {
			array[index] = (String) value;
			index++;
		}
		return array;
	}

	public static String getMsgStringFromArgs(final String[] args, final int indexBegin, final int indexEnd) {
		return TextUtils.join(TextUtils.getMsgFromArgs(args, indexBegin, indexEnd), " ");
	}

	public static boolean isHyperlink(final String s) {
		final String strippedColor = ChatColor.stripColor(s);
		final String s0 = strippedColor.toLowerCase();
		final Pattern p = Pattern.compile("[^.]+[.][^.]+");
		final Scanner scanner = new Scanner(s0);
		while (scanner.hasNext())
			if (scanner.hasNext(p)) {
				String possibleUrl = scanner.next(p);
				if (!possibleUrl.contains("://"))
					possibleUrl = "http://" + possibleUrl;

				try {
					new URL(possibleUrl);
					return true;
				} catch (final MalformedURLException e) {
					continue;
				}
			} else
				scanner.next();
		scanner.close();
		return false;
	}

	public static boolean isInteger(final String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (final NumberFormatException e) {
			return false;
		}
	}

	public static String join(final String[] arr, final String seperator) {
		String s = "";
		for (int i = 0; i < arr.length; i++)
			s = s + arr[i] + seperator;
		return s;
	}

	public static String nonIndentedSubTitle(final String s) {
		return ChatColor.BLUE + "[" + ChatColor.AQUA + s + ChatColor.BLUE + "]";
	}

	public static void paginate(final CommandSender sender, final List<String> textData, final int pageSize, final int pageNumberToShow) {
		final HashMap<Integer, List<String>> pages = new HashMap<Integer, List<String>>();

		final ChatColor a = ChatColor.AQUA;
		final ChatColor g = ChatColor.WHITE;

		if ((pageNumberToShow == 0) || (pageNumberToShow < 0)) {
			ErrorUtils.error(sender, "That number is too small!");
			return;
		}

		final int amountOfPages = textData.size() / pageSize;
		final int amountOfLinesOfExtraData = textData.size() % pageSize;

		/* error could appear here -.- */
		if (textData.size() < pageSize)
			pages.put(1, textData);
		else
			pages.put(1, textData.subList(0, pageSize - 1));

		for (int i = 2; i < amountOfPages; i++)
			pages.put(i, textData.subList(i * pageSize, (i + 1) * pageSize));
		pages.put(pages.size() + 1, textData.subList(textData.size() - amountOfLinesOfExtraData, textData.size()));
		if (pageNumberToShow > amountOfPages) {
			ErrorUtils.error(sender, "That number is too large!");
			return;
		}

		sender.sendMessage(AllAssets.title + "Showing page " + a + pageNumberToShow + g + "/" + a + amountOfPages);
		for (final String s : pages.get(pageNumberToShow))
			sender.sendMessage(s);
		return;
	}

	/* One of these pagination ones are deprecated and I can't remember which one! */
	public static void paginate(final CommandSender sender, final Set<String> data, final int pageSize, final int pageNumberToShow) {
		final List<String> textData = new ArrayList<String>();
		textData.addAll(data);
		Collections.sort(textData);
		final HashMap<Integer, List<String>> pages = new HashMap<Integer, List<String>>();

		final ChatColor a = ChatColor.AQUA;
		final ChatColor g = ChatColor.WHITE;

		if ((pageNumberToShow == 0) || (pageNumberToShow < 0)) {
			ErrorUtils.error(sender, "That number is too small!");
			return;
		}

		final int amountOfPages = textData.size() / pageSize;
		final int amountOfLinesOfExtraData = textData.size() % pageSize;

		/* error could appear here -.- */
		if (textData.size() < pageSize)
			pages.put(1, textData);
		else
			pages.put(1, textData.subList(0, pageSize - 1));

		for (int i = 2; i < amountOfPages; i++)
			pages.put(i, textData.subList(i * pageSize, (i + 1) * pageSize));
		pages.put(pages.size() + 1, textData.subList(textData.size() - amountOfLinesOfExtraData, textData.size()));
		if (pageNumberToShow > amountOfPages) {
			ErrorUtils.error(sender, "That number is too large!");
			return;
		}

		sender.sendMessage(AllAssets.title + "Showing page " + a + pageNumberToShow + g + "/" + a + amountOfPages);
		for (final String s : pages.get(pageNumberToShow))
			sender.sendMessage(s);
		return;
	}

	public static String stringBetween(final String overallString, final String firstString, final int index) {
		final String s = overallString.substring(overallString.indexOf(firstString) + firstString.length(), index);
		return s;
	}

	public static String stringBetween(final String overallString, final String firstString, final String secondString) {
		final String s = overallString.substring(overallString.indexOf(firstString) + firstString.length(), overallString.indexOf(secondString));
		return s;
	}

	public static String subTitle(final String s) {
		return "  " + ChatColor.BLUE + "[" + ChatColor.AQUA + s + ChatColor.BLUE + "]";
	}

	private static ArrayList<String> swearWords() {
		final ArrayList<String> arr = new ArrayList<String>();
		//work on some crazy awesome regex for this part here :)
		arr.add("fuck");
		arr.add("shit");
		arr.add("bitch");
		arr.add("cunt");
		arr.add("penis");
		arr.add("vagina");
		arr.add("porn");
		return arr;
	}

	public static String title(final String s) {
		final String str = ChatColor.AQUA + "--" + ChatColor.BLUE + "[" + ChatColor.AQUA + s + ChatColor.BLUE + "]" + ChatColor.AQUA + "--";
		return center(str);
	}

	public static List<String> multipleStringBetween(final String str, final String beginTag, final String endTag) {
		final Pattern regexTag = Pattern.compile(beginTag + "(.+?)" + endTag);
		final List<String> tagValues = new ArrayList<String>();
		final Matcher matcher = regexTag.matcher(str);
		while (matcher.find())
			tagValues.add(matcher.group(1));
		return tagValues;
	}

	public static String replace(final String input, final int startindex, final int endindex, final String replacement) {
		final String first = input.substring(0, startindex);
		final String end = input.substring(endindex);
		return first + replacement + end;
	}

	public static boolean hasColorCode(final String s) {
		if (s.contains("\u00A7"))
			return true;
		return false;
	}

	public static String colorizeTextPart(final String overallString, final String textPart, final ChatColor color) {
		return overallString.replace(textPart, color + textPart);
	}
}
