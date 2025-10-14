package tiendat.example;

import java.util.regex.Pattern;
import java.util.HashSet;
import java.util.Set;

public class AccountService {
    private static final Pattern EMAIL_REGEX = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    // Password: min 7 chars, at least 1 uppercase, 1 digit, 1 special
    private static final Pattern PASSWORD_REGEX = Pattern.compile(
            "^(?=.{7,}$)(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).*$");

    // Track successful registrations (username/email) to enforce uniqueness across
    // successful creates
    private final Set<String> registeredUsernames = new HashSet<>();
    private final Set<String> registeredEmails = new HashSet<>();

    public boolean isValidEmail(String email) {
        if (email == null)
            return false;
        return EMAIL_REGEX.matcher(email).matches();
    }

    public boolean isValidPassword(String password) {
        if (password == null)
            return false;
        return PASSWORD_REGEX.matcher(password).matches();
    }

    // - username, password, email là bắt buộc
    // - username phải > 3 kí tự
    // - username và email phải là unique nhưng only against previously SUCCESSFUL
    // registrations
    // - password > 6 kí tự, có ít nhất 1 chữ hoa, 1 số, 1 ký tự đặc biệt
    // - email hợp lệ
    public boolean registerAccount(String username, String password, String email) {
        if (username == null || username.trim().isEmpty())
            return false;
        String uname = username.trim();
        if (uname.length() <= 3)
            return false;
        if (password == null)
            return false;
        if (!isValidPassword(password))
            return false;
        if (email == null || email.trim().isEmpty())
            return false;
        String mail = email.trim();
        if (!isValidEmail(mail))
            return false;

        // uniqueness only against previously successful registrations
        if (registeredUsernames.contains(uname))
            return false;
        if (registeredEmails.contains(mail))
            return false;

        // Passed all checks: record as successful registration
        registeredUsernames.add(uname);
        registeredEmails.add(mail);
        return true;
    }

    // Helper for tests to clear tracked successful registrations
    public void resetRegistered() {
        registeredUsernames.clear();
        registeredEmails.clear();
    }
}
