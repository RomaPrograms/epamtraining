package by.training.lakes_paradise.custom;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class declares user-defined navbar-tag.
 */
public class NavbarTag extends TagSupport {
    /**
     * Current profile of user.
     */
    private Profile profile;
    /**
     * Current language of page.
     */
    private Locale language;
    /**
     * Message for user connected with authentication.
     */
    private String logInMessage;

    /**
     * Name of action for changing language to english.
     */
    private static final String ENGLISH_LANGUAGE_URL = "/language/en_US.html";
    /**
     * Name of action for changing language to belorussian.
     */
    private static final String BELORUSSIAN_LANGUAGE_URL
            = "/language/be_BY.html";
    /**
     * Name of action for changing language to russian.
     */
    private static final String RUSSIAN_LANGUAGE_URL = "/language/ru_RU.html";

    /**
     * Name of action for opening menu page.
     */
    private static final String MENU_URL = "/menu.html";
    /**
     * Name of action for opening registration page.
     */
    private static final String SIGN_UP_URL = "/sign_up.html";
    /**
     * Name of action for opening homesteads catalog page.
     */
    private static final String HOMESTEADS_LIST_HTML = "/homesteadsList.html";
    /**
     * Name of action for opening users catalog page.
     */
    private static final String USERS_LIST_URL = "/admin/usersList.html";
    /**
     * Name of action for opening user cabinet page.
     */
    private static final String USER_CABINET_URL
            = "/authorized_user/userCabinet.html";

    private static final String OWNER_CABINET_URL
            = "/owner/ownerCabinet.html";
    /**
     * Name of action for opening owner homesteads catalog page.
     */
    private static final String OWNER_HOMESTEADS_URL
            = "/owner/ownerHomesteads.html";
    /**
     * Name of action for authentication.
     */
    private static final String LOG_IN_URL = "/log_in.html";

    /**
     * Name of action to log out.
     */
    private static final String LOG_OUT_URL = "/authorized_user/log_out.html";

    /**
     * Name of action for opening admin cabinet page.
     */
    private static final String ADMIN_CABINET_URL
            = "/admin/cabinetAdmin.html";

    /**
     * Gets the value of profile property.
     *
     * @return value of profile property
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Sets the value of profile property.
     *
     * @param curProfile - value of profile property
     */
    public void setProfile(final Profile curProfile) {
        this.profile = curProfile;
    }

    /**
     * Gets the value of language property.
     *
     * @return value of language property
     */
    public Locale getLanguage() {
        return language;
    }

    /**
     * Sets the value of curLanguage property.
     *
     * @param curLanguage - value of curLanguage property
     */
    public void setLanguage(final Locale curLanguage) {
        this.language = curLanguage;
    }

    /**
     * Gets the value of logInMessage property.
     *
     * @return value of logInMessage property
     */
    public String getLogInMessage() {
        return logInMessage;
    }

    /**
     * Sets the value of curLogInMessage property.
     *
     * @param curLogInMessage - value of curLogInMessage property
     */
    public void setLogInMessage(final String curLogInMessage) {
        this.logInMessage = curLogInMessage;
    }

    /**
     * Method that starts processing of navbar-tag.
     *
     * @return code which means how to process navbar-tag body
     */
    @Override
    public int doStartTag() throws JspException {

        try {
            JspWriter out = pageContext.getOut();
            ResourceBundle rb = ResourceBundle
                    .getBundle("property.text", language);
            out.write("<nav class=\"navbar fixed-top scrolling-navbar\">");

            out.write(" <div class=\"container\">\n" +
                    "        <div class=\"navbar-header\">\n" +
                    "            <a class=\"navbar-brand blue-text\">" + rb.getString("siteName") + "</a>\n" +
                    "        </div>\n" +
                    "        <div class=\"collapse navbar-collapse\" id=\"myNavbar\">\n" +
                    "            <ul id=\"list\" class=\"nav navbar-nav\">");

            out.write("<li><a href=\"" + MENU_URL + "\">"
                    + rb.getString("navbarMenu") + "</a>\n" + "</li>\n");

            if (profile == null) {
                out.write("<li><a href=\"" + SIGN_UP_URL + "\">"
                        + rb.getString("registration") + "</a>\n" + "</li>\n");
            }

            if (profile != null && profile.getRole().equals(Role.ADMINISTRATOR)) {
                out.write("<li><a href=\"" + SIGN_UP_URL + "\">"
                        + rb.getString("signUpOwner") + "</a>\n" + "</li>\n");
                out.write(" <li><a href=\"" + USERS_LIST_URL + "\">"
                        + rb.getString("users") + "</a></li>");
            }

            out.write(" <li><a href=\"" + HOMESTEADS_LIST_HTML + "\">"
                    + rb.getString("homesteads") + "</a></li>");

            if (profile != null
                    && profile.getRole().equals(Role.USER)) {
                out.write("<li><a href=\"" + USER_CABINET_URL + "\">" + rb.getString("personalCabinet") + "</a></li>");
            } else {
                if(profile != null && profile.getRole().equals(Role.USER)) {
                    out.write("<li><a href=\"" + ADMIN_CABINET_URL + "\">" + rb.getString("personalCabinet") + "</a></li>");
                } else {
                    if(profile != null) {
                        out.write("<li><a href=\"" + OWNER_CABINET_URL + "\">" + rb.getString("personalCabinet") + "</a></li>");
                    }
                }
            }

            if (profile != null && profile.getRole().equals(Role.OWNER)) {
                out.write("<li><a href=\"" + OWNER_HOMESTEADS_URL + "\">" + rb.getString("navbarOwnerHomesteads") + "</a></li>");
            }

            out.write("<li class=\"dropdown\">\n" +
                    "                    <a class=\"dropdown-toggle\"\n" +
                    "                       data-toggle=\"dropdown\">" + rb.getString("navbarLanguage") +
                    "                        <span class=\"caret\"></span></a>");

            out.write("<ul class=\"dropdown-menu\">\n" +
                    "                        <li><a href=\"" + ENGLISH_LANGUAGE_URL + "\">" + rb.getString("englishLanguage") + "</a></li>\n" +
                    "                        <li><a href=\"" + BELORUSSIAN_LANGUAGE_URL + "\">" + rb.getString("belorussianLanguage") + "</a></li>\n" +
                    "                        <li><a href=\"" + RUSSIAN_LANGUAGE_URL + "\">" + rb.getString("russianLanguage") + "</a></li>\n" +
                    "                    </ul>\n" +
                    "               </li>\n" +
                    "          </ul>\n");

            out.write("<form class=\"navbar-form navbar-right\" action=\"" + LOG_IN_URL + "\"\n" +
                    "                  method=\"post\" id=\"log_in_form\">\n");

            if (profile == null) {
                out.write("<div class=\"form-group\">\n" +
                        "                        <input type=\"text\" placeholder=\"" + rb.getString("login") + "\"\n" +
                        "                               class=\"form-control\" name=\"login\">\n" +
                        "                    </div>\n" +
                        "                    <div class=\"form-group\">\n" +
                        "                        <input type=\"password\" placeholder=\"" + rb.getString("password") + "\"\n" +
                        "                               class=\"form-control\" name=\"password\">\n" +
                        "                    </div>\n" +
                        "                    <input type=\"submit\" class=\"btn btn-primary\"\n" +
                        "                           value=\"" + rb.getString("navbarEnter") + "\">\n" +
                        "                    <br/>\n" +
                        "                    <div class=\"form-group\">\n" +
                        "                        <div id=\"navbarMessage\"></div>\n" +
                        "                    </div>");
            }

            if (logInMessage != null && !logInMessage.isEmpty()) {
                out.write("<div class=\"alert alert-danger\">\n" +
                        "                            <strong>" + rb.getString("navbarIssue") + "!</strong>\n" +
                        "                            " + logInMessage + "\n" +
                        "                        </div>");
            }

            if (profile != null) {
                out.write("<ul class=\"nav navbar-right\">\n" +
                        "                            <li>" +
                        "                        <label class=\"text-primary\">" + rb.getString("navbarWelcome") + " , " +
                        "                               " + profile.getLogin() + "</label>\n" +
                        "                    </li>\n" +
                        "                            <li>\n" +
                        "                                <a href=\"" + LOG_OUT_URL + "\">" + rb.getString("logOut") +
                        "                                         </a>\n" +
                        "                            </li>\n" +
                        "                        </ul>\n" +
                        "                </c:if>");
            }

            out.write("</form>\n" +
                    "        </div>\n" +
                    "    </div>");
            out.write("</nav>");

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    /**
     * Method that end processing of navbar-tag.
     *
     * @return code which means how to complete processing of navbar-tag
     */
    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }
}
