package am.iunetworks.command;

import am.iunetworks.data.Contact;
import java.io.*;
import java.util.StringTokenizer;

public class UpdateCommand implements BaseCommand {

    @Override
    public void execute(Contact contact, File file) {
        File tempe = new File("command2.txt");
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            String list ;
            fileReader = new FileReader(file);
            fileWriter = new FileWriter(tempe);
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);
            while ((list = bufferedReader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(list);
                String tkNameNumber = stringTokenizer.nextToken();
                if (!contact.getName().equals(tkNameNumber)) {
                    bufferedWriter.write(list + "\r\n");
                } else {
                    bufferedWriter.write(contact.getName() + " " + contact.getNumber() + "\r\n");
                }
            }

            close(bufferedWriter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(fileReader);
            close(fileWriter);
            close(bufferedReader);
            file.delete();
            tempe.renameTo(file);
        }
    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
