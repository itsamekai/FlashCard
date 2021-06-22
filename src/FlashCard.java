import java.sql.*;
import java.util.ArrayList;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import java.util.Random;

public class FlashCard extends Application {
	
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "";
	private static final String JDBC_URL = "jdbc:mysql://localhost/flashcard"; //serverTimezone=UTC";
	
	//score buttons
	private Button score0 = new Button();
	private Button score1 = new Button();
	private Button score2 = new Button();
	private Button score3 = new Button();
	private Button score4 = new Button();
	private Button score5 = new Button();
	
	
	//button to return to game
	private Button returnToGame = new Button();
	
	//button to return to menu
	private Button returnToMenu = new Button();
	
	
	//main menu buttons
	private Button singlePlayer = new Button();	
	private Button multiPlayer = new Button();
	private Button EditDatabase = new Button();
	
	//edit DB menu buttons
	private Button goUpdate = new Button();
	private Button goDelete = new Button();
	private Button goInsert = new Button();
	private Button update = new Button();
	private Button delete = new Button();
	private Button insert = new Button();
	private Button returnEditMenu = new Button();
	private Button returnEditMenu2 = new Button();
	private Button returnEditMenu3 = new Button();
	private Button returnMainMenu = new Button();
	
	
	// main menu consisting of 3 buttons
	private VBox mainMenu = new VBox();
	
	//menu to edit database
	private VBox editPane = new VBox();
	private VBox deletePane = new VBox();
	private VBox insertPane = new VBox();
	private VBox updatePane = new VBox();
	
	//pane (container?)
	private StackPane pane1 = new StackPane();
	
	//pane for 2nd scene
	private StackPane pane2 = new StackPane();
	
	//pane for 3rd scene
	private StackPane pane3 = new StackPane();
	
	//main menu label
	private Label Introduction = new Label();
	
	//edit DB labels
	private Label insertDesc = new Label();
	private Label updateDesc = new Label();
	private Label updateDesc2 = new Label();
	private Label deleteDesc = new Label();
	private Label addinfo = new Label();
	private Label updateinfo = new Label();
	private Label deleteinfo = new Label();
	
	//edit DB textfields
	private TextField insertField = new TextField();
	private TextField updateField = new TextField();
	private TextField updateField2 = new TextField();
	private TextField deleteField = new TextField();
	
	//word label (for question)
	private Label qnWord = new Label();
	
	//word label for question counter
	private Label questionNo = new Label();
	
	//word label for score
	private Label mainScore = new Label();
	
	//word label for final score
	private Label finalScore = new Label();
	
	//multiplayer
	private Button startMM = new Button();
	private Button playerNotiButton = new Button();
	private Button nextPlayer = new Button();
	private Button ContinueP2 = new Button();
	private Button gameEnd = new Button();
	private VBox playerNotiPane = new VBox();
	private VBox mmPane = new VBox();
	private VBox midMMPane= new VBox();
	private VBox endMMPane = new VBox();
	
	//private StackPane mmEndPane = new StackPane();
	private Scene mmScene;
	private Scene playerNotiScene;
	private Scene mmChangeScene;
	private Scene mmEndScene;
	private Label p1namedesc = new Label();
	private Label p2namedesc = new Label();
	private Label playerNoti = new Label();
	private Label middleSceneNoti = new Label();
	private Label p1score = new Label();
	private Label p2score = new Label();
	private Label gameEndText = new Label();
	private TextField p1text = new TextField();
	private TextField p2text = new TextField();
	private boolean mmActivated = false;
	private boolean p1turn = false;
	private boolean p2turn = false;
	private boolean mmEnd = false;
	private int p1scorecount = 0;
	private int p2scorecount = 0;
	private int counter = 0;
	private String name1 = "";
	private String name2 = "";
	
	
	
	//
	
	//question number
	private int number = 1;
	
	//score number
	private int score = 0;
	
	//final score number
	//private int fScore = 0;
	
	//score increment
	private int scoreIncrement = 0;
	
	//checks the arraylist
	private ArrayList<String> checker = new ArrayList<String>();
	
	//random generator
	int random = 0;
	
	//main menu scene for button display
	private Scene menuScene;
	
	//edit DB scene
	private Scene editScene;
	private Scene deleteScene;
	private Scene insertScene;
	private Scene updateScene;
	
	//first scene for general game GUI
	private Scene scene1;
	
	// second scene for encouraging words after each button click
	private Scene scene2;
	
	// third scene for final score etc
	private Scene scene3;
	


	
	// encouraging words after each questions
	private Label Encourage = new Label();
	
	// encouraging words at end screen
	private Label finalEncourage = new Label();
	
	// button to restart the game.
	private Button restartGame = new Button();
	
	
	
	
	
	public static void main(String[] args) {
	
		launch(args);
	
	}

	@Override
	public void start(Stage primaryStage) {
		
		//multiplayer menu scene
		
		mmPane.getChildren().addAll(p1namedesc, p1text, p2namedesc, p2text, startMM);
		mmPane.setSpacing(10);
		mmPane.setAlignment(Pos.CENTER);
		p1text.setMaxSize(200, 100);
		p2text.setMaxSize(200, 100);
		p1namedesc.setText("Player 1 Name");
		p2namedesc.setText("Player 2 Name");
		startMM.setText("Start Game");
		pane1.getChildren().addAll(p1score, p2score);
		p1score.setFont(new Font("Georgia", 15));
		p2score.setFont(new Font("Georgia", 15));
		//p1score.setTranslateX(-220);
		StackPane.setAlignment(p2score, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(p1score, Pos.CENTER_LEFT);
		p1score.setTranslateY(170);
		midMMPane.setAlignment(Pos.CENTER);
		midMMPane.setSpacing(10);
		ContinueP2.setText("Continue");
		middleSceneNoti.setFont(new Font("Courier New 8", 12));
		midMMPane.getChildren().addAll(middleSceneNoti,ContinueP2);
		
		endMMPane.setAlignment(Pos.CENTER);
		endMMPane.getChildren().addAll(gameEndText, gameEnd);
		endMMPane.setSpacing(10);
		gameEndText.setFont(new Font("Courier New 8",  15));
		gameEnd.setFont(new Font("Courier New 8", 12));
		gameEnd.setText("Return to Main Menu");
		gameEnd.setMaxSize(150, 30);
		
		
		// next player
		nextPlayer.setMinSize(100, 45);
		nextPlayer.setMaxSize(100,45);
		StackPane.setAlignment(nextPlayer, Pos.BOTTOM_CENTER);
		nextPlayer.setFont(new Font("Georgia", 15));
		
		//scene to display which player goes first
		playerNotiPane.getChildren().addAll(playerNoti, playerNotiButton);
		playerNotiButton.setText("Start Game");
		playerNotiPane.setAlignment(Pos.CENTER);
		playerNotiPane.setSpacing(10);
//		playerNoti.setAlignment(Pos.CENTER);
//		playerNotiButton.setAlignment(Pos.BOTTOM_CENTER);
		
		playerNotiButton.setOnAction(event -> {
			primaryStage.setScene(scene1);
		});
		 
		startMM.setOnAction(event -> {
			mmActivated = true;	
			p1turn = true;
			name1 = p1text.getText();
			name2 = p2text.getText();
			playerNoti.setFont(new Font("Arial", 15));
			playerNotiButton.setMaxSize(100, 20);
			playerNoti.setText(name1 + " plays first!");
			p1score.setText(name1 + ": " + p1scorecount + "/25");
			p2score.setText(name2 + ": " + p2scorecount + "/25");
			primaryStage.setScene(playerNotiScene);
			
			//check
//			System.out.println("mmActivated: " + mmActivated);
//			System.out.println("p1turn: " + p1turn);
//			System.out.println("p2turn: " + p2turn);
//			System.out.println("mmEnd: " + mmEnd);
//			System.out.println("p1 score: " + p1scorecount);
//			System.out.println("p2 score: " +p2scorecount);
//			System.out.println("counter: " +counter);
//			System.out.println("name 1: " + name1);
//			System.out.println("name 2: " + name2);
//			System.out.println("number: " + number);
		});
		
		gameEnd.setOnAction(event -> {
			mmActivated = false;
			p1turn = false;
			p2turn = false;
			//mmEnd = false;
			p1scorecount = 0;
			p2scorecount = 0;
			counter = 0;
			number = 1;
			name1 = "";
			name2 = "";
			questionNo.setText("Question " + number + ":");
			primaryStage.setScene(menuScene);
		});
		
		//question number, top left
		questionNo.setText("Question " + number + ":");
		questionNo.setFont(new Font("Georgia", 15));
		StackPane.setAlignment(questionNo, Pos.TOP_LEFT);
		pane1.getChildren().add(questionNo);
		
		//Score, bottom right
		
		//mainScore.setText("Score: " + score + "/25");
		mainScore.setFont(new Font("Georgia", 15));
		StackPane.setAlignment(mainScore, Pos.BOTTOM_LEFT);
		pane1.getChildren().add(mainScore);
		
		qnWord.setText(getQuestion());
		qnWord.setFont(new Font("Georgia", 50));
		StackPane.setAlignment(qnWord, Pos.CENTER);
		pane1.getChildren().add(qnWord);
		
		//all scene resolutions
	
		menuScene = new Scene(mainMenu, 400, 300);
		mmChangeScene = new Scene(midMMPane,250, 250);
		mmEndScene = new Scene(endMMPane, 400, 200);
		mmScene = new Scene(mmPane, 300,300);
		playerNotiScene = new Scene(playerNotiPane, 250, 250);
		editScene = new Scene(editPane, 300, 300);
		deleteScene = new Scene(deletePane, 200, 200);
		insertScene = new Scene(insertPane, 200, 200);
		updateScene = new Scene(updatePane, 200,200);
		scene1 = new Scene(pane1, 500, 400);
		scene2 = new Scene(pane2, 400, 300);
		scene3 = new Scene(pane3, 400, 300);
		
		
		//setting main menu GUI
		Introduction.setText("Welcome to Flash Card Game! Pick an option.");
		singlePlayer.setText("Single Player");
		singlePlayer.setMaxSize(150, 20);
		multiPlayer.setText("Multi Player");
		multiPlayer.setMaxSize(150, 20);
		EditDatabase.setText("Edit Questions");
		EditDatabase.setMaxSize(150, 20);
		
		mainMenu.setPadding(new Insets(5,5,5,5));
		mainMenu.setSpacing(10);
		mainMenu.setAlignment(Pos.CENTER);
		mainMenu.getChildren().addAll(Introduction, singlePlayer, multiPlayer, EditDatabase);
		
		
		
		multiPlayer.setOnAction(event -> {
			primaryStage.setScene(mmScene);
		});
		
		//button configuration for main menu GUI
		singlePlayer.setOnAction(event ->{
			p1score.setText(null);
			p2score.setText(null);
			mainScore.setText("Score: " + score + "/25");
			primaryStage.setScene(scene1);
		});
		
		ContinueP2.setOnAction(event -> {
//			System.out.println(p1scorecount);
			primaryStage.setScene(scene1);
			number = 1;
			questionNo.setText("Question " + number + ":");
			p1score.setText(name1 + ": " + p1scorecount + "/25");
			p1turn = false;
			p2turn = true;
			number = 1;
			scoreIncrement = 0;
			checker.clear();
			qnWord.setText(getQuestion());
		});
		
		//multiplayer switch scene
		
		//edit questions switch scene
		EditDatabase.setOnAction(event ->{
			primaryStage.setScene(editScene);
		});
		
		
		//buttons related
		score0.setText("0");
		score0.setShape(new Circle(15));
		score0.setMinSize(35, 35);
		score0.setMaxSize(35, 35);
		score0.setTranslateX(-120);
		score0.setTranslateY(100);
		
		score1.setText("1");
		score1.setShape(new Circle(15));
		score1.setMinSize(35, 35);
		score1.setMaxSize(35, 35);
		score1.setTranslateX(-70);
		score1.setTranslateY(100);
		
		score2.setText("2");
		score2.setShape(new Circle(15));
		score2.setMinSize(35, 35);
		score2.setMaxSize(35, 35);
		score2.setTranslateX(-20);
		score2.setTranslateY(100);
		
		score3.setText("3");
		score3.setShape(new Circle(15));
		score3.setMinSize(35, 35);
		score3.setMaxSize(35, 35);
		score3.setTranslateX(30);
		score3.setTranslateY(100);
		
		score4.setText("4");
		score4.setShape(new Circle(15));
		score4.setMinSize(35, 35);
		score4.setMaxSize(35, 35);
		score4.setTranslateX(80);
		score4.setTranslateY(100);
		
		score5.setText("5");
		score5.setShape(new Circle(15));
		score5.setMinSize(35, 35);
		score5.setMaxSize(35, 35);
		score5.setTranslateX(130);
		score5.setTranslateY(100);
		
		pane1.getChildren().addAll(score0,score1,score2,score3,score4,score5);
		//for encouragement GUI after each question
		//scene 2
		StackPane.setAlignment(returnToGame, Pos.BOTTOM_CENTER);
		returnToGame.setMinSize(100, 45);
		returnToGame.setMaxSize(100, 45);
		returnToGame.setText("Next Question");
		restartGame.setFont(new Font("Georgia", 15));
		StackPane.setAlignment(Encourage, Pos.CENTER);
		Encourage.setFont(new Font("Georgia", 20));
		pane2.getChildren().addAll(Encourage,returnToGame);
		
		// for scene3, final display of score + restart button
		pane3.getChildren().addAll(finalEncourage, finalScore, restartGame, returnToMenu);
		StackPane.setAlignment(finalEncourage, Pos.CENTER);
		StackPane.setAlignment(finalScore, Pos.BOTTOM_LEFT);
		finalScore.setFont(new Font("Georgia",15));
		finalEncourage.setFont(new Font("Georgia", 20));
		
		//restart button
		restartGame.setText("Play Again!");
		restartGame.setMinSize(100, 45);
		restartGame.setMaxSize(100,45);
		restartGame.setTranslateX(-20);
		returnToMenu.setFont(new Font("Georgia", 15));
		returnToMenu.setMinSize(140,45);
		returnToMenu.setMaxSize(140, 45);
		returnToMenu.setTranslateX(110);
		returnToMenu.setText("Return to Menu");
		StackPane.setAlignment(returnToMenu, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(restartGame, Pos.BOTTOM_CENTER);
		restartGame.setFont(new Font("Georgia", 15));
		
		
		
		//button action for button 0
		score0.setOnAction(event -> {
			if (mmActivated == true && p1turn == true) {
				p1scorecount += Integer.valueOf(score0.getText());
			}
			else if (mmActivated == true && p2turn == true) {
				p2scorecount += Integer.valueOf(score0.getText());
			}
			
			else if (mmActivated == false && (p1turn == false || p2turn == false)) {
				score += Integer.valueOf(score0.getText());
			}
			
				scoreIncrement = Integer.valueOf(score0.getText());
				
			if (number != 5) {
			
				number ++;
				increment();
				scoreEncouragement();
				primaryStage.setScene(scene2);
				qnWord.setText(getQuestion());
			
		}
			else {
				
				if (mmActivated == true && counter == 0) {
					middleSceneNoti.setText(String.format("%s has scored %d/25!\n%s's turn now!", name1, p1scorecount, name2));
					primaryStage.setScene(mmChangeScene);
					counter++;
				}
				else if(mmActivated == true && counter == 1) {
					if (p1scorecount > p2scorecount) {
						gameEndText.setText(String.format("%s has won the game!\nExcellent job!", name1));
					}
					else if (p2scorecount > p1scorecount) {
						gameEndText.setText(String.format("%s has won the game!\nExcellent job!", name2));
					}
					else {
						gameEndText.setText("Both of you have tied! Well done both of you!");
					}
					primaryStage.setScene(mmEndScene);
				}
				else {
					finalScore.setText("Final Score: " + score + "/25");
					finalScoreWords();
					primaryStage.setScene(scene3);
				}
				
//				System.out.println(p1scorecount);
//				System.out.println("p2: " + p2scorecount);
			}
		});
		
		//button action for button 1
		score1.setOnAction(event -> {
			if (mmActivated == true && p1turn == true) {
				p1scorecount += Integer.valueOf(score1.getText());
			}
			else if (mmActivated == true && p2turn == true) {
				p2scorecount += Integer.valueOf(score1.getText());
			}
			
			else if (mmActivated == false && (p1turn == false || p2turn == false)) {
				score += Integer.valueOf(score1.getText());
			}
			
				scoreIncrement = Integer.valueOf(score1.getText());
				
			if (number != 5) {
			
				number ++;
				increment();
				scoreEncouragement();
				primaryStage.setScene(scene2);
				qnWord.setText(getQuestion());
			
		}
			else {
				
				if (mmActivated == true && counter == 0) {
					middleSceneNoti.setText(String.format("%s has scored %d/25!\n%s's turn now!", name1, p1scorecount, name2));
					primaryStage.setScene(mmChangeScene);
					counter++;
				}
				else if(mmActivated == true && counter == 1) {
					if (p1scorecount > p2scorecount) {
						gameEndText.setText(String.format("%s has won the game!\nExcellent job!", name1));
					}
					else if (p2scorecount > p1scorecount) {
						gameEndText.setText(String.format("%s has won the game!\nExcellent job!", name2));
					}
					else {
						gameEndText.setText("Both of you have tied! Well done both of you!");
					}
					primaryStage.setScene(mmEndScene);
				}
				else {
					finalScore.setText("Final Score: " + score + "/25");
					finalScoreWords();
					primaryStage.setScene(scene3);
				}
				
//				System.out.println(p1scorecount);
//				System.out.println("p2: " + p2scorecount);
			}
		});
		
		
		//button action for button 2
		score2.setOnAction(event -> {
			if (mmActivated == true && p1turn == true) {
				p1scorecount += Integer.valueOf(score2.getText());
			}
			else if (mmActivated == true && p2turn == true) {
				p2scorecount += Integer.valueOf(score2.getText());
			}
			
			else if (mmActivated == false && (p1turn == false || p2turn == false)) {
				score += Integer.valueOf(score2.getText());
			}
			
				scoreIncrement = Integer.valueOf(score2.getText());
				
			if (number != 5) {
			
				number ++;
				increment();
				scoreEncouragement();
				primaryStage.setScene(scene2);
				qnWord.setText(getQuestion());
			
		}
			else {
				
				if (mmActivated == true && counter == 0) {
					middleSceneNoti.setText(String.format("%s has scored %d/25!\n%s's turn now!", name1, p1scorecount, name2));
					primaryStage.setScene(mmChangeScene);
					counter++;
				}
				else if(mmActivated == true && counter == 1) {
					if (p1scorecount > p2scorecount) {
						gameEndText.setText(String.format("%s has won the game!\nExcellent job!", name1));
					}
					else if (p2scorecount > p1scorecount) {
						gameEndText.setText(String.format("%s has won the game!\nExcellent job!", name2));
					}
					else {
						gameEndText.setText("Both of you have tied! Well done both of you!");
					}
					primaryStage.setScene(mmEndScene);
				}
				else {
					finalScore.setText("Final Score: " + score + "/25");
					finalScoreWords();
					primaryStage.setScene(scene3);
				}
				
//				System.out.println(p1scorecount);
//				System.out.println("p2: " + p2scorecount);
			}
		});
		
		
		//button action for button 3
		score3.setOnAction(event -> {
			if (mmActivated == true && p1turn == true) {
				p1scorecount += Integer.valueOf(score3.getText());
			}
			else if (mmActivated == true && p2turn == true) {
				p2scorecount += Integer.valueOf(score3.getText());
			}
			
			else if (mmActivated == false && (p1turn == false || p2turn == false)) {
				score += Integer.valueOf(score3.getText());
			}
			
				scoreIncrement = Integer.valueOf(score3.getText());
				
			if (number != 5) {
			
				number ++;
				increment();
				scoreEncouragement();
				primaryStage.setScene(scene2);
				qnWord.setText(getQuestion());
			
		}
			else {
				
				if (mmActivated == true && counter == 0) {
					middleSceneNoti.setText(String.format("%s has scored %d/25!\n%s's turn now!", name1, p1scorecount, name2));
					primaryStage.setScene(mmChangeScene);
					counter++;
				}
				else if(mmActivated == true && counter == 1) {
					if (p1scorecount > p2scorecount) {
						gameEndText.setText(String.format("%s has won the game!\nExcellent job!", name1));
					}
					else if (p2scorecount > p1scorecount) {
						gameEndText.setText(String.format("%s has won the game!\nExcellent job!", name2));
					}
					else {
						gameEndText.setText("Both of you have tied! Well done both of you!");
					}
					primaryStage.setScene(mmEndScene);
				}
				else {
					finalScore.setText("Final Score: " + score + "/25");
					finalScoreWords();
					primaryStage.setScene(scene3);
				}
				
//				System.out.println(p1scorecount);
//				System.out.println("p2: " + p2scorecount);
			}
		});
		
		
		//button action for button 4
		score4.setOnAction(event -> {
			if (mmActivated == true && p1turn == true) {
				p1scorecount += Integer.valueOf(score4.getText());
			}
			else if (mmActivated == true && p2turn == true) {
				p2scorecount += Integer.valueOf(score4.getText());
			}
			
			else if (mmActivated == false && (p1turn == false || p2turn == false)) {
				score += Integer.valueOf(score4.getText());
			}
			
				scoreIncrement = Integer.valueOf(score4.getText());
				
			if (number != 5) {
			
				number ++;
				increment();
				scoreEncouragement();
				primaryStage.setScene(scene2);
				qnWord.setText(getQuestion());
			
		}
			else {
				
				if (mmActivated == true && counter == 0) {
					middleSceneNoti.setText(String.format("%s has scored %d/25!\n%s's turn now!", name1, p1scorecount, name2));
					primaryStage.setScene(mmChangeScene);
					counter++;
				}
				else if(mmActivated == true && counter == 1) {
					if (p1scorecount > p2scorecount) {
						gameEndText.setText(String.format("%s has won the game!\nExcellent job!", name1));
					}
					else if (p2scorecount > p1scorecount) {
						gameEndText.setText(String.format("%s has won the game!\nExcellent job!", name2));
					}
					else {
						gameEndText.setText("Both of you have tied! Well done both of you!");
					}
					primaryStage.setScene(mmEndScene);
				}
				else {
					finalScore.setText("Final Score: " + score + "/25");
					finalScoreWords();
					primaryStage.setScene(scene3);
				}
				
//				System.out.println(p1scorecount);
//				System.out.println("p2: " + p2scorecount);
			}
		});
		
		//button action for button 5
		score5.setOnAction(event -> {
			if (mmActivated == true && p1turn == true) {
				p1scorecount += Integer.valueOf(score5.getText());
			}
			else if (mmActivated == true && p2turn == true) {
				p2scorecount += Integer.valueOf(score5.getText());
			}
			
			else if (mmActivated == false && (p1turn == false || p2turn == false)) {
				score += Integer.valueOf(score5.getText());
			}
			
				scoreIncrement = Integer.valueOf(score5.getText());
				
			if (number != 5) {
			
				number ++;
				increment();
				scoreEncouragement();
				primaryStage.setScene(scene2);
				qnWord.setText(getQuestion());
			
		}
			else {
				
				if (mmActivated == true && counter == 0) {
					middleSceneNoti.setText(String.format("%s has scored %d/25!\n%s's turn now!", name1, p1scorecount, name2));
					primaryStage.setScene(mmChangeScene);
					counter++;
				}
				else if(mmActivated == true && counter == 1) {
					if (p1scorecount > p2scorecount) {
						gameEndText.setText(String.format("%s has won the game!\nExcellent job!", name1));
					}
					else if (p2scorecount > p1scorecount) {
						gameEndText.setText(String.format("%s has won the game!\nExcellent job!", name2));
					}
					else {
						gameEndText.setText("Both of you have tied! Well done both of you!");
					}
					primaryStage.setScene(mmEndScene);
				}
				else {
					finalScore.setText("Final Score: " + score + "/25");
					finalScoreWords();
					primaryStage.setScene(scene3);
				}
//				
//				System.out.println(p1scorecount);
//				System.out.println("p2: " + p2scorecount);
			}
		});
		
		//button action to change scene after each question
		//score button pressed > new GUI > button in encouraging GUI
		returnToGame.setOnAction(e -> {
			primaryStage.setScene(scene1);
		});
		
		//button that restarts the gmae.
		//clears out the array (to reset all duplications)
		//clears and default everything back to square 1
		
		restartGame.setOnAction(e -> {
			primaryStage.setScene(scene1);
			mainScore.setText("Score: " + score + "/25");
			number = 1;
			score = 0;
			scoreIncrement = 0;
			checker.clear();
			increment();
			qnWord.setText(getQuestion());
			
			//to check
			//System.out.println(String.format("number: %d , score: %d, scoreIncrement: %d, array size: %d", number, score, scoreIncrement, checker.size()));
		});
		
		returnToMenu.setOnAction(e -> {
			primaryStage.setScene(menuScene);
			number = 1;
			score = 0;
			scoreIncrement = 0;
			checker.clear();
			mainScore.setText(null);
			questionNo.setText("Question " + number + ":");
			
		});
		
		//GUI to perform edit for database
		
		goUpdate.setText("Update Question");
		goUpdate.setMaxSize(150, 30);
		goDelete.setText("Delete a Question");
		goDelete.setMaxSize(150, 30);
		goInsert.setText("Add a Question");
		goInsert.setMaxSize(150, 30);
		returnMainMenu.setText("Return to Main Menu");
		returnMainMenu.setMaxSize(150,30);
		editPane.setSpacing(10);
		editPane.setPadding(new Insets(10,10,10,10));
		editPane.setAlignment(Pos.CENTER);
		editPane.getChildren().addAll(goInsert, goDelete, goUpdate, returnMainMenu);
		
		
		insertDesc.setText("Enter Word to Add");
		insert.setText("Insert!");
		returnEditMenu.setText("Return to Edit Menu");
		insertPane.setSpacing(5);
		insertPane.setPadding(new Insets(10,10,10,10));
		insertPane.setAlignment(Pos.CENTER);
		addinfo.setMaxSize(200, 20);
		addinfo.setAlignment(Pos.CENTER);
		insertPane.getChildren().addAll(insertDesc, insertField, insert, addinfo, returnEditMenu);
		
		updateDesc.setText("Enter word to Update: ");
		updateDesc2.setText("Update the word to: ");
		returnEditMenu2.setText("Return to Edit Menu");
		update.setText("Update!");
		updatePane.setSpacing(5);
		updatePane.setPadding(new Insets(10,10,10,10));
		updatePane.setAlignment(Pos.CENTER);
		updateinfo.setMaxSize(200, 20);
		updateinfo.setAlignment(Pos.CENTER);
		updatePane.getChildren().addAll(updateDesc, updateField, updateDesc2, updateField2, update, updateinfo,returnEditMenu2);
		
		//delete to do
		deleteDesc.setText("Enter Word to Delete");
		delete.setText("Delete!");
		returnEditMenu3.setText("Return to Edit Menu");
		deleteinfo.setMaxSize(200, 20);
		deleteinfo.setAlignment(Pos.CENTER);
		deletePane.setSpacing(5);
		deletePane.setPadding(new Insets(10,10,10,10));
		deletePane.setAlignment(Pos.CENTER);
		deletePane.getChildren().addAll(deleteDesc, deleteField, delete, deleteinfo, returnEditMenu3);
		
		
		
		goUpdate.setOnAction(event ->{
			primaryStage.setScene(updateScene);
		});
		
		goDelete.setOnAction(event ->{
		primaryStage.setScene(deleteScene);
		});
		
		goInsert.setOnAction(event ->{
			primaryStage.setScene(insertScene);
		});
		
		returnMainMenu.setOnAction(event ->{
			primaryStage.setScene(menuScene);
		});
		
		returnEditMenu.setOnAction(event ->{
			primaryStage.setScene(editScene);
		});
		
		returnEditMenu2.setOnAction(event ->{
			primaryStage.setScene(editScene);
		});
		
		returnEditMenu3.setOnAction(event ->{
			primaryStage.setScene(editScene);
		});
		
		EventHandler<ActionEvent> handleInsert = (ActionEvent e) -> InsertWord();
		insert.setOnAction(handleInsert);
		
		EventHandler<ActionEvent> handleUpdate = (ActionEvent e) -> UpdateWord();
		update.setOnAction(handleUpdate);
		
		EventHandler<ActionEvent> handleDelete = (ActionEvent e) -> DeleteWord();
		delete.setOnAction(handleDelete);
		
		
		
		
		
		//border size, 500x400, title Flash Card
		primaryStage.setTitle("Flash Card");
		primaryStage.setScene(menuScene);
		primaryStage.show();
	
		

		
	}
	
	public void finalScoreWords() {
		
		
		if (score <= 10) {
			finalEncourage.setText("You are getting there!\nClick below to play again!");
		}
		else if (score >=11 && score <=20) {
			finalEncourage.setText("Well done! You are doing really well!\nClick below to play again!");
		}
		else {
			finalEncourage.setText("Excellent Job!\nClick below to play again!");
		}
		
		
	
	}
	
	public void scoreEncouragement() {
		if (scoreIncrement <= 1) {
			Encourage.setText("You can do better!");
	}
		else if (scoreIncrement <=3 && scoreIncrement >=2) {
			Encourage.setText("Excellent!");
		}
		else {
			Encourage.setText("Bravo! Bravo!");
		}
		
	}
	
	//calculates the score, increments the question number
	
	public void increment() {
		questionNo.setText("Question " + number + ":");
		if (p1turn == true) {
			p1score.setText(name1 + ": " + p1scorecount + "/25");			
		}
		else if (p2turn == true) {
			p2score.setText(name2 + ": " + p2scorecount + "/25");	
		}
		else {
			mainScore.setText("Score: " + score + "/25");
		}

	}
	
	

	
	
	/*
	 * public int getRandom() {
	 * 
	 * int output = 0; try { DBUtil.init(JDBC_URL, DB_USERNAME, DB_PASSWORD);
	 * 
	 * String sql = "SELECT FLOOR(RAND()*(25-1+1))+1"; ResultSet rs =
	 * DBUtil.getTable(sql);
	 * 
	 * if (rs.next()) { output = rs.getInt("FLOOR(RAND()*(25-1+1))+1"); } } catch
	 * (SQLException se) { se.printStackTrace(); }
	 * 
	 * DBUtil.close(); return output;
	 * 
	 * 
	 * }
	 */
	
	//appends the randomnumber generated to the arraylist checker.
	//if the boolean is true, it returns true and then continues to duplicate
	//
	public boolean check(ArrayList<String> checker, String duplicate) {
		boolean dupe = false;
		
			for (String d : checker) {
				if (d.equals(duplicate)) {
					dupe = true;
				}
			}
			// no duplicate. we add it to the array. 
			//numbers in the arrays are numbers used already (aka duplicates)
			//the numbers are used to check for duplicates
			if (dupe == false) {
				checker.add(duplicate);
			}
			return dupe;
		
	}


	//checks if needed to regenerate a word
	public String getQuestion() {
		
		String Word = "";
		boolean ifTrue = true;
		
		while(ifTrue != false) {
			Word = getWord();
			//System.out.println(checker.size());
		
			if (check(checker, Word) == false) {
				//System.out.println(checker.size());
				ifTrue = false;
				break;
			}
			else if (check(checker,Word) == true) {
				
				//check
				//System.out.println("duplicate word: " + Word);
			}
		}
		

		return Word;
}
	
	public String getWord() {
		String Word = "";
		try {
			DBUtil.init(JDBC_URL, DB_USERNAME, DB_PASSWORD);
			String sql = "SELECT question_name FROM question ORDER BY RAND() LIMIT 1";
			ResultSet rs = DBUtil.getTable(sql);
			if (rs.next()) {
				Word = rs.getString("question_name");	 
				//System.out.println(Word);	
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}
		DBUtil.close();
		return Word;
	}

	public void InsertWord() {
		
		String newWord = insertField.getText().toUpperCase();
			
			DBUtil.init(JDBC_URL, DB_USERNAME, DB_PASSWORD);
			String sql = "INSERT INTO question (question_name) " + "VALUES ('" + newWord + "')";
			
			int rowsAffected = DBUtil.execSQL(sql);
			if (rowsAffected == 1) {
				addinfo.setText(newWord + " successfully added!");
				//System.out.println("yes!");
			}
			else {
				addinfo.setText("Error occured in adding.");
			}
			
			DBUtil.close();
			
	
	}
	
	public void DeleteWord() {
		
		String word = deleteField.getText().toUpperCase();
		
		DBUtil.init(JDBC_URL, DB_USERNAME, DB_PASSWORD);
		String sql = "DELETE FROM question WHERE question_name = '" + word + "'";
		int rowsAffected = DBUtil.execSQL(sql);
		if (rowsAffected == 1) {
			deleteinfo.setText(word + " has been deleted!");
		}
		else {
			deleteinfo.setText("Failed to delete word.");
		}
		
		DBUtil.close();
		
	}
	
	public void UpdateWord() {
		
		String oldWord = updateField.getText().toUpperCase();
		String newWord = updateField2.getText().toUpperCase();
		
		DBUtil.init(JDBC_URL, DB_USERNAME, DB_PASSWORD);
		String sql = "UPDATE question SET question_name ='" + newWord + "' WHERE question_name ='" + oldWord + "'";
		int rowsAffected = DBUtil.execSQL(sql);
		if (rowsAffected == 1) {
			updateinfo.setText("Word successfully updated!");
		}
		else {
			updateinfo.setText("Update failed.");
		}
		
		DBUtil.close();
	
	}
}

