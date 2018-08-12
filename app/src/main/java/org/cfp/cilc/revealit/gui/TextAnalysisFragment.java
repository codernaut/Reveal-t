package org.cfp.cilc.revealit.gui;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import org.cfp.cilc.revealit.accessors.FakeboxAccessor;

import cilc.cfp.org.revealit.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TextAnalysisFragment.OnTextFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TextAnalysisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TextAnalysisFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnTextFragmentInteractionListener mListener;
    private EditText body;
    private EditText title;
    private EditText url;
    private BusyFragment busyfragment;

    public TextAnalysisFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TextAnalysisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TextAnalysisFragment newInstance(String param1, String param2) {
        TextAnalysisFragment fragment = new TextAnalysisFragment();
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
        View view = inflater.inflate(R.layout.fragment_text_analysis, container, false);
        view.findViewById(R.id.textanButton).setOnClickListener(this);
        body=view.findViewById(R.id.body);
        title=view.findViewById(R.id.title);
        url=view.findViewById(R.id.url);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onTextFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTextFragmentInteractionListener) {
            mListener = (OnTextFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        busyfragment = new BusyFragment();
        fragmentTransaction.addToBackStack("busyText");
        fragmentTransaction.hide(this);
        fragmentTransaction.add(android.R.id.content, busyfragment);
        fragmentTransaction.commit();
        FakeboxAccessor accessor=new FakeboxAccessor(this);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("url",url.getText().toString());
        params.add("content",body.getText().toString());
        params.add("title",title.getText().toString());
        client.post("http://65.52.173.195:8080/fakebox/check",params,accessor);



    }

    public void success(String s) {
        FragmentManager fragmentManager = getFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TextResult fragment = new TextResult();
        Bundle args=new Bundle();
        args.putString("message",s);
        fragment.setArguments(args);
        //fragmentTransaction.addToBackStack("busyText");
       // fragmentTransaction.hide(this);
        fragmentTransaction.hide(busyfragment);
        fragmentTransaction.add(android.R.id.content, fragment);
        fragmentTransaction.commit();
    }

    public void fail(String s) {
        FragmentManager fragmentManager = getFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TextResult fragment = new TextResult();
        Bundle args=new Bundle();
        args.putString("message",s);
        fragment.setArguments(args);
        fragmentTransaction.hide(busyfragment);
        fragmentTransaction.add(android.R.id.content, fragment);
        fragmentTransaction.commit();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnTextFragmentInteractionListener {
        // TODO: Update argument type and name
        void onTextFragmentInteraction(Uri uri);
    }
}
