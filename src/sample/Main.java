package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Вход в систему.fxml"));
        primaryStage.setTitle("Поиск работы");
        primaryStage.setScene(new Scene(root, 1700, 900));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}//всё работает
