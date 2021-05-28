package com.checkapplive.mylibrary;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.balysv.materialripple.MaterialRippleLayout;

public class ImmediateDialog extends AlertDialog implements View.OnClickListener {
    private View mBtnGroupView, mBkgView, mDialogView;
    private MaterialRippleLayout ripple_apply;
    private OnApplyListener applyListener;
    private Context con;
    private AnimationSet mAnimIn, mAnimOut;

    private int mpos, mBackgroundColor, mTitleTextColor, mContentTextColor;
    private int mPlaceHolder;
    private CharSequence mTitleText;
    private boolean mApplied;
    private boolean mIsCustomGIFTheme = false;
    private CharSequence mImagePath, mGifPath;
    private ImageView ivThemeBg;
    private boolean mIsShowAnim;
    private LinearLayout lay_apply;
    private int mImagePath_drawable;
    private Drawable mbutton_drawable;

    public ImmediateDialog(Context context) {
        this(context, 0);
    }

    public ImmediateDialog(Context context, int theme) {
        super(context, R.style.color_dialog_3);
        con = context;
        init();
    }


    private void callDismiss() {
        super.dismiss();
    }

    private void init() {
        mAnimIn = AnimationLoader.getInAnimation(getContext());
        mAnimOut = AnimationLoader.getOutAnimation(getContext());
        initAnimListener();
    }

    public void setTitle(CharSequence title) {
        mTitleText = title;

    }

    public ImmediateDialog setTitleText(CharSequence title) {
        mTitleText = title;
        return this;
    }

    public ImmediateDialog setApplied(boolean applied) {
        mApplied = applied;
        return this;
    }

    public ImmediateDialog setAllowDelete(boolean allow) {
        return this;
    }

    public ImmediateDialog setOnlineTheme(boolean onlineTheme) {
        return this;
    }

    public ImmediateDialog setCustomGifTheme(boolean customTheme, String gifBgPath) {
        mIsCustomGIFTheme = customTheme;
        mGifPath = gifBgPath;
        return this;
    }

    public ImmediateDialog setCustomTheme(boolean customTheme) {
        return this;
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(getContext().getText(titleId));
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View contentView = View.inflate(getContext(), R.layout.layout_immediatedialog, null);
        setContentView(contentView);

        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        mBkgView = contentView.findViewById(R.id.llBkg);
        ripple_apply = contentView.findViewById(R.id.ripple_apply);


        ivThemeBg = (ImageView) contentView.findViewById(R.id.ivThemeBg);
        ivThemeBg.setImageResource(mImagePath_drawable);
        mBtnGroupView = contentView.findViewById(R.id.llBtnGroup);
        lay_apply = (LinearLayout) contentView.findViewById(R.id.lay_apply);
        lay_apply.setBackgroundDrawable(mbutton_drawable);
        lay_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyListener.onClick(ImmediateDialog.this, 0);
            }
        });
        ripple_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyListener.onClick(ImmediateDialog.this, mpos);
            }
        });

        setTextColor();
        setBackgroundColor();
    }


    @Override
    protected void onStart() {
        super.onStart();
        startWithAnimation(mIsShowAnim);
    }

    @Override
    public void dismiss() {
        dismissWithAnimation(mIsShowAnim);
    }

    private void startWithAnimation(boolean showInAnimation) {
        if (showInAnimation) {
            mDialogView.startAnimation(mAnimIn);
        }
    }

    private void dismissWithAnimation(boolean showOutAnimation) {
        if (showOutAnimation) {
            mDialogView.startAnimation(mAnimOut);
        } else {
            super.dismiss();
        }
    }

    private void initAnimListener() {
        mAnimOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mDialogView.post(new Runnable() {
                    @Override
                    public void run() {
                        callDismiss();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void setBackgroundColor() {
        if (0 == mBackgroundColor) {
            return;
        }

        int radius = DisplayUtil.dp2px(getContext(), 6);
        float[] outerRadii = new float[]{radius, radius, radius, radius, 0, 0, 0, 0};
        RoundRectShape roundRectShape = new RoundRectShape(outerRadii, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape);
        shapeDrawable.getPaint().setColor(mBackgroundColor);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        mBkgView.setBackgroundDrawable(shapeDrawable);
    }

    private void setTextColor() {

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

    }

    public ImmediateDialog setAnimationEnable(boolean enable) {
        mIsShowAnim = enable;
        return this;
    }

    public ImmediateDialog setAnimationIn(AnimationSet animIn) {
        mAnimIn = animIn;
        return this;
    }

    public ImmediateDialog setAnimationOut(AnimationSet animOut) {
        mAnimOut = animOut;
        initAnimListener();
        return this;
    }

    public ImmediateDialog setColor(int color) {
        mBackgroundColor = color;
        return this;
    }

    public ImmediateDialog setImagePath(String path, int place_re) {
        mImagePath = path;
        mPlaceHolder = place_re;
        return this;
    }

    public ImmediateDialog setpos(int pos) {
        mpos = pos;
        return this;
    }

    public ImmediateDialog setColor(String color) {
        try {
            setColor(Color.parseColor(color));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ImmediateDialog setTitleTextColor(int color) {
        mTitleTextColor = color;
        return this;
    }

    public ImmediateDialog setTitleTextColor(String color) {
        try {
            setTitleTextColor(Color.parseColor(color));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ImmediateDialog setContentTextColor(int color) {
        mContentTextColor = color;
        return this;
    }

    public ImmediateDialog setContentTextColor(String color) {
        try {
            setContentTextColor(Color.parseColor(color));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return this;
    }


    public ImmediateDialog setApplyListener(OnApplyListener l) {
        applyListener = l;
        return this;
    }

    public ImmediateDialog setEditListener(OnEditListener l) {
        return this;
    }

    public ImmediateDialog setDeleteListener(OnDeleteListener l) {
        return this;
    }

//        i

    public ImmediateDialog setShareListener(OnShareListener l) {
        return this;
    }


    public ImmediateDialog setContentImage(Bitmap bitmap) {
        return this;
    }

    public ImmediateDialog setContentImage(int resId) {
        return this;
    }

    public CharSequence getTitleText() {
        return mTitleText;
    }

    public void setImagePath(int icon_immediate) {
        mImagePath_drawable = icon_immediate;


    }
    public void setDrawblePath(Drawable icon_immediate) {
        mbutton_drawable = icon_immediate;


    }

    public interface OnEditListener {
        void onClick(ImmediateDialog dialog);
    }

    public interface OnShareListener {
        void onClick(ImmediateDialog dialog);
    }

    public interface OnApplyListener {
        void onClick(ImmediateDialog dialog, int i);
    }

    public interface OnDismissListener {
        void onClick(ImmediateDialog dialog);
    }

    public interface OnDeleteListener {
        void onClick(ImmediateDialog dialog);
    }
}
