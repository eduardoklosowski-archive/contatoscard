package br.eduardoklosowski.contatoscard;

import java.util.ArrayList;
import java.util.List;

import br.eduardoklosowski.contatoscard.models.Contact;
import br.eduardoklosowski.contatoscard.persistences.VcardFile;

public class Cmd {
    public static void main(String[] args) {
        if (args.length >= 1) {
            if (args[0].equals("save") && args.length >= 5) {
                save(args[1], args[2], args[3], args[4]);
                System.exit(0);
            } else if (args[0].equals("remove") && args.length >= 2) {
                remove(args[1]);
                System.exit(0);
            } else if (args[0].equals("list")) {
                list();
                System.exit(0);
            } else if (args[0].equals("filter-name") && args.length >= 2) {
                filterName(args[1]);
                System.exit(0);
            }
        }
        printHelp();
    }

    public static void printHelp() {
        System.out.println("Uso: contatos-card [ACAO]");
        System.out.println();
        System.out.println("ACAO:");
        System.out.println("  list");
        System.out.println("  save <nome> <e-mail> <telefone> <celular>");
        System.out.println("  remove <nome>");
        System.out.println("  filter-name <nome>");
    }

    public static void printContact(List<Contact> contacts) {
        for (Contact contact : contacts) {
            printContact(contact);
        }
    }

    public static void printContact(Contact contact) {
        System.out.println(contact.getFn() + ":");
        System.out.println("  E-mail: " + contact.getEmail());
        System.out.println("  Tel: " + contact.getTel());
        System.out.println("  Cell: " + contact.getCell());
    }

    public static void list() {
        VcardFile dir = new VcardFile(".");
        printContact(dir.readFile());
    }

    public static void save(String fn, String email, String tel, String cell) {
        Contact contact = new Contact();
        contact.setFn(fn);
        contact.setEmail(email);
        contact.setTel(tel);
        contact.setCell(cell);

        String filename = contact.genN() + ".vcf";
        filename = filename.replaceFirst(";", ", ");
        VcardFile vcf = new VcardFile(filename);
        vcf.saveFile(contact);
    }

    public static void remove(String fn) {
        Contact contact = new Contact();
        contact.setFn(fn);

        String filename = (contact.genN() + ".vcf").replaceFirst(";", ", ");
        VcardFile vcf = new VcardFile(filename);
        vcf.removeFile();
    }

    public static void filterName(String name) {
        String nameLower = name.toLowerCase();
        VcardFile dir = new VcardFile(".");
        List<Contact> filtered = new ArrayList<Contact>();
        for (Contact contact : dir.readFile()) {
            if (contact.getFn().toLowerCase().contains(nameLower)) {
                filtered.add(contact);
            }
        }
        printContact(filtered);
    }
}
