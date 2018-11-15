
package project.iii;

//imports our APIs
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ProjectIII extends Application 
{
    
    private Scene theScene;
    //For our scene.
    private Button calculate;
    //This will the button that will perform our action event (Calculation).
    
    //Text Fields
    private TextField principalText;
    private TextField rateText;
    private TextField timeText;
    private TextField comText;
    private TextField amountText;
    
    //One of our radio buttons. This will be for Future Value.
    private RadioButton fChoice;
    
    private final int sceneWidth = 700; //for the scence width
    private final int sceneHeight = 700; //for the scene height
    
    //These strings will be the default values when the application executes;
    private final String principalString = "15000"; //15,00
    private final String rateString = ".05"; //5%
    private final String timeString = "30"; //30
    private final String compoundString = "12"; //1 year
    private final String amountString = ""; 
    //This will show blank. We will assume that the user will want to find the 
    //future value first, but if they select to find the present value,
    //they are free to enter the Amount Value first if they would like.
    
    
    @Override
    public void start(Stage primaryStage) 
    {
        visInterFace();
        //Draws our visual interface.
        //This creates how the user will see the application.
        
        //Below is for the Calculate button to execute the calculation event.
        calculate.setOnAction(new EventHandler<ActionEvent>()
        {
           @Override
           public void handle(ActionEvent f)
           {
               //The actual calculation.
               calculation();
           }  
        });
        
        //For the window.
        primaryStage.setTitle("Financial App");
        primaryStage.setScene(theScene);
        primaryStage.show();
       
    }

    
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    private void visInterFace()
    {
        //Using the border pane for visual purposes;
        BorderPane bp = new BorderPane();
        
        bp.setTop(valueChoices()); 
        //The Radio Buttions will go on Top.
        //Future Value and Present Value.
        
        bp.setCenter(financialInfo());
        //All of the TextFields will go in the center of the application.
        //Heres how it will look.
        
        //Principal: $[15000]
        //Rate: [.05]
        //Time: [30]
        //Number of Compounds: [12]
        //Amount: $[ ]
        
        calculate = new Button("Calculate");
        //instantiates the calculation button for the purpose of the interface. 
        
        bp.setBottom(calculate);
        //The Calculate button will go on the bottom.
        
        
        theScene = new Scene(bp, sceneWidth, sceneHeight);
        //Scene will be set.
    }
    
    private HBox valueChoices() //For the radio buttons
    {
        fChoice = new RadioButton("Future Value");
        //Instantiates the Future Value Button.
        
        RadioButton pChoice = new RadioButton("Present Value");
        //The Present Value Button is made.
        
        fChoice.setSelected(true);
        //By default we are using the Future Value option.
        
        ToggleGroup options = new ToggleGroup();
        //The toggle will be for the user can select either value option.
        
        fChoice.setToggleGroup(options);
        //Adds future button for toggle
        pChoice.setToggleGroup(options);
        //Adds present button for toggle.
        
        HBox valueChoices = new HBox(15);
        //For adding our buttons
        
        //Both buttons will be formatted
        valueChoices.getChildren().add(fChoice);
        valueChoices.getChildren().add(pChoice);
        
        return valueChoices;
        //This will go on the Border Pane top.
    
    }
    
    private VBox financialInfo()//For our text fields.
    {

        VBox financialInfo = new VBox();
        //Vertical boxes

        //Text Fields will be added vertically
        
        //Principal: $[15000]
        //Rate: [.05]
        //Time: [30]
        //Number of Compounds: [12]
        //Amount: $[ ]
        financialInfo.getChildren().add(setPrincipal());
        financialInfo.getChildren().add(setRate());
        financialInfo.getChildren().add(setTime());
        financialInfo.getChildren().add(setCom());
        financialInfo.getChildren().add(setAmount());


        return financialInfo;
    
    }
    private HBox setPrincipal()
    {
        //Principal Text Box
        Label pName = new Label("Principal: $");
        principalText = new TextField(principalString);
        
        HBox setPrincipal = new HBox(10);
    
        setPrincipal.getChildren().add(pName);
        setPrincipal.getChildren().add(principalText);
        
        return setPrincipal;
    }
    
    private HBox setRate()
    {
        //Rate Text Box
        Label rName = new Label("Rate: ");
        rateText = new TextField(rateString);
        
        HBox setRate = new HBox(10);

        setRate.getChildren().add(rName);
        setRate.getChildren().add(rateText);
        
        return setRate;
    }
            
    private HBox setTime()
    {
        //Time Text Box
       Label tName = new Label("Time: ");
       timeText = new TextField(timeString);
       
       HBox setTime = new HBox(10);
       
       setTime.getChildren().add(tName);
       setTime.getChildren().add(timeText);
       
       return setTime;
    
    }
    
    private HBox setCom()
    {
        //Compounds Text Box
        Label cName = new Label("Number of Compoundings: ");
        comText = new TextField(compoundString);
        
        HBox setCom = new HBox(1);
        
        setCom.getChildren().add(cName);
        setCom.getChildren().add(comText);
    
        return setCom;
    }
    
    private HBox setAmount()
    {
        //Amont Text Box
        Label aName = new Label("Amount: $");
        amountText = new TextField(amountString);
        
        HBox setAmount = new HBox(10);
    
        setAmount.getChildren().add(aName);
        setAmount.getChildren().add(amountText);
        
        return setAmount;
    }
    
    private void calculation()
    {
        if(fChoice.isSelected())//If future value is selected.
        {
            //Values will be turned from string to double for calculation purposes.
            double principalValue = Double.parseDouble(principalText.getText());
            double rateValue = Double.parseDouble(rateText.getText());
            double timeValue = Double.parseDouble(timeText.getText());
            double comValue = Double.parseDouble(comText.getText());
            
            //Calculates future value.
            double futureValue = principalValue * Math.pow(1 + rateValue / comValue, comValue * timeValue);
            
            //Takes the future value and turn it into string and outputs future value.
            amountText.setText(String.valueOf(futureValue));
        }
        else
        {
            //Values will be turned from string to double for calculation purposes.
            double amountValue = Double.parseDouble(amountText.getText());
            double rateValue = Double.parseDouble(rateText.getText());
            double timeValue = Double.parseDouble(timeText.getText());
            double comValue = Double.parseDouble(comText.getText());
            
            //Calculates present value.
            double presentValue = amountValue * Math.pow(1 + rateValue / comValue, -comValue * timeValue);
            
            //Takes the present value and turn it into string and outputs present value.
            principalText.setText(String.valueOf(presentValue));
        }
    
    
    }
}
