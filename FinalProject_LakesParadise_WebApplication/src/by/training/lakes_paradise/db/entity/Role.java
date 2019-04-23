package by.training.lakes_paradise.db.entity;

/**
 * Enum class which describes role.
 */
public enum Role {
    /**
     * Administrator of site.
     */
    ADMINISTRATOR("admin"),
    /**
     * Owner of homesteads.
     */
    OWNER("owner"),
    /**
     * User of site.
     */
    USER("user");

    /**
     * Values of roles.
     */
    private String name;

    /**
     * One-argument constructor.
     *
     * @param roleName - type of role
     */
    Role(final String roleName) {
        this.name = roleName;
    }

    /**
     * Gets the value of property role.
     *
     * @return value of property role
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the identity of role.
     *
     * @return identity of role
     */
    public Integer getIdentity() {
        return ordinal();
    }

    /**
     * Returns role by identity.
     *
     * @param identity - identity of role
     * @return role
     */
    public static Role getByIdentity(final Integer identity) {
        return Role.values()[identity];
    }
}
