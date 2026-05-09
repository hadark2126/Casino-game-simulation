package Casino;

import java.util.List;

public class AggressivePlayer extends Player{

    public AggressivePlayer(){
        super();
    }

    public  Decision TakeAction(){
        if(this.GetHandValue()>=19){return Decision.STAND;}
        return Decision.HIT;
    }

    public String GetStrategy(){
        return "AggressivePlayer";
    }
}