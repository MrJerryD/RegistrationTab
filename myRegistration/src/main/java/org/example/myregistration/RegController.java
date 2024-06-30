package org.example.myregistration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button auth_btn;

    @FXML
    private TextField auth_login;

    @FXML
    private PasswordField auth_pass;

    @FXML
    private Button reg_btn;

    @FXML
    private TextField reg_email;

    @FXML
    private TextField reg_login;

    @FXML
    private PasswordField reg_pass;

    @FXML
    private CheckBox reg_rights;

    private DB db = new DB();


    @FXML
    void initialize() {
        reg_btn.setOnAction(event -> {
            try {
                registrationUser();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void registrationUser() throws SQLException, ClassNotFoundException {
        String login = reg_login.getCharacters().toString();
        String email = reg_email.getCharacters().toString();
        String pass = reg_pass.getCharacters().toString();

        reg_login.setStyle("-fx-border-color: white");
        reg_email.setStyle("-fx-border-color: white");
        reg_pass.setStyle("-fx-border-color: white");

        if (login.length() <= 1) {
            reg_login.setStyle("-fx-border-color: red");
        }
        else if (email.length() <= 5 || !email.contains("@") || !email.contains(".")) {
            reg_email.setStyle("-fx-border-color: red");
        }
        else if (pass.length() <= 3) {
            reg_pass.setStyle("-fx-border-color: red");
        }
        else if (!reg_rights.isSelected()) {
            reg_btn.setText("SET THE ARROW");
        } else if (db.isExistUser(login)) {
            reg_btn.setText("already exist");
        }
        else {
            db.regUser(login, email, pass);
            reg_login.setText("");
            reg_email.setText("");
            reg_pass.setText("");
            reg_btn.setText("REGISTER");
        }
    }

}








