package searchapplication.servicespp.com.search;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Smartbwoy on 7/3/2018.
 */

public class SearchItemAdapter  extends RecyclerView.Adapter<SearchItemAdapter.ViewHolder> {
    ArrayList<String[]> searchlist;
    public SearchItemAdapter(ArrayList<String[]> searchlist) {

        this.searchlist=searchlist;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView service;

        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            service = (TextView)itemView.findViewById(R.id.service);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewmeal,parent,false));
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,parent,false);
        SearchItemAdapter.ViewHolder vh = new SearchItemAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(searchlist.get(position)[0]);
        holder.service.setText(searchlist.get(position)[1]);
    }

    @Override
    public int getItemCount() {
        return searchlist.size();
    }


}
