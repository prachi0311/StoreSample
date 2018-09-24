package com.example.prachisingh.storesample.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.example.prachisingh.storesample.Adapter.ProductItemAdapter;
import com.example.prachisingh.storesample.ApiResponses.ProductItemsResponse;
import com.example.prachisingh.storesample.ApiResponses.ProductObject;
import com.example.prachisingh.storesample.Network.ApiClient;
import com.example.prachisingh.storesample.Network.ApiInterface;
import com.example.prachisingh.storesample.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.prachisingh.storesample.StringConstants.ACCESSTOKEN;
import static com.example.prachisingh.storesample.StringConstants.PAGENUMBER;
import static com.example.prachisingh.storesample.StringConstants.PRODUCTIDARRAY;
import static com.example.prachisingh.storesample.StringConstants.SELECTEDTAG;
import static com.example.prachisingh.storesample.StringConstants.PRODUCTIDARRAY;

public class ProductListActivity extends AppCompatActivity {
    ArrayList<Long> productIdList;
    int pageNumber;
    String accessToken;
    String tagName;
    ArrayList<ProductObject> productList;
    ArrayList<ProductObject> showProductList;
    @BindView(R.id.product_recyclerview)
    RecyclerView recyclerView;
    ProductItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        ActionBar abar = getSupportActionBar();
        abar.setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        tagName=i.getStringExtra(SELECTEDTAG);
        abar.setTitle("TAG"+" = " + '"' + tagName +'"');
        pageNumber = i.getIntExtra(PAGENUMBER, -1);
        accessToken = i.getStringExtra(ACCESSTOKEN);
        productIdList = new ArrayList<>();
        productList = new ArrayList<>();
        showProductList = new ArrayList<>();
        productIdList = (ArrayList<Long>) i.getSerializableExtra(PRODUCTIDARRAY);
        adapter=new ProductItemAdapter(this,showProductList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        getProducts(pageNumber, accessToken);
    }

    private void getProducts(int pageNumber, String accessToken) {
        ApiInterface apiInterface = new ApiClient().getAuthorizedApiInterface();
        Call<ProductItemsResponse> call = apiInterface.getProduct(pageNumber, accessToken);
        call.enqueue(new Callback<ProductItemsResponse>() {
            @Override
            public void onResponse(Call<ProductItemsResponse> call, Response<ProductItemsResponse> response) {
                if (response.code() == 200) {
                    productList.addAll(response.body().getProducts());
                    selectProducts(productList);

                }
            }

            @Override
            public void onFailure(Call<ProductItemsResponse> call, Throwable t) {
                Log.i("ApiResponse", t.toString());

            }
        });

    }

    private void selectProducts(ArrayList<ProductObject> productList) {

        for (int i = 0; i < productIdList.size(); i++) {
            for (int j = 0; j < productList.size(); j++) {
                if(productList.get(j).getId()==productIdList.get(i))
                showProductList.add(productList.get(j));

            }

        }
        adapter.notifyDataSetChanged();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
