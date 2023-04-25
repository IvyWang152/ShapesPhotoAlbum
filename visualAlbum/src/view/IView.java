package view;

import model.IAlbumModel;

import java.awt.event.ActionListener;

/**
 * This interface declares operations of view classes.
 */
public interface IView {
  /**
   * Update view when view class is interactive (GUIView in this case).
   */
  default void updateView() {}
  //display snapShot and description

  /**
   * Register action listener for the view class.
   * @param listener action listener (controller in this case)
   */
  default void registerActionListener(ActionListener listener) {}

  /**
   * Display snapShots, their ID and descriptions.
   * @param model photo album model instance
   */
  void display(IAlbumModel model);



}
