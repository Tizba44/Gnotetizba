module org.openjfx.sio2E4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens org.openjfx.sio2E4 to javafx.fxml;
    exports org.openjfx.sio2E4;
}
