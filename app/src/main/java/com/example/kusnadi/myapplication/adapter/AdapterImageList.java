package com.example.kusnadi.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import com.example.kusnadi.myapplication.R;
import com.example.kusnadi.myapplication.data.Constant;
import com.example.kusnadi.myapplication.model.Images;

public class AdapterImageList extends RecyclerView.Adapter<AdapterImageList.ViewHolder> {

    private List<Images> items = new ArrayList<>();
    private ImageLoader imgloader = ImageLoader.getInstance();
    private OnItemClickListener onItemClickListener;

    private int lastPosition = -1;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView image;
        public MaterialRippleLayout lyt_parent;

        public ViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
            lyt_parent = (MaterialRippleLayout) v.findViewById(R.id.lyt_parent);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterImageList(List<Images> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // ReString the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String p = items.get(position).getImageUrl();
        imgloader.displayImage(Constant.getURLimgPlace(p), holder.image);
        holder.lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Give some delay to the ripple to finish the effect
                onItemClickListener.onItemClick(v, p, position);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, String viewModel, int pos);
    }
}