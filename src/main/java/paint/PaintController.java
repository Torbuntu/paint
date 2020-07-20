package paint;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class PaintController implements Initializable {

    private int size;

    private Paint brushColor = Color.BLACK;

    @FXML
    private RadioButton blackRadioButton;
    @FXML
    private RadioButton redRadioButton;
    @FXML
    private RadioButton greenRadioButton;
    @FXML
    private RadioButton blueRadioButton;

    @FXML
    private Slider sizeSlider;

    @FXML
    private RadioButton circleRadioButton;
    @FXML
    private RadioButton squareRadioButton;
    @FXML
    private RadioButton hexagonRadioButton;
    @FXML
    private RadioButton rhombusRadioButton;

    @FXML
    private ToggleGroup colorToggleGroup;
    @FXML
    private ToggleGroup shapeToggleGroup;

    @FXML
    private Button clearButton;
    @FXML
    private BorderPane rootPane;

    @FXML
    private Canvas drawingCanvas;

    GraphicsContext gc;

    @FXML
    void drawingAreaMouseDragged(MouseEvent e) {
        switch ((String) shapeToggleGroup.getSelectedToggle().getUserData()) {
            case "circle" -> {
                gc.fillOval(e.getX() - size / 2, e.getY() - size / 2, size, size);
            }
            case "square" -> {
                gc.fillRect(e.getX() - size / 2, e.getY() - size / 2, size, size);
            }
            case "hexagon" -> {
                gc.fillPolygon(
                        new double[]{e.getX() - 0.25 * size, e.getX() + 0.25 * size, e.getX() + 0.50 * size, e.getX() + 0.25 * size, e.getX() - 0.25 * size, e.getX() - 0.50 * size},
                        new double[]{e.getY() - 0.40 * size, e.getY() - 0.40 * size, e.getY(), e.getY() + 0.40 * size, e.getY() + 0.40 * size, e.getY()},
                        6);
            }
            case "rhombus" -> {
                gc.fillPolygon(
                        new double[]{e.getX(), e.getX() + 0.75 * size, e.getX(), e.getX() - 0.75 * size},
                        new double[]{e.getY() - 1 * size, e.getY(), e.getY() + 1 * size, e.getY()},
                        4);
            }
        }
    }

    @FXML
    void colorRadioButtonSelected(ActionEvent e) {
        brushColor = (Color) colorToggleGroup.getSelectedToggle().getUserData();
        gc.setFill(brushColor);
    }

    private void clearScreen() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
        gc.setFill(brushColor);
    }

    @FXML
    void clearButtonPressed(ActionEvent e) {
        clearScreen();
    }

    @FXML
    void sizeSliderDragged(MouseEvent e) {
        size = (int) sizeSlider.getValue();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = drawingCanvas.getGraphicsContext2D();
        clearScreen();

        blackRadioButton.setUserData(Color.BLACK);
        redRadioButton.setUserData(Color.RED);
        greenRadioButton.setUserData(Color.GREEN);
        blueRadioButton.setUserData(Color.BLUE);

        circleRadioButton.setUserData("circle");
        squareRadioButton.setUserData("square");
        hexagonRadioButton.setUserData("hexagon");
        rhombusRadioButton.setUserData("rhombus");

        size = 5;
        sizeSlider.setValue(size);
    }

}
