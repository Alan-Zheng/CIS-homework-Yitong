module org.example.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens org.example.gui to javafx.fxml;
    exports org.example.gui;
}