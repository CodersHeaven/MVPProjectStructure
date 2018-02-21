package projects.in.projectstructure.data.remote;

import projects.in.projectstructure.data.DataSource;
import projects.in.projectstructure.utils.threading.MainUiThread;
import projects.in.projectstructure.utils.threading.ThreadExecutor;

public class RemoteDataSource extends DataSource {
    private ApiService apiService;
    private static RemoteDataSource remoteDataSource;

    public RemoteDataSource(MainUiThread mainUiThread, ThreadExecutor threadExecutor) {
        super(mainUiThread, threadExecutor);
    }

    public static RemoteDataSource getInstance(MainUiThread mainUiThread, ThreadExecutor threadExecutor) {
        if (remoteDataSource == null) {
            remoteDataSource = new RemoteDataSource(mainUiThread, threadExecutor);
        }

        return remoteDataSource;
    }
} 