package command;

import exception.CommandException;
import movie.MovieDeque;
import movie.MovieXML;

import java.io.*;

public class SaveCommand extends Command{
    MovieXML movieXML;

    public SaveCommand(MovieXML movieXML) {
        super("save", true, false);
        this.movieXML = movieXML;
    }

    @Override
    public void start(String argument) {
        movieXML.saveInXML();
    }
    @Override
    public String description() {
        return this.getName() + " - сохранить коллекцию в файл";
    }
}
