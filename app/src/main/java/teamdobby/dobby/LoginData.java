package teamdobby.dobby;

import java.util.ArrayList;

/**
 * Created by Marie on 13.04.2016.
 */
public class LoginData {
    static String curruser;
    static boolean logged;
    String name;
    String pass;
    static ArrayList<LoginData> user = new ArrayList<LoginData>();

    static void main() {
        user.add(new LoginData("Dobby", "123"));
        user.add(new LoginData("Test", "123"));
        user.add(new LoginData("User", "test"));
    }

    public LoginData(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    static boolean isValidName(String name) {
        for (int i = 0; i < user.size(); i++) {
            if (user.get(i) != null) {
                if (user.get(i).name.equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean isValidPass(String name, String pass) {
        int id;
        for (int i = 0; i < user.size(); i++) {
            if (user.get(i).name.equals(name)) {
                if (user.get(i).pass.equals(pass)) {
                    return true;
                }
            }
        }
        return false;
    }

    static void setCurrUser(String Name){
        curruser=Name;
    }

    static String getCurrUser(){
        if (curruser != null && curruser!="") {
            return curruser;
        }
        else return "ERROR";
    }

    static void setLogged(){
        logged=true;
    }
    static void notLogged(){
        logged=false;
    }
    static boolean getLogged(){
        return logged;
    }
}
