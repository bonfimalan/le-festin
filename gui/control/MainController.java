package gui.control;

import java.net.URL;
import java.util.ResourceBundle;

import astar.AStar;
import gui.components.ANode;
import gui.components.NodeContainer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MainController implements Initializable {
  @FXML private AnchorPane anchorPane;
  @FXML private VBox vBox;

  private NodeContainer actualLine;

  private int startIndex = -1;
  private int goalIndex;
  private boolean isStarted = false;

  private Circle[] stations;

  private AStar aStar;

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
  }

  public void reset() {
    isStarted = false;
    startIndex = -1;
    for (Circle circle : stations) {
      circle.setFill(Paint.valueOf("#1e90ff"));
    }
    vBox.getChildren().clear();
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
        aStar = new AStar(startIndex, goalIndex, this);
        aStar.start();
      }
    });

    station.setOnMouseEntered(event -> {
      station.setCursor(Cursor.HAND);
    });
  }

  public void showSolution(String path, String pathCost) {
    VBox container = new VBox(
      new Label(path),
      new Label(pathCost)
    );
    container.setSpacing(10);
    container.setAlignment(Pos.CENTER);

    Scene scene = new Scene(container);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setOnCloseRequest( event -> 
      this.reset()
    );
    stage.setWidth(300);
    stage.setHeight(200);
    stage.show();
  }

  public void visitNode(int index) {
    stations[index].setFill(Paint.valueOf("25a501"));
  }

  public void backTrackColoring(int index) {
    stations[index].setFill(Paint.valueOf("c039e5"));
  }

  public void choseStation(int index, String color){
    actualLine.choseStation(index + 1, color);
  }

  public void addLine() {
    actualLine = new NodeContainer();
    vBox.getChildren().addAll(actualLine);
  }

  public void addNodeToLine(int index) {
    actualLine.getChildren().addAll(new ANode(index + 1));
  }

  public VBox getVBox() {
    return this.vBox;
  }
}
