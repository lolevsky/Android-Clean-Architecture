package me.lolevsky.nasaplanetary.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.lolevsky.nasaplanetary.R;
import me.lolevsky.nasaplanetary.data.net.request.PhotoComment;
import me.lolevsky.nasaplanetary.view.screens.photocomment.PhotoCommentPresenter;

public class PhotoCommentsAdapter extends RecyclerView.Adapter<PhotoCommentsAdapter.MyViewHolder> {
    private PhotoCommentPresenter photoCommentPresenter;

    public PhotoCommentsAdapter(PhotoCommentPresenter photoCommentPresenter) {
        this.photoCommentPresenter = photoCommentPresenter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photos_comment_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PhotoComment comment = photoCommentPresenter.getModel().getComments().get(position);
        holder.setData(comment);
    }

    @Override
    public int getItemCount() {
        if (photoCommentPresenter.getModel() == null) {
            return 0;
        }
        return photoCommentPresenter.getModel().getComments() == null ? 0 : photoCommentPresenter.getModel().getComments().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.comment) TextView comment;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(PhotoComment comment) {
            this.comment.setText(comment.getComment());
        }
    }
}