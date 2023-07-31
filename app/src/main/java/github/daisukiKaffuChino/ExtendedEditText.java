package github.daisukiKaffuChino;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.InputType;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.view.GravityCompat;

public class ExtendedEditText extends AppCompatEditText {
  Paint paint = new Paint();
  int mLineColor = Color.argb(45, 125, 168, 250);
  Paint.Style mStyle = Paint.Style.FILL_AND_STROKE;

  public ExtendedEditText(@NonNull Context context) {
    super(context);
    init();
  }

  public ExtendedEditText(@NonNull Context context, AttributeSet attributeSet) {
    super(context, attributeSet);
    init();
  }

  private void init() {
    setPadding(90, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    setGravity(Gravity.TOP | GravityCompat.START);
    setBackgroundColor(0);
  }

  private int getCurrentLine() {
    int i = getSelectionStart();
    Layout layout = getLayout();
    if (i != -1 && layout != null) {
      return layout.getLineForOffset(i);
    }
    return -1;
  }

  public void setLineBackgroundColor(int color) {
    mLineColor = color;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    paint.setStyle(mStyle);
    paint.setColor(mLineColor);
    int curPosLine = getCurrentLine();
    int getTop = getLayout().getLineTop(curPosLine);
    int getBottom = getLayout().getLineBottom(curPosLine);
    int pTop = getPaddingTop();
    int lineCount = getLineCount();
    canvas.drawRect(0, getTop + pTop, getWidth(), getBottom + pTop, paint);
    Paint nPaint = getPaint();
    // int h = getLineHeight();
    for (int i = 0; i < lineCount; i++) {
      int i1 = i + 1;
      int lineBottom = getLayout().getLineBottom(i);
      canvas.drawText(String.valueOf(i1), 0, lineBottom - nPaint.descent() + pTop, nPaint);
    }
  }
}
