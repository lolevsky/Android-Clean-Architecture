package me.lolevsky.nasaplanetary.adapters;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.lolevsky.nasaplanetary.R;
import me.lolevsky.nasaplanetary.domain.imageloader.IImageLoader;
import me.lolevsky.nasaplanetary.model.objects.MarsPhoto;
import me.lolevsky.nasaplanetary.model.objects.PhotoComment;
import me.lolevsky.nasaplanetary.presenter.MarsPhotosPresenter;
import me.lolevsky.nasaplanetary.widget.ProgressImageView;

public class MarsPhotosAdapter extends RecyclerView.Adapter<MarsPhotosAdapter.MyViewHolder> {
    private IImageLoader imageLoader;
    private MarsPhotosPresenter marsPhotosPresenter;

    public MarsPhotosAdapter(IImageLoader imageLoader, MarsPhotosPresenter marsPhotosPresenter) {
        this.imageLoader = imageLoader;
        this.marsPhotosPresenter = marsPhotosPresenter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mars_photos_screen_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MarsPhoto marsPhoto = marsPhotosPresenter.getModel().getMarsPhotos().get(position);
        holder.setData(marsPhoto);
    }

    @Override
    public int getItemCount() {
        if (marsPhotosPresenter.getModel() == null) {
            return 0;
        }
        return marsPhotosPresenter.getModel().getMarsPhotos() == null ? 0 : marsPhotosPresenter.getModel().getMarsPhotos().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title) TextView title;
        @BindView(R.id.comments) TextView commentsText;
        @BindView(R.id.image) ProgressImageView image;
        MarsPhoto marsPhoto;
        List<PhotoComment> comments = new ArrayList<>();
        ValueEventListener valueEventListener;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(MarsPhoto marsPhoto) {
            if (this.marsPhoto != null && valueEventListener != null) {
                marsPhotosPresenter.removeMarsPhotoComments(this.marsPhoto.getId(), this.valueEventListener);
                this.valueEventListener = null;
                this.marsPhoto = null;
            }

            this.marsPhoto = marsPhoto;

            comments.clear();
            title.setText(marsPhoto.getRoverName() + " - " + marsPhoto.getCameraFullName());

            image.getProgressBar().setVisibility(View.VISIBLE);
            imageLoader.loadImage(marsPhoto.getImgSrc(), R.drawable.place_holder, image.getImageView(), image
                    .getProgressBar());

            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getKey().equals(String.valueOf(MyViewHolder.this.marsPhoto.getId()))) {
                        GenericTypeIndicator<Map<String, PhotoComment>> t = new GenericTypeIndicator<Map<String, PhotoComment>>
                                () {
                        };

                        comments.clear();
                        Map<String, PhotoComment> stringList = dataSnapshot.getValue(t);

                        if (stringList != null && stringList.size() > 0) {
                            comments.addAll(stringList.values());
                        }

                        updateCommentsNumber();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            updateCommentsNumber();
            marsPhotosPresenter.getMarsPhotoComments(marsPhoto.getId(), valueEventListener);
        }

        private void updateCommentsNumber() {
            commentsText.setText(String.format(commentsText.getContext().getString(R.string.number_of_comments),
                    comments.size()));
        }

        @OnClick(R.id.comments)
        public void onCommentClick() {
            marsPhotosPresenter.onCommentClick(marsPhoto.getId());
        }

        @OnClick(R.id.row)
        public void onItemClick() {

        }
    }
}