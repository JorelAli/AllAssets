package io.github.Skepter.AllAssets.Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionUtils {

	/** Retrieves an Enumeration via Reflection */
	public static Object getEnum(final Class<?> enumClass, final String enumName) throws NullPointerException {
		for (final Object object : enumClass.getEnumConstants())
			if (object.toString().equals(enumName))
				return object;
		throw new NullPointerException();
	}

	/** Return the value from a private field */
	public static Object getPrivateField(final Object object, final String fieldName) throws Exception {
		final Field field = object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(object);
	}

	/** Return the value from a non private field */
	public static Object getField(final Object object, final String fieldName) throws Exception {
		return object.getClass().getDeclaredField(fieldName).get(object);
	}

	/** Sets the value of a private field */
	public static void setPrivateField(final Object object, final String fieldName, final Object data) throws Exception {
		final Field field = object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(object, data);
	}

	/** Sets the value of a final static field */
	public static void setFinalStaticField(Field field, Object data) throws Exception {
		field.setAccessible(true);

		final Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

		field.set(null, data);
	}

}
