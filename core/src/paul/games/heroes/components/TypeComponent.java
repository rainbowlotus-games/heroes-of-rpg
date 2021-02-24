package paul.games.heroes.components;

import com.badlogic.ashley.core.Component;

public class TypeComponent implements Component {
    public enum Type {
        PLAYER(0),
        ENEMY(1),
        OTHER(2);

        private final int id;

        Type(int id) {
            this.id = id;
        }
    }

    private Type type;

    public void setType(Type type) {
        this.type = type;
    }

}
