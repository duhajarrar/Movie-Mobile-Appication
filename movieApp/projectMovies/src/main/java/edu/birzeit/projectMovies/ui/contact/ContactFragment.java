package edu.birzeit.projectMovies.ui.contact;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.birzeit.projectMovies.R;
import edu.birzeit.projectMovies.HomeActivity;

import android.content.Intent;
import android.net.Uri;

import androidx.annotation.Nullable;

import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;

public class ContactFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CallUsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ImageButton sendEmail = (ImageButton) getActivity().findViewById(R.id.imageButton);

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText msg=(EditText)getActivity().findViewById(R.id.msg);
                sendEmail.setEnabled(false);
                sendEmail.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendEmail.setEnabled(true);
                    }
                }, 5000);
                sendEmail.startAnimation((AnimationUtils.loadAnimation(getActivity(),R.anim.send)));
                Intent gmailIntent = new Intent();
                gmailIntent.setAction(Intent.ACTION_SENDTO);
                gmailIntent.setType("message/rfc822");
                gmailIntent.setData(Uri.parse("mailto:movies@movies.com"));
                gmailIntent.putExtra(Intent.EXTRA_TEXT, msg.getText());
                gmailIntent.putExtra(Intent.EXTRA_SUBJECT, HomeActivity.user.getFirstname());
                startActivity(gmailIntent);
            }
        });
    }
}
