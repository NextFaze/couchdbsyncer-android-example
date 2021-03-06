package au.com.team2moro.couchdbsyncerexample;

import android.util.Log;
import au.com.team2moro.couchdbsyncer.ConnectionSettings;
import au.com.team2moro.couchdbsyncer.DatabaseStore;
import au.com.team2moro.couchdbsyncer.SyncerService;

public class TestSyncerService extends SyncerService {
	public static final String TAG = "TestSyncerService";
	
	public TestSyncerService() {
		super();
	}
	
	protected DatabaseStore getDatabaseStore() {
		Log.d(TAG, "application = " + ((TestApplication) getApplication()));
		DatabaseStore dbstore = ((TestApplication) getApplication()).getDatabaseStore();
		return dbstore;
	}
	
	protected ConnectionSettings getConnectionSettings() {
		ConnectionSettings connectionSettings = ((TestApplication) getApplication()).getDatabaseConnectionSettings(getDatabase());
		return connectionSettings;
	}
}
