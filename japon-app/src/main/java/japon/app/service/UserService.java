package japon.app.service;

import japon.app.exceptions.JaponAlreadyExistException;
import japon.app.exceptions.JaponIntegrityException;
import japon.app.exceptions.JaponNotFountException;
import japon.app.service.input.UserCreateServiceInput;
import japon.app.service.output.UserCreateServiceOutput;
import japon.domain.enums.DelFlg;
import japon.domain.model.params.UserDaoParameter;
import japon.domain.model.response.UserSearchResponse;
import japon.domain.repository.FirebaseRepository;
import japon.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FirebaseRepository firebaseRepository;

    public UserCreateServiceOutput create(UserCreateServiceInput input) throws Exception{
        var user = userRepository.selectOptional(UserDaoParameter.builder()
                .userId(input.getUserId()).build());
        if (user.isPresent()) {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("User.uid", input.getUid());
            throw new JaponAlreadyExistException("存在エラー", "すでにユーザーが登録されています。" , param);
        }
        userRepository.save(input.toEntity());
        firebaseRepository.accountClaims(input.getUid());
        return UserCreateServiceOutput.builder()
                .build();
    }

    public UserSearchResponse show(String uid) throws JaponNotFountException, JaponIntegrityException {
        var user = userRepository.selectOptional(
                UserDaoParameter.builder()
                        .uid(uid).build()
        ).orElseThrow(
                () -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("uid: ", uid);
                    return new JaponNotFountException("goal detail info no found", param);
                }
        );

        if(DelFlg.DELETED.equals(user.getDelFlg())) {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("account uid: ", uid);
            throw  new JaponIntegrityException("不整合エラー", "Japonアカウントが削除済み", param, "E0001");
        }

        if(DelFlg.FIREBASE_DELETED.equals(user.getDelFlg())) {
            Map<String, String> param = new LinkedHashMap<>();
            param.put("account uid: ", uid);
            throw  new JaponIntegrityException("不整合エラー", "Firebaseアカウントが削除済み", param, "E0005");
        }

        return user;
    }
}
