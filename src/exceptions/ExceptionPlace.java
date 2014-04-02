package exceptions;

import java.sql.SQLException;

/**
 * Created by donatien on 30/03/14.
 */
public class ExceptionPlace extends SQLException {

    public ExceptionPlace() {
        // TODO Auto-generated constructor stub
    }

    public ExceptionPlace(String m) {
        super(m);
    }
}
