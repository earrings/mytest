package com.example.hellodear;


import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;
import android.os.Build;

public class MainActivity extends Activity implements OnCompletionListener,OnErrorListener,OnInfoListener, 
OnPreparedListener, OnSeekCompleteListener,OnVideoSizeChangedListener,SurfaceHolder.Callback{ 
private Display currDisplay; 
private SurfaceView surfaceView; 
private SurfaceHolder holder; 
private MediaPlayer player; 
private int vWidth,vHeight; 
//private boolean readyToPlay = false; 
       
public void onCreate(Bundle savedInstanceState){ 
    super.onCreate(savedInstanceState); 
    this.setContentView(R.layout.activity_main); 
               
    surfaceView = (SurfaceView)this.findViewById(R.id.video_surface); 
    //��SurfaceView���CallBack���� 
    holder = surfaceView.getHolder(); 
    holder.addCallback(this); 
    //Ϊ�˿��Բ�����Ƶ����ʹ��CameraԤ����������Ҫָ����Buffer���� 
    holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); 
       
    //���濪ʼʵ����MediaPlayer���� 
    player = new MediaPlayer(); 
    player.setOnCompletionListener(this); 
    player.setOnErrorListener(this); 
    player.setOnInfoListener(this); 
    player.setOnPreparedListener(this); 
    player.setOnSeekCompleteListener(this); 
    player.setOnVideoSizeChangedListener(this); 
    Log.v("Begin:::", "surfaceDestroyed called"); 
    //Ȼ��ָ����Ҫ�����ļ���·������ʼ��MediaPlayer 
    //String dataPath = Environment.getExternalStorageDirectory().getPath()+"/Test_Movie.m4v"; 
    String dataPath = "http://116.211.111.250/share.php?method=Share.download&cqid=1acc93049fd469acbe349ff025131a51&dt=53.e3050a26cec2eb1422c3ae24ecf9f4b2&e=1403107761&fhash=eae8797988d66421a32798437ae6674e537c29f8&fname=MOV008.mp4&fsize=15497693&nid=14029320224978335&scid=53&st=b4654676cf4300035c2118c2b3ff1004&xqid=198731395";
    try { 
        player.setDataSource(dataPath); 
        Log.v("Next:::", "surfaceDestroyed called"); 
    } catch (IllegalArgumentException e) { 
        e.printStackTrace(); 
    } catch (IllegalStateException e) { 
        e.printStackTrace(); 
    } catch (IOException e) { 
        e.printStackTrace(); 
    } 
    //Ȼ������ȡ�õ�ǰDisplay���� 
    currDisplay = this.getWindowManager().getDefaultDisplay(); 
} 
   
@Override 
public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) { 
    // ��Surface�ߴ�Ȳ����ı�ʱ���� 
    Log.v("Surface Change:::", "surfaceChanged called"); 
} 
@Override 
public void surfaceCreated(SurfaceHolder holder) { 
    // ��SurfaceView�е�Surface��������ʱ�򱻵��� 
    //����������ָ��MediaPlayer�ڵ�ǰ��Surface�н��в��� 
    player.setDisplay(holder); 
    //��ָ����MediaPlayer���ŵ����������ǾͿ���ʹ��prepare����prepareAsync��׼�������� 
    player.prepareAsync(); 
       
} 
@Override 
public void surfaceDestroyed(SurfaceHolder holder) { 
       
    Log.v("Surface Destory:::", "surfaceDestroyed called"); 
} 
@Override 
public void onVideoSizeChanged(MediaPlayer arg0, int arg1, int arg2) { 
    // ��video��С�ı�ʱ���� 
    //�������������player��source�����ٴ���һ�� 
    Log.v("Video Size Change", "onVideoSizeChanged called"); 
       
} 
@Override 
public void onSeekComplete(MediaPlayer arg0) { 
    // seek�������ʱ���� 
    Log.v("Seek Completion", "onSeekComplete called"); 
       
} 
@Override 
public void onPrepared(MediaPlayer player) { 
    // ��prepare��ɺ󣬸÷������������������ǲ�����Ƶ 
       
    //����ȡ��video�Ŀ�͸� 
    vWidth = player.getVideoWidth(); 
    vHeight = player.getVideoHeight(); 
       
    if(vWidth > currDisplay.getWidth() || vHeight > currDisplay.getHeight()){ 
        //���video�Ŀ���߸߳����˵�ǰ��Ļ�Ĵ�С����Ҫ�������� 
        float wRatio = (float)vWidth/(float)currDisplay.getWidth(); 
        float hRatio = (float)vHeight/(float)currDisplay.getHeight(); 
           
        //ѡ����һ���������� 
        float ratio = Math.max(wRatio, hRatio); 
           
        vWidth = (int)Math.ceil((float)vWidth/ratio); 
        vHeight = (int)Math.ceil((float)vHeight/ratio); 
           
        //����surfaceView�Ĳ��ֲ��� 
        surfaceView.setLayoutParams(new LinearLayout.LayoutParams(vWidth, vHeight)); 
           
        //Ȼ��ʼ������Ƶ 
           
        player.start(); 
    } 
} 
@Override 
public boolean onInfo(MediaPlayer player, int whatInfo, int extra) { 
    // ��һЩ�ض���Ϣ���ֻ��߾���ʱ���� 
    switch(whatInfo){ 
    case MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING: 
        break; 
    case MediaPlayer.MEDIA_INFO_METADATA_UPDATE:   
        break; 
    case MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING: 
        break; 
    case MediaPlayer.MEDIA_INFO_NOT_SEEKABLE:  
        break; 
    } 
    return false; 
} 
@Override 
public boolean onError(MediaPlayer player, int whatError, int extra) { 
    Log.v("Play Error:::", "onError called"); 
    switch (whatError) { 
    case MediaPlayer.MEDIA_ERROR_SERVER_DIED: 
        Log.v("Play Error:::", "MEDIA_ERROR_SERVER_DIED"); 
        break; 
    case MediaPlayer.MEDIA_ERROR_UNKNOWN: 
        Log.v("Play Error:::", "MEDIA_ERROR_UNKNOWN"); 
        break; 
    default: 
        break; 
    } 
    return false; 
} 
@Override 
public void onCompletion(MediaPlayer player) { 
    // ��MediaPlayer������ɺ󴥷� 
    Log.v("Play Over:::", "onComletion called"); 
    this.finish(); 
} 
}


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
////        if (savedInstanceState == null) {
////            getSupportFragmentManager().beginTransaction()
////                    .add(R.id.container, new PlaceholderFragment())
////                    .commit();
////        }
//         
////        new Handler().post((new Runnable() {
////			@Override
////			public void run() {
//        		Log.i("aa", "aa");
//			   Uri uri = Uri.parse("http://v.youku.com/v_show/id_XNzI3MTU3OTM2.html"); 
//			   VideoView videoView = (VideoView)this.findViewById(R.id.video_view);
//		       videoView.setMediaController(new MediaController(this)); 
//		       videoView.setVideoURI(uri); 
//		       //videoView.start(); 
//		       videoView.requestFocus();
//		       Log.i("bb", "bb");
//			        
////			}
////		}));
//	}
//        
//        
//        
//     
//    
//
////
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        
////        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.main, menu);
////        return true;
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        // Handle action bar item clicks here. The action bar will
////        // automatically handle clicks on the Home/Up button, so long
////        // as you specify a parent activity in AndroidManifest.xml.
////        int id = item.getItemId();
////        if (id == R.id.action_settings) {
////            return true;
////        }
////        return super.onOptionsItemSelected(item);
////    }
////
////    /**
////     * A placeholder fragment containing a simple view.
////     */
////    public static class PlaceholderFragment extends Fragment {
////
////        public PlaceholderFragment() {
////        }
////
////        @Override
////        public View onCreateView(LayoutInflater inflater, ViewGroup container,
////                Bundle savedInstanceState) {
////            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
////            return rootView;
////        }
////    }
//
//}
