package SERVICEE;

import DAOO.GenericDAO;
import object.LogAbsensi;
import java.time.LocalDateTime;
import java.util.UUID;
/**
 *
 * @author bsame
 */
public class LogAbsensiService {
    private final GenericDAO<LogAbsensi> LogDAO = new GenericDAO<>("log_absensi" , LogAbsensi.class);
    public void simpanLog (String hashedUid, String status){
        LogAbsensi log = new LogAbsensi(
                UUID.randomUUID().toString(),
                hashedUid,
                LocalDateTime.now(),
                status
        );
        LogDAO.save(log);
    }
    
}
