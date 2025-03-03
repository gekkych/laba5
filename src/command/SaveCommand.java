package command;

import movie.MovieDeque;
import movie.SaveManager;

public class SaveCommand extends Command{
    MovieDeque movies;
    SaveManager saveManager;

    public SaveCommand(MovieDeque movies, SaveManager saveManager) {
        super("save", true, false);
        this.movies = movies;
        this.saveManager = saveManager;
    }

    @Override
    public void start(String argument) {
        saveManager.saveInXML(movies);
        System.out.println("Коллекция сохранена в файл " + saveManager.getFileName());
    }
    @Override
    public String description() {
        return this.getName() + " - сохранить коллекцию в файл";
    }
}
