public class Main {
    public static void main(String[] args) {
        validateCredentials("login", "password", "password");
        validateCredentials("login%", "password", "password");
        validateCredentials("login17login17login17login17login17", "pass", "pass");
        validateCredentials("login", "pass&", "pass");
        validateCredentials("login", "pass", "pass^&");


    }

    public static boolean validateCredentials(String login, String password, String repeatPassword) {
        try {
            checkLogin(login);
            checkPassword(password, repeatPassword);
            return true;
        } catch (WrongLoginException e) {
            System.out.println("Invalid login: " + e.getMessage());
            return false;
        } catch (WrongPasswordException e) {
            System.out.println("Invalid password: " + e.getMessage());
            return false;
        }
    }


    private static void checkLogin(String login) {
        if (hasLengthMoreThen(login, 20) || isNoneAlphanumeric(login)) {
            throw new WrongLoginException("Login is wrong");
        }
    }

    private static void checkPassword(String password, String repeatPassword) {
        if (hasLengthMoreThen(password, 20) || isNoneAlphanumeric(password) || stringNotEquals(password, repeatPassword)) {
            throw new WrongPasswordException("Password is wrong");
        }
    }

    private static boolean stringNotEquals(String value, String value2) {
        return !value.equals(value2);
    }

    private static boolean isNoneAlphanumeric(String string) {
        final String alphabet = "abcdefghijclmnopqrstuvwxyz123456789_";
        for (int i = 0; i < string.length(); i++) {
            if (!alphabet.contains(String.valueOf(string.charAt(i)).toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasLengthMoreThen(String string, int length) {
        return string.length() > length;
    }
}