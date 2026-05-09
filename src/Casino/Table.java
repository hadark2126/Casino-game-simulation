package Casino;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Table implements Runnable{

    private final int [] Cards;
    private List<Player> PlayersInTable;
    private int Rounds;
    private Dealer dealer;
    private Casino TableCasino;


    public Table(int Rounds,Casino TableCasino){
        this.Cards=new int [13];
        this.ResetCards();
        this.PlayersInTable=new ArrayList<>();
        this.Rounds=Rounds;
        this.dealer=new Dealer();
        this.TableCasino=TableCasino;
    }


    public void AddPlayer(Player player){ this.PlayersInTable.add(player);}

    public void AllocateCardToPlayer(Player player){
        int index= ThreadLocalRandom.current().nextInt(13);
        if(this.IsCardsPileEmpyty()){
            System.out.println("Cards are empty");
            System.out.println("Shuffling");
            this.ResetCards();
        }
        while(this.Cards[index]==0){
            index=ThreadLocalRandom.current().nextInt(13);
            System.out.println("stuck here");
        }
        this.Cards[index]-=1;
        player.addCard(index+1);
    }
    public boolean IsCardsPileEmpyty(){
        for(int i=0;i<13;i++)
        {
            if(this.Cards[i]>0){return false;}
        }
        return true;
    }

    public void ResetCards(){
        for(int i=0;i<13;i++)
            this.Cards[i]=4;

    }
    public void ResetRound(){
        for(Player player:this.PlayersInTable){player.ResetHand();}
        this.ResetCards();
    }


    @Override
    public void run(){
        System.out.println("Table running with " + this.PlayersInTable.size() + " players");
        for(int i=0;i<this.Rounds;i++)
        {
            //Initial cards allocation
            for (Player player : this.PlayersInTable)
            {
                this.AllocateCardToPlayer(player);
                this.AllocateCardToPlayer(player);
            }
            this.AllocateCardToPlayer(this.dealer);
            this.AllocateCardToPlayer(this.dealer);

            //End of First Allocation

            // Players actions until they STAND
            for(int j=0;j<this.PlayersInTable.size();j++){
                Player player=this.PlayersInTable.get(j);
                while(player.TakeAction()==Decision.HIT){
                    this.AllocateCardToPlayer(player);
                }
            }

            //Dealer must have value of >=17
            while(this.dealer.GetHandValue()<17)
            {
                this.AllocateCardToPlayer(this.dealer);
            }
            int DealerValue=this.dealer.GetHandValue();

            for(int j=0;j<this.PlayersInTable.size();j++)
            {
                Player player=this.PlayersInTable.get(j);
                int PlayerValue=player.GetHandValue();

                // Player loses and Dealer Wins
                if( (PlayerValue>21)  || ( (DealerValue<=21 && PlayerValue<=21 ) &&(DealerValue>PlayerValue) ))
                {
                    this.TableCasino.IncreaseDealerWins();
                }
                //Player wins Dealer lose
                else if (DealerValue>21 && PlayerValue<=21 || ( (DealerValue<=21 && PlayerValue<=21 ) &&(DealerValue<PlayerValue)))
                {
                    this.TableCasino.IncreasePlayerWins();
                    switch (player.GetStrategy()){
                        case "RandomPlayer" -> this.TableCasino.IncreaseRandomPlayerWins();
                        case "ConservativePlayer" -> this.TableCasino.IncreaseConservativePlayerWins();
                        case "AggressivePlayer" -> this.TableCasino.IncreaseAggressivePlayerWins();
                    }
                }
                //Tie
                else if ((DealerValue==PlayerValue) && DealerValue<=21)
                {
                    this.TableCasino.IncreasePlayerTies();
                    this.TableCasino.IncreaseDealerTIes();
                }
                //Both looses
                else {}
                this.TableCasino.IncreaseHandsPlayed();
            }
            this.TableCasino.IncreaseRoundsPlayed();
            this.ResetRound();
        }
    }
}