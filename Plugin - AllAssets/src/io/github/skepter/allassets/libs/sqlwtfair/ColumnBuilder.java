package io.github.skepter.allassets.libs.sqlwtfair;

/** Makes creating columns a LOT easier. All SQL constraints are not included by
 * default. */
public class ColumnBuilder {
	private String name = "a_column", dataType = "VARCHAR(225)", def = null,
			foreignKey = null;
	private boolean notNull = false, unique = false, primaryKey = false;

	/** Sets the name of this column
	 * 
	 * @param name the name of the column
	 * @return {@code this} for chaining */
	public ColumnBuilder setName(final String name) {
		this.name = name;
		return this;
	}

	/** Sets the data type of this column
	 * 
	 * @param dataType the data type. For ease of use, use {@link DataType}.
	 * @return {@code this} for chaining */
	public ColumnBuilder setDataType(final String dataType) {
		this.dataType = dataType;
		return this;
	}

	/** Sets whether this column should not accept null values
	 * 
	 * @param notNull {@code true} if this column should not accept null values
	 * @return {@code this} for chaining */
	public ColumnBuilder setNotNull(final boolean notNull) {
		this.notNull = notNull;
		return this;
	}

	/** Sets whether this column should ensure that every row has a unique value
	 * 
	 * @param unique {@code true} if this column should ensure that every row
	 * has a unique value
	 * @return {@code this} for chaining */
	public ColumnBuilder setUnique(final boolean unique) {
		this.unique = unique;
		return this;
	}

	/** Sets whether this column is the primary key for the table
	 * 
	 * @param primaryKey {@code true} if this column is the primary key for the
	 * table
	 * @return {@code this} for chaining */
	public ColumnBuilder setPrimaryKey(final boolean primaryKey) {
		this.primaryKey = primaryKey;
		return this;
	}

	/** Sets the default value for rows in this column
	 * 
	 * @param def the default value for rows in this column. You can use
	 * functions, values may need to be surrounded by ''.
	 * @return {@code this} for chaining */
	public ColumnBuilder setDefault(final String def) {
		this.def = def;
		return this;
	}

	/** Makes this column a foreign key of the specified string
	 * 
	 * @param foreignKey the column to correspond with in the format
	 * {@code TABLE(COLUMN)}
	 * @return {@code this} for chaining */
	public ColumnBuilder setForeignKey(final String foreignKey) {
		this.foreignKey = foreignKey;
		return this;
	}

	/** Gets whether this column is the primary key of the table
	 * 
	 * @return {@code true} if it is the primary key of the table */
	protected boolean isPrimaryKey() {
		return this.primaryKey;
	}

	/** Gets whether this column is unique
	 * 
	 * @return {@code true} if this column is unique */
	protected boolean isUnique() {
		return this.unique;
	}

	/** Gets whether this column if a foreign ket
	 * 
	 * @return {@code true} if this column is a foreign key */
	protected boolean isForeignKey() {
		return this.foreignKey != null && this.foreignKey.equals("");
	}

	/** Gets the foreign key string for this column. It is advised that you check
	 * if the foreign key exists using {@link ColumnBuilder#foreignKey}
	 * 
	 * @return the foreign key string */
	protected String getForeignKey() {
		return this.foreignKey;
	}

	/** Gets the name of this table
	 * 
	 * @return the name */
	protected String getName() {
		return this.name;
	}

	/** Builds this {@link ColumnBuilder} into a {@link String}
	 * 
	 * @return the built string */
	protected String build() {
		final StringBuilder builder = new StringBuilder(name);
		builder.append(" ");
		builder.append(dataType);

		if (notNull)
			builder.append(" NOT NULL");

		if (def != null && !def.equals("")) {
			builder.append(" DEFAULT ");
			builder.append(def);
		}

		return builder.toString();
	}

}
