package simulator;

import controller.ControllerRobot;

public class MoveSimulatorCommand implements SimulatorCommand{
    private ControllerRobot controllerRobot;
    private double[] args;

    public MoveSimulatorCommand(ControllerRobot controllerRobot, double[] args) {
        this.controllerRobot = controllerRobot;
        this.args = args;
    }
    @Override
    public void run() {

    }
}
