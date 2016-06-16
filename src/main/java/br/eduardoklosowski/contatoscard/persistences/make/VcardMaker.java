package br.eduardoklosowski.contatoscard.persistences.make;

import java.util.List;

import br.eduardoklosowski.contatoscard.models.Contact;

public interface VcardMaker {
    String makeVcard(Contact contact);

    String makeVcard(List<Contact> contacts);
}
