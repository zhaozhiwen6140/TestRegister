package com.example.cnlive.testregister;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * Created by CNLive on 2016/3/22.
 */
public class CountDownTimerUtils extends CountDownTimer {
    private TextView mTextView;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CountDownTimerUtils(TextView textView,long millisInFuture, long countDownInterval) {
      //这里的构造方法需要加入一个参数，传入一个TextView对象参数是为了对这个TextView对象进行点击事件的处理。
        // millisInFuture是传给onTick()的参数，countDownInterval是表示多长时间调用一次onTick()。即倒计时每隔多长时间
        //调用onTick()方法，即倒计时时间每次显示间隔多少秒。
        super(millisInFuture, countDownInterval);
        mTextView=textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {//该方法在倒计时时调用
        mTextView.setClickable(false); //设置不可点击
        mTextView.setText(millisUntilFinished / 1000 + "秒后可重新发送");  //设置倒计时时间
        mTextView.setBackgroundResource(R.drawable.bg_identify_code_press); //设置按钮为灰色，这时是不能点击的
        //如果想给按钮上的字设置颜色可以进行以下操作：
         SpannableString spannableString = new SpannableString(mTextView.getText().toString());  //获取按钮上的文字
         ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);//设置文本颜色
        /**
         * public void setSpan(Object what, int start, int end, int flags) {
         * 主要是start跟end，start是起始位置,无论中英文，都算一个。
         * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
         */
        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
        mTextView.setText(spannableString);
    }

    @Override
    public void onFinish() {//计时结束之后实现这个方法
        mTextView.setText("重新获取验证码");
        mTextView.setClickable(true);//重新获得点击
        mTextView.setBackgroundResource(R.drawable.bg_identify_code_normal);//还原背景色
    }
}

