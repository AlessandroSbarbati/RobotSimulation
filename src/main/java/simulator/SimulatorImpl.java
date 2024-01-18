package simulator;

import model.Area;
import model.InfiniteSurface;
import model.Robot;
import utils.RobotCommand;

import java.util.ArrayList;
import java.util.List;

public class SimulatorImpl implements Simulator {

    private final List<Robot> robots;
    private final InfiniteSurface infiniteSurface;

    public SimulatorImpl(List<Robot> robots, InfiniteSurface infiniteSurface) {
        this.robots = robots;
        this.infiniteSurface = infiniteSurface;
    }

    public void simulate(double dt, double time) {
        for (double currentTime = 0; currentTime < time; currentTime += dt) {
            // Logica principale della simulazione
        }
    }

    public void addAreaToInfiniteSurface(Area area) {
        infiniteSurface.addArea(area);
    }

    public void createRandomRobots(int numRobots, Area area) {
        for (int i = 0; i < numRobots; i++) {
            double randomX = Math.random() * (area.getMaxX() - area.getMinX()) + area.getMinX();
            double randomY = Math.random() * (area.getMaxY() - area.getMinY()) + area.getMinY();
            double randomSpeed = Math.random() * 10;

            Robot robot = new Robot(randomX, randomY, randomSpeed);
            robots.add(robot);
        }
    }

    public List<Robot> getRobots() {
        return robots;
    }

    public InfiniteSurface getInfiniteSurface() {
        return infiniteSurface;
    }

    /**
     * Trova un robot con una specifica etichetta.
     *
     * @param label L'etichetta da cercare.
     * @return Il robot con l'etichetta specificata, null se non trovato.
     */
    public Robot findRobotByLabel(String label) {
        return robots.stream()
                .filter(robot -> robot.getConditions().contains(label))
                .findFirst()
                .orElse(null);
    }

    /**
     * Trova i robot con una specifica condizione.
     *
     * @param condition La condizione da cercare.
     * @return Lista di robot con la condizione specificata.
     */
    public List<Robot> findRobotsByCondition(String condition) {
        List<Robot> robotsWithCondition = new ArrayList<>();

        for (Robot robot : robots) {
            if (robot.getConditions().contains(condition)) {
                robotsWithCondition.add(robot);
            }
        }
        return robotsWithCondition;
    }
    /**
     * Cancella tutti i robot e le aree presenti nel simulatore.
     * Questo metodo rimuove tutti i robot dalla lista e pulisce l'area infinita
     * rimuovendo tutte le aree contenute al suo interno.
     */
    public void clear() {
        // Pulisci la lista dei robot
        robots.clear();

        // Pulisci l'area infinita
        infiniteSurface.getContainedAreas().clear();
    }

}
