package com.kaba4cow.jmenubuilder;

import java.awt.event.ActionListener;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

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
	 * Executes an action on this {@link JMenuBuilder}.
	 * 
	 * @param action the action to execute on this {@link JMenuBuilder}
	 * @return reference to this object
	 */
	public JMenuBuilder execute(Consumer<JMenuBuilder> action) {
		action.accept(this);
		return this;
	}

	/**
	 * Executes a condition on this {@link JMenuBuilder}. If {@code condition}
	 * returns {@code true} then {@code passAction} is executed.
	 * 
	 * @param condition  the condition to check
	 * @param passAction the action to execute on condition passed
	 * @return reference to this object
	 */
	public JMenuBuilder executeIf(Supplier<Boolean> condition, Consumer<JMenuBuilder> passAction) {
		if (condition.get())
			passAction.accept(this);
		return this;
	}

	/**
	 * Executes a branched condition on this {@link JMenuBuilder}. If
	 * {@code condition} returns {@code true} then {@code passAction} is executed,
	 * else {@code failAction} is executed.
	 * 
	 * @param condition  the condition to check
	 * @param passAction the action to execute on condition passed
	 * @param failAction the action to execute on condition failed
	 * @return reference to this object
	 */
	public JMenuBuilder executeIfElse(Supplier<Boolean> condition, Consumer<JMenuBuilder> passAction,
			Consumer<JMenuBuilder> failAction) {
		if (condition.get())
			passAction.accept(this);
		else
			failAction.accept(this);
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

	/**
	 * Adds the constructed menu to a specified {@link JPopupMenu}.
	 *
	 * @param target the target menu to add the menu to
	 */
	public void build(JPopupMenu target) {
		target.add(menu);
	}

}
