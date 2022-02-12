package com.example.numad22sp_xuebai;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class LinkInputDialog extends AppCompatDialogFragment {
    private EditText inputName;
    private EditText inputURL;
    private LinkDialogListener linkInputListener;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        // connect the class with the dialog layout
        View v = inflater.inflate(R.layout.link_input_dialog,null);
        inputName = v.findViewById(R.id.inputName);
        inputURL = v.findViewById(R.id.inputURL);
        dialogBuilder.setView(v)
                .setTitle("Add a new link")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = inputName.getText().toString();
                        String URL = inputURL.getText().toString();

                        // linkInputListener was assigned with context by the onAttach method, then
                        // the applyInputs will follow the implementation from the context class
                        linkInputListener.applyInputs(name, URL);

                    }
                });
        return dialogBuilder.create();
    }

    //from official: Called when a fragment is first attached to its context. onCreate(android.os.Bundle) will be called after this.q
    @Override
    public void onAttach(@NonNull Context context) {
        // here the context refers to the caller, the link collector activity/page
        super.onAttach(context);
        try {
            linkInputListener = (LinkDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement LinkDialogListener");
        }
    }

    public interface LinkDialogListener {
        void applyInputs(String Name, String URL);
    }


}
