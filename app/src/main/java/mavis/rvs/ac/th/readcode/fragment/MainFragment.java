package mavis.rvs.ac.th.readcode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import mavis.rvs.ac.th.readcode.R;
import mavis.rvs.ac.th.readcode.utility.GetAllUser;
import mavis.rvs.ac.th.readcode.utility.MyConstant;
import mavis.rvs.ac.th.readcode.utility.myAlert;

/**
 * Created by Mavis on 20/03/2018.
 */

public class MainFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Register Controler
        registerControler();

//        Loing Controller
        loingController();
    }// Main Method

    private void loingController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            get Value EditText
                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

//                Change EditText to String
                String userString = userEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();

                if (userString.isEmpty() || passwordString.isEmpty()) {
//                    Have Space  -------
                    myAlert objMyAlert = new myAlert(getActivity());
                    //การเรียกค่า Strings.xml Show in Dialog Alert
                    objMyAlert.myDialog(getString(R.string.th_alertkeyall),getString(R.string.th_alertspace));


                } else {
//                    No Space
                    try {  // ดึง Jason มาทำงาน
                        MyConstant myConstant = new MyConstant();
                        GetAllUser getAllUser = new GetAllUser(getActivity());
                        getAllUser.execute(myConstant.getUrlGetAllUserString());

                        String jsonString = getAllUser.get();
//                  Check ค่าในข้อมูลแบบ Jason มาตรงจสอบว่ามีค่าจริงหรือไม่
                        Log.d("22MarchV1", "JSON==>" + jsonString);

                        String[] columnUserStrings = myConstant.getLoginStrings();
                        String[] loginStrings = new String[columnUserStrings.length];
                        boolean statusBoolean = true;
                        // แยก Jason ออกแป็น ชิ้น ๆ
                        JSONArray jsonArray = new JSONArray(jsonString);
                        for (int i=0; i<jsonArray.length(); i+=1) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i); // ตัวชี้เป้า
                            if (userString.equals(jsonObject.getString(columnUserStrings[2]))) {
                                statusBoolean = false;
                                for (int i1=0; i< columnUserStrings.length; i1+=1) {
                                    loginStrings[i1] = jsonObject.getString(columnUserStrings[i]);
                                    Log.d("22MarchV1", "loginStrings[" + i1 + "]==>" + loginStrings[i1]);
                                }
                            }
                        } // for
                        if (statusBoolean) {
//                            User False
                            myAlert nmyAlert = new myAlert(getActivity());
                            nmyAlert.myDialog("User False","No This User in mySQL");
                        } else if (passwordString.equals(loginStrings[3])) {
//                            Password True   ถ้า Login ผ่าน
                            Toast.makeText(getActivity(), "Welcome " + loginStrings[1],Toast.LENGTH_SHORT).show();

                        } else {
                            myAlert nmyAlert = new myAlert(getActivity());
                            nmyAlert.myDialog("Password False","Please Try Again Password False");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            } //onClick
        });

    }

    private void registerControler() {
        TextView textView = getView().findViewById(R.id.txtRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Replace Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
}  //Main Class
