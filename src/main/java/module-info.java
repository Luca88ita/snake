module com.snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.snake to javafx.fxml;
    exports com.snake;
}
