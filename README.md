# JMenuBuilder

**JMenuBuilder** is a utility class designed to simplify the creation and configuration of **Swing JMenu** objects using a fluent API. By chaining method calls, you can easily construct menus, add submenus, items, separators, and attach action listeners in a clean and readable way.

### Features

-     Create and configure **JMenu** objects easily.
-     Add submenus with nested **JMenuBuilder** instances or pre-existing **JMenu** objects.
-     Add menu items with or without action listeners.
-     Add separators to structure your menu.
-     Attach the constructed **JMenu** to either a **JMenuBar** or another **JMenu**.

### Usage

##### Constructor

    JMenuBuilder builder = new JMenuBuilder("File");

The constructor creates a new **JMenuBuilder** with a specified label (e.g., "File"). This label will appear as the menu name.

##### Adding Menu Items

You can add **JMenuItem** objects to the menu. You can either add items with no action listeners or specify an action listener:

    JMenuItem newItem = new JMenuItem("New");
    builder.item(newItem, event -> System.out.println("New clicked!"));

If no action listener is needed:

    JMenuItem openItem = new JMenuItem("Open");
    builder.item(openItem);

##### Adding Submenus

You can add submenus using another **JMenuBuilder** or a **JMenu**:

    JMenuBuilder editMenuBuilder = new JMenuBuilder("Edit");
    builder.menu(editMenuBuilder);

Alternatively, if you have a pre-existing **JMenu**:

    JMenu helpMenu = new JMenu("Help");
    builder.menu(helpMenu);

##### Adding Separators

You can add separators to visually break up sections of the menu:

    builder.separator();

##### Applying Custom Functions

You can apply custom functions using the **JMenuBuilderFunction** interface. This allows you to encapsulate certain menu-building logic in reusable functions:

    builder.function(builder -> {
    		JMenuItem saveItem = new JMenuItem("Save");
    		builder.item(saveItem, event -> System.out.println("Save clicked!"));
    });

##### Building the Menu

Once the menu has been configured, you can build it and attach it to a **JMenuBar** or another **JMenu**:

    JMenuBar menuBar = new JMenuBar();
    builder.build(menuBar);

Alternatively, to add it to another menu:

    JMenu mainMenu = new JMenu("Main Menu");
    builder.build(mainMenu);

### Example

Here’s an example of how to use **JMenuBuilder** to construct a simple menu:

    JMenuBar menuBar = new JMenuBar();
    
    JMenuBuilder fileMenu = new JMenuBuilder("File")
    		.item(new JMenuItem("New"), event -> System.out.println("New clicked!"))
    		.item(new JMenuItem("Open"), event -> System.out.println("Open clicked!"))
    		.separator()
    		.item(new JMenuItem("Exit"), event -> System.exit(0));
    
    JMenuBuilder editMenu = new JMenuBuilder("Edit")
    		.item(new JMenuItem("Cut"))
    		.item(new JMenuItem("Copy"))
    		.item(new JMenuItem("Paste"));
    
    fileMenu.build(menuBar);
    editMenu.build(menuBar);

This creates a menu bar with two menus ("File" and "Edit") and several menu items.