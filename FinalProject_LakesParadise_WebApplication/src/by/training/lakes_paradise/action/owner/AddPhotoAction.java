package by.training.lakes_paradise.action.owner;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.exception.PersistentException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class handles owner request for adding new homestead photo.
 */
public class AddPhotoAction extends Action {



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
        HttpSession session = request.getSession();
        Forward forward = new Forward((String) session.getAttribute("lastAction"), true);
        try {
            //int homesteadIdentity = Integer.parseInt(request.getParameter("homesteadIdentity"));
            String photoAddress = request.getParameter("photo");
            //String photoName = photoAddress.substring(photoAddress.lastIndexOf('\\') + 1);
            File file = new File(photoAddress);
            BufferedImage image1 = ImageIO.read(file);
            File outputFile = new File("web\\img\\please.jpg");
            String uploadPath = request.getServletContext().getRealPath("");

            ImageIO.write(image1, "png", outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return forward;
    }

//    private String extractFileName(Part part) {
//        String contentDisp = part.getHeader("content-disposition");
//        String[] items = contentDisp.split(";");
//        for(String s : items) {
//            if(s.trim().startsWith("filename")) {
//                return s.substring(s.indexOf("=") + 2, s.length() - 1);
//            }
//        }
//        return "";
//    }
}
