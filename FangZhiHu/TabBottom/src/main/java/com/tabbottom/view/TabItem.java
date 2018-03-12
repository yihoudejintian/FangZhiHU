package com.tabbottom.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tabbottom.R;

import java.util.IdentityHashMap;
import java.util.Locale;

/**
 * Created by tongqinyuan on 2018/3/12.
 */

public class TabItem extends ConstraintLayout {


    private ImageView mIcon;
    private TextView mTitle;
    private View mDot;
    private TextView mMessage;

    private int mMessageNumber;//消息数量
    private boolean mIsDot;//一点代替消息数量

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(getContext()).inflate(R.layout.common_tab_item, this, true);

        mIcon = findViewById(R.id.ig_tab_icon);
        mTitle = findViewById(R.id.tv_tab_title);
        mMessage = findViewById(R.id.tv_tab_message);
        mDot = findViewById(R.id.v_tab_dot);

        setClickable(true);
        if (Build.VERSION.SDK_INT >= 21) {
            setBackground(new RippleDrawable(new ColorStateList(new int[][]{{}}, new int[]{0xFFFFFF & 0x56000000 | 0x56000000}), null, null));
        } else {
            setBackgroundResource(R.drawable.material_item_background);
        }

    }

    public static Drawable getColorPrimary(Context context) {
        Resources res = context.getResources();
        int attrRes = res.getIdentifier("selectableItemBackground", "attr", context.getPackageName());
        if (attrRes == 0) {
            return null;
        }
        return ContextCompat.getDrawable(context, getResourceId(context, attrRes));
    }

    /**
     * 获取自定义属性的资源ID
     *
     * @param context 上下文
     * @param attrRes 自定义属性
     * @return resourceId
     */
    private static int getResourceId(Context context, int attrRes) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrRes, typedValue, true);
        return typedValue.resourceId;
    }

    //设置消息数量
    public void setMessageNumber(int number) {
        mMessageNumber = number;

        if (mMessageNumber > 0) {
            mDot.setVisibility(View.INVISIBLE);
            mMessage.setVisibility(View.VISIBLE);

            if (mMessageNumber < 10) {
                mMessage.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
            } else {
                mMessage.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
            }

            if (mMessageNumber <= 99) {
                mMessage.setText(String.format(Locale.ENGLISH, "%d", mMessageNumber));
            } else {
                mMessage.setText(String.format(Locale.ENGLISH, "%d+", 99));
            }
        } else {
            mMessage.setVisibility(View.INVISIBLE);
            if (mIsDot) {
                mDot.setVisibility(View.VISIBLE);
            }
        }
    }

    //设置显示消息点
    public void setIsDot(boolean isdot) {
        mIsDot = isdot;

        if (isdot) {
            mDot.setVisibility(mMessageNumber > 0 ? View.INVISIBLE : View.VISIBLE);
        } else {
            mDot.setVisibility(View.INVISIBLE);
        }
    }

    //设置标题
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setTitle(@StringRes int titleRes) {
        mTitle.setText(titleRes);
    }

    //设置icon
    public void setIconImage(@DrawableRes int drawableRes) {
//        Drawable drawable = ContextCompat.getDrawable(getContext(), drawableRes);
//        mIcon.setImageDrawable(drawable);
        mIcon.setImageResource(drawableRes);
    }

    //


}
