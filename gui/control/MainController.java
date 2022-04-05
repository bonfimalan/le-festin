package gui.control;

import java.net.URL;
import java.util.ResourceBundle;

import gui.components.ANode;
import gui.components.NodeContainer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class MainController implements Initializable {
  @FXML private AnchorPane anchorPane;
  @FXML private VBox vBox;

  private NodeContainer actualLine;

  private int startIndex = -1;
  private int goalIndex;
  private boolean isStarted = false;

  private Circle[] stations;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    vBox.setSpacing(5);

    // instatiates the array of Circle, that represents the stations
    stations = new Circle[14];
    ObservableList<Node> children = anchorPane.getChildren();
    int i = 0;
    for(Object o : children){
      if (o instanceof Circle){
        stations[i] = (Circle) o;
        addOnStationMouseEvents(stations[i], i);
        i++;
      }
    }    

    

    new Thread(() -> {
        try {
          Thread.sleep(100);
          addLine();
          Thread.sleep(100);
          addNodeToLine(1);
          Thread.sleep(100);
          addNodeToLine(2);
          Thread.sleep(100);
          addNodeToLine(3);
          Thread.sleep(100);
          addLine();
          Thread.sleep(100);
          addNodeToLine(1);
          Thread.sleep(100);
          addNodeToLine(2);
          Thread.sleep(100);
          addNodeToLine(3);
          Thread.sleep(100);
          choseStationAndAddLine(3);
          Thread.sleep(100);
          addNodeToLine(1);
          Thread.sleep(100);
          addNodeToLine(2);
          Thread.sleep(100);
          addNodeToLine(3);
          Thread.sleep(100);
          addLine();
          Thread.sleep(100);
          addNodeToLine(1);
          Thread.sleep(100);
          addNodeToLine(2);
          Thread.sleep(100);
          addNodeToLine(3);
        } catch (InterruptedException e) { }
    }).start();
    

    /*new Thread(() -> {
      for (Circle c : stations) {
        try {
          Thread.sleep(500);
          c.setFill(Paint.valueOf("ffffff"));
        } catch (InterruptedException e) { }
      }
    }).start();*/
  }

  public void algFinished() {
    isStarted = false;
    startIndex = -1;

  }

  public void addOnStationMouseEvents(Circle station, int index) {
    station.setOnMouseClicked(event -> {
      if (isStarted)
        return;

      if (startIndex == -1) {
        startIndex = index;
        station.setFill(Paint.valueOf("25a501"));
      }
      else if (startIndex != index) {
        goalIndex = index;
        station.setFill(Paint.valueOf("fc0707"));
        isStarted = true;
        // starts the alg now
      }          
      System.out.println(startIndex + "    " + goalIndex);
    });

    station.setOnMouseEntered(event -> {
      station.setCursor(Cursor.HAND);
    });
  }

  public void visitNode(int index) {
    stations[index].setFill(Paint.valueOf("fc0707"));
  }

  public void choseStationAndAddLine(int stationNumber){
    Platform.runLater(() -> actualLine.choseStation(stationNumber));
    addLine();
  }

  public void addLine() {
    Platform.runLater(() ->{
      //System.out.println("a");
      actualLine = new NodeContainer();
      vBox.getChildren().addAll(actualLine);
    });
  }

  public void addNodeToLine(int index) {
    Platform.runLater(() -> {
      //System.out.println("b");
      actualLine.getChildren().addAll(new ANode(index));
    });
  }
}
