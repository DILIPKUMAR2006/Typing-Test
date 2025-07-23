import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.util.Duration;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;

public class MSSTVD extends Application 
{
	int timeSeconds = 60;
	int taindex = 0;
	int correct = 0;
	int wrong = 0;
	int typed=0;
	int length = 0;
	int flag = 0;
    public void start(Stage stage) 
	{
        Label label = new Label("TYPING TEST");
		Font font = new Font("Times New Roman",25);
		label.setFont(font);
        label.setTextFill(Color.WHITE);
        label.setStyle("-fx-background-color: #007ACC; -fx-padding: 10px;");
		
		Label funny = new Label("Strat the Typing Test !!!");
		funny.setTextFill(Color.BLACK);
		funny.setFont(new Font("Times New Roman",20));
		
		HBox hb1 = new HBox(label);
		hb1.setAlignment(Pos.CENTER); 
        hb1.setStyle("-fx-background-color: #007ACC;");
		
		TextArea textArea = new TextArea("My favorite person is someone I truly admire for their calm nature and positive attitude. They always inspire others through their Kindness ,Caring and Words. Their ability to handle challenges with confidence and guide people with care makes them very special to me. I will learned many life lessons just by observing them. They balance everything in life so well and always stay humble. Whenever I feel low, I just thinking about them gives me strength and keeps me moving forward and Why not ? Everyone's life have atleast one favorite person who they can change our life !!!");
		textArea.setPrefSize(400, 300);
		textArea.setPromptText("Start typing here...");
        textArea.setFont(new Font("Georgia", 30));
        //textArea.setPromptText("Start typing here...");
		textArea.setWrapText(true);
		textArea.setEditable(false);
		textArea.setStyle("-fx-control-inner-background: #E6F0FF;"); 
		
		VBox vb1 = new VBox(textArea);
		vb1.setPadding(new Insets(30));
		
		Label l1 = new Label("1:00");
		l1.setTextFill(Color.BLUE);
		l1.setFont(new Font(20));
		
		Button b1 = new Button("Start");
		b1.setPrefWidth(140);
        b1.setPrefHeight(40);
        b1.setStyle("-fx-background-color: White; -fx-text-fill: blue; -fx-font-size: 20px; -fx-border-color:blue");
		
		HBox hh1 = new HBox(b1);
		hh1.setAlignment(Pos.CENTER);
		hh1.setPadding(new Insets(20));

		
		HBox hh2 = new HBox(l1);
		hh2.setAlignment(Pos.CENTER);
		hh2.setPadding(new Insets(20));

		
		Label l2 = new Label("Accuracy : ");
		l2.setFont(new Font("Times New Roman",25)); 
		l2.setTextFill(Color.BLUE);
		Label lab2 = new Label("  -  ");
		lab2.setStyle("-fx-border-color: blue");
		
		HBox hbl1 = new HBox();
		hbl1.getChildren().addAll(l2,lab2);
		hbl1.setAlignment(Pos.CENTER);
		hbl1.setPadding(new Insets(20));
		
		Label l3 = new Label("WPM : ");
		l3.setFont(new Font("Times New Roman",25)); 
		l3.setTextFill(Color.BLUE);
		Label lab3 = new Label("  -  ");
		lab3.setStyle("-fx-border-color: blue");
		
		HBox hbl2 = new HBox();
		hbl2.getChildren().addAll(l3,lab3);
		hbl2.setAlignment(Pos.CENTER);
		hbl2.setPadding(new Insets(20));
		
		HBox hbl = new HBox(funny);
		hbl.setAlignment(Pos.CENTER);
		hbl.setPadding(new Insets(20));
		
		HBox combine = new HBox();
		combine.getChildren().addAll(hh1,hh2,hbl1,hbl2,new Separator(),hbl);
		
		BorderPane root = new BorderPane();
        root.setTop(hb1);
		root.setCenter(vb1);
		root.setBottom(combine);
		root.setStyle("-fx-background-color: #D9E8FF;");
	
        Scene scene = new Scene(root,1100,600);
        stage.setScene(scene);
        stage.setTitle("Manikandan_Typing.com");
        stage.show();
		
		b1.setOnAction(e -> 
		{
			if(flag==1)
			{
		     Platform.runLater(() -> {
             Stage newStage = new Stage();
             new MSSTVD().start(newStage);
             stage.close(); // Close the current stage
             });
			}
	    //count the time
		Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), ev -> {
        timeSeconds--;
        l1.setText(String.valueOf("00 : "+timeSeconds));
        if (timeSeconds <= 0) 
		{
			//when the time becomes 0 it shows the congratulations and calculate the wpm and acuuracy 
			funny.setText("Congratulations! You Completed the Test");
			funny.setFont(new Font("Comic Sans MS", 20));
            funny.setTextFill(Color.web("#28A745"));  // Green color
            funny.setAlignment(Pos.CENTER);
			
			double accu = ((double) correct / length) * 100.0; 
			double wpm = (double)(typed/5.0)/0.5;
			lab2.setText(String.valueOf(String.format("%.2f %%", accu)));
			lab3.setText(String.valueOf(String.format("%.2f %%", wpm)));
			lab2.setTextFill(Color.BLUE);
			lab3.setTextFill(Color.BLUE);
			lab2.setFont(new Font("Times New Roman",15));
			lab3.setFont(new Font("Times New Roman",15));
            timeline.stop();
			scene.addEventFilter(javafx.scene.input.KeyEvent.ANY, Event::consume);
			 b1.setDisable(false);
			 flag=1;
			
        }
        }));
        timeline.setCycleCount(timeSeconds);
        timeline.play();
        b1.setDisable(true);  //disable button after starting timer
		
		root.requestFocus();
		
		scene.addEventHandler(KeyEvent.KEY_TYPED, event -> 
		{
		length = textArea.getText().length();
		if(taindex<=length)
		{
	    typed++;
		char ch = textArea.getText().charAt(taindex);
		taindex++;
        String typedChar = event.getCharacter(); // this gives the character the user typed
		textArea.selectRange(0,taindex);
		if(typedChar.equals(String.valueOf(ch)))
		{
			//if user type correct char appears correct like 
			funny.setText("You Typed Correct Letter  \u2705");
			funny.setTextFill(Color.GREEN);
			funny.setFont(new Font("Arial", 20));
			++correct;
		}
		else
		{
			//if user type wrong char it show wrong
			funny.setText("You Typed Wrong Letter \u274C");
			funny.setTextFill(Color.RED);
			funny.setFont(new Font("Arial", 20));
			++wrong;
		}
		if(length==taindex)
		{
			//calculate the accuracy and wpm of the user and stop the every key event
			double accu = ((double) correct / length) * 100.0; 
			double wpm = (double)(typed/5.0)/0.5;
			lab2.setText(String.valueOf(String.format("%.2f %%", accu)));
			lab3.setText(String.valueOf(String.format("%.2f %%", wpm)));
            timeline.stop();
			scene.addEventFilter(javafx.scene.input.KeyEvent.ANY, Event::consume);
		    
			//after finish this text appear to motivate the user
			funny.setText("Congratulations! You Completed the Test");
			funny.setFont(new Font("Comic Sans MS", 20));
            funny.setTextFill(Color.web("#28A745"));  
            funny.setAlignment(Pos.CENTER);
			b1.setDisable(false);
			flag=1;
		}
		}
        });
        });
		
		//when the mouse cursor enter into the start button it becomes blue color
		 b1.setOnMouseEntered(e -> 
		{
            b1.setStyle(
                "-fx-background-color: blue; " +
                "-fx-text-fill: white; " +
                "-fx-border-color: blue; " +
                "-fx-border-width: 2px;"+
				"-fx-font-size: 20px; "
            );
        });

        //when the mouse cursor enter into the start button it becomes default color
        b1.setOnMouseExited(e -> 
		{
            b1.setStyle(
                "-fx-background-color: white; " +
                "-fx-text-fill: blue; " +
                "-fx-border-color: blue; " +
                "-fx-border-width: 2px;"+
				"-fx-font-size: 20px; "
            );
        });
    }

    public static void main(String[] args) 
	{
        launch();
    }
}
