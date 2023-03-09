module com.example.theardpanelfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.theardpanelfx to javafx.fxml;
    exports com.example.theardpanelfx;
}