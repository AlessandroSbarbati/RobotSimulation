package controller;

import model.Area;
import model.CircularArea;
import model.RectangularArea;
import model.Robot;
import simulator.Simulator;
import utils.RobotCommand;

public class ControllerRobot implements InterfaceControllerRobot {

    private Robot robot;
    private Area robotArea;
    private Simulator simulator;
    public ControllerRobot(Robot robot, Area robotArea, Simulator simulator) {
        this.robot = robot;
        this.robotArea=robotArea;
        this.simulator=simulator;
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
                robot.startMoving();
                robot.addCondition("Moving");

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

        // Imposta la modalità "follow"
        robot.setFollowing(true);

        // Ottieni il robot da seguire in base all'etichetta
        Robot robotToFollow = findRobotByLabel(label);

        if (robotToFollow != null) {
            // Simula il movimento seguendo l'altro robot
            simulateFollowMovement(robotToFollow);
        } else {
            System.out.println("Robot con etichetta " + label + " non trovato.");
        }

        // Resetta la modalità "follow" alla fine
        robot.setFollowing(false);
    }

    private Robot findRobotByLabel(String label) {
        // Implementa la logica per trovare il robot con l'etichetta specificata
        // Questo potrebbe coinvolgere la ricerca tra i robot nel simulatore.
        // Restituisci il robot trovato o null se non è stato trovato.
        return simulator.findRobotByLabel(label);
    }

    private void simulateFollowMovement(Robot robotToFollow) {
        // Simula il movimento seguendo l'altro robot
        while (robot.isFollowing()) {
            // Calcola la direzione e la distanza tra il robot corrente e il robot da seguire
            double direction = Math.atan2(robotToFollow.getY() - robot.getY(), robotToFollow.getX() - robot.getX());
            double distance = Math.sqrt(Math.pow(robotToFollow.getX() - robot.getX(), 2) +
                    Math.pow(robotToFollow.getY() - robot.getY(), 2));

            // Calcola il passo da muovere (puoi regolare il valore in base alle tue esigenze)
            double step = 0.1;

            // Calcola i cambiamenti nelle coordinate x e y
            double deltaX = step * Math.cos(direction);
            double deltaY = step * Math.sin(direction);

            // Sposta il robot nella direzione del robot da seguire
            robot.move(deltaX, deltaY);

            // Puoi aggiungere ulteriori logiche o azioni durante il movimento di "follow", se necessario

            try {
                Thread.sleep(100); // Attendi per 100 millisecondi (puoi regolare il valore)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

            // Esegui il movimento continuo
            move(moveArgs);

            // Aggiungi qui logiche specifiche da eseguire durante il movimento continuo
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

            // Puoi aggiungere logiche specifiche da eseguire durante l'attesa
            // Ad esempio, puoi modificare la posizione del robot in base a certe condizioni.

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

            // Puoi aggiungere logiche specifiche da eseguire durante ogni iterazione dell'infinito
            // Ad esempio, puoi modificare la posizione del robot in base a certe condizioni.

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


    private void simulateMovement(double targetX, double targetY, double time) {
        // Calcola la quantità di spostamento richiesta per ogni passo
        double deltaX = (targetX - robot.getX()) / time;
        double deltaY = (targetY - robot.getY()) / time;

        // Suddividi il tempo di movimento in passi più piccoli (ogni passo di 0.1 secondi)
        double stepTime = 0.1;
        int numSteps = (int) (time / stepTime);

        // Simula il movimento attraverso i passi
        for (int step = 0; step < numSteps; step++) {
            // Aggiorna direttamente le coordinate del robot durante ciascun passo
            robot.move(deltaX * stepTime, deltaY * stepTime);

            // Puoi aggiungere ulteriori logiche o azioni durante il movimento, se necessario

            // Attendi per il tempo specificato tra i passi (100 millisecondi nel tuo esempio)
            try {
                Thread.sleep(100); // Puoi regolare il valore in base alle tue esigenze
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInteraction(Robot robot, Area area) {
        if (area instanceof RectangularArea) {
            robot.addCondition("Interacting with RectangularArea");
        } else if (area instanceof CircularArea) {
            robot.addCondition("Interacting with CircularArea");
        }

        // Altre logiche di interazione, se necessario
    }

}
