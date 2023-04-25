# ShapesPhotoAlbum

-----Overview-----

This project builds a simple Java application that allows users to create and manage photo albums for various shapes (currently covering rectangle and oval shapes). Users can add shapes to the album, apply transformations to shapes in the album such as moving, coloring, resizing, and removing. Then users can take snapshots of shapes that currently exist in the album canvas with optional descriptions. An album consists of zero to multiple snapshots. Users can choose either Web View or GUI View to view snapshots in the album. While Web View is a static style that displays all snapshots at once, GUI View is more interactive where users can perform basic operations by clicking on different buttons, such as "Prev", "Next", "Select", and "Quit".

-----Design-----

This project follows the Model-View-Controller (MVC) architecture, which separates the program into three main parts:

Model: Represents the data and business logic of this application. The IAlbumModel interface, PhotoAlbumModel class, and the IShape interface, along with its concrete subclasses, form the main part of the Model.

View: Represents the user interface for displaying the data from the Model. In this project, there are two kinds of views, WebView and GUIView. Users can choose which view mode to display the album.

Controller: Represents an intermediary between the Model and View, handling user input, updating the model, and sending updated data to the view.

This project also follows an object-oriented design approach and adopts design patterns to make the code more robust and maintainable. For example, by using high-level interfaces like IShape and IAlbumModel, it provides extensibility for adding more operations in the future. The ShapeFactory class, which uses the factory method pattern, makes the code less coupled and easier to extend. With the command design pattern, the command classes in the utils package encapsulate all the data required for performing a certain action (depending on the actual input). In this project, the "shape" command tells the command object to call the add(IShape shape) method from the model. The Command Pattern is suitable for this project as it can be seen as a request-response model, and the command pattern allows creating a sequence of requests (commands).

-----Instructions-----

This application supports command-line arguments. For example, running "java -jar visualAlbum.jar -in demo_input.txt -view web -out out.html" will generate a file named "out.html". PhotoAlbumMain is the entry point of this application, which reads command-line arguments, parses the input file, updates the model, and displays the view by calling the run method from the controller.
