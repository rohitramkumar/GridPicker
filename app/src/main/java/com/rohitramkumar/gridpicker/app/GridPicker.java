//TODO: add license info


package com.rohitramkumar.gridpicker.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;

/**
 * Created by rohitramkumar on 8/2/14.
 */
public class GridPicker extends FrameLayout {
    private ClickListener mClickCallback;
    private float mStartValue;
    private int mNumColumns;
    private int mNumRows;
    private GridView mGridView;
    private float mIncrement;
    private CustomGridAdapter mAdapter;
    private Drawable[] mDrawableResourceList = new Drawable[3];

    public GridPicker(Context context, AttributeSet attr) {
        super(context, attr);
        applyAttr(context, attr);
        init(context);
        initGridViewOnClick();
    }


    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.custom_number_picker, this, true);
        mGridView = (GridView) v.findViewById(R.id.grid_view);
        mAdapter = new CustomGridAdapter(context, -1, -1, mNumColumns, mNumRows, mStartValue, mIncrement);
        mAdapter.setDrawableResourceList(mDrawableResourceList);
        mGridView.setNumColumns(mNumColumns);
        mGridView.setAdapter(mAdapter);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if(widthMode == MeasureSpec.UNSPECIFIED || heightMode == MeasureSpec.UNSPECIFIED
                || widthMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.AT_MOST) {
            Log.e("GridPicker", "Do not use wrap_content, please use exact dimension values for both width and height");
        }
        else {
            mAdapter.setWidth(MeasureSpec.getSize(widthMeasureSpec));
            mAdapter.setHeight(MeasureSpec.getSize(heightMeasureSpec));
            mAdapter.notifyDataSetChanged();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void applyAttr(Context context, AttributeSet attr) {
        mDrawableResourceList = new Drawable[3];

        int[] standardAttributes = new int[] {android.R.attr.layout_width, android.R.attr.layout_height};
        TypedArray standardAttributeArray = context.obtainStyledAttributes(attr, standardAttributes);
        standardAttributeArray.recycle();

        TypedArray customAttributeArray = context.obtainStyledAttributes(attr, R.styleable.GridPicker, 0, 0);
        mStartValue = customAttributeArray.getFloat(0, 0f);
        mIncrement = customAttributeArray.getFloat(1, 1f);
        mNumColumns = customAttributeArray.getInteger(2, 3);
        mNumRows = customAttributeArray.getInteger(3, 3);
        if(customAttributeArray.getDrawable(6) == null) {
            mDrawableResourceList[0] = null;
        }
        else {
            mDrawableResourceList[0] = customAttributeArray.getDrawable(6);
        }

        if(customAttributeArray.getDrawable(5) == null) {
            mDrawableResourceList[1] = context.getResources().getDrawable(R.drawable.ic_action_forward);
        }
        else {
            mDrawableResourceList[1] = customAttributeArray.getDrawable(5);
        }

        if(customAttributeArray.getDrawable(4) == null) {
            mDrawableResourceList[2] =  context.getResources().getDrawable(R.drawable.ic_action_back);
        }
        else {
            mDrawableResourceList[2] = customAttributeArray.getDrawable(4);
        }
        customAttributeArray.recycle();
    }

    private void initGridViewOnClick() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    mAdapter.rewindGridValues();
                    if (mClickCallback != null) mClickCallback.onRewindButtonClicked();
                } else if (position == mAdapter.getCount() - 1) {
                    mAdapter.advanceGridValues();
                    if (mClickCallback != null) mClickCallback.onAdvanceButtonClicked();
                } else {
                    float value = computeValueForPosition(position);
                    if (mClickCallback != null) mClickCallback.onNumberClicked(value);
                }
            }
        });
    }

    private float computeValueForPosition(int position) {
        return mAdapter.getStartValue() + (position - 1) * mIncrement;
    }

    public void setClickListeners(ClickListener clickListener) {
        mClickCallback = clickListener;
    }

    public void setDrawableResources(Drawable standardViewDrawableResource, Drawable advanceViewDrawableResource, Drawable rewindViewDrawableResource) {
        Drawable[] drawableList = new Drawable[] {standardViewDrawableResource, advanceViewDrawableResource, rewindViewDrawableResource};
        mAdapter.setDrawableResourceList(drawableList);
        mAdapter.notifyDataSetChanged();
    }

    public void setStartValue(float x) {
        mStartValue = x;
        mAdapter.setStartValue(mStartValue);
        mAdapter.notifyDataSetChanged();
    }

    public float getStartValue() {
        return mStartValue;
    }

    public void setNumColumns(int x) {
        mNumColumns = x;
        mAdapter.setNumColumns(x);
        mAdapter.resetCellDimensions();
        mGridView.setNumColumns(x);
        mAdapter.notifyDataSetChanged();
    }

    public int getNumColumns() {
        return mNumColumns;
    }


    public void setNumRows(int x) {
        mNumRows = x;
        mAdapter.setNumRows(x);
        mAdapter.resetCellDimensions();
        mAdapter.notifyDataSetChanged();
    }

    public int getNumRows() {
        return mNumRows;
    }

    public void setIncrement(int x) {
        mIncrement = x;
        mAdapter.setIncrement(x);
        mAdapter.notifyDataSetChanged();
    }

    public float getIncrement() {
        return mIncrement;
    }
}
