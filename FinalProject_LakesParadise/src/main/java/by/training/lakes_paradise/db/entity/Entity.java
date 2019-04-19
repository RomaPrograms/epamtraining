package by.training.lakes_paradise.db.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity class with "id" which is used by all other entity classes.
 */
public abstract class Entity implements Serializable {
    /**
     * Id of entity class.
     */
    private int id;

    /**
     * Gets the value of id property.
     *
     * @return value of id property
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of id property.
     *
     * @param newId - value of id property
     */
    public void setId(final int newId) {
        this.id = newId;
    }

    /**
     * Checks equality of entities by id.
     *
     * @param object - object of entity class
     * @return {@true} if entities are equal, {@false} otherwise
     */
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

    /**
     * Returns hash code of entities calculated by id.
     *
     * @return hash code of entities calculated by id
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /**
     * Returns description of Entity class in String.
     *
     * @return description of Entity class in String
     */
    @Override
    public String toString() {
        return "Entity{"
                + "id=" + id
                + '}';
    }
}
