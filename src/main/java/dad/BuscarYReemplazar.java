package dad;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BuscarYReemplazar extends Application {

	public static int MAX_ANCHO = 110;
	public static int MAX_ALTO = 10;

	// view
	private TextField buscarText;
	private TextField reemplazarText;
	private RadioButton mayMinButton, expRegButton, haciaAtrasButton, resaltarResButton;
	private Button buscarButton, reemplazarButton, reemplazarTodoButton, cerrarButton, ayudaButton;

	@Override
	public void start(Stage primaryStage) throws Exception {

		buscarText = new TextField();
		reemplazarText = new TextField();

		mayMinButton = new RadioButton("Mayúsculas y minúsculas");
		expRegButton = new RadioButton("Expresión regular");
		haciaAtrasButton = new RadioButton("Buscar hacia atrás");
		resaltarResButton = new RadioButton("Resaltar resultados");

		VBox vertical = new VBox(mayMinButton, expRegButton);
		VBox vertical2 = new VBox(haciaAtrasButton, resaltarResButton);
		HBox botones = new HBox(vertical, vertical2);
		botones.setAlignment(Pos.TOP_CENTER);

		buscarButton = new Button("Buscar");
		reemplazarButton = new Button("Reemplazar");
		reemplazarTodoButton = new Button("Reemplazar todo");
		cerrarButton = new Button("Cerrar");
		ayudaButton = new Button("Ayuda");

		buscarButton.setPrefSize(MAX_ANCHO, MAX_ALTO);
		reemplazarButton.setPrefSize(MAX_ANCHO, MAX_ALTO);
		reemplazarTodoButton.setPrefSize(MAX_ANCHO, MAX_ALTO);
		cerrarButton.setPrefSize(MAX_ANCHO, MAX_ALTO);
		ayudaButton.setPrefSize(MAX_ANCHO, MAX_ALTO);

		VBox opciones = new VBox(buscarButton, reemplazarButton, reemplazarTodoButton, cerrarButton, ayudaButton);
		opciones.setAlignment(Pos.TOP_CENTER);
		opciones.setSpacing(5);

		GridPane root = new GridPane();
		root.setPadding(new Insets(5));
		root.setHgap(5);
		root.setVgap(5);
//		root.setGridLinesVisible(true);
		root.addRow(0, new Label("Buscar:"), buscarText);
		root.addRow(1, new Label("Reemplazar con:"), reemplazarText);
		root.addRow(2, new Label(""), botones);
		root.addColumn(2, opciones);

		// se crean 3 objetos porque hay 3 columnas
		ColumnConstraints[] cols = { new ColumnConstraints(), new ColumnConstraints(), new ColumnConstraints() };
		root.getColumnConstraints().setAll(cols);

		cols[0].setHalignment(HPos.LEFT); // alinear texto de la columna 0 a la derecha
		cols[0].setHgrow(Priority.NEVER);
		cols[0].setMinWidth(Control.USE_COMPUTED_SIZE);
		cols[1].setHgrow(Priority.NEVER);

		RowConstraints[] rows = new RowConstraints[3];
		for (int i = 0; i < rows.length; i++) {
			rows[i] = new RowConstraints();
			rows[i].setPrefHeight(30);
		}
		root.getRowConstraints().setAll(rows);

		GridPane.setRowSpan(opciones, 3);

		primaryStage.setTitle("Buscar y reemplazar");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		ToggleGroup opcionesGroup = new ToggleGroup();
		opcionesGroup.getToggles().addAll(mayMinButton, expRegButton, haciaAtrasButton, resaltarResButton);
		opcionesGroup.selectedToggleProperty().addListener((o, ov, nv) -> {
			RadioButton selected = (RadioButton) nv;
			System.out.println(selected.getText());
		});

	}

	public static void main(String[] args) {
		launch(args);
	}

}
