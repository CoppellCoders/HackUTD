package hackutd.com.dormdash;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class MenuActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Restraunts data = (Restraunts) getIntent().getSerializableExtra("info");





    }
}
