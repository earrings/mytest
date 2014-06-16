package com.example.buttonActivity;



import com.example.hellodear.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity{
	
	private EditText tvUsername = null;
	private EditText tvPassWord = null;
	private Button btLogin = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		tvUsername = (EditText)super.findViewById(R.id.username);
		tvPassWord = (EditText)super.findViewById(R.id.password);
		btLogin = (Button)super.findViewById(R.id.button1);
		btLogin.setOnClickListener(new LoginOnClickListener());
				
				
				
//				new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				String username = tvUsername.getText().toString();
//				String password = tvPassWord.getText().toString();
//				String info = "用户名："+ username + "@@@@密码:"+ password;
//				
//				// 第一个参数：当前的上下文环境。可用getApplicationContext()或this 
//				// 第二个参数：要显示的字符串。也可是R.string中字符串ID 
//				// 第三个参数：显示的时间长短。Toast默认的有两个LENGTH_LONG(长)和LENGTH_SHORT(短)，也可以使用毫秒如2000ms
//				Toast.makeText(getApplicationContext(), info,Toast.LENGTH_SHORT).show();				
//			}
//		});	
	}	
	
	 private class LoginOnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			String username = tvUsername.getText().toString();
			String password = tvPassWord.getText().toString();
			String info = "用户名："+ username + "@@@@密码:"+ password;
			
			// 第一个参数：当前的上下文环境。可用getApplicationContext()或this 
			// 第二个参数：要显示的字符串。也可是R.string中字符串ID 
			// 第三个参数：显示的时间长短。Toast默认的有两个LENGTH_LONG(长)和LENGTH_SHORT(短)，也可以使用毫秒如2000ms
			Toast.makeText(getApplicationContext(), info,Toast.LENGTH_SHORT).show();
			
		}
		
	}
	
}
