package simulator;

import model.Area;
import model.Robot;

import java.util.List;


public class Simulator implements SimulatorInterface{

    private List<Robot> robots;
    private List<Area> areas;

    public Simulator(List<Robot> robots, List<Area> areas){
        this.robots=robots;
        this.areas=areas;
    }
    @Override
    public void simulate(double dt, double time) {

    }
}
