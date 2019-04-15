package com.saul.arf.Menu;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import com.saul.arf.R;
import com.saul.arf.ScenarioCamara.Categoria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MenuActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String activityTilte;
    private String [] items;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> listTitle;
    private Map<String, List<String>> listChild;
    private NavigationManager navigationManager;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        drawerLayout=  findViewById(R.id.drawer_layout);
        activityTilte = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        navigationManager = FragmentNavigationManager.getmInstance(this);
        initItems();
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header,null,false);
        expandableListView.addHeaderView(listHeaderView);
        genData();
        addDrawersItem();
        setupDrawer();
        if(savedInstanceState == null){
            selectFirstItemAsDefault();
        }


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectFirstItemAsDefault(){
        if(navigationManager != null){
            String firstItem = listTitle.get(0);
            navigationManager.showFragment(firstItem);
//            getSupportActionBar().setTitle(firstItem);
        }
    }

    private void genData(){

        listChild = new TreeMap<>();
        ArrayList<Categoria> arrayListCategorias=new ArrayList<Categoria>();
        arrayListCategorias = (ArrayList<Categoria>) getIntent().getExtras().getSerializable("c");

        for(int i=0; i<arrayListCategorias.size(); i++){
            arrayListCategorias.get(i).setListNombres();
            listChild.put(arrayListCategorias.get(i).getNombre(),arrayListCategorias.get(i).getArrayListNombresSubCategorias());

            /*
            for(int j=0; j<arrayListCategorias.get(i).getSubCategorias().size(); j++){
                for(int k=0; k<arrayListCategorias.get(i).getSubCategorias().get(j).getMuebles().size(); k++){
                    System.out.println(arrayListCategorias.get(i).getSubCategorias().get(j).getMuebles().get(k).getName());
                }
            }
            */
        }

        //List<String> title = Arrays.asList("Categorias", "Subcategoras", "Muebles");
        //List<String> childItem = Arrays.asList("Hogar", "Cocina", "Sala","Comedor");
        //listChild = new TreeMap<>();
        //listChild.put(title.get(0),childItem);
        //listChild.put(title.get(1),childItem);
        //listChild.put(title.get(2),childItem);
        //listChild.put(title.get(3),childItem);

        listTitle = new ArrayList<>(listChild.keySet());

    }

    private void addDrawersItem(){
        adapter = new CustomExpandableListAdapter(this,listTitle,listChild);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //getSupportActionBar().setTitle(listTitle.get(groupPosition).toString());
            }
        });
        expandableListView.setOnGroupCollapseListener(groupPosition -> {
            //getSupportActionBar().setTitle("Close");
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selectedItem =((List)(listChild.get(listTitle.get(groupPosition)))).get(childPosition).toString();
//                getSupportActionBar().setTitle(selectedItem);

                if(items[0].equals(listTitle.get(groupPosition)))
                    navigationManager.showFragment(selectedItem);
                //else
                    //throw  new IllegalArgumentException("fragment no soportado");

                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void setupDrawer(){
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        {
            @Override
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
                //getSupportActionBar().setTitle("SDCSCS");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
                //getSupportActionBar().setTitle(activityTilte);
                invalidateOptionsMenu();
            }
        };

        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

    }

    private void initItems(){
        items = new String[]{"Categorias", "Subcategoras", "Muebles", "Mis muebles"};
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}

/*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.quit_arf) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.categorias_arf) {
            // Handle the camera action
        } else if (id == R.id.subcategorias_arf) {

        } else if (id == R.id.mis_muebles) {

        }else if (id == R.id.escenarios) {

        }else if (id == R.id.proyectos) {

        } else if (id == R.id.foto_arf) {

        } else if (id == R.id.cerrar_session) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
