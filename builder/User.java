package builder;

/**
 * Created by lomak on 19.11.2016.
 */
public class User {
    private String name;
    private int age;
    private String login;
    private String passwd;
    private boolean isAdmin;
    private String email;

    public User(String name, int age, String login, String passwd, boolean isAdmin, String email) {
        this.name = name;
        this.age = age;
        this.login = login;
        this.passwd = passwd;
        this.isAdmin = isAdmin;
        this.email = email;
    }

}
