package ib.database.unused;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import ib.database.R;

public class MajorMyAdapter extends RecyclerView.Adapter<MajorMyAdapter.ViewHolder> {
    private String[][] mDataset;
    public static View v_student;
    public static String post_id;
    public static String post_name;
    private Context context;
    private static RecyclerViewClickListener itemListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView post_name;
        public TextView desc;
        public TextView price;
        public LinearLayout test;

        public ViewHolder(View v) {
            super(v);
            post_name = (TextView) v.findViewById(R.id.Favstock_name);
            desc = (TextView) v.findViewById(R.id.item_desc);
            price = (TextView) v.findViewById(R.id.Favstock_price);
            test=(LinearLayout) v.findViewById(R.id.test);

        }
    }
/**
    public void add(int position, String item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }
    **/

    // Provide a suitable constructor (depends on the kind of dataset)
  /*  public MyAdapter(String[][] myDataset) {
        mDataset = myDataset;
    }
*/
    public MajorMyAdapter(Context context, RecyclerViewClickListener itemListener, String[][] myDataset) {

        this.context = context;
        this.itemListener = itemListener;
        mDataset = myDataset;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public MajorMyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.major_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       /*
        for(int i=0;i<mDataset[0].length;i++)
            for(int j=0;j<mDataset.length+1;j++)
            {
                System.out.println(" j= " +j  +" i= " +i  +" " +val[j][i]);
            }
*/
        //if (mDataset.length-1>position) {
            holder.post_name.setText(mDataset[position][1]);
            holder.desc.setText(mDataset[position][2]);
            holder.price.setText(mDataset[position][3]);

            post_id = mDataset[position][0];
            System.out.println(post_id);
            holder.test.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //System.out.println(mDataset[position][4]);
                    post_id = mDataset[position][3];
                    // new click();
                    itemListener.recyclerViewListClicked(v, position);
                }
            });


            // holder.txtFooter.setText("Footer: " + mDataset.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }





}
