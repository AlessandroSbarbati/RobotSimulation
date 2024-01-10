package simulator;

/**
 * L'interfaccia Simulator definisce il metodo necessario per eseguire la simulazione di un sistema.
 */
public interface Simulator {

    /**
     * Esegue la simulazione del sistema per un periodo di tempo specificato.
     *
     * @param dt   Il passo temporale della simulazione.
     * @param time Il tempo totale della simulazione.
     */
    void simulate(double dt, double time);

}
