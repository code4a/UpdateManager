# UpdateManager

android application update library

# Statement

code of library is from github auther snowdreamframework(autoupdater) j256(ormlite-come/android) and kevinsawicki(http-request)

# Permission requirements

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    
# Use
    
    final UpdateManager mUM = new UpdateManager(this);
    findViewById(R.id.update_btn).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UpdateOptions options = new UpdateOptions.Builder(
                            MainActivity.this)
                            .checkUrl(
                            "https://raw.github.com/hi-github/UpdateManager/master/docs/test/UpdateInfo.xml")
                            .updateFormat(UpdateFormat.XML)
                            .updatePeriod(
                                    new UpdatePeriod(UpdatePeriod.EACH_TIME))
                            .checkPackageName(true).build();
                    mUM.check(MainActivity.this, options);
                }
            });

# Usage

* [snowdreamframework](https://github.com/SnowdreamFramework/android-autoupdater)
