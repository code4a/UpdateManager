package com.sk.umlibrary.dao;

import java.util.Locale;
import java.util.Map;

import android.content.Context;
import android.os.Handler;

import com.sk.umlibrary.UpdateManager;
import com.sk.umlibrary.bean.UpdateInfo;
import com.sk.umlibrary.concurrent.TaskListener;
import com.sk.umlibrary.data.UpdateOptions;
import com.sk.umlibrary.downloader.DownloadTask;

/**
 * crate by hi-github on 01/13/2015
 *
 * code from snowdream
 */
public abstract class AbstractUpdateListener extends TaskListener<Integer, UpdateInfo>{

    private Handler handler = null;
    private Context context = null;
    private UpdateOptions updateOptions = null;
    
    /**
     * Get Handler
     *
     * @return
     */
    public Handler getHandler() {
        return handler;
    }

    /**
     * Set Handler
     *
     * @param handler
     */
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    /**
     * Get the Context
     *
     * @return
     */
    public Context getContext() {
        return context;
    }

    /**
     * Set the Context
     *
     * @param context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Get the update options
     *
     * @return
     */
    public UpdateOptions getUpdateOptions() {
        return updateOptions;
    }

    /**
     * Set the update options
     *
     * @param updateOptions
     */
    public void setUpdateOptions(UpdateOptions updateOptions) {
        this.updateOptions = updateOptions;
    }

    /**
     * show the update dialog
     *
     * @param info the info for the new app
     */
    public abstract void onShowUpdateUI(final UpdateInfo info);

    /**
     * It's the latest app,or there is no need to update.
     */
    public abstract void onShowNoUpdateUI();

    /**
     * show the progress when downloading the new app
     */
    public abstract void onShowUpdateProgressUI(final UpdateInfo info, final DownloadTask task, final int progress);

    /**
     * user click to confirm the update
     */
    public final void informUpdate(final UpdateInfo info) {
        if (handler != null) {
            handler.obtainMessage(
                    UpdateManager.MSG_INFORM_UPDATE,
                    info).sendToTarget();
        }
    }

    /**
     * user click to cancel the update
     */
    public final void informCancel(final UpdateInfo info) {
        if (handler != null) {
            handler.obtainMessage(
                    UpdateManager.MSG_INFORM_CANCEL);
        }

        if ((updateOptions != null && updateOptions.shouldForceUpdate())
                || (info != null && info.isForceUpdate())) {
            ExitApp();
        }
    }

    /**
     * user click to skip the update
     */
    public final void informSkip(final UpdateInfo info) {
        if (handler != null) {
            handler.obtainMessage(
                    UpdateManager.MSG_INFORM_SKIP, info).sendToTarget();
        }

        if ((updateOptions != null && updateOptions.shouldForceUpdate())
                || (info != null && info.isForceUpdate())) {
            ExitApp();
        }
    }

    /**
     * Exit the app here
     */
    public abstract void ExitApp();

    /**
     * Get the update tips for the current language
     *
     * @param info update info
     * @return
     */
    public String getUpdateTips(UpdateInfo info) {
        String tip = null;
        Context context = getContext();
        if (context == null || info == null) {
            return tip;
        }

        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        Map<String, String> tips = info.getUpdateTips();
        if (tips == null) {
            return tip;
        }

        if (language != null && tips.containsKey(language)) {
            tip = tips.get(language);
        } else {
            tip = tips.get("default");
        }

        //Android textview not supporting line break.see http://stackoverflow.com/a/12422965/821624
        return tip.replace("\\n", "\n");
    }
}
