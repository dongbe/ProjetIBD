package affichage;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * Created by donatien on 30/03/14.
 */
public class StylePage {
    public StylePage(){

    }
    public static void Header(ServletOutputStream out) throws IOException {
        out.println("<html>");
        out.println("<HEAD><script type=\"text/javascript\" src=\"/scripts/dateShow.js\"></script>\n<TITLE> Ajouter une nouvelle representation </TITLE><LINK href=\"/stylesheets/aind_delhi_style1.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "\t<LINK href=\"/stylesheets/aind_delhi_MainStyleS.css\" rel=\"stylesheet\" type=\"text/css\"></HEAD>");
        out.println("<BODY bgproperties=\"fixed\" background=\"/images/rideau.JPG\">");
        out.println("<div class=frame0 >");

    }
    public static void Footer(ServletOutputStream out) throws IOException {
        out.println("<hr><p><font color=\"#FFFFFF\"><a href=\"/admin/admin.html\">Page d'administration</a></p>");
        out.println("<hr><p><font color=\"#FFFFFF\"><a href=\"/index.html\">Page d'accueil</a></p>");
        out.println("</div>");
        out.println("</BODY>");
        out.println("</html>");
        out.close();
    }
}
