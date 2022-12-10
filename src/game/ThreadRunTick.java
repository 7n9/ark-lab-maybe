package game;

public class ThreadRunTick extends Thread {

    public void run() {
        while (main.tickCount < 20  ) {
            if (System.currentTimeMillis() / 1000 > lastTime) {
                lastTime = System.currentTimeMillis() / 1000;

                main.onTick();
            }
        }
    }

    private long lastTime = 0;
}
