package controller;


import model.IAlbumModel;
import utils.CommandInvoker;
import utils.FileCommandReceiver;
import utils.FileHandler;
import view.IView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * This class is the controller of the Shape Photo Album application.
 * Its main function is to interpret events from the view and update instances of
 * PhotoAlbumModel and GUIView as appropriate.
 */
public class PhotoAlbumController implements ActionListener {
  private final IAlbumModel model;
  private IView view;
  private FileHandler reader;
  private CommandInvoker handler;


  /**
   * Constructor.
   *
   * @param fileName command file name
   * @param model    photo album model instance
   * @param view     view instance
   */
  public PhotoAlbumController(String fileName, IAlbumModel model, IView view) {
    if (fileName == null || model == null) {
      throw new IllegalArgumentException("Input cannot be null");
    }
    //this.fileName = fileName;
    this.model = model;
    this.view = view;
    reader = new FileHandler(fileName);
  }

  /**
   * Execute this application by handling command file input and displaying view.
   *
   * @throws IOException when input parameter is invalid
   */
  public void run() throws IOException {
    handler = new FileCommandReceiver(reader).readCommand(model);
    handler.executeCommand();
    view.display(model);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    List<LocalDateTime> IDList = model.getSnapShotsID();
    if (e.getActionCommand() == "previous") {
      if (model.getIndex() > 0) {
        model.updateIndex(model.getIndex() - 1);
        view.updateView();
      } else {
        //prompt
        JOptionPane.showMessageDialog(null,
            "No snapshots to show prior to this one");
      }
    }

    if (e.getActionCommand() == "next") {
      if (model.getIndex() < model.getSnapShotsID().size() - 1) {
        model.updateIndex(model.getIndex() + 1);
        view.updateView();

      } else {
        JOptionPane.showMessageDialog(null, "End of the photo album. No "
            + "snapshots to show after this one");
      }
    }

    if (e.getActionCommand() == "quit") {
      System.exit(0);
    }

    if (e.getActionCommand() == "select") {
      Object option = JOptionPane.showInputDialog(null, "Choose", "Menu",
          JOptionPane.PLAIN_MESSAGE, null, IDList.toArray(), IDList.toArray()[model.getIndex()]);

      model.updateIndex(IDList.indexOf(option));
      view.updateView();

    }
  }
}
