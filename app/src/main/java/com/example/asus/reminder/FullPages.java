package com.example.asus.reminder;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FullPages extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Pages> list=new ArrayList<>();
    ViewAdapter2 viewAdapter;
    FloatingActionButton floatingActionButton;
    Button newpage1;
    Pages pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_pages);

        floatingActionButton=(FloatingActionButton) findViewById(R.id.newpage2);
        newpage1=(Button) findViewById(R.id.newpage1);


        setData();





        recyclerView=(RecyclerView) findViewById(R.id.recycle);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        viewAdapter=new ViewAdapter2(list);
        recyclerView.setAdapter(viewAdapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent tnt=new Intent(FullPages.this,MsWord.class);
                startActivity(tnt);
            }
        });
    }

    public class ViewAdapter2 extends RecyclerView.Adapter<ViewAdapter2.MyViewHolder2>{


        List<Pages> pages=new ArrayList<>();

        public class MyViewHolder2 extends RecyclerView.ViewHolder{

            TextView title,description,date,day,time;
            public MyViewHolder2(View itemView) {
                super(itemView);

                title=(TextView) itemView.findViewById(R.id.titleText);
                description=(TextView) itemView.findViewById(R.id.description);
                date=(TextView) itemView.findViewById(R.id.date);
                day=(TextView) itemView.findViewById(R.id.day);
                time=(TextView) itemView.findViewById(R.id.time);




            }
        }

        public ViewAdapter2(List<Pages> pages){


            this.pages=pages;
        }

        @NonNull
        @Override
        public ViewAdapter2.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fullpage,parent,false);

            return new ViewAdapter2.MyViewHolder2(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewAdapter2.MyViewHolder2 holder, int position) {

            Toast.makeText(FullPages.this,"position "+position,Toast.LENGTH_LONG).show();


            holder.title.setText(list.get(position).getTitle());
            holder.description.setText(list.get(position).getDescription());
            holder.date.setText(list.get(position).getDate());
            holder.day.setText(list.get(position).getDay());
            holder.time.setText(list.get(position).getTime());


        }

        @Override
        public int getItemCount() {
            return pages.size();
        }
    }

    public void setData(){

        DatabaseForDiary databaseForDiary=new DatabaseForDiary(getApplicationContext());
        Cursor c=databaseForDiary.showAllData();
        if(c!=null) {

            while (c.moveToNext()) {
                pages = new Pages(c.getString(2), c.getString(1), c.getString(4), c.getString(5), c.getString(3));
                list.add(pages);
            }
        }

    }
}
