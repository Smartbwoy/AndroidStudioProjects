package searchapplication.servicespp.com.search;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<String[]> recipes= new ArrayList<>();
    ArrayList<String[]> rlist= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.searchItem);

        RecyclerView.LayoutManager llm;
        llm=new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);


        //RecyclerView.LayoutManager llm = new LinearLayoutManager(rootView.getContext());
        rv.setLayoutManager(llm);
        recipes.add(new String[] {"ACKEE", "Miguel"});
        recipes.add(new String[] {"Mango", "Miguel"});
        recipes.add(new String[] {"Apple", "Miguel"});
        recipes.add(new String[] {"Rice and Peas with fry chicken and green beans", "Miguel"});
        recipes.add(new String[] {"meal", "Miguel"});
        recipes.add(new String[] {"ACKEE", "Miguel"});
        recipes.add(new String[] {"Apple", "Miguel"});
        recipes.add(new String[] {"Orgin", "Miguel"});
        //rv.setAdapter( new SearchItemAdapter(recipes));
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();

        search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(rv.getAdapter()!=null){
                    rlist.clear();;
                    rv.getAdapter().notifyDataSetChanged();
                }

                //Toast.makeText(getBaseContext(),s, Toast.LENGTH_SHORT).show();
                for(int i=0;i<recipes.size();i++){
                    if(recipes.get(i)[0].startsWith(s)){
                        rlist.add(recipes.get(i));
                    }
                    if(recipes.get(i)[1].startsWith(s)){
                        rlist.add(recipes.get(i));
                    }
                }

                rv.setAdapter( new SearchItemAdapter(rlist));

                return true;
            }
        });

        return true;

    }
}
