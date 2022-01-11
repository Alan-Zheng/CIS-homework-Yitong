module org.example.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens org.example.gui.calculator to javafx.fxml;
    opens org.example.gui.friendbook to javafx.fxml;
    exports org.example.gui.calculator;
    exports org.example.gui.friendbook;
}