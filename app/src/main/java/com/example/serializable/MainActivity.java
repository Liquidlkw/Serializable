package com.example.serializable;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serializable.Student.Score;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Name
     */
    private EditText mEdName;
    /**
     * AGE
     */
    private EditText mEdAge;
    /**
     * English
     */
    private EditText mEdEnglish;
    /**
     * save
     */
    private Button mButtonSave;
    /**
     * load
     */
    private Button mButtonLoad;
    /**
     * Math
     */
    private EditText mEdMath;

    private static final String FILE_NAME = "myFile.data";
    private static final String TAG = "MainActivity";
    private Score score;
    /**
     * TextView
     */
    private TextView mTvGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mEdName = (EditText) findViewById(R.id.edName);
        mEdAge = (EditText) findViewById(R.id.edAge);
        mEdMath = (EditText) findViewById(R.id.edMath);
        mEdEnglish = (EditText) findViewById(R.id.edEnglish);

        mButtonSave = (Button) findViewById(R.id.buttonSave);
        mButtonSave.setOnClickListener(this);
        mButtonLoad = (Button) findViewById(R.id.buttonLoad);
        mButtonLoad.setOnClickListener(this);

        mTvGrade = findViewById(R.id.tvGrade);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.buttonSave:
                int math = Integer.valueOf(mEdMath.getText().toString());
                int english = Integer.valueOf(mEdEnglish.getText().toString());
                int age = Integer.valueOf(mEdAge.getText().toString());
                String name = mEdName.getText().toString();
                Score score = new Score(math, english);
                Student student = new Student(name, age, score);

                //对象Serializable序列化
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(openFileOutput(FILE_NAME, MODE_PRIVATE));
                    objectOutputStream.writeObject(student);
                    //清空缓冲区
                    objectOutputStream.flush();
                    objectOutputStream.close();
                    Toast.makeText(this, "save successful", Toast.LENGTH_SHORT).show();
                    mEdAge.getText().clear();
                    mEdEnglish.getText().clear();
                    mEdMath.getText().clear();
                    mEdName.getText().clear();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Serializable io error!");
                }


                break;
            case R.id.buttonLoad:
                InputStream in;
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(openFileInput(FILE_NAME));
                    Student student1 = (Student) objectInputStream.readObject();
                    mEdName.setText(student1.getName() + "");
                    mEdAge.setText(student1.getAge() + "");
                    mEdEnglish.setText(student1.getScore().getEnglish() + "");
                    mEdMath.setText(student1.getScore().getMath() + "");
                    mTvGrade.setText(""+student1.getScore().getGrade());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}



































