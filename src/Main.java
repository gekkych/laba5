//Вариант 4326
import command.*;
import exception.DequeException;
import exception.InvalidArgumentException;
import exception.MovieFieldException;
import movie.*;

import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static final String GECKO = "\uD83E\uDD8E";
    static Scanner scanner = new Scanner(System.in);
    static MovieDeque movies;
    static SaveManager saveManager;
    static String filePath;
    static HashMap<String, Command> commandMap = new HashMap<>();

    public static void main(String[] args) {
        if (args.length > 0) {
            filePath = args[0];
        } else {
            filePath = "save.xml";
            System.out.println("Файл не указан, используется стандартный save.xml");
        }
        saveManager = new SaveManager(filePath);
        try {
            movies = saveManager.loadFromXML();
            movies.manageDeque();

            initializeCommands();
            input();
        } catch (DequeException | MovieFieldException  e) {
            System.out.println(e.getMessage());
        }
    }

    public static void initializeCommands() {
        ExitCommand exit = new ExitCommand();
        HelpCommand help = new HelpCommand(commandMap);
        ShowCommand show = new ShowCommand(movies);
        RemoveByIdCommand remove_by_id = new RemoveByIdCommand(movies);
        ClearCommand clear = new ClearCommand(movies);
        AddCommand add = new AddCommand(movies, scanner);
        CommandClearTerminal clearTerminal = new CommandClearTerminal();
        SumOfOscarCountCommand sumOfOscar = new SumOfOscarCountCommand(movies);
        AverageOfOscarCountCommand averageOfOscar = new AverageOfOscarCountCommand(movies);
        InfoCommand info = new InfoCommand(movies);
        UpdateCommand update = new UpdateCommand(movies, scanner);
        SaveCommand save = new SaveCommand(movies, saveManager);
        AddIfMaxCommand addIfMax = new AddIfMaxCommand(movies, scanner);
        AddIfMinCommand addIfMin = new AddIfMinCommand(movies, scanner);
        RemoveIfLowerCommand removeIfLower = new RemoveIfLowerCommand(movies, scanner);

        addCommand(help);
        addCommand(exit);
        addCommand(show);
        addCommand(remove_by_id);
        addCommand(clear);
        addCommand(clearTerminal);
        addCommand(add);
        addCommand(sumOfOscar);
        addCommand(averageOfOscar);
        addCommand(info);
        addCommand(update);
        addCommand(save);
        addCommand(addIfMax);
        addCommand(addIfMin);
        addCommand(removeIfLower);
    }

    public static void addCommand(Command command) {
        commandMap.put(command.getName(), command);
    }

    public static void input() {
        boolean commandFound;
        Command currentCommand;
        System.out.println("Введите help для списка команд");
        while (true) {
            commandFound = false;
            System.out.print(GECKO + " > ");
            String[] input = scanner.nextLine().trim().split(" ");
            String commandName = input[0];
            String argument = input.length > 1 ? input[1] : null;

            if (commandMap.containsKey(commandName)) {
                currentCommand = commandMap.get(commandName);
                commandFound = true;

                if (currentCommand.isRequiresConfirmation()) {
                    if (!confirmCommand(currentCommand)) {
                        continue;
                    }
                }

                try {
                    currentCommand.start(argument);
                } catch (InvalidArgumentException e) {
                    System.out.println(e.getMessage());
                }

                if (currentCommand.isRequiresExit()) {
                    scanner.close();
                    return;
                }
            }

            if (!commandFound) {
                System.out.println("\u001B[31m" + "Команда неопознана" + "\u001B[0m");
            }
        }
    }

    public static boolean confirmCommand(Command command) {
        final String[] CONFIRMATION_WORDS = {"y", "yes"};
        System.out.println("Вы уверены, что хотите выполнить команду " + command.getName() + "? (y/n)");
        System.out.print(GECKO + " > ");
        String input = scanner.nextLine().trim();
        for (String word : CONFIRMATION_WORDS) {
            if (input.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }
}