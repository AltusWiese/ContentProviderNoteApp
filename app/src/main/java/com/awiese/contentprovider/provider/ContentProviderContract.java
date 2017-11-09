package com.awiese.contentprovider.provider;


import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import java.util.HashMap;

public final class ContentProviderContract {

    public static final String AUTHORITY = "com.awiese.contentprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final class Notes implements Columns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(
                ContentProviderContract.CONTENT_URI, "notes");

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE +
                        "/com.awiese.contentprovider_notes";

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE +
                        "/com.awiese.contentprovider_notes";

        public static final String[] PROJECTION_ALL =
                {_ID, NOTE_TITLE, NOTE_BODY_TEXT};
    }


    public interface Columns extends BaseColumns {

        String _ID = "_id";
        String NOTE_TITLE = "note_title_text";
        String NOTE_BODY_TEXT = "note_body_text";
    }
}
