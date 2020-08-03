package neointernship;

import java.util.ArrayList;

public class MoreBots {

    public static void main(String[] args)  {

        int countBot = 2;

        ArrayList<ControllerBot> controllerBots = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < countBot; i++) {
            controllerBots.add(new ControllerBot(i));
        }
        for(ControllerBot controllerBot : controllerBots){
            threads.add(new Thread(controllerBot));
        }
        for (Thread thread : threads){
            thread.start();
        }
    }

}
