/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
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
