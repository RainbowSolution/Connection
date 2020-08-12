package com.connection.driverModule;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.connection.R;

import java.util.HashMap;
import java.util.List;

public class NavigationExpandableListAdapterHomeDriver extends BaseExpandableListAdapter {
    private Context context;
    private List<MenuModelHomeDriver> listDataHeader;
    private HashMap<MenuModelHomeDriver, List<MenuModelHomeDriver>> listDataChild;
    private String login;

    public NavigationExpandableListAdapterHomeDriver(Context context, List<MenuModelHomeDriver> listDataHeader,
                                                     HashMap<MenuModelHomeDriver, List<MenuModelHomeDriver>> listChildData) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;

    }

    @Override
    public MenuModelHomeDriver getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = getChild(groupPosition, childPosition).menuName;
        final int imageicon = getChild(groupPosition, childPosition).itemimage;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_child, null);
        }

        TextView txtListChild = convertView
                .findViewById(R.id.lblListItem);
        ImageView imageView = convertView.findViewById(R.id.icon);
        imageView.setBackgroundResource(imageicon);
        txtListChild.setText(childText);
        convertView.setBackgroundColor(Color.WHITE);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        if (this.listDataChild.get(this.listDataHeader.get(groupPosition)) == null)
            return 0;
        else
            return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                    .size();
    }

    @Override
    public MenuModelHomeDriver getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = getGroup(groupPosition).menuName;
        final int imageicon = getGroup(groupPosition).itemimage;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_header, null);
        }
        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
        ImageView imageView = convertView.findViewById(R.id.icon);
        ImageView imageView1 = convertView.findViewById(R.id.imageView1);
        imageView.setBackgroundResource(imageicon);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
