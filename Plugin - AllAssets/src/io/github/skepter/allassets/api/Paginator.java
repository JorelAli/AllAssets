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
	 * @param sender - The person executing the command (or use Player)
	 * @param textData - The data with text
	 * @param pageSize - The size of the page to show to the sender */
	public Paginator(final List<String> textData, final int pageSize) {
		this.textData = textData;
		this.pageSize = pageSize;
		doPreconditions();
	}

	/** Creates a new Paginator with a Set<String>, then organised the textData
	 * 
	 * @param sender - The person executing the command (or use Player)
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
		maxPageNumber = MathUtils.toInt(MathUtils.roundUp(textData.size(), pageSize)) / pageSize;
		pages = new HashMap<Integer, List<String>>();

		Debugger.printVariable("pageNum", maxPageNumber);

		if (textData.size() < pageSize)
			pages.put(1, textData);
		else
			pages.put(1, textData.subList(0, pageSize));

		for (int i = 2; i < maxPageNumber; i++)
			try {
				//20 - 30
				//30 - 40
				pages.put(i, textData.subList(i * pageSize, (i + 1) * pageSize));
			} catch (final Exception e) {
				pages.put(i, textData.subList(i * pageSize, textData.size() - 1));
				System.out.println("Error: " + i);
			}

		Debugger.printMap(pages);
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
}
