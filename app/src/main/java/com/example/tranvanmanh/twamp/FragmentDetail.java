package com.example.tranvanmanh.twamp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by tranvanmanh on 4/18/2018.
 */

public class FragmentDetail extends DialogFragment {

    ImageButton imvbtnCancel;
    TextView tvInfo;
    int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        imvbtnCancel = (ImageButton) view.findViewById(R.id.imvbtnCancel);
        tvInfo = (TextView) view.findViewById(R.id.tvinfo);

        imvbtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        Bundle bundle = getArguments();
       position = bundle.getInt("position");
       if(position == 0){
           tvInfo.setText(MyApp.infoPacketLoss);
       }
        if(position == 1){
            tvInfo.setText(MyApp.infoDupplicate);
        }
        if(position == 2){
            tvInfo.setText(MyApp.infoOutOfOrder);
        }
        return view;
    }
}
