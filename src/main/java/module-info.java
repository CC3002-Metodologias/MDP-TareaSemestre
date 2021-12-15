module com.example.aventurasdemarcoyluis {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires java.desktop;


    opens com.example.aventurasdemarcoyluis to javafx.fxml;
    exports com.example.aventurasdemarcoyluis;
}