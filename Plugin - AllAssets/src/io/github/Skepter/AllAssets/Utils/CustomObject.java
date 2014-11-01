/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Utils;

public class CustomObject extends Object {

	Object object;

	public CustomObject(Object object) {
		this.object = object;
	}

	public String toString() {
		return String.valueOf(object);
	}

	public int toInt() {
		try {
			return Integer.parseInt(toString());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	/** Returns an int from a String. Turns Hello123 to 123 */
	public int stripInteger() {
		return new CustomObject(toString().replaceAll("[\\D]", "")).toInt();
	}

}
