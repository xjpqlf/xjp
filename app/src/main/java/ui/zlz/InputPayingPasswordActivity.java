package ui.zlz;

import android.app.Activity;
import android.os.Bundle;
import com.jungly.gridpasswordview.GridPasswordView;

public class InputPayingPasswordActivity extends Activity {

    private GridPasswordView gridPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_paying_password);

        gridPasswordView = findViewById(R.id.pswView);
        //gridPasswordView.getPassWord();
    }

}
