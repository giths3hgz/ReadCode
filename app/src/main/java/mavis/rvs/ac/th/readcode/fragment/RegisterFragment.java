package mavis.rvs.ac.th.readcode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import mavis.rvs.ac.th.readcode.MainActivity;
import mavis.rvs.ac.th.readcode.R;
import mavis.rvs.ac.th.readcode.utility.MyConstant;
import mavis.rvs.ac.th.readcode.utility.PostUserToServer;
import mavis.rvs.ac.th.readcode.utility.myAlert;

/**
 * Created by Mavis on 21/03/2018.
 */

public class RegisterFragment extends Fragment {
//    Explicit
    private String nameString, userString, passwordString;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create ToolBar
        createToolBar();
//        Register Controler
        registerControler();
    }//Main Method

    private void registerControler() {
        Button button = getView().findViewById(R.id.btnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Get Value From EditText
                EditText nameEditText = getView().findViewById(R.id.edtName);
                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

//                Change EditText to String
                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

//                Check Space
                if (nameString.isEmpty()|| userString.isEmpty() || passwordString.isEmpty()) {
//                    Have Space
                    myAlert objMyAlert = new myAlert(getActivity());
                    objMyAlert.myDialog("Have Space","Please Fill All Blank");
                } else {
//                    No Space  บันทึกข้อมูลลง Database
                    try {
                        MyConstant myConstant = new MyConstant();
                        PostUserToServer postUserToServer = new PostUserToServer(getActivity());
                        postUserToServer.execute(nameString, userString, passwordString,
                                myConstant.getUrlPostUserString());
                        String result = postUserToServer.get();
                        Log.d("22MarchV1", "Result==>" + result); //ตัวแปรที่ Result

                        if (Boolean.parseBoolean(result)) { // เปลี่ยน ค่า result ที่เป็น Boolean >> String เพื่อตรวจสอบ
                            getActivity().getSupportFragmentManager().popBackStack();
                        } else {
                            myAlert nmyAlert = new myAlert(getActivity());
                            nmyAlert.myDialog("Cannot Post User","Please Try Again");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void createToolBar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

//        Setup Title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Register");
//        Show Navigator Icon
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}//Main Class
