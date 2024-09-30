package com.kaba4cow.jmenubuilder;

/**
 * A functional interface representing a function that can build a
 * {@link JMenuBuilder}.
 */
public interface JMenuBuilderFunction {

	/**
	 * Builds a menu using the provided {@link JMenuBuilder}.
	 *
	 * @param builder the {@link JMenuBuilder} to configure
	 */
	public void build(JMenuBuilder builder);

}
