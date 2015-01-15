package com.sk.umlibrary.downloader.dao;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.sk.umlibrary.downloader.DownloadTask;

/**
 * crate by hi-github on 01/13/2015
 *
 * code from snowdream
 */
public class ISqlImpl implements ISql {

    private DatabaseHelper databaseHelper = null;

    private Context mContext = null;

    public ISqlImpl(Context context) {
        mContext = context;
    }

    /*
     * You'll need this in your class to release the helper when done.
     */
    public void Release() {

        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    /**
     * You'll need this in your class to get the helper from the manager once
     * per class.
     */
    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(mContext, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    public void addDownloadTask(DownloadTask task) throws SQLException {
        if (task == null) {
            return;
        }

        Dao<DownloadTask, Integer> taskDao = getHelper().getTaskDao();

        taskDao.createOrUpdate(task);
    }

    public void updateDownloadTask(DownloadTask task) throws SQLException {
        if (task == null) {
            return;
        }

        Dao<DownloadTask, Integer> taskDao = getHelper().getTaskDao();

        taskDao.update(task);
    }

    public DownloadTask queryDownloadTask(DownloadTask task) throws SQLException {
        List<DownloadTask> tasks = null;

        DownloadTask ttask = null;

        if (task == null) {
            return ttask;
        }

        Dao<DownloadTask, Integer> taskDao = getHelper().getTaskDao();

        tasks = taskDao.queryForEq("url", task.getUrl());

        if (tasks != null && tasks.size() > 0) {
            ttask = tasks.get(0);
        }

        return ttask;
    }

    public void deleteDownloadTask(DownloadTask task) throws SQLException {
        if (task == null) {
            return;
        }

        Dao<DownloadTask, Integer> taskDao = getHelper().getTaskDao();

        taskDao.delete(task);
    }

}
