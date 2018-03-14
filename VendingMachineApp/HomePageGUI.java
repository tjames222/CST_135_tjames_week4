/*
 * Written by: Phillip Sorrell Radke
 * Written for: Bug Smasher's group
 * CST-135
 * This class is for the main GUI of the application. It is the first screen that the customer will be welcomed with.
 *
 * ADD THE ADMIN. HOME AND BACK BUTTONS 
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class HomePageGUI extends Application {

	//Create the dispenser. Generates 5 Products in an array within the dispenser class.
	private final Dispenser dispenser = new Dispenser();
	//Get background image and add it to the stack pane
	private final Image bgImage = new Image("file:src/DispenserDesign/Background&ButtonImages/Background.png");
	private final Button backButton = new Button("", new ImageView(
		  new Image("file:src/DispenserDesign/Background&ButtonImages/NavigationButtons/BackButton.png")));
        private final Button adminButton = new Button("", new ImageView(
		  new Image("file:src/DispenserDesign/Background&ButtonImages/NavigationButtons/Admin.png")));
        private final Button cartButton = new Button("", new ImageView(
                  new Image("file:src/DispenserDesign/Background&ButtonImages/NavigationButtons/Cart.png")));
        private final Button homeButton = new Button("", new ImageView(
		  new Image("file:src/DispenserDesign/Background&ButtonImages/NavigationButtons/HomeButton.png")));
        private final Image bgCartImage = new Image(
                  "file:src/DispenserDesign/Background&ButtonImages/FinalSalePage/FinalSaleBackground.png");
        private final Image bgThankImage = new Image(
                  "file:src/DispenserDesign/Background&ButtonImages/ThankYou.png");
	private StackPane backgroundPane = new StackPane();
	private Scene scene;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage = homePage(primaryStage);
		primaryStage.show();
	}
	
	//Display the home page
	private Stage homePage(Stage stage) {

		//IMAGE CREATION
		Image drinksCategoryImage = new Image (
			  "file:src/DispenserDesign/Background&ButtonImages/StarterPage/DrinksButton.png");

		Image gumCategoryImage = new Image (
			  "file:src/DispenserDesign/Background&ButtonImages/StarterPage/GumButton.png");

		Image sweetsCategoryImage = new Image (
			  "file:src/DispenserDesign/Background&ButtonImages/StarterPage/SweetsButton.png");
                
		//END IMAGE CREATION

		//Create the buttons with the image displayed on them.
		Button drinksCategoryButton = new Button(
			  "", new ImageView(drinksCategoryImage));
		drinksCategoryButton.setOnAction(e -> {
			drinksCategory(stage); });
                drinksCategoryButton.setBackground(Background.EMPTY);

		Button gumCategoryButton = new Button(
			  "", new ImageView(gumCategoryImage));
		gumCategoryButton.setOnAction(e -> {
			gumCategory(stage); });
                gumCategoryButton.setBackground(Background.EMPTY);

		Button sweetsCategoryButton = new Button(
			  "", new ImageView(sweetsCategoryImage));
		sweetsCategoryButton.setOnAction(e -> {
			sweetsCategory(stage); });
                sweetsCategoryButton.setBackground(Background.EMPTY);
                
		//END BUTTON CREATION

		//This is where the buttons for the three categories will sit.
		GridPane homePane = new GridPane();
		homePane.setVgap(10);
		//Max size is set because if not, the GridPane will not center in the StackPane
		homePane.setMaxSize(575, 293);

		//Add the buttons to the GridPane.
		homePane.addColumn(0, drinksCategoryButton, gumCategoryButton, sweetsCategoryButton);
                
                GridPane something = new GridPane();
                something.setHgap(10);
		something.setVgap(10);
		something.setPadding(new Insets(0, 10, 0, 10));
                something.add(adminButton, 66, 2);
                
                adminButton.setBackground(Background.EMPTY);

		backgroundPane.getChildren().addAll(new ImageView(bgImage), homePane, something);
		
		scene = new Scene(backgroundPane);
		stage.setTitle("Vending Machine");
		stage.setResizable(false);
		stage.setScene(scene);
		return stage;
	}
	
	//Displays the Drinks categories page.
	private void drinksCategory(Stage stage) {
		backgroundPane = new StackPane();
		
		GridPane something = new GridPane();
                something.setHgap(10);
		something.setVgap(10);
		something.setPadding(new Insets(0, 10, 0, 10));
                something.add(cartButton, 50, 1);
                something.add(homeButton, 4, 1);
                
		homeButton.setBackground(Background.EMPTY);
                homeButton.setOnAction(e -> {
			homePage(stage); });
                cartButton.setBackground(Background.EMPTY);
                cartButton.setOnAction(e -> {
                    try {
                        shoppingCart(stage);
                    } catch (Exception ex) {
                        Logger.getLogger(HomePageGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
		
		//Set Grid pane settings. 
		GridPane pane = new GridPane();
		pane.setVgap(10);
		pane.setHgap(10);
		pane.setMaxSize(575, 293);
		
		int rowControl = 0; //used to limit how many items are in a row.
		// search the array for objects of Drink type, then create and display their buttons. 
		// add event handler here for sales page
		for (int i = 0; i < dispenser.productsForSale.length; i++) {
			
			if (dispenser.getProduct(i) instanceof Drink) {
				
				Drink temp = (Drink)dispenser.getProduct(i);
				
				Button button = new Button("", new ImageView(temp.image));
                                button.setBackground(Background.EMPTY);
				
				Text text = new Text(temp.getName() + "\n$" + temp.getPrice());
				text.setFill(Color.WHITE);
				text.setTextAlignment(TextAlignment.CENTER);
                                text.setTranslateX(65);
                                text.setLayoutX(i);
				
				if (rowControl < 3) {
					pane.addRow(0, button);
					pane.addRow(1, text);
				} else {
					pane.addRow(2, button);
					pane.addRow(3, text);
				}
				rowControl++;
			}
		}
		
		backgroundPane.getChildren().addAll(new ImageView(bgImage), pane, something);
		scene = new Scene(backgroundPane);
		stage.setTitle("Drinks Category");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	public void gumCategory(Stage stage) {
		backgroundPane = new StackPane();
                
                GridPane buttons = new GridPane();
		buttons.setHgap(10);
		buttons.setVgap(10);
		buttons.setPadding(new Insets(0, 10, 0, 10));
                buttons.add(cartButton, 50, 1);
                buttons.add(homeButton, 4, 1);
                homeButton.setBackground(Background.EMPTY);
                homeButton.setOnAction(e -> {
			homePage(stage); });
                cartButton.setBackground(Background.EMPTY);
                cartButton.setOnAction(e -> {
                    try {
                        shoppingCart(stage);
                    } catch (Exception ex) {
                        Logger.getLogger(HomePageGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });        
                GridPane pane = new GridPane();
		pane.setVgap(10);
		pane.setHgap(10);
		pane.setMaxSize(575, 293);
		
		// search the array for objects of Gum type, then create and display their buttons. 
		// add event handler here for sales page
		for (int i = 0; i < dispenser.productsForSale.length; i++) {
			if (dispenser.getProduct(i) instanceof Gum) {
				Gum temp = (Gum)dispenser.getProduct(i);
				
				Button button = new Button("", new ImageView(temp.image));
                                button.setBackground(Background.EMPTY);
				
				Text text = new Text(temp.getName() + "\n" + temp.getFlavor() + "\n$" + temp.getPrice());
				text.setFill(Color.WHITE);
				text.setTextAlignment(TextAlignment.CENTER);
                                text.setTranslateX(65);
                                text.setLayoutX(i);
				pane.addRow(0, button);
				pane.addRow(1, text);
			}
		}
		
		backgroundPane.getChildren().addAll(new ImageView(bgImage), pane, buttons);
		scene = new Scene(backgroundPane);
		stage.setTitle("Gum Category");
		stage.setScene(scene);
		stage.show();
		
	}
	
	private void sweetsCategory(Stage stage) {
		backgroundPane = new StackPane();
                
                GridPane something = new GridPane();
                something.setHgap(10);
		something.setVgap(10);
		something.setPadding(new Insets(0, 10, 0, 10));
                something.add(cartButton, 50, 1);
                something.add(homeButton, 4, 1);
                
		homeButton.setBackground(Background.EMPTY);
                homeButton.setOnAction(e -> {
			homePage(stage); });
                cartButton.setBackground(Background.EMPTY);
                cartButton.setOnAction(e -> {
                    try {
                        shoppingCart(stage);
                    } catch (Exception ex) {
                        Logger.getLogger(HomePageGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
		
		GridPane pane = new GridPane();
		pane.setVgap(10);
		pane.setHgap(10);
		pane.setMaxSize(575, 293);
		
		int rowControl = 0; //used to limit how many items are in a row.
		
		// search the array for objects of Snack type, then create and display their buttons. 
		// add event handler here for sales page
		for (int i = 0; i < dispenser.productsForSale.length; i++) {
			
			if (dispenser.getProduct(i) instanceof Snack) {
				
				Snack temp = (Snack)dispenser.getProduct(i);
				
				Button button = new Button("", new ImageView(temp.image));
                                button.setBackground(Background.EMPTY);
				
				Text text = new Text(temp.getName() + "\n$" + temp.getPrice());
				text.setFill(Color.WHITE);
				text.setTextAlignment(TextAlignment.CENTER);
                                text.setTranslateX(65);
                                text.setLayoutX(i);
				
				if (rowControl < 3) {
					pane.addRow(0, button);
					pane.addRow(1, text);
				} else if (rowControl >= 3 && rowControl < 6) {
					pane.addRow(2, button);
					pane.addRow(3, text);
				} else if (rowControl >= 6 && rowControl < 9) {
					pane.addRow(4, button);
					pane.addRow(5, text);
				}
				rowControl++;
			}
		}
		
		backgroundPane.getChildren().addAll(new ImageView(bgImage), pane, something);
		scene = new Scene(backgroundPane);
		stage.setTitle("Sweets Category");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	public void shoppingCart(Stage stage) {
            
                backgroundPane = new StackPane();

		// PANE CREATION
  
                // Home and Back Buttons Pane
		GridPane something = new GridPane();
                something.setHgap(10);
		something.setVgap(10);
		something.setPadding(new Insets(0, 10, 0, 10));
                something.add(homeButton, 3, 1);
                
		homeButton.setBackground(Background.EMPTY);
                homeButton.setOnAction(e -> {
			homePage(stage); 
                });

                // TableView Cart Pane
		GridPane cartView = new GridPane();
		cartView.setHgap(9);
		cartView.setVgap(9);
		cartView.setOpacity(0.9);
		cartView.setPadding(new Insets(0, 10, 0, 10));
                
                // Delete and Purchase Buttons Pane
                GridPane buttons2 = new GridPane();
		buttons2.setHgap(10);
		buttons2.setVgap(10);
		buttons2.setPadding(new Insets(0, 10, 0, 10));
                                
                // Main Pane for arrangement
                VBox vBox = new VBox();
                vBox.setPadding(new Insets(10, 10, 10, 10));
                vBox.getChildren().addAll(something, cartView, buttons2);
                
		// BUTTON CREATION
                   
                // Purchase Button
		Button purchase = new Button("", new ImageView(
		  new Image("file:src/DispenserDesign/Background&ButtonImages/FinalSalePage/Purchase.png")));
		purchase.setBackground(Background.EMPTY);
                purchase.setMaxSize(5, 10);
                purchase.setMinSize(5, 10);
		buttons2.add(purchase, 45, 11);
		purchase.setBackground(Background.EMPTY);
                purchase.setOnAction(e -> {
			thankYou(stage); });
                
                // Delete Button
               // Button delete = new Button("Delete Product");
              //  delete.setOnAction(e -> deleteClicked());

		// CREATE TABLE FOR DISPLAYING CART
		TableView<Product> table;
                
		// Name column
		TableColumn<Product, String> nameCol = new TableColumn<>("Name");
		nameCol.setMinWidth(150);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

		// Price column
		TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
		priceCol.setMinWidth(20);
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
                
		// Weight column
		TableColumn<Product, Double> weightCol = new TableColumn<>("Weight");
		weightCol.setMinWidth(50);
		weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));

		// Quantity column
		TableColumn<Product, Double> quantityCol = new TableColumn<>("Quantity");
		quantityCol.setMinWidth(20);
		quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                
		table = new TableView<>();
		table.setItems(getProduct());
		table.setBackground(Background.EMPTY);
		table.setMaxSize(720, 343);
                table.setMinSize(720, 343);
		table.getColumns().addAll(nameCol, priceCol, weightCol, quantityCol);
		cartView.add(table, 10, 16);

		backgroundPane.getChildren().addAll(new ImageView(bgCartImage), vBox);
		scene = new Scene(backgroundPane);
		stage.setTitle("Checkout");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}
        
        // DELETE BUTTON CLICKED
        // public void deleteClicked() {
        //    ObservableList<Product> productSelected, allProducts;
        //    allProducts = table.getItems();
        //    productSelected = table.getSelectionMode1().getSelectedItems();
            
        //    productSelected.forEach(allProducts::remove);
       // }
        
        
        
	// Shopping cart info view
	public ObservableList<Product> getProduct() {
		ObservableList<Product> products = FXCollections.observableArrayList();
                // Add to cart button 
                Button addToCart = new Button();
		addToCart.setOnAction(e -> products.add(new Drink("Fanta", 1.75, 16, 1, null)));
                products.add(new Product("DP", 1.75, 16, 1, null) {});
		products.add(new Drink("Coke", 1.75, 16, 1, null));
                products.add(new Snack("Doritos", 1.75, 16, 1, null) {});
                products.add(new Drink("Dasani", 1.75, 16, 2, null));
                products.add(new Drink("Coke", 1.75, 16, 1, null));
                products.add(new ProductImpl("Lays", 1.50, 16, 1, null));
		return products;
	}   
        
        // Make a method that adds up price for total price 
        // totalPrice += (price * quantity);
        

	public static void main(String[] args) {
		launch(args);
	}

    private static class ProductImpl extends Product {

        public ProductImpl(String nameIn, double priceIn, int weightIn, int quantity, Image image) {
            super(nameIn, priceIn, weightIn, quantity, image);
        }
    }
    
    	private void thankYou(Stage stage) {
		backgroundPane = new StackPane();
                
                GridPane something = new GridPane();
                something.setHgap(10);
		something.setVgap(10);
		something.setPadding(new Insets(0, 10, 0, 10));
                something.add(homeButton, 4, 1);
                
		homeButton.setBackground(Background.EMPTY);
                homeButton.setOnAction(e -> {
			homePage(stage); });
                
                backgroundPane.getChildren().addAll(new ImageView(bgThankImage), something);
		scene = new Scene(backgroundPane);
		stage.setTitle("Checkout");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
        }

}
	
	
