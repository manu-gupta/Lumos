package com.manu.lumos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.manu.lumos.Model.Explore.Item_;

import java.util.List;

public class ExploreListAdapter extends ArrayAdapter<Item_> {
	private LayoutInflater layoutInflater_;

	private static class ViewHolder {
		TextView tvName;
		TextView tvPoint;
		TextView tvGenre;
		TextView tvDistance;
		TextView tvComment;
	}

	public ExploreListAdapter(Context context, int layout, List<Item_> objects) {
		super(context, layout, objects);
		layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Item_ item_ = getItem(position);

		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.item_list, parent, false);

			viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_item_name);
			viewHolder.tvGenre = (TextView) convertView.findViewById(R.id.tv_item_genre);
			viewHolder.tvDistance = (TextView) convertView.findViewById(R.id.tv_item_distance);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		String name = item_.getVenue().getName();
		String genre = item_.getVenue().getCategories().get(0).getName();
		float distance = item_.getVenue().getLocation().getDistance();
		distance=distance/1000;

		viewHolder.tvName.setText(name);
		viewHolder.tvGenre.setText(genre);
		viewHolder.tvDistance.setText(String.valueOf(distance)+" KM");

		return convertView;
	}
}