package com.rohitramkumar.gridpicker.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

;


/**
 * Created by rohitramkumar on 8/2/14.
 */
public class CustomGridAdapter extends BaseAdapter {

    private Context context;
    private int numColumns;
    private int numRows;
    private float startValue;
    private float increment;
    private Drawable[] drawableResourceList;
    private int cellWidth = -1;
    private int cellHeight = -1;
    private int width;
    private int height;

    public CustomGridAdapter(Context context, int width, int height, int numColumns, int numRows, float startValue, float increment) {
        this.context = context;
        this.numColumns = numColumns;
        this.numRows = numRows;
        this.startValue = startValue;
        this.increment = increment;
        this.height = height;
        this.width = width;
    }

    public void setWidth(int x) {
        width = x;
    }

    public void setHeight(int x) {
        height = x;
    }

    public void setStartValue(float x) {
        startValue = x;
    }

    public float getStartValue() { return startValue; }

    public void setNumColumns(int x) {
        numColumns = x;
    }

    public void setNumRows(int x) {
        numRows = x;
    }

    public void setIncrement(float x) {
        increment = x;
    }

    public void setDrawableResourceList(Drawable[] drawableResourceList) {
        this.drawableResourceList = drawableResourceList;
    }

    private int getCellWidth() {
        if (cellWidth < 0 && width != -1) {
            cellWidth = width / numColumns;
        }
        return cellWidth;
    }

    private int getCellHeight() {
        if (cellHeight < 0 && height != -1) {
            cellHeight = height / numRows;
        }
        return cellHeight;
    }

    @Override
    public int getCount() {
        return numRows * numColumns;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        float value = startValue + (position - 1) * increment;

        convertView = LayoutInflater.from(context).inflate(R.layout.button_layout, null);
        if(width != -1 && height != -1) convertView.setLayoutParams(new GridView.LayoutParams(getCellWidth(), getCellHeight()));

        Button button = (Button) convertView.findViewById(R.id.standard_button);

        if(position == 0) {
            if(drawableResourceList[2] != null) {
                button.setBackgroundDrawable(null);
                button.setBackgroundDrawable(drawableResourceList[2]);
            }
        }
        else if(position == getCount() - 1) {
            if(drawableResourceList[1] != null) {
                button.setBackgroundDrawable(null);
                button.setBackgroundDrawable(drawableResourceList[1]);
            }
        }
        else {
            if(Math.round(value) == value) button.setText(Integer.toString(Math.round(value)));
            else button.setText(Float.toString(value));
            if(drawableResourceList[0] != null) {
                button.setBackgroundDrawable(null);
                button.setBackgroundDrawable(drawableResourceList[0]);
            }
        }

        return convertView;
    }

    private int resizeText(int cellWidth, int cellHeight) {


        return 0;


    }


    public void advanceGridValues() {
        startValue += ((numColumns * numRows) - 2) * increment;
        notifyDataSetChanged();
    }

    public void rewindGridValues() {
        startValue -= ((numColumns * numRows) - 2) * increment;
        notifyDataSetChanged();
    }

    public void resetCellDimensions() {
        cellHeight = -1;
        cellWidth = -1;
    }
}

