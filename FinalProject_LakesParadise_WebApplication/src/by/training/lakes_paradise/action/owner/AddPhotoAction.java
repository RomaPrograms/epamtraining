package by.training.lakes_paradise.action.owner;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.db.entity.Image;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.ImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class handles owner request for adding new homestead photo.
 */
public class AddPhotoAction extends Action {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(AddPhotoAction.class);

    private static final String NAME_OF_HIDDEN_FOLDER
            = "\\out\\artifact";

    private static final String PATH_TO_IMAGE_FOLDER
            =  "\\web\\img\\";

    private static final String PATH_TO_IMAGE_FOLDER_FROM_TABLE
            = "../img/";
    /**
     * Method executes request for adding new homestead photo.
     *
     * @param request  - user request
     * @param response - user response
     * @return URL of jsp page which should be shown
     * @throws PersistentException - exception connected with DAO
     */
    @Override
    public Forward exec(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws PersistentException {
        String stringHomesteadIdentity
                = request.getParameter("homesteadIdentity");
        int homesteadIdentity = Integer.parseInt(stringHomesteadIdentity);
        Forward forward = new Forward(
                "/owner/ownerHomesteads.html", true);
        try {
            String photoPath = request.getParameter("photo");
            int photoNameIndex = photoPath.lastIndexOf('\\');
            String photoName = photoPath.substring(photoNameIndex + 1);
            int expansionIndex = photoName.lastIndexOf('.');
            String expansion = photoName.substring(expansionIndex + 1);
            File file = new File(photoPath);
            BufferedImage image1 = ImageIO.read(file);
            String uploadPath = request.getServletContext().getRealPath("");
            Integer uploadPathIndex = uploadPath.indexOf(NAME_OF_HIDDEN_FOLDER);
            String pathToProject = uploadPath.substring(0, uploadPathIndex);
            String newPhotoPath
                    = pathToProject + PATH_TO_IMAGE_FOLDER + photoName;
            File outputFile = new File(newPhotoPath);
            ImageIO.write(image1, expansion, outputFile);

            Image image = new Image();
            image.setHomesteadId(homesteadIdentity);
            image.setPathToImage(PATH_TO_IMAGE_FOLDER_FROM_TABLE + photoName);
            ImageService imageService = factory.getService(ImageService.class);
            imageService.create(image);

        } catch (IOException e) {
            LOGGER.error("File was saved or created incorrectly.");
        }
        return forward;
    }
}
