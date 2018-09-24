package com.example.prachisingh.storesample.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.prachisingh.storesample.Adapter.TagItemAdapter;
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

public class TagActivity extends AppCompatActivity {
    int pageNumber;
    String accessToken;
    @BindView(R.id.tags_recyclerview)
    RecyclerView recyclerView;
    ListView mListView;
    TagItemAdapter adapter;
    ArrayList<ProductObject> productList;
    ArrayList<String> tagList;
    HashMap<String,ArrayList<Long>> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);
        ActionBar abar = getSupportActionBar();
        abar.setTitle("TAGS");
        ButterKnife.bind(this);
        productList=new ArrayList<>();
        tagList=new ArrayList<>();
        map=new HashMap<>();
        pageNumber=1;
        accessToken=ACCESSTOKEN;
        adapter=new TagItemAdapter(this,tagList,map,pageNumber,accessToken);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
        getProducts(pageNumber,accessToken);

    }

    private void getProducts(int pageNumber, String accessToken) {
        ApiInterface apiInterface=new ApiClient().getAuthorizedApiInterface();
        Call<ProductItemsResponse> call=apiInterface.getProduct(pageNumber,accessToken);
        call.enqueue(new Callback<ProductItemsResponse>() {
            @Override
            public void onResponse(Call<ProductItemsResponse> call, Response<ProductItemsResponse> response) {
                if(response.code()==200){
                    productList.addAll(response.body().getProducts());
                    getTagList(productList);

                }
            }

            @Override
            public void onFailure(Call<ProductItemsResponse> call, Throwable t) {
                Log.i("ApiResponce",t.toString());

            }
        });
    }

    private void getTagList(ArrayList<ProductObject> productList) {

        for(int i=0;i<productList.size();i++){
            String tag=productList.get(i).getTags();
            String[] tags=tag.split(", ");
            getUniqueList(tags,i);
        }
        updateTagList(map);
    }

    private void getUniqueList(String[] tags,int productItem) {

        for(int i=0;i<tags.length;i++){
            if(!map.containsKey(tags[i])){
                ArrayList<Long> temp=new ArrayList<Long>();
                temp.add(productList.get(productItem).getId());
                map.put(tags[i],temp);
            }
            else{
                ArrayList<Long> temp=map.get(tags[i]);
                temp.add(productList.get(productItem).getId());
                map.put(tags[i],temp);
            }

        }

    }

    private void updateTagList(HashMap<String, ArrayList<Long>> map) {
        tagList.addAll(map.keySet());
        adapter.notifyDataSetChanged();
    }
}
