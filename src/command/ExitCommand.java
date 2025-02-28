package command;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", true, true);
    }

    @Override
    public void start(String argument) {
        System.out.println("Программа завершена");
    }

    @Override
    public String description() {
        return this.getName() + " - завершение программы (без сохранения файла)";
    }
}