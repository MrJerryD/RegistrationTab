module org.example.myregistration {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens org.example.myregistration to javafx.fxml;
    exports org.example.myregistration;
}