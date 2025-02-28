package command;

public class CommandClearTerminal extends Command{
    public CommandClearTerminal() {
        super("clear_terminal", false, false);
    }

    @Override
    public void start(String argument) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public String description() {
        return this.getName() + " - очищает терминал (UNIX-only)";
    }
}
