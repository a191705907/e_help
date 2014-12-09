package client.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import communicate.PushConfig;
import communicate.PushSender;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends Activity implements OnClickListener{

	private EditText userId,password,password1;
	private TextView tv_phone;
	private Button cancel,register;
	private Map<String,Object> data=new HashMap<String,Object>();
	private Regis regis;
	private String country;
	private String phone;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//打开注册页面
		SMSSDK.initSDK(this, "4a0d041cc7be", "030ab5f5a5363e4fb03399bdea4b522d");
		RegisterPage registerPage = new RegisterPage();
		registerPage.setRegisterCallback(new EventHandler() {
			public void afterEvent(int event, int result, Object data) {
// 解析注册结果
				if (result == SMSSDK.RESULT_COMPLETE) {
					@SuppressWarnings("unchecked")
					HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
					country = (String) phoneMap.get("country");
					phone = (String) phoneMap.get("phone");
					init();
				}
			}
		});
		registerPage.show(RegisterActivity.this);


	}

	//初始化控件
	private void init() {
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);

		userId=(EditText)findViewById(R.id.user);
		password=(EditText)findViewById(R.id.password);
		password1=(EditText)findViewById(R.id.password2);
		cancel=(Button)findViewById(R.id.cancel);
		register=(Button)findViewById(R.id.register);
		tv_phone = (TextView)findViewById(R.id.tv_phone);
		tv_phone.setText(phone);
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				regis=new Regis();
				regis.execute();
			}
		});
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
	private class Regis extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
        	data.put("username",phone);
			data.put("nickname",userId.getText().toString());
            data.put("password",password.getText().toString());
            data.put("password1",password1.getText().toString());
            return PushSender.sendMessage("register",data);
			//打开注册页面

        }
        @Override
        protected void onPreExecute() {

        }
        @Override
        protected void onPostExecute(String result) {
        	if(result.equals("network error")){
        		Toast.makeText(RegisterActivity.this,"您还没有联网", Toast.LENGTH_SHORT).show();
        	}
        	if(result.equals("error")){
        		Toast.makeText(RegisterActivity.this,"连接服务器失败", Toast.LENGTH_SHORT).show();
        	}
            super.onPostExecute(result);
            try {
            	switch (new JSONObject(result).getInt("state")) {
            	case 1:
            		Toast.makeText(RegisterActivity.this, "用户名已经存在", Toast.LENGTH_SHORT).show();
            		break;
            	case 2:
            		Toast.makeText(RegisterActivity.this, "用户名格式错误", Toast.LENGTH_SHORT).show();
            		break;
            	case 3:
            		Toast.makeText(RegisterActivity.this, "两次输入密码不一样", Toast.LENGTH_SHORT).show();
            		break;
            	case 4:
            		Toast.makeText(RegisterActivity.this, "密码长度不符合要求", Toast.LENGTH_SHORT).show();
            		break;
            	default:
            		Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            		PushConfig.username = userId.getText().toString();
					PushSender.sendClientId();
					Intent intent = new Intent(RegisterActivity.this,PerfectInfomationActivity.class);
            		startActivity(intent);
            		finish();
            	}
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            super.onPostExecute(result);
        }
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}

