package controller;

import model.Area;
import model.Robot;
import utils.RobotCommand;

public class ControllerRobot implements InterfaceControllerRobot {

    private Robot robot;
    private Area robotArea;

    public ControllerRobot(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void executeCommand(RobotCommand command, double[] args, String label) {
        switch (command) {
            case MOVE:
                move(args);
                break;
            case SIGNAL:
                signal(label);
                break;
            case UNSIGNAL:
                unsignal(label);
                break;
            case FOLLOW:
                follow(label, args);
                break;
            case STOP:
                stop();
                break;
            case CONTINUE:
                continueCommand((int) args[0], args);
                break;
            case REPEAT:
                repeatCommand((int) args[0]);
                break;
            case UNTIL:
                until(label);
                break;
            case FOREVER:
                doForever();
                break;
            case DONE:
                done();
                break;
            default:
                // Gestione del caso in cui il comando non è riconosciuto
                System.out.println("Comando non riconosciuto: " + command);
                break;
        }
    }

    @Override
    public void move(double[] args) {
        double x = args[0];
        double y = args[1];
        double speed = args[2];

        if (robotArea != null && robotArea.contains(x, y)) {
            if (!robot.isMoving()) {
                robot.stopMoving();
                robot.addCondition("Stopped");

                double distance = Math.sqrt(Math.pow(x - robot.getX(), 2) + Math.pow(y - robot.getY(), 2));
                double time = distance / speed;

                simulateMovement(x, y, time);

                robot.stopMoving();
                robot.addCondition("Stopped");
            } else {
                System.out.println("Il robot è già in movimento.");
            }
        } else {
            System.out.println("La posizione specificata non è contenuta nell'Area del robot.");
        }
    }

    @Override
    public void signal(String label) {
        robot.addCondition(label);
        System.out.println("Segnalazione: " + label);
    }

    @Override
    public void unsignal(String label) {
        robot.removeCondition(label);
        System.out.println("Terminata segnalazione: " + label);
    }

    @Override
    public void follow(String label, double[] args) {
        System.out.println("Segue il robot con etichetta " + label);
    }

    @Override
    public void stop() {
        robot.stopMoving();
        robot.addCondition("Stopped");
        System.out.println("Movimento interrotto");
    }

    @Override
    public void continueCommand(long seconds, double[] moveArgs) {
        System.out.println("Movimento continua per " + seconds + " secondi");

        long endTime = System.currentTimeMillis() + seconds * 1000; // Calcola il tempo di fine

        while (System.currentTimeMillis() < endTime) {
            // Logica da eseguire durante il periodo di continuazione
            // Ad esempio, puoi aggiungere azioni specifiche che devono essere eseguite durante il movimento continuo.
            System.out.println("Esecuzione di comandi durante il movimento continuo...");

            // Esegui il movimento continuo
            move(moveArgs);

            try {
                Thread.sleep(1000); // Attendi per 1 secondo durante ogni iterazione (puoi regolare il valore)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Fine del periodo di continuazione
        System.out.println("Fine del movimento continuo.");
    }

    @Override
    public void repeatCommand(int iterations) {
        for (int i = 0; i < iterations; i++) {
            System.out.println("Iterazione " + (i + 1));

            // Aggiungi qui la logica dei comandi che devono essere ripetuti
            // Ad esempio, puoi richiamare altri metodi del controller per eseguire azioni specifiche.

            try {
                Thread.sleep(100); // Attendi per 100 millisecondi (puoi regolare il valore)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void until(String label) {
        while (!robot.hasCondition(label)) {
            // Aggiungi la logica dei comandi che devono essere ripetuti finché la condizione non è soddisfatta
            // Ad esempio, potresti aspettare un breve periodo di tempo prima di controllare nuovamente la condizione.
            try {
                Thread.sleep(100); // Attendi per 100 millisecondi (puoi regolare il valore)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Condizione " + label + " percepita");
    }

    @Override
    public void doForever() {
        while (true) {
            System.out.println("Iterazione infinita");
            // Aggiungi la logica dei comandi che devono essere ripetuti all'infinito
            // Ad esempio, potresti aspettare un breve periodo di tempo prima di iniziare la prossima iterazione.
            try {
                Thread.sleep(100); // Attendi per 100 millisecondi (puoi regolare il valore)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void done() {
        System.out.println("Fine della sequenza di comandi");
    }

    @Override
    public void setRobotArea(Area area) {
        this.robotArea = area;
    }

    @Override
    public Area getRobotArea() {
        return this.robotArea;
    }

    @Override
    public Robot getRobot() {
        return robot;
    }

    @Override
    public Area setRobotArea() {
        return null;
    }

    private void simulateMovement(double targetX, double targetY, double time) {
        // Implementa la logica per simulare il movimento del robot per il tempo specificato
        // Puoi utilizzare un timer, un thread o un'altra logica di simulazione a seconda delle tue esigenze.
        // Ad esempio, potresti aggiornare le coordinate del robot in modo incrementale per simulare il movimento.

        double deltaX = (targetX - robot.getX()) / time;
        double deltaY = (targetY - robot.getY()) / time;

        // Simula il movimento per il tempo specificato
        for (double elapsed = 0; elapsed < time; elapsed += 0.1) {
            robot.move(deltaX * 0.1, deltaY * 0.1); // Simula un passo di 0.1 secondi
            // Puoi regolare il valore 0.1 in base alla frequenza di aggiornamento desiderata.
            // Assicurati di gestire correttamente la concorrenza se stai utilizzando thread.
            try {
                Thread.sleep(100); // Attendi per 100 millisecondi (puoi regolare il valore)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
