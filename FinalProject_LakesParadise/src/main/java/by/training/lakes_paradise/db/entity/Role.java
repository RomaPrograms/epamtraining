package by.training.lakes_paradise.db.entity;

/**
 * Enum class which describes role.
 */
public enum Role {
    ADMINISTRATOR("admin"),
    OWNER("owner"),
    USER("user");

    private String name;

    Role(final String roleName) {
        this.name = roleName;
    }

    public String getName() {
        return name;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public static Role getByIdentity(final Integer identity) {
        return Role.values()[identity];
    }
}
