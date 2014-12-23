/*******************************************************************************
 * Skepter's licence
 * Copyright 2014 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. I've just made life easier for you :)
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
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Utils;

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
	
	/** Returns a String from a String. Turns Hello123 to Hello.
	 * Reverse of stripInteger */
	public String stripString() {
		return new CustomObject(toString().replaceAll("[\\d]", "")).toString();
	}

}
