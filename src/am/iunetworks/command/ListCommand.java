package am.iunetworks.command;

import am.iunetworks.data.Contact;
import java.io.*;
import java.util.StringTokenizer;

public class ListCommand implements BaseCommand {

    @Override
    public void execute(Contact contact, File file){

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            String list;
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            if(contact.getName() != null) {
                while ((list = bufferedReader.readLine()) != null){
                    StringTokenizer nameContact = new StringTokenizer(list);
                    String serchContact = nameContact.nextToken();
                    if(serchContact.equals(contact.getName())){
                        serchContact = nameContact.nextToken();
                        System.out.println(contact.getName() + " " + serchContact);
                    }
                }
            } else {
                while ((list = bufferedReader.readLine()) != null) {
                    System.out.println(list);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           close(fileReader);
           close(bufferedReader);
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
