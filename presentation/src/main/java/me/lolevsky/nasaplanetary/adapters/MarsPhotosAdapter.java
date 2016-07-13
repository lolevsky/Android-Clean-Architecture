package me.lolevsky.nasaplanetary.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.lolevsky.nasaplanetary.R;
import me.lolevsky.nasaplanetary.domain.imageloader.IImageLoader;
import me.lolevsky.nasaplanetary.model.objects.MainScreen;
import me.lolevsky.nasaplanetary.model.objects.MarsPhoto;
import me.lolevsky.nasaplanetary.widget.ProgressImageView;

public class MarsPhotosAdapter extends RecyclerView.Adapter<MarsPhotosAdapter.MyViewHolder> {

    private List<MarsPhoto> mainList;
    private IImageLoader imageLoader;
    private OnItemClicked onItemClicked;

    public MarsPhotosAdapter(IImageLoader imageLoader, OnItemClicked onItemClicked) {
        this.imageLoader = imageLoader;
        this.onItemClicked = onItemClicked;
    }

    public void setList(List<MarsPhoto> mainList) {
        this.mainList = mainList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_screen_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.position = position;

        MarsPhoto marsPhoto = mainList.get(position);
        holder.title.setText(marsPhoto.getRoverName() + " - " + marsPhoto.getCameraFullName());

        holder.image.getProgressBar().setVisibility(View.VISIBLE);
        imageLoader.loadImage(marsPhoto.getImgSrc(), R.drawable.place_holder, holder.image.getImageView(), holder
                .image.getProgressBar());
    }

    @Override
    public int getItemCount() {
        return mainList == null ? 0 : mainList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title) public TextView title;
        @BindView(R.id.image) public ProgressImageView image;

        public int position = -1;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.row)
        public void onItemClick(){
            if(onItemClicked != null){
                onItemClicked.onItemClicked(position);
            }
        }
    }
}