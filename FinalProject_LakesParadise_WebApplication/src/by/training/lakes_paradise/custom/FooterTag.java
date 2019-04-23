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
            pageContext.getOut().write("<hr/>");
            pageContext.getOut().write("<footer><p>" + text + "</p></footer>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
