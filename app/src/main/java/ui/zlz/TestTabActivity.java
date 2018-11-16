package ui.zlz;

import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

public class TestTabActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tab);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("tab1"));
        tabLayout.addTab(tabLayout.newTab().setText("tab2"));

    }

}
