package com.sample.soapapiloginsample;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class LoginTaskLoader extends AsyncTaskLoader<PartnerConnection> {
	private String username;
	private String password;

	public LoginTaskLoader(Context context, Bundle args) {
		super(context);
		username = args.getString("USER_NAME");
		password = args.getString("PASSWORD");
	}

	@Override
	public PartnerConnection loadInBackground() {
		PartnerConnection connection = null;
		
		ConnectorConfig config = new ConnectorConfig();
		config.setUsername(username);
		config.setPassword(password);
		
		try {
			connection = Connector.newConnection(config);
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	@Override
	protected void onStartLoading(){
		forceLoad();
	}

}
