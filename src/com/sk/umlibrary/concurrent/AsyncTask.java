package com.sk.umlibrary.concurrent;

import java.lang.reflect.Field;
import java.util.concurrent.Executor;

import com.sk.umlibrary.utils.Log;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * crate by hi-github on 01/13/2015
 *
 * code from snowdream
 */
public abstract class AsyncTask<Params, Progress, Result> extends
        android.os.AsyncTask<Params, Progress, Result> {
    protected TaskListener<Progress, Result> listener = null;

    public AsyncTask() {
        super();
    }

    public AsyncTask(TaskListener<Progress, Result> listener) {
        super();
        this.listener = listener;
    }


    public TaskListener<Progress, Result> getListener() {
        return listener;
    }

    public void setListener(TaskListener<Progress, Result> listener) {
        this.listener = listener;
    }

    /**
     * TODO <BR/>
     * if error occurs,carry it out.<BR/>
     * <p/>
     * <pre>
     * if (listener != null) {
     *     listener.onError(new Throwable());
     * }
     * </pre>
     */
    @Override
    protected abstract Result doInBackground(Params... params);

    @Override
    protected void onCancelled() {
        super.onCancelled();
        if (listener != null) {
            listener.onCancelled();
            listener.onFinish();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCancelled(Result result) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            super.onCancelled(result);
        } else {
            super.onCancelled();
        }
    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);
        if (listener != null) {
            if (result != null) {
                listener.onSuccess(result);
            }

            listener.onFinish();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (listener != null) {
            listener.onStart();
        }
    }

    @Override
    protected void onProgressUpdate(Progress... values) {
        super.onProgressUpdate(values);
        if (listener != null) {
            listener.onProgressUpdate(values);
        }
    }

    /**
     * set the default Executor
     *
     * @param executor
     */
    public static void setDefaultExecutor(Executor executor) {
        Class<?> c = null;
        Field field = null;
        try {
            c = Class.forName("android.os.AsyncTask");
            field = c.getDeclaredField("sDefaultExecutor");
            field.setAccessible(true);
            field.set(null, executor);
            field.setAccessible(false);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Log.e("IllegalArgumentException", e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e("ClassNotFoundException", e);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            Log.e("NoSuchFieldException", e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.e("IllegalAccessException", e);
        }
    }

    /**
     * get the default Executor
     *
     * @return the default Executor
     */
    public static Executor getDefaultExecutor() {
        Executor exec = null;

        Class<?> c = null;
        Field field = null;
        try {
            c = Class.forName("android.os.AsyncTask");
            field = c.getDeclaredField("sDefaultExecutor");
            field.setAccessible(true);
            exec = (Executor) field.get(null);
            field.setAccessible(false);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Log.e("IllegalArgumentException", e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e("ClassNotFoundException", e);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            Log.e("NoSuchFieldException", e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.e("IllegalAccessException", e);
        }

        return exec;
    }
}
