package neointernship;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        LocalTime startTime= LocalTime.now();
        System.out.println(startTime);

        int countBot = 10;

        ArrayList<ControllerBot> controllerBots = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();

        /*for (int i = 0; i < countBot; i++) {
            threads.add(new Thread(new ControllerBot(i)));
        }
        for (Thread thread : threads){
            thread.start();
        }
        for (Thread thread : threads){
            thread.join();
        }*/

        for (int i = 0; i < countBot; i++) {
            controllerBots.add(new ControllerBot(i));
        }
        for(ControllerBot controllerBot : controllerBots){
            threads.add(new Thread(controllerBot));
        }
        for (Thread thread : threads){
            thread.start();
        }
        for(ControllerBot controllerBot : controllerBots){
            System.out.println(controllerBot.getTime());
        }
        int maxTime = controllerBots.stream().mapToInt(ControllerBot::getTime).max().getAsInt();
        System.out.println(maxTime);


        LocalTime endTime = LocalTime.now();

        System.out.println(endTime);
    }
}
