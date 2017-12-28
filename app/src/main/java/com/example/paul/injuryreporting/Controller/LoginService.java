package com.example.paul.injuryreporting.Controller;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Paul on 12/25/2017.
 */

public class LoginService {

    private FirebaseUser currentUser;

    public LoginService() {

    }

    public Boolean verifyNewPassword(String newPassword, String newPasswordConfirm) {
        return newPassword.equals(newPasswordConfirm);
    }

//    public void loginUser(String email, String password) {
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            currentUser = mAuth.getCurrentUser();
//                        } else {
//
//                        }
//
//                    }
//                });
//    }

}
