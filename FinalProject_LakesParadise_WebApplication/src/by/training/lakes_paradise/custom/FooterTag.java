package by.training.lakes_paradise.custom;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@SuppressWarnings("footer")
public class FooterTag extends TagSupport {
    private String text;
    private Locale language;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    @Override
    public int doStartTag() throws JspException {
        try {

            ResourceBundle rb = ResourceBundle
                    .getBundle("property.text", language);

            pageContext.getOut().write("<hr/><footer class=\"footer\">\n" +
                    "    <div class=\"footer-bottom text-center\">\n" +
                    rb.getString("footer") +
                    "    </div>\n" +
                    "</footer>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
