package by.training.lakes_paradise.db.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity class with "id" that used by all other entity classes.
 */
public abstract class Entity implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    @Override
    public boolean equals(final Object object) {
        if (object != null) {
            if (object != this) {
                if (object.getClass() == getClass() && id != 0) {
                    return id == ((Entity) object).id;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
