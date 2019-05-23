package by.training.lakes_paradise.custom;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class declares user-defined footer-tag.
 */
public class FooterTag extends TagSupport {
    /**
     * Text inside of footer-tag.
     */
    private String text;
    /**
     * Current language of page.
     */
    private Locale language;

    /**
     * Sets the value of text property.
     *
     * @param curText - value of text property
     */
    public void setText(final String curText) {
        this.text = curText;
    }

    /**
     * Gets the value of text property.
     *
     * @return value of text property
     */
    public String getText() {
        return text;
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
     * Sets the value of language property.
     *
     * @param curLanguage value of language property
     */
    public void setLanguage(final Locale curLanguage) {
        this.language = curLanguage;
    }

    /**
     * Method that starts processing of footer-tag.
     *
     * @return code which means how to process footer-tag body
     */
    @Override
    public int doStartTag() throws JspException {
        try {

            ResourceBundle rb = ResourceBundle
                    .getBundle("property.text", language);

            pageContext.getOut().write("<hr/><footer class=\"footer\">\n"
                    + "    <div class=\"footer-bottom text-center\">\n"
                    + rb.getString("footer")
                    + "    </div>\n"
                    + "</footer>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
