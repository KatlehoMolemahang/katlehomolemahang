package com.example.gallary;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import java.util.Objects;

public class HelloApplication extends Application {

    private static final int THUMBNAIL_SIZE = 100;
    private static final int FULL_IMAGE_SIZE = 400;

    private ImageView fullImageView;
    private Button back_Btn;
    private Button backBtn;
    private Button nextBtn;


    private Image[] fullSizeImages = new Image[]{
             new Image(Objects.requireNonNull(getClass().getResourceAsStream("4.png"))),
             new Image(Objects.requireNonNull(getClass().getResourceAsStream("3.png"))),
             new Image(Objects.requireNonNull(getClass().getResourceAsStream("5.png")))
    };
    private int currentImageIndex = 0;
    private String css = getClass().getResource("style.css").toExternalForm().toString();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Image Gallery");


        GridPane thumbnailGrid = createThumbnailGrid();


        fullImageView = new ImageView();
        fullImageView.setFitWidth(FULL_IMAGE_SIZE);
        fullImageView.setFitHeight(FULL_IMAGE_SIZE);


        back_Btn = new Button("Back to Thumbnails");
        back_Btn.setOnAction(e -> showThumbnailView());

        backBtn = new Button("Previous");
        backBtn.setOnAction(e -> showPreviousImage());
        nextBtn = new Button("Next");
        nextBtn.setOnAction(e -> showNextImage());
        HBox hBox44 =  new HBox(12);
        VBox vBox44 = new VBox();
        hBox44.getChildren().addAll(backBtn,nextBtn,back_Btn);
        hBox44.setId("btn");
        thumbnailGrid.setId("thumb");
        vBox44.getChildren().addAll(thumbnailGrid,fullImageView,hBox44);

        StackPane stackPane = new StackPane();
        stackPane.setId("stack");
        stackPane.getChildren().addAll(vBox44);

        Scene scene = new Scene(stackPane, 800, 600);
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createThumbnailGrid() {
        GridPane grid = new GridPane();

        Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("4.png")));

        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("5.png")));
        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("3.png")));



        ImageView thumbnail1 = createThumbnailImageView(image1);
        ImageView thumbnail2 = createThumbnailImageView(image2);
        ImageView thumbnail3 = createThumbnailImageView(image3);


        grid.add(thumbnail1, 0, 0);
        grid.add(thumbnail2, 1, 0);
        grid.add(thumbnail3, 2, 0);


        thumbnail1.setOnMouseClicked(e -> showFullImage(image1));
        thumbnail2.setOnMouseClicked(e -> showFullImage(image2));
        thumbnail3.setOnMouseClicked(e -> showFullImage(image3));

        return grid;
    }

    private ImageView createThumbnailImageView(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(THUMBNAIL_SIZE);
        imageView.setFitHeight(THUMBNAIL_SIZE);
        return imageView;
    }

    private void showFullImage(Image image) {
        fullImageView.setImage(image);
        fullImageView.setVisible(true);
        back_Btn.setVisible(true);
        backBtn.setVisible(true);
        nextBtn.setVisible(true);
    }
    private void showThumbnailView() {
        fullImageView.setVisible(false);
        back_Btn.setVisible(false);
    }

    private void showPreviousImage() {
        currentImageIndex = (currentImageIndex - 1 + fullSizeImages.length) % fullSizeImages.length;
        fullImageView.setImage(fullSizeImages[currentImageIndex]);
    }

    private void showNextImage() {
        currentImageIndex = (currentImageIndex + 1) % fullSizeImages.length;
        fullImageView.setImage(fullSizeImages[currentImageIndex]);
    }
}
