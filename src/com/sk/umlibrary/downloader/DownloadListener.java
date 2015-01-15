package com.sk.umlibrary.downloader;

import com.sk.umlibrary.concurrent.TaskListener;

/**
 * crate by hi-github on 01/13/2015
 *
 * code from snowdream
 */
public class DownloadListener<Integer, DownloadTask> extends TaskListener<Integer, DownloadTask> {
    /**
     * The download task has been added to the sqlite.
     * <p/>
     * operation of UI allowed.
     *
     * @param task the download task which has been added to the sqlite.
     */
    public void onAdd(DownloadTask task) {
    }

    /**
     * The download task has been delete from the sqlite
     * <p/>
     * operation of UI allowed.
     * @param task the download task which has been deleted to the sqlite.
     */
    public void onDelete(DownloadTask task) {
    }

    /**
     * The download task is stop
     * <p/>
     * operation of UI allowed.
     * @param task the download task which has been stopped.
     */
    public void onStop(DownloadTask task) {
    }
}
