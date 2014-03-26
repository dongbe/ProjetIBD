package servlets;

import com.sun.xml.internal.messaging.saaj.util.Base64;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by donatien on 26/03/14.
 */
public class SecurityCheck extends HttpServlet {
    private Hashtable users = new Hashtable();
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        users.put("mickael:baron","allowed");
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/plain");
        ServletOutputStream out = res.getOutputStream();
        String auth = req.getHeader("Authorization");
        if (!allowUser(auth)) {
            res.setHeader("WWW-Authenticate", "BASIC realm=\"users\"");
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else out.println("Page Top-Secret");
    }
    protected boolean allowUser(String auth) throws IOException {
        if (auth == null) return false;
        if (!auth.toUpperCase().startsWith("BASIC "))
            return false;

        String userpassEncoded = auth.substring(6);
        String userpassDecoded = Base64.base64Decode(userpassEncoded);
        return
                ("allowed".equals(users.get(userpassDecoded)));
    }
}
