package view;

import javafx.scene.layout.Region;
import model.ProjectPlanningModel;

import javax.swing.text.View;

public class ViewEditGeneralController
{
  private ProjectPlanningModel model;
  private Region root;
  private ViewHandler viewHandler;

  public Region getRoot() {
    return root;
  }

  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  public void reset() {
    // Reset logic
    init(viewHandler, model, root);
  }

}
