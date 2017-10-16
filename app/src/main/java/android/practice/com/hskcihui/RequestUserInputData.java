package android.practice.com.hskcihui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class RequestUserInputData extends AppCompatActivity {

    private EditText editTextUserInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_user_input_data);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getResources().getString( R.string.app_name)+" - "+
                getResources().getString(R.string.requestUserInputActivityName));

        Intent intent = getIntent();
        String currentValue = intent.getStringExtra("currentValue");


        editTextUserInput = (EditText) findViewById(R.id.editTextUserInput);
        editTextUserInput.setText(currentValue);
    }

    public void sendUserInputData(View view) {
        Intent intentReturnValue = new Intent();
        String returnValue;
        if (editTextUserInput.getText().toString().equals("")){
            returnValue = getResources().getString(R.string.empty);
        }else {
            returnValue = editTextUserInput.getText().toString();
        }
        intentReturnValue.putExtra("updatedValue", returnValue);
        setResult(Activity.RESULT_OK, intentReturnValue);
        finish();
    }
}
