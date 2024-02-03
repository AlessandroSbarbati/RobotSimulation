package utils;


import java.util.Optional;
import java.util.stream.Stream;

public enum RobotCommand {




    MOVE("MOVE"),
    SIGNAL("SIGNAL"),
    UNSIGNAL("UNSIGNAL"),
    FOLLOW("FOLLOW"),
    STOP("STOP"),
    CONTINUE("CONTINUE"),
    REPEAT("REPEAT"),
    UNTIL("UNTIL"),
    FOREVER("DO FOREVER"),
    DONE("DONE"),
    MOVERANDOM("RANDOM");

    private final String code;

    RobotCommand(String code) {
        this.code = code;
    }

    boolean isCommandOfLine(String line) {
        return line.startsWith(this.code);
    }

    static Optional<RobotCommand> selectCommand(String line) {
        return Stream.of(RobotCommand.values()).filter(c -> c.isCommandOfLine(line)).findFirst();
    }

    public boolean isLoopCommand() {
        return this == RobotCommand.REPEAT || this == RobotCommand.UNTIL || this == RobotCommand.FOREVER || this == RobotCommand.DONE;
    }
}