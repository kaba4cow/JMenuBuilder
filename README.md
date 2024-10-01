# JMenuBuilder

**JMenuBuilder** is a utility class designed to simplify the creation and configuration of **Swing JMenu** objects using a fluent API. By chaining method calls, you can easily construct menus, add submenus, items, separators, and attach action listeners in a clean and readable way.

### Features

 - Create and configure **JMenu** objects easily.
 - Add submenus with nested **JMenuBuilder** instances or pre-existing **JMenu** objects.
 - Add menu items with or without action listeners.
 - Add separators to structure your menu.
 - Attach the constructed **JMenu** to either a **JMenuBar** or another **JMenu**.

### Usage

##### Constructor

    new JMenuBuilder("File");

The constructor creates a new **JMenuBuilder** with a specified label (e.g., "File"). This label will appear as the menu name.

##### Adding Menu Items

You can add **JMenuItem** objects to the menu. You can either add items with no action listeners or specify an action listener:

    new JMenuBuilder("File")
    		.item(new JMenuItem("New"), event -> System.out.println("New clicked"))
    		.item(new JMenuItem("Open"));

##### Adding Submenus

You can add submenus using another **JMenuBuilder** or a **JMenu**:

    new JMenuBuilder("Menu")
    		.menu(new JMenuBuilder("File"))
    		.menu(new JMenu("Edit"));

##### Adding Separators

You can add separators to visually break up sections of the menu:

    new JMenuBuilder("Help")
    		.item("About")
    		.separator()
    		.item("License");

##### Executing Actions

You can execute actions on the **JMenuBuilder** using the **execute** method. This allows you to encapsulate certain menu-building logic in reusable functions:

    new JMenuBuilder("File").execute(builder ->
    		builder
    			.item(new JMenuItem("Open"))
    			.item(new JMenuItem("Save"))
    			.separator()
    			.item(new JMenuItem("Exit"), event -> System.exit(0))
    );

##### Conditions

You can branch your menu-building logic using conditional methods **executeIf** and **executeIfElse**. The **executeIf** method takes a **condition** and **passAction** which is executed if condition passes. The **executeIfElse** method does the same but also calls **failAction** if condition fails.

	boolean condition1 = true;
	boolean condition2 = false;
	new JMenuBuilder("File")
			.executeIf(() -> condition1, 
				builder -> builder.item(new JMenuItem("Condition 1 passed")))
			.executeIfElse(() -> condition2,
				builder -> builder.item(new JMenuItem("Condition 2 passed")), 
				builder -> builder.item(new JMenuItem("Condition 2 failed")));

##### Building the Menu

Once the menu has been configured, you can build it and attach it to a **JMenuBar** or another **JMenu**:

    JMenuBar menuBar = new JMenuBar();
    new JMenuBuilder("File")
    		.item("Open")
    		.item("Save")
    		.build(menuBar);
    new JMenuBuilder("Help")
    		.item("About")
    		.build(menuBar);

Alternatively, to add it to another menu:

    JMenu mainMenu = new JMenu("Main Menu");
    new JMenuBuilder("Settings")
    		.item("Difficulty")
    		.item("Volume")
    		.build(mainMenu);

### Example

Here’s an example of how to use **JMenuBuilder** to construct a simple menu:

    JMenuBar menuBar = new JMenuBar();
    new JMenuBuilder("File")
    		.item(new JMenuItem("New"), event -> System.out.println("Creating new file"))
    		.item(new JMenuItem("Open"), event -> System.out.println("Loading a file"))
    		.separator()
    		.item(new JMenuItem("Exit"), event -> {
    			System.out.println("Exiting");
    			System.exit(0);
    		})
    		.build(menuBar);
    new JMenuBuilder("Edit")
    		.item(new JMenuItem("Cut"))
    		.item(new JMenuItem("Copy"))
    		.item(new JMenuItem("Paste"))
    		.build(menuBar);

This creates a menu bar with two menus ("File" and "Edit") and several menu items. The **JMenuBuilderExample.java** file contains a more complex example of building a menu.