package io.github.skepter.allassets.libs.sqlwtfair;

/** List of all MySQL datatypes. Descriptions taken from <a
 * href="http://dev.mysql.com/doc/refman/5.0/en/data-type-overview.html">MySQL
 * Docs</a>. */
public class DataType {

	/** <b>Numeric data types.</b>
	 * <p>
	 * M indicates the maximum display width for integer types. The maximum
	 * legal display width is 255. Display width is unrelated to the range of
	 * values a type can contain, as described in <a
	 * href="http://dev.mysql.com/doc/refman/5.0/en/numeric-types.html">Section
	 * 11.1.4, "Numeric Types"</a>. For floating-point and fixed-point types, M
	 * is the total number of digits that can be stored. */
	public enum Numeric {
		/** BIT[(M)]
		 * <p>
		 * A bit-field type. M indicates the number of bits per value, from 1 to
		 * 64. The default is 1 if M is omitted. */
		BIT,

		/** TINYINT[(M)]
		 * <p>
		 * A very small integer. The signed range is -128 to 127. The unsigned
		 * range is 0 to 255. */
		TINYINT,

		/** BOOLEAN
		 * <p>
		 * These types are synonyms for TINYINT(1). A value of zero is
		 * considered false. Nonzero values are considered true. You may also
		 * use {@code TRUE} or {@code FALSE}. */
		BOOLEAN,

		/** SMALLINT[(M)]
		 * <p>
		 * A small integer. The signed range is -32768 to 32767. The unsigned
		 * range is 0 to 65535. */
		SMALLINT,

		/** MEDIUMINT[(M)]
		 * <p>
		 * A medium-sized integer. The signed range is -8388608 to 8388607. The
		 * unsigned range is 0 to 16777215. */
		MEDIUMINT,

		/** INT[(M)]
		 * <p>
		 * A normal-size integer. The signed range is -2147483648 to 2147483647.
		 * The unsigned range is 0 to 4294967295. */
		INT,

		/** BIGINT[(M)]
		 * <p>
		 * A large integer. The signed range is -9223372036854775808 to
		 * 9223372036854775807. The unsigned range is 0 to 18446744073709551615. */
		BIGINT,

		/** DECIMAL[(M[,D])]
		 * <p>
		 * A packed “exact” fixed-point number. M is the total number of digits
		 * (the precision) and D is the number of digits after the decimal point
		 * (the scale). The decimal point and (for negative numbers) the “-”
		 * sign are not counted in M. If D is 0, values have no decimal point or
		 * fractional part. The maximum number of digits (M) for DECIMAL is 65
		 * (64 from 5.0.3 to 5.0.5). The maximum number of supported decimals
		 * (D) is 30. If D is omitted, the default is 0. If M is omitted, the
		 * default is 10. */
		DECIMAL,

		/** FLOAT[(M,D)]
		 * <p>
		 * A small (single-precision) floating-point number. Permissible values
		 * are -3.402823466E+38 to -1.175494351E-38, 0, and 1.175494351E-38 to
		 * 3.402823466E+38. These are the theoretical limits, based on the IEEE
		 * standard. The actual range might be slightly smaller depending on
		 * your hardware or operating system. */
		FLOAT,

		/** DOUBLE[(M,D)]
		 * <p>
		 * A normal-size (double-precision) floating-point number. Permissible
		 * values are -1.7976931348623157E+308 to -2.2250738585072014E-308, 0,
		 * and 2.2250738585072014E-308 to 1.7976931348623157E+308. These are the
		 * theoretical limits, based on the IEEE standard. The actual range
		 * might be slightly smaller depending on your hardware or operating
		 * system. */
		DOUBLE;

		/** Returns the string representation of this {@link Numeric}
		 * {@link DataType}
		 * 
		 * @param zerofill if the data type should be zerofill
		 * @param unsigned if the data type should be unsigned
		 * @param arguments single letter arguments for the data type
		 * @return the string representation of this numeric data type */
		public java.lang.String toString(final boolean zerofill, final boolean unsigned, final Object... arguments) {
			final StringBuilder builder = new StringBuilder(this.name());

			if (arguments != null && arguments.length != 0) {
				builder.append("(");
				for (int i = 0; i < arguments.length; i++) {
					builder.append(arguments[i]);
					if (i != (arguments.length - 1))
						builder.append(",");
				}
				builder.append(")");
			}

			if (zerofill)
				builder.append(" ZEROFILL");
			else if (unsigned)
				builder.append(" UNSIGNED");

			return builder.toString();
		}

	}

	/** <b>Temporal Data Types</b>
	 * <p>
	 * For the {@link Temporal#DATE} and {@link Temporal#DATETIME} range
	 * descriptions, “supported” means that although earlier values might work,
	 * there is no guarantee. */
	public enum Temporal {
		/** DATE
		 * <p>
		 * A date. The supported range is '1000-01-01' to '9999-12-31'. MySQL
		 * displays DATE values in 'YYYY-MM-DD' format, but permits assignment
		 * of values to DATE columns using either strings or numbers. */
		DATE,

		/** DATETIME
		 * <p>
		 * A date and time combination. The supported range is '1000-01-01
		 * 00:00:00' to '9999-12-31 23:59:59'. MySQL displays DATETIME values in
		 * 'YYYY-MM-DD HH:MM:SS' format, but permits assignment of values to
		 * DATETIME columns using either strings or numbers. */
		DATETIME,

		/** TIMESTAMP
		 * <p>
		 * A timestamp. The range is '1970-01-01 00:00:01' UTC to '2038-01-19
		 * 03:14:07' UTC. TIMESTAMP values are stored as the number of seconds
		 * since the epoch ('1970-01-01 00:00:00' UTC). A TIMESTAMP cannot
		 * represent the value '1970-01-01 00:00:00' because that is equivalent
		 * to 0 seconds from the epoch and the value 0 is reserved for
		 * representing '0000-00-00 00:00:00', the “zero” TIMESTAMP value. */
		TIMESTAMP,

		/** TIME
		 * <p>
		 * A time. The range is '-838:59:59' to '838:59:59'. MySQL displays TIME
		 * values in 'HH:MM:SS' format, but permits assignment of values to TIME
		 * columns using either strings or numbers. */
		TIME,

		/** YEAR[(2|4)]
		 * <p>
		 * A year in two-digit or four-digit format. The default is four-digit
		 * format. YEAR(2) or YEAR(4) differ in display format, but have the
		 * same range of values. In four-digit format, values display as 1901 to
		 * 2155, and 0000. In two-digit format, values display as 70 to 69,
		 * representing years from 1970 to 2069. MySQL displays YEAR values in
		 * YYYY or YYformat, but permits assignment of values to YEAR columns
		 * using either strings or numbers. */
		YEAR;

		/** Returns the string representation of this {@link Temporal}
		 * {@link DataType}
		 * 
		 * @return the string representation of this temporal data type */
		@Override
		public java.lang.String toString() {
			return toString(new Object[] {});
		}

		/** Returns the string representation of this {@link Temporal}
		 * {@link DataType}
		 * 
		 * @param arguments single letter arguments for the data type
		 * @return the string representation of this temporal data type */
		public java.lang.String toString(final Object... arguments) {
			final StringBuilder builder = new StringBuilder(this.name());

			if (arguments != null && arguments.length != 0) {
				builder.append("(");
				for (int i = 0; i < arguments.length; i++) {
					builder.append(arguments[i]);
					if (i != (arguments.length - 1))
						builder.append(",");
				}
				builder.append(")");
			}

			return builder.toString();
		}
	}

	/** <b>String Data Type</b>
	 * <p> */
	public enum String {
		/** CHAR[(M)]
		 * <p>
		 * A fixed-length string that is always right-padded with spaces to the
		 * specified length when stored. M represents the column length in
		 * characters. The range of M is 0 to 255. If M is omitted, the length
		 * is 1. */
		CHAR,

		/** VARCHAR(M)
		 * <p>
		 * A variable-length string. M represents the maximum column length in
		 * characters. In MySQL 5.0, the range of M is 0 to 255 before MySQL
		 * 5.0.3, and 0 to 65,535 in MySQL 5.0.3 and later. The effective
		 * maximum length of a VARCHAR in MySQL 5.0.3 and later is subject to
		 * the maximum row size (65,535 bytes, which is shared among all
		 * columns) and the character set used. For example, utf8 characters can
		 * require up to three bytes per character, so a VARCHAR column that
		 * uses the utf8 character set can be declared to be a maximum of 21,844
		 * characters. */
		VARCHAR,

		/** BINARY(M)
		 * <p>
		 * The BINARY type is similar to the CHAR type, but stores binary byte
		 * strings rather than nonbinary character strings. M represents the
		 * column length in bytes. */
		BINARY,

		/** VARBINARY(M)
		 * <p>
		 * The VARBINARY type is similar to the VARCHAR type, but stores binary
		 * byte strings rather than nonbinary character strings. M represents
		 * the maximum column length in bytes. */
		VARBINARY,

		/** TINYBLOB
		 * <p>
		 * A BLOB column with a maximum length of 255 (28 – 1) bytes. Each
		 * TINYBLOB value is stored using a 1-byte length prefix that indicates
		 * the number of bytes in the value. */
		TINYBLOB,

		/** TINYTEXT
		 * <p>
		 * A TEXT column with a maximum length of 255 (28 – 1) characters. The
		 * effective maximum length is less if the value contains multi-byte
		 * characters. Each TINYTEXT value is stored using a 1-byte length
		 * prefix that indicates the number of bytes in the value. */
		TINYTEXT,

		/** BLOB[(M)]
		 * <p>
		 * A BLOB column with a maximum length of 65,535 (216 – 1) bytes. Each
		 * BLOB value is stored using a 2-byte length prefix that indicates the
		 * number of bytes in the value. An optional length M can be given for
		 * this type. If this is done, MySQL creates the column as the smallest
		 * BLOB type large enough to hold values M bytes long. */
		BLOB,

		/** TEXT[(M)]
		 * <p>
		 * A TEXT column with a maximum length of 65,535 (216 – 1) characters.
		 * The effective maximum length is less if the value contains multi-byte
		 * characters. Each TEXT value is stored using a 2-byte length prefix
		 * that indicates the number of bytes in the value. An optional length M
		 * can be given for this type. If this is done, MySQL creates the column
		 * as the smallest TEXT type large enough to hold values M characters
		 * long. */
		TEXT,

		/** MEDIUMBLOB
		 * <p>
		 * A BLOB column with a maximum length of 16,777,215 (224 – 1) bytes.
		 * Each MEDIUMBLOB value is stored using a 3-byte length prefix that
		 * indicates the number of bytes in the value. */
		MEDIUMBLOB,

		/** MEDIUMTEXT
		 * <p>
		 * A TEXT column with a maximum length of 16,777,215 (224 – 1)
		 * characters. The effective maximum length is less if the value
		 * contains multi-byte characters. Each MEDIUMTEXT value is stored using
		 * a 3-byte length prefix that indicates the number of bytes in the
		 * value. */
		MEDIUMTEXT,

		/** LONGBLOB
		 * <p>
		 * A BLOB column with a maximum length of 4,294,967,295 or 4GB (232 – 1)
		 * bytes. The effective maximum length of LONGBLOB columns depends on
		 * the configured maximum packet size in the client/server protocol and
		 * available memory. Each LONGBLOB value is stored using a 4-byte length
		 * prefix that indicates the number of bytes in the value. */
		LONGBLOB,

		/** LONGTEXT
		 * <p>
		 * A TEXT column with a maximum length of 4,294,967,295 or 4GB (232 – 1)
		 * characters. The effective maximum length is less if the value
		 * contains multi-byte characters. The effective maximum length of
		 * LONGTEXT columns also depends on the configured maximum packet size
		 * in the client/server protocol and available memory. Each LONGTEXT
		 * value is stored using a 4-byte length prefix that indicates the
		 * number of bytes in the value. */
		LONGTEXT,

		/** ENUM('value 1', 'value 2', ...)
		 * <p>
		 * An enumeration. A string object that can have only one value, chosen
		 * from the list of values 'value1', 'value2', ..., NULL or the special
		 * '' error value. ENUM values are represented internally as integers.
		 * An ENUM column can have a maximum of 65,535 distinct elements. (The
		 * practical limit is less than 3000.) A table can have no more than 255
		 * unique element list definitions among its ENUM and SET columns
		 * considered as a group. */
		ENUM,

		/** SET('value 1', 'value 2, ...)
		 * <p>
		 * A set. A string object that can have zero or more values, each of
		 * which must be chosen from the list of values 'value1', 'value2', ...
		 * SET values are represented internally as integers. A SET column can
		 * have a maximum of 64 distinct members. A table can have no more than
		 * 255 unique element list definitions among its ENUM and SET columns
		 * considered as a group. For more information on this limit, see
		 * Section E.7.5, “Limits Imposed by .frm File Structure”. */
		SET;

		/** Returns the string representation of this {@link String}
		 * {@link DataType}
		 * 
		 * @param national indicates if this column should use some predefined
		 * character set
		 * @param charset the optional charset name of the string
		 * @param collate the optional collation name of the string
		 * @param arguments arguments single letter arguments for the data type
		 * @return the string representation of this string data type */
		public java.lang.String toString(final boolean national, final java.lang.String charset, final java.lang.String collate, final Object... arguments) {
			final StringBuilder builder = new StringBuilder();

			if (national)
				builder.append("NATIONAL ");

			builder.append(this.name());

			if (arguments != null && arguments.length != 0) {
				builder.append("(");
				for (int i = 0; i < arguments.length; i++) {
					builder.append(arguments[i]);
					if (i != (arguments.length - 1))
						builder.append(",");
				}
				builder.append(")");
			}

			if (charset != null) {
				builder.append(" CHARACTER SET ");
				builder.append(charset);
			}

			if (collate != null) {
				builder.append(" COLLATE ");
				builder.append(collate);
			}

			return builder.toString();
		}
	}

}
