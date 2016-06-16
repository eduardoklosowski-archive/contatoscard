package br.eduardoklosowski.contatoscard.models;

public class Contact {
    private String n;
    private String fn;
    private String email;
    private String tel;
    private String cell;

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String genN() {
        String fn = this.getFn();
        String[] names = fn.split(" ");
        if (names.length <= 1) {
            this.setN(fn);
        } else {
            StringBuffer name = new StringBuffer();
            name.append(names[names.length - 1] + ";");
            for (int i = 0, m = names.length - 1; i < m; i++) {
                name.append((i == 0 ? "" : " ") + names[i]);
            }
            this.setN(name.toString());
        }
        return this.getN();
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }
}
