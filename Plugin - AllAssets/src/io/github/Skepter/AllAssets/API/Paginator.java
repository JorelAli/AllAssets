package io.github.Skepter.AllAssets.API;

import static org.bukkit.ChatColor.AQUA;
import static org.bukkit.ChatColor.WHITE;
import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Utils.MathUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.command.CommandSender;

/** Class designed to paginate text :D */
public class Paginator {

	private List<String> textData;
	private int pageSize;
	private int shownPageNumber;
	private int maxPageNumber;

	/* Map storing the page number and the amount of data storesd based ion that page number. */
	private HashMap<Integer, List<String>> pages;

	/** Creates a new Paginator with a List<String>
	 * 
	 * @param sender - The person executing the command (or use Player)
	 * @param textData - The data with text
	 * @param pageSize - The size of the page to show to the sender */
	public Paginator(List<String> textData, int pageSize) {
		this.textData = textData;
		this.pageSize = pageSize;
		doPreconditions();
	}

	/** Creates a new Paginator with a Set<String>, then organised the textData
	 * 
	 * @param sender - The person executing the command (or use Player)
	 * @param textData - The data with text
	 * @param pageSize - The size of the page to show to the sender */
	public Paginator(Set<String> textData, int pageSize) {
		List<String> data = new ArrayList<String>();
		data.addAll(textData);
		Collections.sort(data);
		this.textData = data;
		this.pageSize = pageSize;
		doPreconditions();
	}

	private void doPreconditions() {
		shownPageNumber = 0;
		maxPageNumber = MathUtils.toInt(MathUtils.roundUp(textData.size(), pageSize)) / pageSize;
		pages = new HashMap<Integer, List<String>>();

		Debugger.printVariable("pageNum", maxPageNumber);

		if (textData.size() < pageSize)
			pages.put(1, textData);
		else
			pages.put(1, textData.subList(0, pageSize));

		for (int i = 2; i < maxPageNumber; i++) {
			try {
				//20 - 30
				//30 - 40
				pages.put(i, textData.subList(i * pageSize, (i + 1) * pageSize));
			} catch (Exception e) {
				pages.put(i, textData.subList(i * pageSize, textData.size() - 1));
				System.out.println("Error: " + i);
			}
		}

		Debugger.printMap(pages);
	}

	public void send(CommandSender sender, int pageNumberToShow) {
		if (pageNumberToShow <= 0) {
			pageNumberToShow = 1;
		}
		if (pageNumberToShow > maxPageNumber) {
			pageNumberToShow = maxPageNumber;
		}

		shownPageNumber = pageNumberToShow;

		sender.sendMessage(AllAssets.title + "Showing page " + AQUA + pageNumberToShow + WHITE + "/" + AQUA + maxPageNumber);
		for (final String s : pages.get(pageNumberToShow))
			sender.sendMessage(s);
	}

	public int getPageNumberShown() {
		return shownPageNumber;
	}

	public int getMaxPageNumber() {
		return maxPageNumber;
	}
}
