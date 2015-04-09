/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and Tundra
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.api;

import static org.bukkit.ChatColor.AQUA;
import static org.bukkit.ChatColor.WHITE;
import io.github.skepter.allassets.api.utils.Debugger;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.MathUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.command.CommandSender;

/** Class designed to paginate text :D */
public class Paginator {

	private final List<String> textData;
	private final int pageSize;
	private int shownPageNumber;
	private int maxPageNumber;

	/* Map storing the page number and the amount of data storesd based ion that page number. */
	private HashMap<Integer, List<String>> pages;

	/** Creates a new Paginator with a List<String>
	 *
	 * @param textData - The data with text
	 * @param pageSize - The size of the page to show to the sender */
	public Paginator(final List<String> textData, final int pageSize) {
		this.textData = textData;
		this.pageSize = pageSize;
		doPreconditions();
	}

	/** Creates a new Paginator with a Set<String>, then organises the textData
	 *
	 * @param textData - The data with text
	 * @param pageSize - The size of the page to show to the sender */
	public Paginator(final Set<String> textData, final int pageSize) {
		final List<String> data = new ArrayList<String>();
		data.addAll(textData);
		Collections.sort(data);
		this.textData = data;
		this.pageSize = pageSize;
		doPreconditions();
	}

	private void doPreconditions() {
		shownPageNumber = 0;
		maxPageNumber = (MathUtils.toInt(MathUtils.roundUp(textData.size(), pageSize)) / pageSize) - 1;
		pages = new HashMap<Integer, List<String>>();

		Debugger.printVariable("pageNum", maxPageNumber);

		List<List<String>> l = splitList(textData, pageSize);
		for (int i = 0; i < l.size(); i++) {
			pages.put(i, l.get(i));
		}
	}

	public void send(final CommandSender sender, int pageNumberToShow) {
		if (pageNumberToShow <= 0)
			pageNumberToShow = 1;
		if (pageNumberToShow > maxPageNumber)
			pageNumberToShow = maxPageNumber;

		shownPageNumber = pageNumberToShow;

		sender.sendMessage(Strings.TITLE + "Showing page " + AQUA + pageNumberToShow + WHITE + "/" + AQUA + maxPageNumber);
		for (final String s : pages.get(pageNumberToShow))
			sender.sendMessage(s);
	}

	public int getPageNumberShown() {
		return shownPageNumber;
	}

	public int getMaxPageNumber() {
		return maxPageNumber;
	}

	private <T> List<List<T>> splitList(List<T> list, int maxListSize) {
		List<List<T>> splittedList = new ArrayList<List<T>>();
		int itemsRemaining = list.size();
		int start = 0;

		while (itemsRemaining != 0) {
			int end = itemsRemaining >= maxListSize ? (start + maxListSize) : (start + itemsRemaining);

			splittedList.add(list.subList(start, end));

			int sizeOfFinalList = end - start;
			itemsRemaining = itemsRemaining - sizeOfFinalList;
			start = start + sizeOfFinalList;
		}

		return splittedList;
	}
}
