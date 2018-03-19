import am.iunetworks.data.Contact;
import am.iunetworks.command.*;
import am.iunetworks.data.ContactCommand;
import am.iunetworks.exception.CommandException;
import am.iunetworks.exception.ExitException;
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            File file = new File("command.txt");
            try {
                String input = br.readLine();
                try {
                    ContactCommand contactCommand = getContact(input);
                    BaseCommand baseCommand = getBaseCommand(contactCommand.getCommand(), contactCommand.getContact());
                    if (baseCommand != null) {
                        baseCommand.execute(contactCommand.getContact(), file);
                    }
                } catch (CommandException e) {
                    e.printStackTrace();
                } catch (ExitException e) {
                    exit = true;
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static BaseCommand getBaseCommand(String command, Contact contact) throws CommandException {
        BaseCommand baseCommand = null;
        if (command.equals("add") && contact.getNumber() != null) { // yete number null chi apa @st verevi if else - i namen el null chi, hetevabar petq chi stugel da
            baseCommand = new AddCommand();
        } else if (command.equals("delete") && contact.getName() != null) {
            baseCommand = new DeleteCommand();
        } else if (command.equals("list")) {
            baseCommand = new ListCommand();
        } else if (command.equals("update")) {
            baseCommand = new UpdateCommand();
        } else {
            throw new CommandException("Incorrect command");
        }
        return baseCommand;
    }

    private static ContactCommand getContact(String input) throws CommandException, ExitException {
        String command = null;
        String name = null;
        String number = null;
        StringTokenizer tk = new StringTokenizer(input, " ");
        if (tk.hasMoreTokens()) {
            command = tk.nextToken();
            if (tk.hasMoreTokens()) {
                name = tk.nextToken();
                if (tk.hasMoreTokens()) {
                    number = tk.nextToken();
                }
            }
        }

        Contact contact = new Contact();
        contact.setNumber(number);
        contact.setName(name);

        if (command == null) {
            throw new CommandException("Incorrect command");
        } else if (command.equals("exit")) {
            throw new ExitException();
        }

        ContactCommand contactCommand = new ContactCommand();
        contactCommand.setContact(contact);
        contactCommand.setCommand(command);

        return contactCommand;
    }


}
