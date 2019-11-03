package com.example.asus.reminder;

import android.app.Notification;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Allpages extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Pages> list=new ArrayList<>();
    List<List> list2=new ArrayList<>();
    ViewAdapter2 viewAdapter;
    FloatingActionButton floatingActionButton;
    Button newpage1;
    Pages pages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allpages);

        floatingActionButton=(FloatingActionButton) findViewById(R.id.newpage2);
        newpage1=(Button) findViewById(R.id.newpage1);


        pages=new Pages("Description1","title1","date1","saturday","9:30pm");
        list.add(pages);
        pages=new Pages("Description2","title2","date2","sunday","10:30pm");
        list.add(pages);
        pages=new Pages("Description3","title3","date3","monday","11:30pm");
        list.add(pages);
        list2.add(list);
        list.removeAll(list);

        pages=new Pages("Description4","title1","date1","saturday","9:30pm");
        list.add(pages);
        pages=new Pages("Description5","title2","date2","sunday","10:30pm");
        list.add(pages);
        pages=new Pages("Description6","title3","date3","monday","11:30pm");
        list.add(pages);
        list2.add(list);
        list.removeAll(list);

        pages=new Pages("Description7","title1","date1","saturday","9:30pm");
        list.add(pages);
        pages=new Pages("Description8","title2","date2","sunday","10:30pm");
        list.add(pages);
        pages=new Pages("Description9","title3","date3","monday","11:30pm");
        list.add(pages);
        list2.add(list);
        list.removeAll(list);

        pages=new Pages("Description10","title1","date1","saturday","9:30pm");
        list.add(pages);
        pages=new Pages("Description11","title2","date2","sunday","10:30pm");
        list.add(pages);
        pages=new Pages("Description12","title3","date3","monday","11:30pm");
        list.add(pages);
        list2.add(list);



        recyclerView=(RecyclerView) findViewById(R.id.recycle);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        viewAdapter=new ViewAdapter2(list2);
        recyclerView.setAdapter(viewAdapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent tnt=new Intent(Allpages.this,MsWord.class);
                startActivity(tnt);
            }
        });

        newpage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent tnt=new Intent(Allpages.this,MsWord.class);
                startActivity(tnt);
            }
        });



    }



    public class ViewAdapter2 extends RecyclerView.Adapter<ViewAdapter2.MyViewHolder2>{


        List<List> pages=new ArrayList<>();

        public class MyViewHolder2 extends RecyclerView.ViewHolder{

            TextView page1,page2,page3;
            public MyViewHolder2(View itemView) {
                super(itemView);

                page1=(TextView) itemView.findViewById(R.id.textView2);
                page2=(TextView) itemView.findViewById(R.id.textView3);
                page3=(TextView) itemView.findViewById(R.id.textView4);


                page1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent tnt=new Intent(Allpages.this,FullPages.class);
                        startActivity(tnt);
                    }
                });

                page3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent tnt=new Intent(Allpages.this,FullPages.class);
                        startActivity(tnt);
                    }
                });

                page3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent tnt=new Intent(Allpages.this,FullPages.class);
                        startActivity(tnt);
                    }
                });




            }
        }

        public ViewAdapter2(List<List> pages){


            this.pages=pages;
        }

        @NonNull
        @Override
        public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.everypages,parent,false);

            return new MyViewHolder2(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {

            Toast.makeText(Allpages.this,"position "+position,Toast.LENGTH_LONG).show();
            List<Pages> list=pages.get(position);

            holder.page1.setText(list.get(0).getDescription());
            holder.page2.setText(list.get(1).getDescription());
            holder.page3.setText(list.get(2).getDescription());


        }

        @Override
        public int getItemCount() {
            return pages.size();
        }
    }


}
