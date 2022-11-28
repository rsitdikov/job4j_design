package ru.job4j.ood.lsp;

class Account {
    private String login;
    private String password;

    Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Account{"
                + "login='" + login + '\''
                + ", password='" + password + '\''
                + '}';
    }
}

class User {
    protected Account account;

    User(Account account) {
        validate(account);
        this.account = account;
    }

    private void validate(Account account) {
        if (account.getLogin() == null || account.getLogin().length() < 3) {
            throw new IllegalArgumentException("Login must contain at least 3 characters!");
        }
        if (account.getPassword() == null || account.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must contain at least 8 characters!");
        }
    }

    public void setAccount(Account account) {
        validate(account);
        this.account = account;
    }
}

class MailUser extends User {

    MailUser(Account account) {
        super(account);
    }

    /*
    Третий пример нарушения принципа LSP:
    в подклассе не сохранены все условия базового класса.
    */
    @Override
    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{"
                + "account=" + account
                + '}';
    }
}

public class ThirdExample {

    public static void main(String[] args) {
        User first = new MailUser(new Account("user", "12345678"));
        first.setAccount(new Account(null, "12"));
        System.out.println(first.toString());
        User second = new User(new Account("u1", "12345678"));
    }
}
