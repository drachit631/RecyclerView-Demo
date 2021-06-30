package com.drachit.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.AccessControlContext;
import java.util.ArrayList;
import java.util.Locale;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView category_recycler_view;
    String[] categoryArray={"Electronics","Fashion","Sports","Grocery"};
    int[] imageArray={R.drawable.elecrtonics,R.drawable.fashion,R.drawable.sports,R.drawable.grocery};
    CategoryAdapter categoryAdapter;
    ArrayList<categoryList> arraycategorylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        category_recycler_view=findViewById(R.id.category_recycler_view);
        RecyclerViewGrid();
    }

    private void RecyclerViewGrid() {
        category_recycler_view.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        category_recycler_view.setItemAnimator(new DefaultItemAnimator());
        category_recycler_view.setNestedScrollingEnabled(false);

        arraycategorylist =new ArrayList<>();
        for (int i=0 ; i<categoryArray.length ; i++){
            categoryList list = new categoryList();
            list.setCategoryName(categoryArray[i]);
            list.setCategoryImage(imageArray[i]);
            arraycategorylist.add(list);
        }

        categoryAdapter = new CategoryAdapter(getApplicationContext(), arraycategorylist);
        category_recycler_view.setAdapter(categoryAdapter);
    }

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.categoryHolder> {
        Context context;
        ArrayList<categoryList> arrayCategorylist;

        public CategoryAdapter(Context context , ArrayList<categoryList> arraylist) {
            this.context = context;
            this.arrayCategorylist = arraylist;
        }

        @NonNull
        @Override
        public CategoryAdapter.categoryHolder onCreateViewHolder(@NonNull  ViewGroup parent , int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_category , parent , false);
            return new categoryHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CategoryAdapter.categoryHolder holder , int position) {
            holder.textView.setText(arrayCategorylist.get(position).getCategoryName());
            holder.imageView.setImageResource(arrayCategorylist.get(position).getCategoryImage());
        }

        @Override
        public int getItemCount() {
            return arrayCategorylist.size();
        }

        public class categoryHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;
            public categoryHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.category_iv);
                textView = itemView.findViewById(R.id.category_tv);
            }
        }
    }
}