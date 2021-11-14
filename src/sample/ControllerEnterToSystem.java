package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ControllerEnterToSystem {

    @FXML
    private TextField enterSystem_password;

    @FXML
    private TextField enterSystem_login;

    @FXML
    private Button enterSystem_enterButton;

    @FXML
    private Button enterSystem_registrButton;


    public void enterToSystem(){
        User user = new User(enterSystem_login.getText(), enterSystem_password.getText());
        if (user.loginExist()){
            if(user.passwordCorrect()){
                //войти в систему
                Stage stage = new Stage();
                stage.setTitle("Поиск работы");
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass ().getResource("Главное меню.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                Stage stage1 = (Stage) enterSystem_enterButton.getScene().getWindow();
                stage1.close();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING); //создаём объект alert для вывода сообщений пользователю
                alert.setTitle("Авторизация");
                alert.setHeaderText("");
                alert.setContentText("Ошибка в логине или пароле.");
                alert.showAndWait();
                //вывести сообщение об ошибке в логине или пароле
            }
        }else{
            //вывести сообщение об ошибке в логине или пароле
            Alert alert = new Alert(Alert.AlertType.WARNING); //создаём объект alert для вывода сообщений пользователю
            alert.setTitle("Авторизация");
            alert.setHeaderText("");
            alert.setContentText("Ошибка в логине или пароле.");
            alert.showAndWait();
        }
    }

    public void registrationInSystem(){
        Stage stage = new Stage();
        stage.setTitle("Поиск работы");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass ().getResource("Регистрация в системе.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) enterSystem_registrButton.getScene().getWindow();
        stage1.close();
    }

    public void initialize() throws IOException {//очищаем файл с избранным
        File file = new File("Favourites.txt");
        file.createNewFile();
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write("");
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}//всё работает
