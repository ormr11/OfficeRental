module com.example.testing {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.testing to javafx.fxml;
    exports com.example.testing;
}