package com.kaba4cow.jmenubuilder;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * A builder class for constructing and configuring {@link JMenu} objects with a
 * fluent API.
 */
public class JMenuBuilder {

	private final JMenu menu;

	/**
	 * Creates a new {@link JMenuBuilder} with the specified menu text.
	 *
	 * @param text the text of the menu
	 */
	public JMenuBuilder(String text) {
		this.menu = new JMenu(text);
	}

	/**
	 * Applies a custom function to this {@link JMenuBuilder} using a
	 * {@link JMenuBuilderFunction}.
	 *
	 * @param function the function to apply
	 * @return reference to this object
	 */
	public JMenuBuilder function(JMenuBuilderFunction function) {
		function.build(this);
		return this;
	}

	/**
	 * Adds a submenu to the current menu using another {@link JMenuBuilder}.
	 *
	 * @param builder the submenu builder
	 * @return reference to this object
	 */
	public JMenuBuilder menu(JMenuBuilder builder) {
		this.menu.add(builder.build());
		return this;
	}

	/**
	 * Adds a pre-existing {@link JMenu} as a submenu to the current menu.
	 *
	 * @param menu the submenu to add
	 * @return reference to this object
	 */
	public JMenuBuilder menu(JMenu menu) {
		this.menu.add(menu);
		return this;
	}

	/**
	 * Adds a separator to the current menu.
	 *
	 * @return reference to this object
	 */
	public JMenuBuilder separator() {
		this.menu.addSeparator();
		return this;
	}

	/**
	 * Adds a {@link JMenuItem} to the current menu without an
	 * {@link ActionListener}.
	 *
	 * @param item the menu item to add
	 * @return reference to this object
	 */
	public JMenuBuilder item(JMenuItem item) {
		return item(item, null);
	}

	/**
	 * Adds a {@link JMenuItem} to the current menu with an optional
	 * {@link ActionListener}.
	 *
	 * @param item           the menu item to add
	 * @param actionListener the action listener to attach to the menu item, or
	 *                       {@code null} if none
	 * @return reference to this object
	 */
	public JMenuBuilder item(JMenuItem item, ActionListener actionListener) {
		if (actionListener != null)
			item.addActionListener(actionListener);
		this.menu.add(item);
		return this;
	}

	/**
	 * Builds and returns the constructed {@link JMenu}.
	 *
	 * @return the built {@link JMenu}
	 */
	public JMenu build() {
		return menu;
	}

	/**
	 * Adds the constructed menu to a specified {@link JMenuBar}.
	 *
	 * @param target the target menu bar to add the menu to
	 */
	public void build(JMenuBar target) {
		target.add(menu);
	}

	/**
	 * Adds the constructed menu to a specified {@link JMenu}.
	 *
	 * @param target the target menu to add the menu to
	 */
	public void build(JMenu target) {
		target.add(menu);
	}

}
