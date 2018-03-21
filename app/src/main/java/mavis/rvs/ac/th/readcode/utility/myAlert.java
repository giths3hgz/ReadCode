package mavis.rvs.ac.th.readcode.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import mavis.rvs.ac.th.readcode.R;

/**
 * Created by Mavis on 21/03/2018.
 */

public class myAlert {

    private Context context;

    public myAlert(Context context) {
        this.context = context;
    }

    public void myDialog(String titleString, String messageString) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_name);
        builder.setTitle(titleString);
        builder.setMessage(messageString);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

} // Main class
