package Casino;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
public class RandomPlayer extends Player{




    public RandomPlayer(){
        super();
    }

    public  Decision TakeAction(){
        if(this.GetHandValue()>=21){return Decision.STAND;}
        if(this.GetHandValue()<=20 && ThreadLocalRandom.current().nextInt(2)==0){return Decision.HIT;}
        return Decision.STAND;
    }

    public String GetStrategy(){
        return "RandomPlayer";
    }

}