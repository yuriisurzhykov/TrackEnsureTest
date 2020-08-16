package com.yuriysurzhikov.trackensuretest.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.yuriysurzhikov.trackensuretest.R;
import com.yuriysurzhikov.trackensuretest.model.entities.StatisticsElement;
import com.yuriysurzhikov.trackensuretest.model.entities.StatisticsStatic;

import java.util.Arrays;
import java.util.List;

/**
 * StatisticsView - class that show
 */
public class StatisticsView extends LinearLayout {

    private Context context;

    //Views params
    private LinearLayout main;
    private TextView title;
    private TextView subTitle;
    private LinearLayout header;
    private View separator;
    private LinearLayout table;
    private Drawable background;
    private Drawable headerBackground;
    private Drawable tableBackground;
    private AttributeSet attrs;

    //Data params
    private String titleString;
    private String subtitleString;
    private List<String> headersTitles = Arrays.asList("Fuel type", "Total amount", "Total cost");
    private StatisticsStatic statisticsLive;


    public StatisticsView(Context context) {
        super(context);
        this.context = context;
        setViews(null);
    }

    public StatisticsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;
        setViews(attrs);
    }

    private void setViews(@Nullable AttributeSet attrs) {
        main = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.statistics_view_item, this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Getting background drawables
            background = getResources().getDrawable(R.drawable.rectangle_with_corners);
            headerBackground = getResources().getDrawable(R.drawable.edit_dialog_button);
            tableBackground = getResources().getDrawable(R.drawable.rectangle_with_corners);
        }
        setOrientation(VERTICAL);
    }

    private void setupTitle() {
        title = main.findViewById(R.id.title);
        /*title = new TextView(new ContextThemeWrapper(context, R.style.TitleText), attrs);
        title.setLayoutParams(new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        ));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)title.getLayoutParams();
        params.setMargins(0, 5, 0, 0);
        title.setLayoutParams(params);*/
        title.setText(statisticsLive.getProvider());
    }

    private void setupSubtitle() {
        subTitle = main.findViewById(R.id.subtitle);
        /*subTitle = new TextView(new ContextThemeWrapper(context, R.style.MainText), attrs);
        subTitle.setLayoutParams(new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        ));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)subTitle.getLayoutParams();
        params.setMargins(0, 5, 0, 0);
        subTitle.setLayoutParams(params);*/
        subTitle.setText(statisticsLive.getAddress());
    }

    private void setupHeaders() {
        //Setting up headers
        header = main.findViewById(R.id.table_header);
        /*header = new LinearLayout(context, attrs);
        header.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT)
        );
        header.setGravity(Gravity.CENTER);
        header.setOrientation(LinearLayout.HORIZONTAL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            header.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        }
        header.setId(R.id.table_header);
        header.setBackground(headerBackground);
        for (int i = 0; i < headersTitles.size(); i++) {
            TextView et = new TextView(context, attrs);
            et.setLayoutParams(new TableLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT,
                    1
            ));
            et.setGravity(Gravity.CENTER_HORIZONTAL);
            et.setTextColor(getResources().getColor(R.color.primaryTextColor));
            et.setText(headersTitles.get(i));
            header.addView(et);
        }*/
    }

    private View setupSeparator() {
        //Setting up separator for table
        View view = LayoutInflater.from(context).inflate(R.layout.custom_table_separator, table);
        /*View view = new View(context, attrs);
        view.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                3
        ));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)view.getLayoutParams();
        params.setMargins(0, 5, 0, 5);
        view.setLayoutParams(params);*/
        return view;
    }

    private void setupTable() {
        //Setting up main table
        table = new LinearLayout(context, attrs);
        table.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        ));
        table.setBackground(tableBackground);
        table.setGravity(Gravity.CENTER);
        table.setOrientation(VERTICAL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            table.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        }
        //table = main.findViewById(R.id.statistics_table);
        for (StatisticsElement el: statisticsLive.getElements()) {
            //LinearLayout row = (LinearLayout)LayoutInflater.from(context).inflate(R.layout.custom_table_row, table, false);
            LinearLayout row = new LinearLayout(context, attrs, R.style.AppTheme);
            row.setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
            ));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                row.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            }
            row.setOrientation(HORIZONTAL);
            row.setGravity(Gravity.CENTER);
            TextView et1 = new TextView(context, attrs, R.style.MainText);
            et1.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT,
                    1
            ));
            et1.setGravity(Gravity.CENTER_HORIZONTAL);
            et1.setText(el.getFuelType());

            TextView et2 = new TextView(context, attrs, R.style.MainText);
            et2.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT,
                    1
            ));
            et2.setGravity(Gravity.CENTER_HORIZONTAL);
            et2.setText(String.valueOf(el.getSumFuelAmount()));

            TextView et3 = new TextView(context, attrs, R.style.MainText);
            et1.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT,
                    1
            ));
            et3.setGravity(Gravity.CENTER_HORIZONTAL);
            et3.setText(String.valueOf(el.getSumCost()));
            row.addView(et1);
            row.addView(et2);
            row.addView(et3);
            table.addView(row);
        }
        /*table = new LinearLayout(context, attrs);
        table.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        ));
        table.setBackground(tableBackground);
        table.setGravity(Gravity.CENTER);
        table.setOrientation(VERTICAL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            table.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        }
        LayoutParams params = (LayoutParams)table.getLayoutParams();
        params.setMargins(0, 8, 0, 0);
        table.setLayoutParams(params);
        int i = 0;
        for (StatisticsElement el: statisticsLive.getElements()) {
            i++;
            TextView et1 = new TextView(context, attrs);
            et1.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT,
                    1
            ));
            et1.setGravity(Gravity.CENTER_HORIZONTAL);
            et1.setText(el.getFuelType());

            TextView et2 = new TextView(context, attrs);
            et2.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT,
                    1
            ));
            et2.setGravity(Gravity.CENTER_HORIZONTAL);
            et2.setText(String.valueOf(el.getSumFuelAmount()));

            TextView et3 = new TextView(context, attrs);
            et1.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT,
                    1
            ));
            et3.setGravity(Gravity.CENTER_HORIZONTAL);
            et3.setText(String.valueOf(el.getSumCost()));

            LinearLayout row = new LinearLayout(context, attrs);
            row.setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
            ));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                row.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            }
            row.setOrientation(HORIZONTAL);
            row.setGravity(Gravity.CENTER);

            row.addView(et1);
            row.addView(et2);
            row.addView(et3);
            table.addView(row);*//*
            if(i != elements.size())
                table.addView(setupSeparator());*//*
        }*/
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        setBackground(background);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setStatisticsLive(StatisticsStatic statisticsLive) {
        this.statisticsLive = statisticsLive;
        //removeView(title);
        //removeView(subTitle);
        //removeView(header);
        removeView(table);
        setupTitle();
        setupSubtitle();
        setupHeaders();
        setupTable();
        //addView(title);
        //addView(subTitle);
        //addView(header);
        addView(table);
    }
}
