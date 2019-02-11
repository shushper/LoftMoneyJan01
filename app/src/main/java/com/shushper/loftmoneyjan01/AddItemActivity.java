package com.shushper.loftmoneyjan01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {


    public final static String KEY_NAME = "name";
    public final static String KEY_PRICE = "price";

    private EditText name;
    private EditText price;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        addBtn = findViewById(R.id.add_btn);


        TextListener listener = new TextListener();
        name.addTextChangedListener(listener);
        price.addTextChangedListener(listener);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                String nameText = name.getText().toString();
                String priceText = price.getText().toString();

                intent.putExtra(KEY_NAME, nameText);
                intent.putExtra(KEY_PRICE, priceText);

                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });
    }

    private class TextListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            CharSequence nameText = name.getText();
            CharSequence priceText = price.getText();

            addBtn.setEnabled(!TextUtils.isEmpty(nameText) && !TextUtils.isEmpty(priceText));
        }
    }
}
