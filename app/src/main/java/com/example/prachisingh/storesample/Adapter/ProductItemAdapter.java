package com.example.prachisingh.storesample.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prachisingh.storesample.ApiResponses.ProductObject;
import com.example.prachisingh.storesample.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by prachisingh on 23/09/18.
 */

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ProductViewHolder> {
    Context mContext;
    ArrayList<ProductObject> mProductList;
    String variantString=null;
    public ProductItemAdapter(Context context, ArrayList<ProductObject> productList){
        mContext=context;
        mProductList=productList;
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_item_layout, parent, false);
        return new ProductViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        holder.productName.setText(mProductList.get(position).getTitle());
        holder.productQuantity.setText("QUANTITY : "+getQuantity(mProductList.get(position)));
        holder.productVariant.setText("VARIANTS : "+getVariants(mProductList.get(position)));
        Picasso.with(mContext).load(mProductList.get(position).getImage().getSrc()).into(holder.productImage);

    }

    private String getVariants(ProductObject productObject) {
        String mVariants="";
        for (int i = 0; i < productObject.getVariants().size(); i++) {
            mVariants=mVariants+productObject.getVariants().get(i).getTitle()+" , ";
        }
        return mVariants;
    }

    private String getQuantity(ProductObject productObject) {
        int mSum=0;
        for (int i = 0; i < productObject.getVariants().size(); i++) {
            mSum=mSum+productObject.getVariants().get(i).getInventoryQuantity();
        }

        return String.valueOf(mSum);
    }


    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_image)
        ImageView productImage;
        @BindView(R.id.product_name)
        TextView productName;
        @BindView(R.id.product_quantity)
        TextView productQuantity;
        @BindView(R.id.product_variant)
        TextView productVariant;
        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
