package movie;

import exception.CommandException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class MovieXML {
    MovieDeque movies;
    JAXBContext context;
    Marshaller marshaller;
    Unmarshaller unmarshaller;
    File file;
    BufferedWriter writer;

    public MovieXML(MovieDeque movies, String filePath) {
        this.movies = movies;
        file = new File(filePath);
        try {
            context = JAXBContext.newInstance(MovieDeque.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            throw new CommandException("");
        }
    }

    public void saveInXML() {
        prepareSaveFile();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(convertToXML().toString());
        } catch (IOException e) {
            throw new CommandException("");
        }

    }

    public StringWriter convertToXML() throws CommandException {
        try {
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(movies, stringWriter);
            return stringWriter;
        } catch (JAXBException e) {
            throw new CommandException("");
        }
    }

    private void prepareSaveFile() throws CommandException{
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("Создан новый файл сохранения");
                }
            }
        } catch (IOException e) {
            throw new CommandException("");
        }
    }



}
