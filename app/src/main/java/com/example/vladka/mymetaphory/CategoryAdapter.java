
package com.example.vladka.mymetaphory;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vladka.mymetaphory.utilities.ColorUtils;


/**
 * Created by Vladka on 20/01/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.NumberViewHolder> {

    private static final String TAG = CategoryAdapter.class.getSimpleName();

    private static int viewHolderCount;

    final private ListItemClickListener mOnClickListener;

    private int mNumberItems;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    // constructor for CategoryAdapter that accepts a name of items to display and
    // the specification for the ListItemClickListener
    public CategoryAdapter(int numberOfItems, ListItemClickListener listener) {
        mNumberItems = numberOfItems;
        mOnClickListener = listener;

        viewHolderCount = 0;
    }

    // called when each new ViewHolder is created - when RV is laid out.
    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.category_list_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attachToParentInstantly = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, attachToParentInstantly);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        viewHolder.viewHolderIndex.setText("ViewHolder index: " + viewHolderCount);


        int backgroundColorForViewHolder = ColorUtils
                .getViewHolderCategoriesBackground(context, viewHolderCount);
        viewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);

        viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: "
        + viewHolderCount);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder (NumberViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    // returns the number of items to display
    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    // creating class NumberViewHolder that extends RecyclerView.ViewHolder
    class NumberViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener {

        TextView listItemNumberView;

        // displays which ViewHolder is displaying this data
        TextView viewHolderIndex;

        // constructor for NumberViewHolder and accepts itemView as a parameter
        // I set onClickListener to listen to clicks - in onClick method
        public NumberViewHolder(View itemView) {
            super (itemView);

            listItemNumberView = itemView.findViewById(R.id.tv_category_number);
            viewHolderIndex = itemView.findViewById(R.id.tv_view_holder_instance);
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {
            listItemNumberView.setText(String.valueOf(listIndex));
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}

