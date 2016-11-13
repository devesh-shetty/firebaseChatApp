package chat.test.com.ping.message.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import chat.test.com.ping.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link FriendListItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MessageListRecyclerViewAdapter extends RecyclerView.Adapter<MessageListRecyclerViewAdapter.ViewHolder> {

    private final List<FriendListItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MessageListRecyclerViewAdapter(List<FriendListItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_message_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTVFriendName.setText(mValues.get(position).getFriendName());

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTVFriendName;
        public FriendListItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTVFriendName = (TextView) view.findViewById(R.id.tv_friendName);
        }

        @Override
        public String toString() {
            return super.toString() + " '";
        }
    }
}
