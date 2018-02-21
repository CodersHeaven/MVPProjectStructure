package projects.in.projectstructure.data;

import projects.in.projectstructure.utils.threading.MainUiThread;
import projects.in.projectstructure.utils.threading.ThreadExecutor;

public abstract class DataSource {
    private MainUiThread mainUiThread;
    private ThreadExecutor threadExecutor;

    public DataSource(MainUiThread mainUiThread, ThreadExecutor threadExecutor) {
        this.mainUiThread = mainUiThread;
        this.threadExecutor = threadExecutor;
    }
} 