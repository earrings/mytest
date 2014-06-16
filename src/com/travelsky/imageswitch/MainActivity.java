package com.travelsky.imageswitch;

import com.example.hellodear.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity implements OnItemSelectedListener,ViewFactory{
	//声明ImageSwitcher、Gallery  
    private ImageSwitcher is=null;  
    private Gallery gallery=null;  
  
    //定义缩微图  
    private Integer[] mThumbIds = {   
            R.drawable.ic_aa,   
            R.drawable.ic_bb,  
            R.drawable.ic_cc,   
            R.drawable.ic_dd,
            R.drawable.ic_aa,   
            R.drawable.ic_bb,  
            R.drawable.ic_cc,   
            R.drawable.ic_dd,
            R.drawable.ic_aa,   
            R.drawable.ic_bb,  
            R.drawable.ic_cc,   
            R.drawable.ic_dd,};  
    //定义图  
    private Integer[] mImageIds = {   
            R.drawable.ic_aa,   
            R.drawable.ic_bb,  
            R.drawable.ic_cc,   
            R.drawable.ic_dd,
            R.drawable.ic_aa,   
            R.drawable.ic_bb,  
            R.drawable.ic_cc,   
            R.drawable.ic_dd,
            R.drawable.ic_aa,   
            R.drawable.ic_bb,  
            R.drawable.ic_cc,   
            R.drawable.ic_dd,};  
	
	@Override  
    protected void onCreate(Bundle savedInstanceState) {  
  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(R.layout.imageswitch_test);  
          
        is = (ImageSwitcher) findViewById(R.id.switcher);  
        is.setFactory(this);  
          
        //显示效果  
        is.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));  
        is.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_out));  
  
        gallery = (Gallery) findViewById(R.id.gallery);  
        gallery.setAdapter(new ImageAdapter(this));  
        //设置OnItemSelected监听事件  
        gallery.setOnItemSelectedListener(this);  
    }  
  
    @Override  
    public View makeView() {  
        ImageView i = new ImageView(this);  
        i.setBackgroundColor(0xFF000000);  
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);  
        i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));  
        return i;  
    }  
  
    public class ImageAdapter extends BaseAdapter {  
        public ImageAdapter(Context c) {  
            mContext = c;  
        }  
  
        public int getCount() {  
            return mThumbIds.length;  
        }  
  
        public Object getItem(int position) {  
            return position;  
        }  
  
        public long getItemId(int position) {  
            return position;  
        }  
  
        public View getView(int position, View convertView, ViewGroup parent) {  
            ImageView i = new ImageView(mContext);  
            i.setImageResource(mThumbIds[position]);  
            i.setAdjustViewBounds(true);  
            i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));  
            i.setBackgroundResource(R.drawable.ic_dd);  
            return i;  
        }  
        private Context mContext;  
    }  
  
    @Override  
    public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {  
        is.setImageResource(mImageIds[position]);  
    }  
  
    @Override  
    public void onNothingSelected(AdapterView<?> parent) {  
    }  
  
}  