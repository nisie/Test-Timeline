package com.nisieapp.nis.testtimeline;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nisie on 7/2/2016.
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private static final int VIEW_PROMO = 111;
    private static final int VIEW_TOPADS_SHOP = 112;
    private static final int VIEW_TOPADS_PRODUCT = 113;
    private static final int VIEW_MANAGE_PRODUCT = 102;
    private static final int VIEW_MANAGE_SHOP = 103;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView poster;
        TextView time;
        TextView context;
        ImageView avatar;

        public ViewHolder(View v) {
            super(v);
            poster = (TextView) v.findViewById(R.id.poster);
            time = (TextView) v.findViewById(R.id.time);
            context = (TextView) v.findViewById(R.id.context);
            avatar = (ImageView) v.findViewById(R.id.avatar);

        }
    }

    ArrayList<Timeline> list;

    public TimelineAdapter() {
        this.list = generateData();
    }

    private ArrayList<Timeline> generateData() {
        ArrayList<Timeline> list = new ArrayList<>();
        list.add(new Timeline(VIEW_MANAGE_PRODUCT, "Cincin Online Shop",
                "telah mengubah harga untuk 5 produk"));
        list.add(new Timeline(VIEW_MANAGE_PRODUCT));
        list.add(new Timeline(VIEW_MANAGE_SHOP, "Nisie Shop", "telah mengubah gambar profil"));
        list.add(new Timeline(VIEW_MANAGE_PRODUCT, "Nisie Shop",
                "telah mengubah 5 produk"));
        list.add(new Timeline(VIEW_PROMO));
        list.add(new Timeline(VIEW_TOPADS_PRODUCT));
        list.add(new Timeline(VIEW_MANAGE_SHOP, "QC 48",
                "telah mengaktifkan fitur GO-JEK untuk pengiriman"));
        list.add(new Timeline(VIEW_MANAGE_SHOP, "QC 48",
                "telah me-nonaktifkan fitur GO-JEK untuk pengiriman"));
        list.add(new Timeline(VIEW_MANAGE_SHOP, "QC 48",
                "telah mengaktifkan fitur GO-JEK untuk pengiriman"));
        list.add(new Timeline(VIEW_PROMO, "Nisie Shop",
                "Pingin beli Mi-band? Di Nisie Shop aja."));
        list.add(new Timeline(VIEW_TOPADS_SHOP));
        return list;
    }

    @Override
    public TimelineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_MANAGE_PRODUCT:
                return createViewManageProduct(parent);
            case VIEW_MANAGE_SHOP:
                return createViewManageShop(parent);
            case VIEW_PROMO:
                return createViewAnnouncement(parent);
            case VIEW_TOPADS_SHOP:
                return createViewTopAdsShop(parent);
            case VIEW_TOPADS_PRODUCT:
                return createViewTopAdsProduct(parent);
            default:
                return null;
        }
    }

    private TimelineAdapter.ViewHolder createViewTopAdsProduct(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_topads_product, parent, false);
        return new ViewHolder(v);
    }


    private TimelineAdapter.ViewHolder createViewTopAdsShop(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_topads_shop, parent, false);
        return new ViewHolder(v);
    }

    private TimelineAdapter.ViewHolder createViewAnnouncement(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_announcement, parent, false);
        return new ViewHolder(v);
    }

    private TimelineAdapter.ViewHolder createViewManageProduct(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_manage_product, parent, false);
        return new ViewHolder(v);
    }

    private TimelineAdapter.ViewHolder createViewManageShop(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_manage_shop, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TimelineAdapter.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case VIEW_MANAGE_PRODUCT:
                bindViewManageProduct(holder, position);
                break;
            case VIEW_MANAGE_SHOP:
                bindViewManageProduct(holder, position);
                break;
            case VIEW_PROMO:
                bindViewManageProduct(holder, position);
                break;
            case VIEW_TOPADS_SHOP:
                bindViewtopAdsShop(holder, position);
                break;
            case VIEW_TOPADS_PRODUCT:
                bindViewManageProduct(holder, position);
                break;
        }
    }

    private void bindViewtopAdsShop(ViewHolder holder, int position) {
        final TextView followButton = (TextView) holder.itemView.findViewById(R.id.follow);

        int fromLoc[] = new int[2];
        followButton.getLocationOnScreen(fromLoc);
        float startX = fromLoc[0];
        float startY = fromLoc[1];

        float destX = 0;
        float destY = 0;


        final Animation.AnimationListener animL = new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //this is just a method call you can create to delete the animated view or hide it until you need it again.
                followButton.setVisibility(View.GONE);
            }
        };
        Animations anim = new Animations();
        final Animation a = anim.fromAtoB(startX, startY, destX, destY, animL, 850);
        followButton.setAnimation(a);
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.startNow();
            }
        });

    }

    private void bindViewManageProduct(TimelineAdapter.ViewHolder holder, int position) {
        bindHeaderTimeline(holder, position);

    }

    private void bindHeaderTimeline(TimelineAdapter.ViewHolder holder, int position) {
        if (list.get(position).context != null)
            holder.context.setText(list.get(position).context);
        if (list.get(position).userId != null)
            holder.poster.setText(list.get(position).userId);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).type;
    }
}
