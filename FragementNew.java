package com.example.dong.news;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.List;

public class FragementNew extends Fragment {
    private ListView titlelist;
    private Adapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_new, container, false);
        titlelist=(ListView)view.findViewById(R.id.mylist);
        Bundle bundle1 = getArguments();
        news neww = (news) bundle1.getSerializable("new");
        Log.d("TAE","////////////////"+neww.getNewslist().get(1).getTitle());
        List<news.NewslistBean> list=neww.getNewslist();
        Log.d("Tea","/////////////////////////"+list.get(1).getTitle());
        adapter=new Adapter(list,this.getActivity());
        titlelist.setAdapter(adapter);
        return view;
    }
}
