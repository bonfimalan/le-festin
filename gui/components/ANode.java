package gui.components;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class ANode extends StackPane {
  private Circle circle;
  private Text text;

  public ANode(int stationNumber) {
    circle = new Circle();
    circle.setRadius(17);
    circle.setFill(Paint.valueOf("ffffff"));
    circle.setStrokeWidth(1);
    circle.setStroke(Paint.valueOf("000000"));
    
    text = new Text("E" + stationNumber);
    super.getChildren().addAll(circle, text);  
  }

  public Circle getCircle() {
    return circle;
  }

  public void setCircle(Circle circle) {
    this.circle = circle;
  }

  public Text getText() {
    return text;
  }

  public void setText(Text text) {
    this.text = text;
  }
}
