package controller;

import model.Area;
import model.Robot;
import utils.RobotCommand;

import java.util.List;

/**
 * L'interfaccia InterfaceControllerRobot definisce il contratto per il controllo del comportamento di un robot.
 */
public interface InterfaceControllerRobot {

    /**
     * Esegue un comando specificato con argomenti e un'etichetta associata.
     *
     * @param command Il comando da eseguire.
     * @param args    Gli argomenti associati al comando.
     * @param label   L'etichetta associata al comando.
     */
    void executeCommand(RobotCommand command, double[] args, String label);

    /**
     * Muove il robot verso una posizione specificata.
     *
     * @param args Gli argomenti che specificano la posizione e la velocità del movimento.
     */
    void move(double[] args);

    /**
     * Muove il robot in modo casuale all'interno di un'area specificata.
     *
     * @param args Gli argomenti che specificano l'area e la velocità del movimento casuale.
     */
    void moveRandom(double[] args);

    /**
     * Aggiunge una condizione al robot indicando una segnalazione con un'etichetta specificata.
     *
     * @param label L'etichetta della segnalazione da aggiungere come condizione al robot.
     */
    void signal(String label);

    /**
     * Rimuove una condizione dal robot indicando il termine di una segnalazione con un'etichetta specificata.
     *
     * @param label L'etichetta della segnalazione da rimuovere come condizione dal robot.
     */
    void unsignal(String label);

    /**
     * Avvia la modalità "follow", facendo muovere il robot verso le posizioni degli altri robot
     * che segnalano la stessa condizione, oppure in una direzione casuale se nessun altro robot è presente.
     *
     * @param label L'etichetta della condizione da seguire.
     * @param args  Gli argomenti, con il passo di movimento specificato.
     */
    void follow(String label, double[] args);

    /**
     * Interrompe il movimento del robot, aggiungendo la condizione "Stopped".
     * Stampa un messaggio indicando che il movimento è stato interrotto.
     */
    void stop();

    /**
     * Continua il movimento del robot per un periodo specificato in secondi.
     * Stampa un messaggio indicando che il movimento continua per il periodo specificato.
     * Durante il periodo di continuazione, esegue il movimento utilizzando gli argomenti forniti.
     *
     * @param seconds   Il numero di secondi per cui il movimento deve continuare.
     * @param moveArgs  Gli argomenti da utilizzare per il movimento.
     */
    void continueCommand(long seconds, double[] moveArgs);

    /**
     * Ripete una sequenza di comandi per un numero specificato di iterazioni.
     * Stampa un messaggio indicando l'inizio di ogni iterazione.
     *
     * @param iterations Il numero di iterazioni da eseguire.
     */
    void repeatCommand(int iterations);

    /**
     * Ripete una sequenza di comandi fino a quando una certa condizione è percepita nell'ambiente (ossia il robot è all'interno di un'area con la label richiesta).
     *
     * @param label L'etichetta della condizione da attendere.
     */
    void until(String label);

    /**
     * Ripete un comportamento per sempre.
     */
    void doForever();

    /**
     * Segnala la fine della sequenza di comandi.
     */
    void done();

    /**
     * Imposta l'area del robot.
     *
     * @param area L'area del robot.
     */
    void setRobotArea(Area area);

    /**
     * Restituisce l'area del robot.
     *
     * @return L'area del robot.
     */
    Area getRobotArea();

    /**
     * Restituisce il robot controllato.
     *
     * @return Il robot controllato.
     */
    Robot getRobot();

    /**
     * Imposta la lista dei comandi che devono essere ripetuti.
     *
     * @param commands La lista dei comandi da impostare per la ripetizione.
     */
    void setListCommand(List<RobotCommand> commands);
}
