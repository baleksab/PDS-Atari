package baleksab.pdsbattleship.util;

import jakarta.enterprise.context.ApplicationScoped;
import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class PasswordEncoder {

    private static final int WORKLOAD = 12;

    public String hashPassword(String plainTextPassword) {
        String salt = BCrypt.gensalt(WORKLOAD);
        return BCrypt.hashpw(plainTextPassword, salt);
    }

    public boolean checkPassword(String plainTextPassword, String hashedPassword) {
        if (hashedPassword == null || !hashedPassword.startsWith("$2a$")) {
            throw new IllegalArgumentException("Invalid hashed password format");
        }
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

}
