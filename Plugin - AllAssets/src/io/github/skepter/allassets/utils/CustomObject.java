/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and MCSpartans
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and MCSpartans
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.utils;

public class CustomObject extends Object {

	Object object;

	public CustomObject(final Object object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return String.valueOf(object);
	}

	public int toInt() {
		try {
			return Integer.parseInt(toString());
		} catch (final NumberFormatException e) {
			return 0;
		}
	}

	/** Returns an int from a String. Turns Hello123 to 123 */
	public int stripInteger() {
		return new CustomObject(toString().replaceAll("[\\D]", "")).toInt();
	}

	/** Returns a String from a String. Turns Hello123 to Hello. Reverse of
	 * stripInteger */
	public String stripString() {
		return new CustomObject(toString().replaceAll("[\\d]", "")).toString();
	}

}
