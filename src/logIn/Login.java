package logIn;

public class Login {

	public static boolean authenticate(String username, String password) {
        // get from database and check 
        if (username.equals("bob") && password.equals("secret")) {
            return true;
        }
        return false;
    }
}
