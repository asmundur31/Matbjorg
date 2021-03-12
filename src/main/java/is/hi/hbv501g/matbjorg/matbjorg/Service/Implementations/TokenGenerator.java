package is.hi.hbv501g.matbjorg.matbjorg.Service.Implementations;

import java.security.SecureRandom;

public class TokenGenerator {

    protected static SecureRandom random = new SecureRandom();

    public synchronized String generateToken(long userId, String email) {
        long longToken = Math.abs( random.nextLong() );
        String random = Long.toString( longToken, 16 );
        return ( userId + "." + email + "." + random );
    }
}
