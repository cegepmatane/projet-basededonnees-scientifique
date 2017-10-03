package vue;

import controleur.ControleurVue;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import modele.Bouee;

public class Test extends Application {


    private Bouee bouee;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        TableView table = new TableView();
        Scene scene = new Scene(new Group());
        stage.setTitle("Bouees");
        table.resize(800,400);

        final Label label = new Label("Liste des bouees");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn latitudeCol = new TableColumn("Latitude");
        TableColumn longitudeCol = new TableColumn("Longitude");
        TableColumn temperatureEauCol = new TableColumn("TemperatureEau");
        TableColumn temperatureAirCol = new TableColumn("TemperatureAir");
        TableColumn saliniteCol = new TableColumn("Salinite");
        TableColumn vitesseVentCol = new TableColumn("VitesseVent");
        TableColumn dimensionCol = new TableColumn("Dimension");
        TableColumn pressionAtmospheriqueCol = new TableColumn("PressionAtmospherique");
        TableColumn actionCol = new TableColumn("Action");

        latitudeCol.setCellValueFactory(
                new PropertyValueFactory<>("Latitude")
        );
        longitudeCol.setCellValueFactory(
                new PropertyValueFactory<>("Longitude")
        );
        temperatureEauCol.setCellValueFactory(
                new PropertyValueFactory<>("TemperatureEau")
        );
        temperatureAirCol.setCellValueFactory(
                new PropertyValueFactory<>("TemperatureAir")
        );
        saliniteCol.setCellValueFactory(
                new PropertyValueFactory<>("Salinite")
        );
        vitesseVentCol.setCellValueFactory(
                new PropertyValueFactory<>("VitesseVent")
        );
        dimensionCol.setCellValueFactory(
                new PropertyValueFactory<>("Dimension")
        );
        pressionAtmospheriqueCol.setCellValueFactory(
                new PropertyValueFactory<>("PressionAtmospherique")
        );
        actionCol.setCellValueFactory(
                new PropertyValueFactory<>("DUMMY")
        );

        Callback<TableColumn<BoueeDAO, String>, TableCell<BoueeDAO, String>> cellFactory
                = //
                new Callback<TableColumn<BoueeDAO, String>, TableCell<BoueeDAO, String>>() {
                    @Override
                    public TableCell call(final TableColumn<BoueeDAO, String> param) {
                        final TableCell<BoueeDAO, String> cell = new TableCell<BoueeDAO, String>() {

                            final Button btn = new Button("Ajouter");
                            final Button btnModifier = new Button("Modifier");
                            final Button btnSupprimer = new Button("Supprimer");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                    	BoueeDAO bouee = getTableView().getItems().get(getIndex());
                                        System.out.println(bouee.getFirstName()
                                                + "   " + bouee.getLastName());
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionCol.setCellFactory(cellFactory);


        table.setItems(modele.BoueeDAO.getInstance().recupererListeBouee());
        //table.getChildrenUnmodifiable().add(btnActionModifier);

        table.getColumns().addAll(latitudeCol, longitudeCol, temperatureEauCol, temperatureAirCol, saliniteCol, vitesseVentCol, dimensionCol, pressionAtmospheriqueCol, actionCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();




    }
}