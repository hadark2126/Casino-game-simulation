package Casino;


import java.util.List;

public class Dealer extends Player{

    public Dealer(){
        super();
    }

    public Decision TakeAction(){return Decision.STAND;}

    public  String GetStrategy(){return "";}
}