package hackutd.com.dormdash;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

public class FreeFoodAdd extends AppCompatActivity {
    private DatabaseReference mFirebaseRef;
    LinearLayout x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_food_add);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        x =findViewById(R.id.linearfreefoodadd);
        mFirebaseRef = database.getReference("freefood");

        final EditText title, loc, desc;

        title = findViewById(R.id.editTextTitle);
        loc = findViewById(R.id.editTextLoc);
        desc = findViewById(R.id.editTextDesc);


        Button btn = findViewById(R.id.submit_freefoodpost);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(title.getText())&&TextUtils.isEmpty(loc.getText())&&TextUtils.isEmpty(desc.getText())) {

                    Toast.makeText(getApplicationContext(),"One or more fields are blank",Toast.LENGTH_SHORT).show();

                }else{

                    mFirebaseRef.push().setValue(new FreeFoods(title.getText().toString(),loc.getText().toString(),String.valueOf(System.currentTimeMillis()),desc.getText().toString()));
                    showSnackbar(x,"Successfully added post!");

                }
            }

        });



    }
    public void showSnackbar(View view, String message) {



        // Create snackbar
        final Snackbar snackbar = Snackbar.make(view, message, 3000 );
        View sbView = snackbar.getView();

        sbView.setBackgroundColor(Color.GREEN);
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(18f);
        textView.setTextAlignment(TEXT_ALIGNMENT_CENTER);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        snackbar.show();
        // Set an action on it, and a handler
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){

                onBackPressed();
            }
        }, 2000);


    }
}
