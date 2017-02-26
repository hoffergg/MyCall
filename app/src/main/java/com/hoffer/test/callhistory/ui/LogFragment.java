package com.hoffer.test.callhistory.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.hoffer.test.callhistory.task.ReadDataTask;

import java.util.ArrayList;
import java.util.List;

/**
 * fragment of call logs.
 */
public class LogFragment extends Fragment implements ILogView{
    private ILogView mLogView;
    private RecyclerView mListView;
    private CallLogAdapter mAdapter;
    private List<CallLog> mLogs;
    private ProgressBar mProgressBar;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public LogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log, container, false);
        initView(view);
        return view;
    }

    public void initView(View view){
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        mListView = (RecyclerView) view.findViewById(R.id.log_list);
        mAdapter = new CallLogAdapter();
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListView.setAdapter(mAdapter);
    }

    /**
     * show logs
     * @param logs
     */
    @Override
    public void showLogs(List<CallLog> logs){
        if(mProgressBar!=null)
            mProgressBar.setVisibility(View.GONE);
        mLogs = new ArrayList<>();
        mLogs.addAll(logs);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addLog(CallLog log) {
        mLogs.add(log);
        mAdapter.notifyItemInserted(mLogs.size());
    }

    @Override
    public void removeLog(CallLog log) {
        for(int i=0;i<mLogs.size();i++){
            if(mLogs.get(i).toString().equals(log.toString())){
                mLogs.remove(i);
                mAdapter.notifyItemRemoved(i);
                break;
            }
        }
    }

    public class CallLogAdapter extends RecyclerView.Adapter<MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return new MyViewHolder(inflater.inflate(R.layout.item_call,null));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            CallLog log = mLogs.get(position);
            holder.nameView.setText(log.getName());
            holder.numberView.setText(log.getNumber());
            holder.typeView.setText(log.getType());
            holder.timeView.setText(log.getTime());
            holder.durationView.setText(log.getDurationSec());
            return;
        }

        @Override
        public int getItemCount() {
            return mLogs == null?0:mLogs.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameView,numberView,typeView,timeView,durationView;
        public MyViewHolder(View itemView) {
            super(itemView);
            nameView = (TextView) itemView.findViewById(R.id.name);
            numberView = (TextView) itemView.findViewById(R.id.number);
            typeView = (TextView) itemView.findViewById(R.id.type);
            timeView = (TextView) itemView.findViewById(R.id.time);
            durationView = (TextView) itemView.findViewById(R.id.duration);
        }
    }
}
