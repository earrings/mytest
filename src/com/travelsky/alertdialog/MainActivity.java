package com.travelsky.alertdialog;

import com.example.hellodear.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity{
	//声明选中项变量  
    private int selectedCityIndex = 0;  
    
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//		
//		Builder builder = new AlertDialog.Builder(this);
//		
//		builder.setTitle("对话框");
//		builder.setIcon(R.drawable.ic_aa);
//		builder.setMessage("这是一个最简单的对话框");
//   1.
//		builder.create();
//		builder.show();
		
//		2.
//		//为“确定”按钮注册监听事件  
//		builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {  
//             @Override  
//             public void onClick(DialogInterface dialog, int which) {  
//                  // 根据实际情况编写相应代码。  
//            	 Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();;
//            	 
//             }  
//        });  
//		
//		//取消
//		builder.setNegativeButton(R.string.cancle, new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();;
//			}
//		});
//		
//		//查看详情
//		builder.setNeutralButton(R.string.detail, new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				Toast.makeText(MainActivity.this, "查看详细", Toast.LENGTH_SHORT).show();;
//			}
//		});
//		builder.create();
//		builder.show();

		//3.
//		setContentView(R.layout.activity_main);
//		final String items[] = {"海口","北京","上海","广州"};
//		Builder builder = new AlertDialog.Builder(this);
//		builder.setTitle("你喜欢下面的哪个城市");
//		builder.setIcon(R.drawable.ic_aa);
//		builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				selectedCityIndex = which;
//				
//			}
//		}); 
//			
//		builder.setNegativeButton(R.string.sure, new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				Toast.makeText(MainActivity.this, "你喜欢的城市是"+ items[selectedCityIndex], Toast.LENGTH_SHORT).show();
//				
//			}
//		});
//		
//		builder.setNeutralButton(R.string.cancle, new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_LONG).show();
//			}
//		});
//		
//		builder.create();
//		builder.show();
		
		setContentView(R.layout.activity_main);
		final String items[] = new String[]{"篮球","排球","乒乓球","保龄球"};
		final boolean[] sportSeleted = new boolean[]{false,false,false,false};
		
		Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("你喜欢下面的哪些运动项目？");
		builder.setIcon(R.drawable.ic_aa);
		
		builder.setMultiChoiceItems(items, sportSeleted, new DialogInterface.OnMultiChoiceClickListener() {  
	           
	           @Override  
	           public void onClick(DialogInterface dialog, int which, boolean isChecked) {  
	        	   sportSeleted[which] = isChecked;              //选中项的布尔真假保存到选中项变量  
	           }  
	       })  ;
		
		builder.setNegativeButton(R.string.sure, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				StringBuilder sb = new StringBuilder();
				for(int i=0;i<sportSeleted.length;i++){
					if(sportSeleted[i] == true){
						sb.append(items[i]+ ",");
					}
				}
				Toast.makeText(MainActivity.this, "你喜欢的运动是"+sb, Toast.LENGTH_SHORT).show();
				
				
			}
		});
			
		builder.setNeutralButton(R.string.cancle, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();			
			}
		});
		
		builder.create();
		builder.show();
	}

}
