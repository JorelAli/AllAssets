package io.github.Skepter.AllAssets.Utils;

import static org.bukkit.ChatColor.AQUA;
import static org.bukkit.ChatColor.WHITE;
import io.github.Skepter.AllAssets.AllAssets;

import java.util.HashMap;
import java.util.List;

import org.bukkit.command.CommandSender;

/** Class designed to paginate text :D */
public class Paginator {

	private CommandSender sender;
	private List<String> textData;
	private int pageSize;
	
	public Paginator(CommandSender sender, List<String> textData, int pageSize) {
		this.sender = sender;
		this.textData = textData;
		this.pageSize = pageSize;
	}
	
	public void showToPlayer() {
		
	}
	
	/*return final int pageNumberToShow*/
	public void getPageNumberShown() {
		
	}
	
	/*return amountOfPages*/
	public void getMaxPageNumber() {
		
	}
	
	public static int paginate(final CommandSender sender, final List<String> textData, final int pageSize, final int pageNumberToShow) {
		final HashMap<Integer, List<String>> pages = new HashMap<Integer, List<String>>();

		if ((pageNumberToShow == 0) || (pageNumberToShow < 0)) {
			ErrorUtils.error(sender, "That number is too small!");
			return 0;
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
			return 0;
		}

		sender.sendMessage(AllAssets.title + "Showing page " + AQUA + pageNumberToShow + WHITE + "/" + AQUA + amountOfPages);
		for (final String s : pages.get(pageNumberToShow))
			sender.sendMessage(s);
		return amountOfPages;
	}
	
}
