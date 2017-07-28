
package com.telivergrocerycustomer;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


/**
 * The Class CustomTextView.
 */
public class CustomTextView extends AppCompatTextView {

    /** The m utils. */
    private Application mUtils;

    /**
     * Instantiates a new custom text view.
     * 
     * @param context
     *            the context
     */
    public CustomTextView(Context context) {
        super(context);
    }

    /**
     * Instantiates a new custom text view.
     * 
     * @param context
     *            the context
     * @param attrs
     *            the attrs
     */
    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mUtils = new Application();
        mUtils.init(this, context, attrs);
    }

    /**
     * Instantiates a new custom text view.
     * 
     * @param context
     *            the context
     * @param attrs
     *            the attrs
     * @param defStyle
     *            the def style
     */
    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mUtils = new Application();
        mUtils.init(this, context, attrs);
    }

}
