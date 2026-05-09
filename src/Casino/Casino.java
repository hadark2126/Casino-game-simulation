package Casino;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
public class Casino{
    private AtomicInteger PlayerWins;
    private AtomicInteger PlayerTies;

    private AtomicInteger AggressivePlayerWins;
    private AtomicInteger ConservativePlayerWins;
    private AtomicInteger RandomPlayerWins;

    private AtomicInteger DealerWins;
    private AtomicInteger DealerTies;

    private AtomicInteger TotalHands;

    private AtomicInteger TotalRounds;

    private final int TablesCount;
    private List<Table> CasinoTables;

    public Casino(int TablesCount, int TablesRounds){
        this.PlayerWins=new AtomicInteger(0);
        this.PlayerTies=new AtomicInteger(0);

        this.AggressivePlayerWins=new AtomicInteger(0);
        this.ConservativePlayerWins=new AtomicInteger(0);
        this.RandomPlayerWins=new AtomicInteger(0);

        this.DealerWins=new AtomicInteger(0);
        this.DealerTies=new AtomicInteger(0);

        this.TotalHands=new AtomicInteger(0);

        this.TotalRounds=new AtomicInteger(0);

        this.TablesCount=TablesCount;
        this.CasinoTables=new ArrayList<>();
        for(int i=0;i<TablesCount;i++){
            Table table = new Table(TablesRounds,this);
            this.CasinoTables.add(table);
        }
    }
    public void IncreasePlayerWins()
    {
        this.PlayerWins.addAndGet(1);
    }

    public void IncreaseAggressivePlayerWins()
    {
        this.AggressivePlayerWins.addAndGet(1);
    }

    public void IncreaseConservativePlayerWins()
    {
        this.ConservativePlayerWins.addAndGet(1);
    }

    public void IncreaseRandomPlayerWins()
    {
        this.RandomPlayerWins.addAndGet(1);
    }


    public void IncreaseDealerWins()
    {
        this.DealerWins.addAndGet(1);
    }


    public void IncreasePlayerTies()
    {
        this.PlayerTies.addAndGet(1);
    }

    public void IncreaseDealerTIes()
    {
        this.DealerTies.addAndGet(1);
    }
    public void IncreaseHandsPlayed()
    {
        this.TotalHands.addAndGet(1);
    }

    public void IncreaseRoundsPlayed()
    {
        this.TotalRounds.addAndGet(1);
    }


    public Table GetTableNumberi(int i){
        return this.CasinoTables.get(i);
    }

    public void PrintStats(){
        System.out.println("=== Casino Summary ===");
        System.out.println("Total Rounds: "+this.TotalRounds);
        System.out.println("Total Hands: "+this.TotalHands);
        System.out.println("Player wins: "+this.PlayerWins);
        System.out.println("Dealer wins: "+this.DealerWins);
        System.out.println("Ties: "+this.DealerTies);

        System.out.println("By strategy:");
        System.out.println("  Conservative: "+this.ConservativePlayerWins.get());
        System.out.println("  Aggressive: "+this.AggressivePlayerWins.get());
        System.out.println("  Random: "+this.RandomPlayerWins.get());

        int SumWins=this.ConservativePlayerWins.get()+this.AggressivePlayerWins.get()+this.RandomPlayerWins.get();
        boolean TruthValue=(SumWins==this.PlayerWins.get());
        System.out.print("The formula :Conservative+Aggressive+Random=Player Wins is ");
        if(TruthValue)
        {System.out.println("True");}
        else{System.out.println("False");}

        int SumTotalhands=this.TotalHands.get();
        TruthValue=(SumTotalhands==(this.PlayerWins.get()+this.DealerWins.get()+this.DealerTies.get()));

        System.out.print("The formula :Player Wins+Dealer Wins+ Ties=Total Hands  is ");
        if(TruthValue)
        {System.out.println("True");}
        else{System.out.println("False");}
    }


}