package com.sailpass.myapplication;

import android.content.Context;
import android.view.*;
import android.widget.*;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> implements Filterable{

    private OnBookListener mOnBookListener;
    private boolean queried;
    Context context;
    LayoutInflater inflater;
    Model.DataSource dataSource;
    Model.DataSource fullData;

    public SearchAdapter(Context context, Model.DataSource dataSource, OnBookListener onBookListener){

        this.context = context;
        this.dataSource = dataSource;
        this.fullData = new Model.DataSource(new ArrayList<Model.Ferry> (dataSource.data));
        this.mOnBookListener = onBookListener;
        this.queried = false;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ferryView = inflater.inflate(R.layout.cardview, parent, false);
        return new SearchViewHolder(ferryView, mOnBookListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
        holder.textViewName.setText("Destination: " + dataSource.get(position).getDestination());
        holder.textViewPrice.setText("Adult Ticket Price: $" + dataSource.get(position).getPrice());
        holder.textViewDepartureDate.setText("Departure Date: " + dataSource.get(position).getDateDeparture() + " " + dataSource.get(position).getTimeDeparture());
        holder.textViewArrivalDate.setText("Arrival Date: " + dataSource.get(position).getDateArrival() + " " + dataSource.get(position).getTimeArrival());
        holder.textViewPriceChild.setText("Child Ticket Price: $"+dataSource.get(position).priceChild);

    }

    @Override
    public int getItemCount() {
        return this.dataSource.count();
    }

    // Filter for Destination
    @Override
    public Filter getFilter(){
        return query;
    }

    private Filter query = new Filter(){
        @Override
        protected FilterResults performFiltering(CharSequence s){
            List<Model.Ferry> filteredList =  new ArrayList<>();
            if (s == null || s.length() == 0){
                filteredList.addAll(fullData.data);
            }  else{
                String filterPattern = s.toString().toLowerCase().trim();
                for (Model.Ferry f : dataSource.data){
                    if(f.getDestination().toLowerCase().contains(filterPattern)){
                        filteredList.add(f);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults (CharSequence s, FilterResults results){
            dataSource.data.clear();
            dataSource.data.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    // Filter for Arrival Date
    public Filter getArrivalFilter(){
        return queryArrival;
    }

    private Filter queryArrival = new Filter(){
        @Override
        protected FilterResults performFiltering(CharSequence s){
            List<Model.Ferry> filteredList =  new ArrayList<>();
            if (s == null || s.length() == 0 ){
                filteredList.addAll(fullData.data);
            } else{
                String filterPattern = s.toString().trim();
                for (Model.Ferry f : dataSource.data){
                    if(f.getDateArrival().toLowerCase().contains(filterPattern)){
                        filteredList.add(f);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults (CharSequence s, FilterResults results){
            dataSource.data.clear();
            dataSource.data.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    // Filter for Departure Date
    public Filter getDepartureFilter(){
        return queryDeparture;
    }

    private Filter queryDeparture = new Filter(){
        @Override
        protected FilterResults performFiltering(CharSequence s){

            List<Model.Ferry> filteredList =  new ArrayList<>();
            if (s == null || s.length() == 0 ){
                filteredList.addAll(fullData.data);
            } else{
                String filterPattern = s.toString().trim();
                for (Model.Ferry f : dataSource.data){
                    if(f.getDateDeparture().toLowerCase().contains(filterPattern)){
                        filteredList.add(f);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults (CharSequence s, FilterResults results){
            dataSource.data.clear();
            dataSource.data.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewName;
        TextView textViewPrice;
        TextView textViewArrivalDate;
        TextView textViewDepartureDate;
        TextView textViewPriceChild;
        Button purchase;
        OnBookListener onBookListener;

        SearchViewHolder(View view, OnBookListener onBookListener) {
            super(view);
            textViewName = view.findViewById(R.id.cardViewTextName);
            textViewPrice = view.findViewById(R.id.cardViewTextPrice);
            textViewPriceChild = view.findViewById(R.id.cardViewTextPriceChild);
            textViewDepartureDate = view.findViewById(R.id.cardViewTextDeparture);
            textViewArrivalDate = view.findViewById(R.id.cardViewTextArrival);
            this.onBookListener = onBookListener;
            purchase = view.findViewById(R.id.bookBtn);
            purchase.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onBookListener.onBtnClick(getAdapterPosition());
        }
    }


    public interface OnBookListener{
        void onBtnClick(int position);
    }

}