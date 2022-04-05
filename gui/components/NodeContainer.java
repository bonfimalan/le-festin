package gui.components;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;

public class NodeContainer extends HBox {
  public NodeContainer() {
    super.setPrefHeight(20);
    super.setSpacing(5);
  }

  public void choseStation(int stationNumber) {
    ObservableList<Node> stations = super.getChildren();
    ANode station;
    for (Node node : stations) {
      station = (ANode) node;
      if (station.getText().getText().equals("E" + stationNumber))
        station.getCircle().setFill(Paint.valueOf("fca6a9"));
    }
  }
}
