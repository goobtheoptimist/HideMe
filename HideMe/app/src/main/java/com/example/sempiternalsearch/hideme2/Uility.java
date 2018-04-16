package com.example.sempiternalsearch.hideme2;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

public class Uility {
    public int returnLength(View v, EditText editText){
        editText = (EditText) v.findViewById(R.id.editText2);
        int length = editText.length();
        return length;
    }
}
