package by.training.lakes_paradise.custom;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("footer")
public class FooterTag extends TagSupport {
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write("<hr/><footer class=\"footer\">\n" +
                    "    <div class=\"footer-bottom text-center\">\n" +
                    "&copy; Все права защищены 2019" +
                    "    </div>\n" +
                    "</footer>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
