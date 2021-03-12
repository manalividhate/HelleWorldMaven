package com.techdenovo.papp.util;
import com.techdenovo.papp.dto.CustomerDtoImpl;
import org.mindrot.jbcrypt.BCrypt;
import java.util.logging.Logger;

public class CustomerPortalHashing {
    //private static final Logger log = (Logger) LoggerFactory.getLogger(CustomerDtoImpl.class);

    private  int logRounds=4;
    BCrypt bCrypt = new BCrypt();

    public void UpdatableBCrypt(int logRounds) {
        this.logRounds = logRounds;
    }
    public String hash(String password) {
        return bCrypt.hashpw(password, BCrypt.gensalt(logRounds));
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}