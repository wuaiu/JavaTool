import java.util.concurrent.*;
import java.util.Random;

public class  {

    public static void main(String[] args) {
        Random rand = new Random();
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        Object obj=null;
        try {
            obj=future1.get(30, TimeUnit.MILLISECONDS);
        }catch (Exception e){

        }
        if(obj==null){
            CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(9000 + rand.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "abc";
            });
//CompletableFuture<Void> f =  CompletableFuture.allOf(future1,future2);
            CompletableFuture<Object> f =  CompletableFuture.anyOf(future1,future2);
            try {
                System.out.println(f.get());
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
