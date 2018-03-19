package am.iunetworks.command;

import am.iunetworks.data.Contact;

import java.io.File;

public interface BaseCommand {
    void execute(Contact contact, File file);
}
