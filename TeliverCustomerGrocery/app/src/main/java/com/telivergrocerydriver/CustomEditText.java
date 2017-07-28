
package com.telivergrocerydriver;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;


/**
 * The Class CustomEditTextView.
 */
public class CustomEditText extends AppCompatEditText {

    /** The m utils. */
    private Application mUtils;

    /**
     * Instantiates a new custom edit text view.
     * 
     * @param context
     *            the context
     */
    public CustomEditText(Context context) {
        super(context);
    }

    /**
     * Instantiates a new custom edit text view.
     * 
     * @param context
     *            the context
     * @param attrs
     *            the attrs
     */
    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mUtils = new Application();
        mUtils.init(this, context, attrs);
    }

    /**
     * Instantiates a new custom edit text view.
     * 
     * @param context
     *            the context
     * @param attrs
     *            the attrs
     * @param defStyle
     *            the def style
     */
    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mUtils = new Application();
        mUtils.init(this, context, attrs);
    }

}
