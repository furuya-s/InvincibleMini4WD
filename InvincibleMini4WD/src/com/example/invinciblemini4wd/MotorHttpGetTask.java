package com.example.invinciblemini4wd;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class MotorHttpGetTask extends AsyncTask<Integer, Void, Void>{
	private Activity parentActivity;
	private ProgressDialog dialog = null;
	
	// 実行するphpのURL
	private final String DEFAULTURL = "http://192.168.11.108/~pi/motortest.php?";
	private String uri = null;
	
	public MotorHttpGetTask(Activity parentActivity) {
		this.parentActivity = parentActivity;
	}
	
	@Override
	protected void onPreExecute() {
		dialog = new ProgressDialog(parentActivity);
		dialog.setMessage("通信中...");
		dialog.show();
	}
	
	@Override
	protected Void doInBackground(Integer... arg0) {
		uri = DEFAULTURL + "stat=" + arg0[0].toString();
		exec_get();
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		dialog.dismiss();
	}
	
	private String exec_get() {
		HttpURLConnection http = null;
		InputStream in = null;
		String src = new String();
		
		try {
			URL url = new URL(uri);
			http = (HttpURLConnection)url.openConnection();
			http.setRequestMethod("GET");
			http.connect();
			
			in = http.getInputStream();
			
			byte[] line = new byte[1024];
			int size;
			while (true) {
				size = in.read(line);
				if (size <= 0) {
					break;
				}
				src += new String(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (http != null) {
					http.disconnect();
				}
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {}
		}
		
		return src;
	}
}
