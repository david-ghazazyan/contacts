package am.iunetworks.command;

import am.iunetworks.data.Contact;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddCommand implements BaseCommand {
    @Override
    public void execute(Contact contact, File file) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file.getAbsoluteFile(),true);
            fileWriter.write(contact.getName() + " " + contact.getNumber() + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(fileWriter);
        }
    }
    public void close(Closeable closeable) {
        if(closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
