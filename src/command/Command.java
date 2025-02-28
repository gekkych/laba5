package command;

public abstract class Command implements Comparable<Command>{
    private final String name;
    private final boolean requiresExit;
    private final boolean requiresConfirmation;

    public Command(String name, boolean requiresConfirmation, boolean requiresExit) {
        this.name = name;
        this.requiresExit = requiresExit;
        this.requiresConfirmation = requiresConfirmation;
    }

    public abstract void start(String argument);
    public abstract String description();

    @Override
    public int compareTo(Command command) {
        return this.getName().compareTo(command.getName());
    }

    public String getName() {
        return name;
    }

    public boolean isRequiresExit() {
        return requiresExit;
    }

    public boolean isRequiresConfirmation() {
        return requiresConfirmation;
    }
}