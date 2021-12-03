package ie.ul.dungeonfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

// This is importing all of the extenstions needed for Firebase login//
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class login extends AppCompatActivity { 

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);         // the call to create an instance of the login page
        setContentView(R.layout.activity_login);   // sets the screen view to the activity_login.xml view
    }

    public void OnClickSignIn (View view){                   
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(), RC_SIGN_IN);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                System.out.println("Sign in successful! \n" +
                        "name = " + user.getDisplayName() + "\n" +
                        "email = " + user.getEmail() + "\n" +
                        "id = " + user.getUid()
                );

                Intent intent = new Intent(login.this, MainActivity.class);
                startActivity(intent);

            } else {
                if (response == null) {
                    System.out.println("Sign in cancelled");
                    return;
                }
                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {  //error ems
                    System.out.println("No internet Connection");
                    return;
                }

            }
        }
    }
}
