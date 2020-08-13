package neointernship.web.client;

import neointernship.web.client.controller.Controller;

public class Client {
    /**
     * Главная функция, начало работы клиента
     *
     * @param args аргументы командной строки
     */ 
    public static void main(final String[] args) throws InterruptedException {
        startController();
    }

    private static void startController() throws InterruptedException {
        final Controller controller = new Controller();
        controller.start();
    }
}