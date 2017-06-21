package lab6;

import java.sql.Statement;
import java.util.HashMap;

public class Database {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static HashMap <String, String> users;
    private static HashMap <String, String> dict;
    public Database() throws Exception {
        Statement stmt = null;
        Class.forName(JDBC_DRIVER);
        users = new HashMap<>();
        users.put("admin", "2222");
        users.put("aliona", "1111");
        dict = new HashMap<>();
        dict.put("house", "a living place");
        dict.put("dog", "a kind of domestic animal");
    }


    public boolean checkCredentials(String login, String password) {
        if (users.get(login) != null && password.equals(users.get(login)))
            return true;
        else
            return false;
    }


    public boolean add(String word, String definition) {
        if (dict.get(word) != null)
            return false;
        else
            dict.put(word, definition);
        return true;
    }

    public String find(String str) {
        if (dict.get(str) != null)
            return dict.get(str);
        if (dict.get(str.toLowerCase()) != null)
            return dict.get(str.toLowerCase());
        return null;
    }
}
