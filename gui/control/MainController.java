package gui.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class MainController implements Initializable {
  @FXML AnchorPane anchorPane;

  private Circle[] stations;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    stations = new Circle[14];
    ObservableList children = anchorPane.getChildren();
    int i = 0;
    for(Object o : children){
      if (o instanceof Circle){
        stations[i] = (Circle) o;
        i++;
      }
    }

    new Thread(() -> {
      for (Circle c : stations) {
        try {
          Thread.sleep(500);
          c.setFill(Paint.valueOf("ffffff"));
        } catch (InterruptedException e) { }
      }
    }).start();;
  }
}
