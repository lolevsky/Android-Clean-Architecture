package me.lolevsky.nasaplanetary.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.lolevsky.nasaplanetary.R;
import me.lolevsky.nasaplanetary.model.objects.MainScreen;

public class MainViewAdapter extends RecyclerView.Adapter<MainViewAdapter.MyViewHolder> {

    private List<MainScreen> mainList;

    public MainViewAdapter() {

    }

    public void setList(List<MainScreen> mainList) {
        this.mainList = mainList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_screen_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MainScreen movie = mainList.get(position);
        holder.title.setText(movie.getName());
    }

    @Override
    public int getItemCount() {
        return mainList == null ? 0 : mainList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title) public TextView title;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}