package com.sample.soapapiloginsample;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sforce.soap.partner.PartnerConnection;

public class LoginActivity extends Activity implements LoaderCallbacks<PartnerConnection>{
	private PartnerConnection connection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		Button btLogin = (Button)findViewById(R.id.bt_login);
		btLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText etUsername = (EditText)findViewById(R.id.et_username);
				String username = etUsername.getText().toString();
				
				EditText etPassword = (EditText)findViewById(R.id.et_password);
				String password = etPassword.getText().toString();
				
				Bundle args = new Bundle();
				args.putString("USER_NAME", username);
				args.putString("PASSWORD", password);
				
				getLoaderManager().restartLoader(0, args, LoginActivity.this);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public Loader<PartnerConnection> onCreateLoader(int id, Bundle args) {
		return new LoginTaskLoader(this, args);
	}

	@Override
	public void onLoadFinished(Loader<PartnerConnection> loader, PartnerConnection connection) {
		this.connection = connection;
		if(connection == null){
			Toast.makeText(this, "ログインに失敗しました。", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(this, "ログインに成功しました。", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onLoaderReset(Loader<PartnerConnection> arg0) {
	}

}
