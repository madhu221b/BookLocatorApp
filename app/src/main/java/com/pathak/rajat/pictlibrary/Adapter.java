package com.pathak.rajat.pictlibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter implements Filterable
{
    Context c;
    ArrayList<Book> category;
    ArrayList<Book> category_filter;
    CustomFilter filter;

    public Adapter(Context context,ArrayList<Book> category)
    {

        this.c = context;
        this.category = category;
        this.category_filter = category;
    }

    @Override
    public int getCount() {
        return category.size();
    }

    @Override
    public Object getItem(int i) {
        return category.get(i);
    }

    @Override
    public long getItemId(int i) {
        return category.indexOf(getItem(i));
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView==null)
        {

            convertView = inflater.inflate(R.layout.rowmodel,null);
        }

        TextView textView = (TextView)convertView.findViewById(R.id.text);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.image);


        textView.setText(category.get(pos).getName());
        imageView.setImageResource(category.get(pos).getImage());

        return convertView;
    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

    @Override
    public Filter getFilter()
    {
        if(filter==null)
        {
            filter = new CustomFilter();
        }
        return filter;
    }

    class CustomFilter extends Filter
    {


        @Override
        public FilterResults performFiltering(CharSequence constraint) {

            FilterResults filterResults = new FilterResults();


            if(constraint!= null && constraint.length() > 0)
            {

                constraint = constraint.toString().toUpperCase();
                ArrayList<Book> filters = new ArrayList<Book>();


                for(int i =0 ;i<category_filter.size();i++)
                {
                    if(category_filter.get(i).getName().toUpperCase().contains(constraint))
                    {
                        Book b = new Book(category_filter.get(i).getName(),category_filter.get(i).getImage());
                        filters.add(b);

                    }

                }

                filterResults.count  = filters.size();
                filterResults.values =filters;
            }
            else
            {

                filterResults.count = category_filter.size();
                filterResults.values = category_filter;
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {


            category = (ArrayList<Book>) results.values;
            notifyDataSetChanged();

        }
    }
}
