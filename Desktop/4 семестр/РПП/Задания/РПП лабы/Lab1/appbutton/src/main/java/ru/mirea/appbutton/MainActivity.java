package ru.mirea.appbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int numbers;
    private TextView textView;
    private Button btn;

    final static String numsVariableKey = "NUMS_VARIABLE";
    final static String textViewTexKey = "TEXTVIEW_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numbers = 0;
        textView = (TextView)findViewById(R.id.main__textView);
        btn = (Button)findViewById(R.id.main__button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers++;
                textView.setText(Integer.toString(numbers));
            }
        });
    }

    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(numsVariableKey, numbers);
        TextView numView = (TextView) findViewById(R.id.main__textView);
        outState.putString(textViewTexKey, numView.getText().toString());

        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        numbers = savedInstanceState.getInt(numsVariableKey);
        String textViewText= savedInstanceState.getString(textViewTexKey);
        TextView nameView = (TextView) findViewById(R.id.main__textView);
        nameView.setText(textViewText);
    }


}
