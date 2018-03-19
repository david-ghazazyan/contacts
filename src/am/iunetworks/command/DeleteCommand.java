package am.iunetworks.command;

import am.iunetworks.data.Contact;
import java.io.*;
import java.util.StringTokenizer;

public class DeleteCommand implements BaseCommand {

    @Override
    public void execute(Contact contact, File file) {
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReaderr = null;
        BufferedWriter bufferedWriter = null;
        File tempe = null;
        try {
            String list;
            tempe = new File("command2.txt");
            fileReader = new FileReader(file);
            bufferedReaderr = new BufferedReader(fileReader);
            fileWriter = new FileWriter(tempe);
            bufferedWriter = new BufferedWriter(fileWriter);
            while ((list = bufferedReaderr.readLine()) != null) {
                StringTokenizer delete = new StringTokenizer(list);
                String deletecontact = delete.nextToken();
                if (!deletecontact.equals(contact.getName())) {
                    bufferedWriter.write(list + "\r\n");
                }
            }
            close(bufferedWriter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(fileReader);
            close(fileWriter);
            close(bufferedReaderr);
            file.delete();
            tempe.renameTo(file);
        }
    }

    public void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
