package com.example.gitshnb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class UsernameDialog extends AppCompatDialogFragment {

    private EditText usernameEdittext;
    private UsernameDialogListner listner;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.username_dialog,null);

        builder.setView(view)
                .setTitle("search")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = usernameEdittext.getText().toString();
                        listner.applyTexts(username);

                    }
                });

        usernameEdittext = view.findViewById(R.id.usernameSearchEdittext);

        return builder.create();
    }

    public interface UsernameDialogListner{
        void applyTexts(String username);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listner = (UsernameDialogListner) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString()+"must implement UsernameDialogListner");
        }
    }
}
