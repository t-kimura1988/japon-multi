package japon.domain.repository;

import com.google.firebase.auth.FirebaseAuthException;
import japon.domain.config.FirebaseClientConfig;
import japon.domain.enums.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class FirebaseRepository {
    @Autowired
    FirebaseClientConfig firebase;

    public void accountClaims(String uid) throws FirebaseAuthException {
        Map<String, Object> claims = new HashMap<>();
        claims.put("account_type", AccountType.GENERAL.getValue());
        firebase.auth().setCustomUserClaims(uid, claims);
    }
    public void deleteAccount(String uid) throws FirebaseAuthException {
        firebase.auth().deleteUser(uid);
    }
}
