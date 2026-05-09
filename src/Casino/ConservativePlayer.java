package Casino;



import java.util.List;

public class ConservativePlayer extends Player{



    public ConservativePlayer(){
        super();
    }

    public  Decision TakeAction(){
        if(this.GetHandValue()>=17){return Decision.STAND;}
        return Decision.HIT;
    }


    public String GetStrategy(){
        return "ConservativePlayer";
    }

}




