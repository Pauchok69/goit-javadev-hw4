package goit_javadev.hw4.connection;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;

@Getter
public class ConnectionOptions {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPass;
    private final String dbName;

    public ConnectionOptions() {
        Dotenv dotenv = Dotenv.load();

        this.dbUrl = dotenv.get("DB_URL");
        this.dbUser = dotenv.get("DB_USER");
        this.dbPass = dotenv.get("DB_PASS");
        this.dbName = dotenv.get("DB_NAME");
    }
}
