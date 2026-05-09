package Casino;


import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private List<Integer> Hand;
    private int CurrSum;
    private StatusInGame Status;

    public Player(){
        this.Hand=new ArrayList<>();
        this.CurrSum=0;
        this.Status=StatusInGame.PLAYING;
    }

    public List<Integer> GetHand() {
        return this.Hand;
    }


    public void UpdateStatusInGame(StatusInGame status){this.Status=status;}

    public StatusInGame GetStatus(){return  this.Status;}


    public void addCard (int card){
        this.Hand.add(card);
        if(card==1) {
            if (this.CurrSum + 11 <= 21) {
                this.CurrSum += 11;
            } else {
                this.CurrSum += 1;
            }
        }
        else{
            int Addition=Math.min(10,card);
            this.CurrSum+=Addition;
        }
       if ( this.IsPlayerSumMoreThan21()){UpdateStatusInGame(StatusInGame.LOST);}
    }
    public abstract Decision TakeAction();

    public void ResetHand(){
        while(!this.Hand.isEmpty())
        {
//            this.Hand.remove(this.Hand.size()-1);
            this.Hand.removeLast();
        }
        this.CurrSum=0;
        this.Status=StatusInGame.PLAYING;
    }
    public boolean IsPlayerSumMoreThan21(){return this.CurrSum>21;}

    public int GetHandValue(){return this.CurrSum;}

    public abstract String GetStrategy();

}