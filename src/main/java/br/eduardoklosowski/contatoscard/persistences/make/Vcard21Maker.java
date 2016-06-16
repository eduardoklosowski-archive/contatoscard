package br.eduardoklosowski.contatoscard.persistences.make;

import java.util.List;

import br.eduardoklosowski.contatoscard.models.Contact;

public class Vcard21Maker implements VcardMaker {
    public String makeVcard(Contact contact) {
        return "BEGIN:VCARD\n" +
                "VERSION:2.1\n" +
                "N:" + contact.getN() + "\n" +
                "FN:" + contact.getFn() + "\n" +
                "EMAIL;PREF;INTERNET:" + contact.getEmail() + "\n" +
                "TEL;HOME;VOICE:" + contact.getTel() + "\n" +
                "TEL;HOME;CELL:" + contact.getCell() + "\n" +
                "END:VCARD\n";
    }

    public String makeVcard(List<Contact> contacts) {
        StringBuffer vcards = new StringBuffer();
        for (Contact contact : contacts) {
            vcards.append(makeVcard(contact));
        }
        return vcards.toString();
    }
}
