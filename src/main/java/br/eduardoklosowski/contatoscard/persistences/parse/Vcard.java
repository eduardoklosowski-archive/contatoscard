package br.eduardoklosowski.contatoscard.persistences.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import br.eduardoklosowski.contatoscard.models.Contact;

public class Vcard {
    public static List<Contact> parse(File file) {
        List<Contact> contacts = new ArrayList<Contact>();
        Scanner fileScan;
        try {
            fileScan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return contacts;
        }

        boolean incard = false;
        Map<String, String> attrs = null;
        String line;
        while (fileScan.hasNextLine()) {
            line = fileScan.nextLine();
            if ("".equals(line)) {
                continue;
            }

            if ("BEGIN:VCARD".equals(line)) {
                incard = true;
                attrs = new HashMap<String, String>();
            } else if ("END:VCARD".equals(line)) {
                incard = false;
                if (attrs.get("VERSION").equals("2.1")) {
                    contacts.add(newVcard21(attrs));
                }
            }
            if (incard) {
                String parts[] = line.split(":");
                attrs.put(parts[0], parts[1]);
            }
        }

        fileScan.close();

        return contacts;
    }

    private static Contact newVcard21(Map<String, String> attrs) {
        Contact contact = new Contact();
        contact.setN(attrs.get("N"));
        contact.setFn(attrs.get("FN"));
        contact.setEmail(attrs.get("EMAIL;PREF;INTERNET"));
        contact.setTel(attrs.get("TEL;HOME;VOICE"));
        contact.setCell(attrs.get("TEL;HOME;CELL"));
        return contact;
    }
}
