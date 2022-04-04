package gui.components;

import javafx.scene.layout.HBox;

public class NodeContainer extends HBox {
  public NodeContainer(ANode... nodes) {
    super.getChildren().addAll(nodes);
    super.setSpacing(5);
  }
}
