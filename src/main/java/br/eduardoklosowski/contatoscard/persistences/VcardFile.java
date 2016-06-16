package br.eduardoklosowski.contatoscard.persistences;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import br.eduardoklosowski.contatoscard.models.Contact;
import br.eduardoklosowski.contatoscard.persistences.make.Vcard21Maker;
import br.eduardoklosowski.contatoscard.persistences.make.VcardMaker;
import br.eduardoklosowski.contatoscard.persistences.parse.Vcard;

public class VcardFile {
    private String path;

    public VcardFile(String path) {
        this.path = path;
    }

    private File getFile() {
        return new File(path);
    }

    public List<Contact> readFile() {
        File file = this.getFile();

        if (file.isDirectory()) {
            List<Contact> contacts = new ArrayList<Contact>();
            for (File subfile : file.listFiles()) {
                if (subfile.getName().endsWith(".vcf")) {
                    contacts.addAll(Vcard.parse(subfile));
                }
            }
            return contacts;
        }

        return Vcard.parse(this.getFile());
    }

    public void saveFile(Contact contact) {
        VcardMaker maker = new Vcard21Maker();
        this.saveFile(this.getFile(), maker.makeVcard(contact));
    }

    public void saveFile(List<Contact> contacts) {
        if (this.getFile().isDirectory()) {
            this.saveDirectory(contacts);
            return;
        }

        VcardMaker maker = new Vcard21Maker();
        this.saveFile(this.getFile(), maker.makeVcard(contacts));
    }

    private void saveDirectory(List<Contact> contacts) {
        VcardMaker maker = new Vcard21Maker();
        for (Contact contact : contacts) {
            this.saveFile(new File(contact.getN().replaceFirst(";", ", ")),
                    maker.makeVcard(contact));
        }
    }

    private void saveFile(File file, String content) {
        try {
            PrintStream ps = new PrintStream(file);
            ps.print(content);
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeFile() {
        this.getFile().delete();
    }
}
