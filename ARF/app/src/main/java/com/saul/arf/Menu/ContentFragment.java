package com.saul.arf.Menu;


import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.saul.arf.R;


public class ContentFragment extends AppCompatActivity implements MueblesFragment.OnFragmentInteractionListener {

    MueblesFragment mueblesFragment;

    public ContentFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_content);
        mueblesFragment = new MueblesFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments,mueblesFragment).commit();
        System.out.println("Estoy en fragmentMuebles!!!!!!");
    }

    public void OnFragmentInteraction(Uri uri){

    }
}
