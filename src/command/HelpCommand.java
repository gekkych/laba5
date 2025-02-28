package command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class HelpCommand extends Command {
    private final HashMap<String, Command> commandMap;
    public HelpCommand(HashMap<String, Command> commandMap) {
        super("help", false, false);
        this.commandMap = commandMap;
    }

    @Override
    public void start(String argument) {
        List<String> commandList = new ArrayList<>();
        System.out.println("Доступные команды:");
        for (Command command : commandMap.values()) {
            commandList.add(command.description());
        }
        Collections.sort(commandList);
        for (String description : commandList) {
            System.out.println(description);
        }
    }

    @Override
    public String description() {
        return this.getName() + " - справка по доступным командам";
    }
}
