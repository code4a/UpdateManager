package com.sk.umlibrary.downloader;

/**
 * crate by hi-github on 01/13/2015
 *
 * code from snowdream
 */
public class DownloadStatus {
    /**
     * the DownloadTask has successfully completed.
     */
    public static final int STATUS_PENDING = 0x00000001;

    /**
     * The DownloadTask is currently running.
     */
    public static final int STATUS_RUNNING = 0x00000002;

    /**
     * The DownloadTask is stopped.
     */
    public static final int STATUS_STOPPED = 0x00000003;

    /**
     * The DownloadTask has successfully completed.
     */
    public static final int STATUS_FINISHED = 0x00000005;

    /**
     * The DownloadTask has failed (and will not be retried).
     */
    public static final int STATUS_FAILED = 0x00000006;

    /**
     * The DownloadTask has been deleted.
     */
    public static final int STATUS_DELETED = 0x00000007;
}
