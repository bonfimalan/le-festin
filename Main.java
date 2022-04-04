import gui.control.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/mainView.fxml"));
    loader.setController(new MainController());
    Parent parent = loader.load();
    Scene scene = new Scene(parent);
    primaryStage.getIcons().add(new Image("/resources/icon.png"));
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}