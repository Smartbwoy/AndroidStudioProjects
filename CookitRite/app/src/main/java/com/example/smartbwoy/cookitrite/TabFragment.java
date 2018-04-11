package com.example.smartbwoy.cookitrite;

/**
 * Created by Smartbwoy on 7/1/2017.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Miguel
 */
public class TabFragment extends Fragment {
    public TabLayout tabLayout;
    public ViewPager viewPager;
    public static GoogleMap mGoogleMap;
    static ExpandableListAdapter listAdapter;
    static ExpandableListView expListView;
    static List<String> listDataHeader;
    static HashMap<String, List<String>> listDataChild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /**
         *Inflate tab_layout and setup Views.
         */
        View x =  inflater.inflate(R.layout.tab_layout,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new SectionsPagerAdapter (getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                setIconTabs();

            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position==3){
                    ProfileActivity.myMenu.name.findItem(R.id.action_edit).setVisible(true);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                //View rootView =getView().findViewById(R.layout.app_bar_profile2);

                //rootView.findViewById(R.id.topbarRemover).setVisibility(View.GONE);
                if(position==3) {
                    ProfileActivity.myMenu.name.findItem(R.id.action_edit).setVisible(false);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return x;

    }

public static class PlaceholderFragment extends Fragment {
        public static String [] MealsAry;
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            TabFragment.PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        RecyclerView rv;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

           //Menu mainMenu;
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1){
                View rootView = inflater.inflate(R.layout.fragment_home, container, false);
                rv = (RecyclerView) rootView.findViewById(R.id.recom_meal);
                //rv.setHasFixedSize(true);
                WindowManager wm = (WindowManager) rootView.getContext().getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;
                RecyclerView.LayoutManager llm;
                if(width<=1500){
                    llm=new LinearLayoutManager(rootView.getContext());
                }else{
                    llm=new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                }

                //RecyclerView.LayoutManager llm = new LinearLayoutManager(rootView.getContext());
                rv.setLayoutManager(llm);
                ArrayList<String[]> recipes= new ArrayList<>();
                recipes.add(new String[] {"ACKEE", "Miguel"});
                recipes.add(new String[] {"Mango", "Miguel"});
                recipes.add(new String[] {"Apple", "Miguel"});
                recipes.add(new String[] {"Rice and Peas with fry chicken and green beans", "Miguel"});
                recipes.add(new String[] {"meal", "Miguel"});
                recipes.add(new String[] {"ACKEE", "Miguel"});
                recipes.add(new String[] {"Apple", "Miguel"});
                recipes.add(new String[] {"Orgin", "Miguel"});
                rv.setAdapter( new MealListAdapter(recipes));

                return rootView;
            }
            if(getArguments().getInt(ARG_SECTION_NUMBER)==2){
                View rootView = inflater.inflate(R.layout.fragment_meals, container, false);
                FloatingActionButton viewAllmeals =(FloatingActionButton) rootView.findViewById(R.id.viewAllmeals);

                viewAllmeals.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent allmeal=new Intent(getActivity(), ListedRecipes.class);
                        startActivity(allmeal);
                    }
                });
                return rootView;
            }
            if(getArguments().getInt(ARG_SECTION_NUMBER)==3){
                View rootView = inflater.inflate(R.layout.fragment_kitchen, container, false);
                return rootView;
            }
            if(getArguments().getInt(ARG_SECTION_NUMBER)==4){
                View rootView = inflater.inflate(R.layout.fragment_grocery_list, container, false);
                expListView = (ExpandableListView) rootView.findViewById(R.id.grocery_list_view);
                TextView someTextView = (TextView) rootView.findViewById(R.id.gitem);

                // preparing list data
                prepareListData();

                listAdapter = new GroceryIteamAdapter(getContext(), listDataHeader, listDataChild);

                // setting list adapter
                expListView.setAdapter(listAdapter);

                // Listview Group click listener
                expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v,
                                                int groupPosition, long id) {
                        // Toast.makeText(getApplicationContext(),
                        // "Group Clicked " + listDataHeader.get(groupPosition),
                        // Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

                // Listview Group expanded listener
                expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                    @Override
                    public void onGroupExpand(int groupPosition) {
                        Toast.makeText(getContext(),
                                listDataHeader.get(groupPosition) + " Expanded",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                // Listview Group collasped listener
                expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                    @Override
                    public void onGroupCollapse(int groupPosition) {
                        Toast.makeText(getContext(),
                                listDataHeader.get(groupPosition) + " Collapsed",
                                Toast.LENGTH_SHORT).show();

                    }
                });

                // Listview on child click listener
                expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v,
                                                int groupPosition, int childPosition, long id) {
                        // TODO Auto-generated method stub
                        Toast.makeText(
                                getContext(),
                                listDataHeader.get(groupPosition)
                                        + " : "
                                        + listDataChild.get(
                                        listDataHeader.get(groupPosition)).get(
                                        childPosition), Toast.LENGTH_SHORT)
                                .show();
                        return false;
                    }
                });
                return rootView;

                // List view
                //ListView lv=(ListView) rootView.findViewById(R.id.grocery_list_view);

                /*HashMap<String,String> itemAddress=new HashMap<>();
                itemAddress.put("Fruit","Apple");
                itemAddress.put("Vegetable","Carrot");
                itemAddress.put("Meat","Ham");

                List<HashMap<String,String>> listItem = new ArrayList<>();
                // Listview Adapter
                SimpleAdapter itemAdapters= new SimpleAdapter(getContext(),listItem,R.layout.grocery_item,
                        new String[]{"First Line","Second Line"},
                        new int[]{R.id.item_category,R.id.gitem});
                Iterator it=itemAddress.entrySet().iterator();

                while (it.hasNext()){
                    HashMap<String,String> resultMap=new HashMap<>();
                    Map.Entry pair= (Map.Entry)it.next();
                    resultMap.put("First Line", pair.getKey().toString());
                    resultMap.put("Second Line", pair.getValue().toString());
                    listItem.add(resultMap);
            }*/
                //ArrayAdapter<String> adapter;

                // Search EditText
                //EditText inputSearch;

                // ArrayList for List
                //Listview Data;
                //String category[]={"Fruit","Vegetable","Ground"};
                //adapter = new ArrayAdapter<String>(getContext(), R.layout.grocery_item, R.id.item_category, category);


                //Adding items to listview


                //lv.setAdapter(itemAdapters);

                //lv.setAdapter(adapter);

                //Menu mainMenu=(Menu)rootView.findViewById(R.menu.profile);
                //mainMenu.findItem(R.id.action_edit).setVisible(false);
                //return rootView;
            }
           /* if(getArguments().getInt(ARG_SECTION_NUMBER)==5){
                View rootView = inflater.inflate(R.layout.fragment_resturants, container, false);
                return rootView;
            }*/
            else {
                View rootView = inflater.inflate(R.layout.tab_layout, container, false);
                return rootView;
            }
        }

    /*private void iniMap(View view) {
            //SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
            //mapFragment.getMapAsync(this);

    }*/


    /*@Override
    public void onMapReady(GoogleMap googleMap) {
            mGoogleMap=googleMap;
    }*/
}

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 6 total pages.
            return 5;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                /*case 4:
                    return "GROCERY LIST";
                case 3:
                    return "MY RECIPES";
                case 4:
                    return "KITCHEN";
                case 4:
                    return "GROCERY LIST";
                case 4:
                    return " ";*/
            }
            return null;
        }
    }

    public static boolean googleServiceAvailable(View view){
        GoogleApiAvailability api=GoogleApiAvailability.getInstance();
        int isAvailable=api.isGooglePlayServicesAvailable(view.getContext());
        if(isAvailable== ConnectionResult.SUCCESS){
            return true;
        }else if(api.isUserResolvableError(isAvailable)){
            Dialog dialog=api.getErrorDialog((Activity) view.getContext(),isAvailable,0);
            dialog.show();
        }else{
            Toast.makeText(view.getContext(), "Can't Connect to Play Services",Toast.LENGTH_LONG).show();
        }
        return false;
    }
    private void setIconTabs(){
        //Store Icons for tabs headings
        int[] tabIcons= {
                R.drawable.ic_home_black_24dp,
                R.drawable.ic_folder_black_24dp,
                R.drawable.ic_kitchen_black_24dp,
                R.drawable.ic_shopping_cart_black_24dp,
                R.drawable.ic_map_black_24dp
        };

        //Set ICONS FOR TABS heandings
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);

    };
    public void editmenu(){
        //if(tabLayout.getTabAt(0).isSelected()){
            CharSequence text = "Hello toast!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this.getContext(), text, duration);
            toast.show();

        //}

    };
    /*
     * Preparing the list data
     */
    public static void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Fruits");
        listDataHeader.add("Vegetable");
        listDataHeader.add("Nuts");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}
