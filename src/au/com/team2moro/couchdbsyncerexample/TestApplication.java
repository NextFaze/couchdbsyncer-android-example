package au.com.team2moro.couchdbsyncerexample;

import android.app.Application;
import android.content.SharedPreferences;
import au.com.team2moro.couchdbsyncer.ConnectionSettings;
import au.com.team2moro.couchdbsyncer.ConnectionSettingsTrustAll;
import au.com.team2moro.couchdbsyncer.Database;
import au.com.team2moro.couchdbsyncer.DatabaseStore;

public class TestApplication extends Application {

	DatabaseStore dbstore;
	
	@Override
	public void onCreate() {
		super.onCreate();

		dbstore = new DatabaseStore(this);
	}
	
	DatabaseStore getDatabaseStore() {
		return dbstore;
	}

	private String getCredentialsPreferenceKey(Database database) {
		return "database_credentials_" + database.getName();
	}
	
	public ConnectionSettings getDatabaseConnectionSettings(Database database) {
		SharedPreferences pref = getSharedPreferences(getCredentialsPreferenceKey(database), MODE_PRIVATE);
		String username = pref.getString("username", null);
		String password = pref.getString("password", null);

	    if (username != null && password != null) {
	    	// allow self-signed ssl certificates
	    	return new ConnectionSettings(username, password,
	    			ConnectionSettingsTrustAll.getSocketFactory(), ConnectionSettingsTrustAll.getHostnameVerifier());
	    }
	    else {
	    	return null;
	    }
	}
	
	public void setDatabaseConnectionSettings(Database database, ConnectionSettings connectionSettings) {
		SharedPreferences pref = getSharedPreferences(getCredentialsPreferenceKey(database), MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString("username", connectionSettings.getUsername());
		editor.putString("password", connectionSettings.getPassword());
		editor.commit();
	}
}
