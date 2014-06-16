package com.travelsky.completetext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.xmlpull.v1.XmlPullParserException;

import com.example.hellodear.R;
import com.test.User;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends Activity{
	
	private static final String NAMESPACE ="http://jaxws.blizz.tk/"; 
	// WebService地址 
	private static String URL ="http://172.16.1.10:8080/jaxws/services/hello_world?wsdl"; 
	private static final String METHOD_NAME ="getUserByName"; 
	private static String SOAP_ACTION ="";
	
	private final String[] city = new String[]{
			"上海","北京","大连","海口","海大","广州"
	};
	
	private AutoCompleteTextView actw = null;
	private MultiAutoCompleteTextView  mactw = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.completetextview_text);
		
		
//		
//		//获取AutoCompleteTextView、MultiAutoCompleteTextView对象  
//		actw=(AutoCompleteTextView)findViewById(R.id.single);  
//		mactw=(MultiAutoCompleteTextView)findViewById(R.id.multi);  
//          
//        //创建适配器  
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(  
//                this,   
//                android.R.layout.simple_dropdown_item_1line,  
//                city);  
//          
//        //初始化single  
//        actw.setAdapter(adapter);  
//        //设置输入多少字符后提示，默认值为2，在此设为１  
//        actw.setThreshold(1);  
//          
//        //初始化multi  
//        mactw.setAdapter(adapter);  
//        mactw.setThreshold(1);  
//        //用户必须提供一个MultiAutoCompleteTextView.Tokenizer用来区分不同的子串。  
//        mactw.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());  
		
		
		 new Thread(){  
	            public void run(){  
	            	testWebService(); 
	            }  
	        }.start();  		
	}
	
	
	/**************
	 * 解析XML
	 * @param str
	 * @return
	 */
	private  List<String> parse(String str){
	    String temp;
	    List<String> list=new ArrayList<String>();
	    if(str!=null && str.length()>0){
	        int start=str.indexOf("name");
	        int end=str.lastIndexOf(";");
	        temp=str.substring(start, end-3);
	        String []test=temp.split(";");
	        
	         for(int i=0;i<test.length;i++){
	             if(i==0){
	                 temp=test[i].substring(7);
	             }else{
	                 temp=test[i].substring(8);
	             }
	             int index=temp.indexOf(",");
	             list.add(temp.substring(0, index));
	         }
	    }
	    return list;
	}
	
	
	public void testWebService(){
		SoapObject request =new SoapObject(NAMESPACE, METHOD_NAME);
		request.addProperty("arg0","User1");
		SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = request;
		AndroidHttpTransport ht =new AndroidHttpTransport(URL); 
//		try {
			try {
				ht.call(null, envelope);
				Object object  = envelope.getResponse();
				System.out.println(object.toString()+ "^^^^^^^");
			//	System.out.println(envelope.bodyIn.toString()+ "************");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			
//			for(int i=0;i<soapObject.getPropertyCount();i++){
//			    SoapObject soapChilds =(SoapObject)soapObject.getProperty(i);
//			   List list =  parse(soapChilds.toString());
//			   for(int j=0;j<list.size();j++){
//				   
//			   }
//			    try{
//			    	System.out.println(soapChilds.getProperty("name").toString()+ "&&&&&&&&&&");
//			    	System.out.println(soapChilds.getProperty("birthday").toString());
//			    }catch(Exception e){
//			    	System.out.println(soapChilds.getAttribute("name").toString()+"@@@@@@@@@@");
//					   
//				    System.out.println(soapChilds.getAttribute("birthday").toString());
//			    }

//			}
//			
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (XmlPullParserException e) {
//			e.printStackTrace();
//		}
		
		
		


	}
}
