package com.hoffer.test.callhistory.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hoffer.test.callhistory.R;
import com.hoffer.test.callhistory.model.CallLog;
import com.hoffer.test.callhistory.model.SortLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * fragment of call log after sort
 */
public class SortFragment extends Fragment implements ILogView{
    private List<SortLog> sortLogs;
    private List<CallLog> callLogs;
    private RecyclerView mListView;
    private SortLogAdapter mAdapter;
    private ProgressBar mProgressBar;

    public SortFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort, container, false);
        // Inflate the layout for this fragment
        initView(view);
        return view;
    }

    public void initView(View view){
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        mListView = (RecyclerView) view.findViewById(R.id.log_list);
        mAdapter = new SortLogAdapter();
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void showLogs(List<CallLog> logs) {
        mProgressBar.setVisibility(View.GONE);
        HashMap<String,SortLog> sortLogsMap = new HashMap<>();
        callLogs = new ArrayList<>();
        callLogs.addAll(logs);
        sortLogs = new ArrayList<>();
        for(CallLog callLog:logs){
            String key = getKey(callLog);
            SortLog sortLog = sortLogsMap.get(key);
            if(sortLog!=null)
                sortLog.addLog(callLog);
            else {
                sortLog = new SortLog(callLog.getNumber(),callLog.getName(),callLog.getType());
                sortLog.addLog(callLog);
                sortLogsMap.put(key,sortLog);
            }
        }
        showSortLogs(sortLogsMap);
    }

    public void showSortLogs(HashMap<String,SortLog> sortLogsMap){
        for(SortLog sortLog:sortLogsMap.values()){
            sortLogs.add(sortLog);
        }
        mAdapter.notifyDataSetChanged();
    }

    public String getKey(CallLog callLog){
        return  callLog.getNumber() + callLog.getType();
    }

    @Override
    public void addLog(CallLog log) {
        callLogs.add(log);
        showLogs(callLogs);
    }

    @Override
    public void removeLog(CallLog log) {
        for(int i=0;i<callLogs.size();i++){
            if(callLogs.get(i).toString().equals(log.toString())){
                callLogs.remove(i);
                break;
            }
        }
        showLogs(callLogs);
    }

    public class SortLogAdapter extends RecyclerView.Adapter<ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return new ViewHolder(inflater.inflate(R.layout.item_sort_call,null));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final SortLog log = sortLogs.get(position);
            holder.nameView.setText(log.getName());
            holder.numberView.setText(log.getNumber());
            holder.typeView.setText(log.getType());
            holder.lastTimeView.setText(log.getLastTime());
            holder.countView.setText(String.valueOf(log.getCount()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(),DetailActivity.class);
                    intent.putParcelableArrayListExtra(DetailActivity.EXTRA_LOGS, (ArrayList<? extends Parcelable>) log.getLogs());
                    startActivity(intent);
                }
            });
            return;
        }

        @Override
        public int getItemCount() {
            return sortLogs == null?0:sortLogs.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nameView,numberView,typeView,lastTimeView,countView;
        public ViewHolder(View itemView) {
            super(itemView);
            nameView = (TextView) itemView.findViewById(R.id.name);
            numberView = (TextView) itemView.findViewById(R.id.number);
            typeView = (TextView) itemView.findViewById(R.id.type);
            lastTimeView = (TextView) itemView.findViewById(R.id.time);
            countView = (TextView) itemView.findViewById(R.id.count);
        }
    }
}
