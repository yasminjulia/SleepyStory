package dev.ruanvictor.sleepystorie;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dev.ruanvictor.sleepystorie.model.User;
import dev.ruanvictor.sleepystorie.utils.MyConstants;
import dev.ruanvictor.sleepystorie.utils.UIUtil;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText textName, textEmail, textBirthday, textCelular, textAddress, textCity, textCEP, textPassword, textConfirmPassword;
    private TextInputLayout textNameLayout, textEmailLayout, textBirthdayLayout, textCelularLayout, textAddressLayout, textCityLayout, textCEPLayout, textPasswordLayout, textConfirmPasswordLayout;
    private boolean isFirstValidation = true, isValidName = false, isValidEmail = false, isValidBirthday = false, isValidCelular = false, isValidAddress = false, isValidCity = false, isValidCEP = false, isValidPassword = false, isValidConfirmPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textName = findViewById(R.id.textName);
        textNameLayout = findViewById(R.id.textNameLayout);
        textEmail = findViewById(R.id.textEmail);
        textEmailLayout = findViewById(R.id.textEmailLayout);
        textBirthday = findViewById(R.id.textBirthday);
        textBirthdayLayout = findViewById(R.id.textBirthdayLayout);
        textCelular = findViewById(R.id.textCelular);
        textCelularLayout = findViewById(R.id.textCelularLayout);
        textAddress = findViewById(R.id.textAddress);
        textAddressLayout = findViewById(R.id.textAddressLayout);
        textCity = findViewById(R.id.textCity);
        textCityLayout = findViewById(R.id.textCityLayout);
        textCEP = findViewById(R.id.textCEP);
        textCEPLayout = findViewById(R.id.textCEPLayout);
        textPassword = findViewById(R.id.textPassword);
        textPasswordLayout = findViewById(R.id.textPasswordLayout);
        textConfirmPassword = findViewById(R.id.textConfirmPassword);
        textConfirmPasswordLayout = findViewById(R.id.textConfirmPasswordLayout);

        Button buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonSignUp) {
            isFirstValidation = false;
            isValidName = validateFieldRequired(textName, textNameLayout);
            isValidEmail = validateFieldRequired(textEmail, textEmailLayout);
            isValidBirthday = validateFieldRequired(textBirthday, textBirthdayLayout);
            isValidCelular = validateFieldRequired(textCelular, textCelularLayout);
            isValidAddress = validateFieldRequired(textAddress, textAddressLayout);
            isValidCity = validateFieldRequired(textCity, textCityLayout);
            isValidCEP = validateFieldRequired(textCEP, textCEPLayout);
            isValidPassword = validateFieldRequired(textPassword, textPasswordLayout);
            isValidConfirmPassword = validConfirmPassword();
            if (isValidName && isValidEmail && isValidBirthday && isValidCelular && isValidAddress && isValidCity && isValidCEP && isValidPassword && isValidConfirmPassword) {
                createUser();
            }
        }
    }

    private void createUser() {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(textEmail.getText().toString(), textPassword.getText().toString()).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {

                User user = User.builder()
                        .address(textAddress.getText().toString())
                        .birthday(textBirthday.getText().toString())
                        .celular(textCelular.getText().toString())
                        .cep(textCEP.getText().toString())
                        .build();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(MyConstants.USERS);
                reference.child(task.getResult().getUser().getUid())
                        .setValue(user);

                finish();
            }

            try{
                throw task.getException();
            } catch (FirebaseAuthWeakPasswordException e) {
                textPasswordLayout.setError(getString(R.string.password_weak));
                textPasswordLayout.setErrorEnabled(true);
            } catch (FirebaseAuthUserCollisionException e) {
                Toast.makeText(SignUpActivity.this, "Usuário já existe!", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Log.e("SIGNUP", "Erro ao criar usuário: " + e.getMessage());
            }
        });
    }

    private boolean validateFieldRequired(TextInputEditText field, TextInputLayout layout) {
        if (field.getText().toString().isEmpty()) {
            layout.setError(getString(R.string.field_required));
            layout.setErrorEnabled(true);
        } else {
            UIUtil.clearErrorStyle(layout);
        }

        return (layout.getError() == null);
    }

    private boolean validConfirmPassword() {
        if(validateFieldRequired(textConfirmPassword, textConfirmPasswordLayout)) {
            return true;
        }

        String password = textPassword.getText().toString();
        String confirmPassword = textConfirmPassword.getText().toString();
        if(!confirmPassword.equals(password)) {
            textConfirmPasswordLayout.setError(getString(R.string.password_not_match));
            textConfirmPasswordLayout.setErrorEnabled(true);
        } else {
            UIUtil.clearErrorStyle(textConfirmPasswordLayout);
        }

        return (textConfirmPasswordLayout.getError() == null);
    }
}
