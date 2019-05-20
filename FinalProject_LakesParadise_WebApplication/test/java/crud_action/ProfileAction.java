package crud_action;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.Role;
import by.training.lakes_paradise.db.mysql.TransactionFactoryRealization;
import by.training.lakes_paradise.db.pool.ConnectionPoolRealization;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.ProfileService;
import by.training.lakes_paradise.service.ServiceFactory;
import by.training.lakes_paradise.service.ServiceFactoryRealization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class ProfileAction {
    private static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/lakes_paradise_db?useUnicode=true&characterEncoding=UTF-8";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "9512684Roma";
    private static final int DB_POOL_START_SIZE = 10;
    private static final int DB_POOL_MAX_SIZE = 1000;

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ProfileAction.class);

    private ProfileService profileService;
    private int createdProfileId;
    private Profile profile1 = new Profile();
    private Profile profile2 = new Profile();
    private Profile profile3 = new Profile();
    private Profile profile4 = new Profile();
    private Profile profile5 = new Profile();
    private Profile profile6 = new Profile();
    private Profile profile7 = new Profile();

    @BeforeClass
    public void initialiseParsingAction() {
        try {
            ConnectionPoolRealization.getInstance().init(DB_DRIVER_CLASS,
                    DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE,
                    DB_POOL_MAX_SIZE);

            TransactionFactoryRealization transaction
                    = new TransactionFactoryRealization();
            ServiceFactory serviceFactory
                    = new ServiceFactoryRealization(transaction);
            profileService
                    = serviceFactory.getService(ProfileService.class);

            profile1.setId(1);
            profile1.setLogin("user1");
            profile1.setPassword("11111");
            profile1.setRole(Role.USER);

            profile2.setId(2);
            profile2.setLogin("user2");
            profile2.setPassword("22222");
            profile2.setRole(Role.USER);

            profile3.setId(3);
            profile3.setLogin("user3");
            profile3.setPassword("33333");
            profile3.setRole(Role.USER);

            profile4.setId(4);
            profile4.setLogin("user4");
            profile4.setPassword("44444");
            profile4.setRole(Role.USER);

            profile5.setId(5);
            profile5.setLogin("owner1");
            profile5.setPassword("11111");
            profile5.setRole(Role.OWNER);

            profile6.setId(6);
            profile6.setLogin("owner2");
            profile6.setPassword("22222");
            profile6.setRole(Role.OWNER);

            profile7.setId(7);
            profile7.setLogin("admin");
            profile7.setPassword("11111");
            profile7.setRole(Role.ADMINISTRATOR);

        } catch (PersistentException e) {
            LOGGER.error("It is impossible to initialize application", e);
        }
    }

    @DataProvider(name = "dataProviderForReadProfileByIdAction")
    public Object[] dataProviderForReadProfileByIdAction() {
        return new Object[][]{{profile1, 1}, {profile4, 4}, {profile6, 6}};
    }

    @Test(dataProvider = "dataProviderForReadProfileByIdAction", priority = 2)
    public void readProfileByIdAction(Profile expectedProfile, int id) {
        try {
            Profile actualProfile = profileService.read(id);
            expectedProfile.setPassword(null);
            Assert.assertEquals(actualProfile, expectedProfile);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "dataProviderForReadProfileByLoginAndPasswordAction")
    public Object[] dataProviderForReadProfileByLoginAndPasswordAction() {
        return new Object[]{profile1, profile3, profile5};
    }

    @Test(dataProvider = "dataProviderForReadProfileByLoginAndPasswordAction",
            priority = 1)
    public void readProfileByLoginAndPasswordAction(Profile expectedProfile) {
        try {
            Profile actualProfile = profileService.read(
                    expectedProfile.getLogin(),
                    expectedProfile.getPassword());
            expectedProfile.setPassword(null);
            Assert.assertEquals(actualProfile, expectedProfile);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "dataProviderForCreateAllProfilesAction")
    public Object[] dataProviderForCreateAllProfilesAction() {
        return new Object[]{profile1, profile2, profile3, profile4, profile5,
                profile6, profile7};
    }

    //@Ignore("This is test for adding profiles to the database")
    @Test(dataProvider = "dataProviderForCreateAllProfilesAction")
    public void createAllProfilesAction(Profile profile) {
        try {
            profileService.update(profile);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "dataProviderForCreateProfileAction")
    public Object[] dataProviderForCreateProfileAction() {
        Profile profile = new Profile();
        profile.setLogin("user5");
        profile.setPassword("55555");
        profile.setRole(Role.USER);
        return new Object[]{profile};
    }

    @Test(dataProvider = "dataProviderForCreateProfileAction",
            priority = 3)
    public void createProfileAction(Profile expectedProfile) {
        try {
            int profileId = profileService.create(expectedProfile);
            createdProfileId = profileId;
            expectedProfile.setId(profileId);
            Profile actualProfile = profileService.read(profileId);
            expectedProfile.setPassword(null);
            Assert.assertEquals(actualProfile, expectedProfile);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "dataProviderForUpdateProfileAction")
    public Object[] dataProviderForUpdateProfileAction() {
        Profile profile = new Profile();
        profile.setId(createdProfileId);
        profile.setLogin("user6");
        profile.setPassword("66666");
        profile.setRole(Role.USER);
        return new Object[][]{{profile, createdProfileId}};
    }

    @Test(dataProvider = "dataProviderForUpdateProfileAction",
            priority = 4)
    public void updateProfileAction(Profile expectedProfile, int id) {
        try {
            profileService.update(expectedProfile);
            Profile actualProfile = profileService.read(id);
            expectedProfile.setPassword(null);
            Assert.assertEquals(actualProfile, expectedProfile);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "dataProviderForDeleteProfileAction")
    public Object[] dataProviderForDeleteProfileAction() {
        return new Object[]{createdProfileId};
    }

    @Test(dataProvider = "dataProviderForDeleteProfileAction",
            priority = 5)
    public void deleteProfileAction(int id) {
        try {
            profileService.delete(id);
            Profile actualProfile = profileService.read(id);
            Assert.assertNull(actualProfile);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

}
