package com.example.develop.gestionpagosservicios.feature;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class myDialogFragment extends DialogFragment{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    private Button btn_web, btn_amazon;
    private TextView tv_bc;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1 is the barcode
     * @return A new instance of fragment myDialogFragment.
     */
    public static myDialogFragment newInstance(String param1) {

        myDialogFragment fragment = new myDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public myDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_my_dialog,  null);
        tv_bc = (TextView) myView.findViewById(R.id.barcode);
        tv_bc.setText(mParam1);
        btn_amazon = (Button) myView.findViewById(R.id.btn_amazon);

        btn_amazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                //http://www.amazon.com/s?url=search-alias%3Daps&field-keywords=honda+parts
                String url = "http://www.amazon.com/s?url=search-alias%3Daps&field-keywords=" + mParam1;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        btn_web = (Button) myView.findViewById(R.id.btn_web);
        btn_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

                String url = mParam1;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.Theme_AppCompat));
        builder.setView(myView);
        return  builder.create();
    }
}
