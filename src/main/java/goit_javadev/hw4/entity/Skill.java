package goit_javadev.hw4.entity;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class Skill {
    private long id;
    private Scope scope;
    private Level level;
    private Boolean status;

    private List<Developer> developers;

    public enum Scope {
        JAVA,
        PHP,
        MYSQL,
        PYTHON,
        HTML,
        CSS
    }

    public enum Level {
        JUNIOR(1),
        MIDDLE(2),
        SENIOR(3),
        EXPERT(4);

        @Getter
        private final int value;

        Level(int id) {
            this.value = id;
        }

        public static Level valueOf(int id) {
            Level level;

            switch (id) {
                case 1:
                    level = Level.JUNIOR;
                    break;
                case 2:
                    level = Level.MIDDLE;
                    break;
                case 3:
                    level = Level.SENIOR;
                    break;
                case 4:
                    level = Level.EXPERT;
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            return level;
        }
    }
}
