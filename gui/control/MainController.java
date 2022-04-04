package gui.control;

import java.net.URL;
import java.util.ResourceBundle;

import gui.components.ANode;
import gui.components.NodeContainer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class MainController implements Initializable {
  @FXML private AnchorPane anchorPane;
  @FXML private VBox vBox;

  private Circle[] stations;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    vBox.setSpacing(5);

    stations = new Circle[14];
    ObservableList<Node> children = anchorPane.getChildren();
    int i = 0;
    for(Object o : children){
      if (o instanceof Circle){
        stations[i] = (Circle) o;
        i++;
      }
    }

    vBox.getChildren().addAll(
      new NodeContainer(new ANode(1), new ANode(2)),
      new NodeContainer(new ANode(3), new ANode(2))
      );

    /*new Thread(() -> {
      for (Circle c : stations) {
        try {
          Thread.sleep(500);
          c.setFill(Paint.valueOf("ffffff"));
        } catch (InterruptedException e) { }
      }
    }).start();;*/
  }
}
