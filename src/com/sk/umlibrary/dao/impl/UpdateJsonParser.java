package com.sk.umlibrary.dao.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import android.text.TextUtils;

import com.google.gson.stream.JsonReader;
import com.sk.umlibrary.bean.UpdateInfo;
import com.sk.umlibrary.dao.AbstractParser;
import com.sk.umlibrary.exception.UpdateException;
import com.sk.umlibrary.utils.Log;

/**
 * crate by hi-github on 01/13/2015
 *
 * code from snowdream
 */
public class UpdateJsonParser extends AbstractParser {


    /**
     * Parse the UpdateInfo form the string
     *
     * @param content
     * @return UpdateInfo
     * @throws UpdateException
     */
    @Override
    public UpdateInfo parse(String content) throws UpdateException {
        UpdateInfo info = null;

        if (TextUtils.isEmpty(content)) {
            throw new UpdateException(UpdateException.PARSE_ERROR);
        }

        JsonReader jsonReader = new JsonReader(new StringReader(content));
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()){
                String name = jsonReader.nextName();
                if (name.equals(TAG_UPDATE_INFO)) {
                    info = parseUpdateInfo(jsonReader);
                } else {
                    jsonReader.skipValue();
                }
            }

            jsonReader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("IOException", e);
            throw new UpdateException(UpdateException.PARSE_ERROR);
        }finally{
            try {
                jsonReader.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("jsonReader.close()", e);
                throw new UpdateException(UpdateException.PARSE_ERROR);
            }
        }

        return info;
    }

    /**
     * Parse UpdateInfo
     *
     * @param jsonReader
     * @return
     * @throws IOException
     */
    private UpdateInfo parseUpdateInfo(JsonReader jsonReader) throws IOException {
        UpdateInfo info = new UpdateInfo();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            if (name.equals(TAG_APP_NAME)) {
                info.setAppName(jsonReader.nextString());
            } else if (name.equals(TAG_APP_DESCRIPTION)) {
                info.setAppDescription(jsonReader.nextString());
            } else if (name.equals(TAG_PACKAGE_NAME)) {
                info.setPackageName(jsonReader.nextString());
            } else if (name.equals(TAG_VERSION_CODE)) {
                info.setVersionCode(jsonReader.nextString());
            } else if (name.equals(TAG_VERSION_NAME)) {
                info.setVersionName(jsonReader.nextString());
            } else if (name.equals(TAG_FORCE_UPDATE)) {
                info.setForceUpdate(jsonReader.nextBoolean());
            } else if (name.equals(TAG_AUTO_UPDATE)) {
                info.setAutoUpdate(jsonReader.nextBoolean());
            } else if (name.equals(TAG_APK_URL)) {
                info.setApkUrl(jsonReader.nextString());
            } else if (name.equals(TAG_UPDATE_TIPS)) {
                Map<String, String> updateTips = parseUpdateTips(jsonReader);
                info.setUpdateTips(updateTips);
            }else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return info;
    }

    /**
     * Parse UpdateTips
     *
     * @param jsonReader
     * @return
     * @throws IOException
     */
    private Map<String, String> parseUpdateTips(JsonReader jsonReader) throws IOException {
        Map<String, String> updateTips = new HashMap<String, String>();

        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            String value = jsonReader.nextString();
            updateTips.put(name, value);
        }
        jsonReader.endObject();
        return updateTips;
    }
}
