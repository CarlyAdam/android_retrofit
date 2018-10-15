package com.carlyadam.retrofit.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.carlyadam.retrofit.Activities.MainActivity;
import com.carlyadam.retrofit.Data.Book;
import com.carlyadam.retrofit.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    public ArrayList<Book> book_list;
    public Context mContext;
    private AdapterListener listener;
    private OnItemClickListener mOnItemClickListener;


    interface OnItemClickListener {
        void onItemClick(Book clickedBook);
    }

    public BookAdapter(ArrayList<Book> book_list, Context mContext) {
        this.book_list = book_list;
        this.mContext = mContext;
    }

    public BookAdapter(ArrayList<Book> book_list, Context mContext, AdapterListener listener) {
        this.book_list = book_list;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Book book = book_list.get(position);

        holder.name.setText(book.getTitle());
        holder.date.setText(book.getPublishDate());
        holder.background.setImageResource(R.drawable.book);
    }

    @Override
    public int getItemCount() {
        return book_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, date;
        private ImageView background;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.textView_book_name);
            date = (TextView) itemView.findViewById(R.id.textView_book_date);
            background = (ImageView) itemView.findViewById(R.id.imageView_background);

            background.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        new MaterialDialog.Builder((Activity) mContext)

                                .callback(new MaterialDialog.Callback() {
                                    @Override
                                    public void onPositive(MaterialDialog materialDialog) {

                                        //
                                }

                                    @Override
                                    public void onNegative(MaterialDialog dialog) {
                                        dialog.dismiss();
                                    }

                                })

                                .title(book_list.get(getAdapterPosition()).getTitle())
                                .content(book_list.get(getAdapterPosition()).getDescription())
                                .positiveText("Ok")
                                .build()
                                .show();
                    }
                }
            });
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                mOnItemClickListener.onItemClick(book_list.get(position));
            }
        }
    }

    public interface AdapterListener {
        void onIconTypeClicked(int position);

    }
}