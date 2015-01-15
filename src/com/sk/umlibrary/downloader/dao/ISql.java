package com.sk.umlibrary.downloader.dao;

import java.sql.SQLException;

import com.sk.umlibrary.downloader.DownloadTask;

/**
 * crate by hi-github on 01/13/2015
 *
 * code from snowdream
 */
public interface ISql {

    public void addDownloadTask(DownloadTask task) throws SQLException;

    public void updateDownloadTask(DownloadTask task) throws SQLException;

    public DownloadTask queryDownloadTask(DownloadTask task)
            throws SQLException;

    // public List<DownloadTask> queryDownloadTasksFromAlbum(Album album)throws
    // SQLException;

    public void deleteDownloadTask(DownloadTask task) throws SQLException;
}
