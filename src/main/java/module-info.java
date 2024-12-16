module tech.neil.checklist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens tech.neil.checklist to javafx.fxml;
    exports tech.neil.checklist;
}