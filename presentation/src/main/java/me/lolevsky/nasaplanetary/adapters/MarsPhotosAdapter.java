package me.lolevsky.nasaplanetary.adapters;

import com.google.firebase.database.DataSnapshot;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.lolevsky.nasaplanetary.R;
import me.lolevsky.nasaplanetary.domain.imageloader.IImageLoader;
import me.lolevsky.nasaplanetary.model.objects.MarsPhoto;
import me.lolevsky.nasaplanetary.presenter.MarsPhotosPresenter;
import me.lolevsky.nasaplanetary.widget.ProgressImageView;
import rx.Subscriber;

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
        int comments = 0;
        Subscriber subscriber;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(MarsPhoto marsPhoto) {
            if (this.marsPhoto != null && subscriber != null) {
                subscriber.unsubscribe();
                this.marsPhoto = null;
            }

            this.marsPhoto = marsPhoto;

            comments = 0;
            title.setText(marsPhoto.getRoverName() + " - " + marsPhoto.getCameraFullName());

            image.getProgressBar().setVisibility(View.VISIBLE);
            imageLoader.loadImage(marsPhoto.getImgSrc(), R.drawable.place_holder, image.getImageView(), image
                    .getProgressBar());

            subscriber = new Subscriber<DataSnapshot>() {

                @Override public void onCompleted() {

                }

                @Override public void onError(Throwable e) {

                }

                @Override public void onNext(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getKey().equals(String.valueOf(MyViewHolder.this.marsPhoto.getId()))) {
                        comments = (int)dataSnapshot.getChildrenCount();
                        updateCommentsNumber();
                    }
                }
            };

            updateCommentsNumber();
            marsPhotosPresenter.getComments(subscriber, String.valueOf(marsPhoto.getId()));
        }

        private void updateCommentsNumber() {
            if(comments == 0){
                commentsText.setText(R.string.no_comments);
            }else{
                commentsText.setText(
                        commentsText.getContext().getResources()
                                    .getQuantityString(R.plurals.number_of_comments, comments, comments));
            }
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