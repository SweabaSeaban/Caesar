package com.example.caesar;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
    }
    public void encrypt(View v)
    {
        int i=0,j=0;
        char[] ref = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        String s= e1.getText().toString().toLowerCase();
        if (e1.getText().toString().isEmpty()||e3.getText().toString().isEmpty()||(e1.getText().toString().isEmpty()&&e3.getText().toString().isEmpty()))
        {
            Toast.makeText(this, "Enter required values", Toast.LENGTH_SHORT).show();
            return;
        }
        s = s.replaceAll("\\s", "");
        int key=Integer.parseInt(e3.getText().toString());
        if(key>25)
        {
            Toast.makeText(this, "Enter a shift value less than 26", Toast.LENGTH_SHORT).show();
            return;
        }
        char[] pt=s.toCharArray();
        int[] pv = new int[pt.length];
        for(char c:pt)
        {
            for(i=0;i<26;i++)
            {
                if(c==ref[i])
                {
                    pv[j++]=i;
                }
            }
        }
        int [] cv=new int[pt.length];
        char[] ct=new char[pt.length];
        for(i=0;i<pv.length;i++)
        {
            cv[i]=(pv[i]+key)%26;
        }
        for(i=0,j=0;i<cv.length;i++)
        {
            ct[j++]=ref[cv[i]];
        }
        e2.setText(String.valueOf(ct));

    }
    public void decrypt(View v)
    {
        int i=0,j=0,temp;
        char[] ref = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        String s= e2.getText().toString().toLowerCase(Locale.ROOT);
        if (s.isEmpty()||e3.getText().toString().isEmpty()||(s.isEmpty()&&e3.getText().toString().isEmpty()))
        {
            Toast.makeText(this, "Enter required values", Toast.LENGTH_SHORT).show();
            return;
        }
        s = s.replaceAll("\\s", "");
        int key=Integer.parseInt(e3.getText().toString());
        if(key>25)
        {
            Toast.makeText(this, "Enter a shift value less than 26", Toast.LENGTH_SHORT).show();
            return;
        }
        char[] ct=s.toCharArray();
        int[] cv = new int[ct.length];
        for(char c:ct)
        {
            for(i=0;i<26;i++)
            {
                if(c==ref[i])
                {
                    cv[j++]=i;
                }
            }
        }
        int [] pv=new int[ct.length];
        char[] pt=new char[ct.length];
        for(i=0;i<cv.length;i++)
        {
            temp=cv[i]-key;
            if(temp<0)
            {
                pv[i]=26-Math.abs(temp);
            }
            else
            {
                pv[i]=temp%26;
            }
            //pv[i]=(cv[i]-key)%26;
        }
        for(i=0,j=0;i<pv.length;i++)
        {
            pt[j++]=ref[pv[i]];
        }
        e1.setText(String.valueOf(pt));
    }

}