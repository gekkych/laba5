package movie;

import exception.CommandException;

import javax.xml.bind.*;
import java.io.*;
import java.util.Scanner;

public class SaveManager {
    JAXBContext context;
    Marshaller marshaller;
    Unmarshaller unmarshaller;
    File file;
    MovieValidator validator = new MovieValidator();

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
    public String getFileName() {
        return file.getName();
    }

    public void saveInXML(MovieDeque movies) {
        prepareSaveFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(convertToXML(movies).toString());
        } catch (IOException e) {
            throw new CommandException("");
        }
    }

    public MovieDeque loadFromXML() {
        if (!file.exists()) return new MovieDeque();
        try (Scanner scanner = new Scanner(file)) {
            StringBuilder xmlContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                xmlContent.append(scanner.nextLine()).append("\n");
            }
            if (xmlContent.isEmpty()) {
                return new MovieDeque();
            }
            StringReader stringReader = new StringReader(xmlContent.toString());
            MovieDeque result = (MovieDeque) unmarshaller.unmarshal(stringReader);
            if (result == null) return new MovieDeque();

            return result;
        } catch (JAXBException | FileNotFoundException e) {
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

    private void prepareSaveFile() throws CommandException {
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
