package Casino;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        int RoundsPerTable = 100;
        int TablesNumber = 10;
        int PlayerInEachTable = 6;
        Casino casino = new Casino(TablesNumber, 100);
        ExecutorService pool = Executors.newFixedThreadPool(TablesNumber);
        for (int i = 0; i < TablesNumber; i++) {
            Table table = casino.GetTableNumberi(i);
            for (int j = 0; j < 2; j++) {
                table.AddPlayer(new RandomPlayer());
                table.AddPlayer(new ConservativePlayer());
                table.AddPlayer(new AggressivePlayer());
            }
        }
        for (int i = 0; i < TablesNumber; i++) {
            pool.submit(casino.GetTableNumberi(i));
        }
        pool.shutdown();
        try {
            boolean finished = pool.awaitTermination(1, TimeUnit.MINUTES);
            if (finished) {
                System.out.println("All Tables have Finished Their Rounds");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        casino.PrintStats();
    }
}
