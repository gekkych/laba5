package movie;

import exception.CommandException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class SaveManager {
    JAXBContext context;
    Marshaller marshaller;
    Unmarshaller unmarshaller;
    File file;

    public SaveManager(String filePath) {
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

    public void saveInXML(MovieDeque movies) {
        prepareSaveFile();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(convertToXML(movies).toString());
        } catch (IOException e) {
            throw new CommandException("");
        }
    }

    public MovieDeque loadFromXML() {
        if (!file.exists()) return new MovieDeque();
        try {
            MovieDeque result = (MovieDeque) unmarshaller.unmarshal(file);
            if (result == null) return new MovieDeque();
            return result;
        } catch (JAXBException e) {
            System.out.println("ошибка при загрузке, создана новая коллекция");
            return new MovieDeque();
        }
    }

    public StringWriter convertToXML(MovieDeque movies) throws CommandException {
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
