package com.awiese.contentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Objects;

import static com.awiese.contentprovider.provider.ContentProviderContract.AUTHORITY;
import static com.awiese.contentprovider.provider.ContentProviderContract.Columns.NOTE_TITLE;
import static com.awiese.contentprovider.provider.ContentProviderContract.Columns._ID;
import static com.awiese.contentprovider.provider.ContentProviderContract.Notes.CONTENT_URI;
import static com.awiese.contentprovider.provider.DbSchema.NOTES_TABLE_NAME;


public class NotepadContentProvider extends ContentProvider {

    private static final int NOTES = 1;
    private static final int NOTES_ID = 2;
    private static final HashMap<String, String> NOTES_PROJECTION_MAP = new HashMap<>();
    private static final UriMatcher URI_MATCHER;
    private NotesOpenHelper mHelper = null;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, "/notes", NOTES);
        URI_MATCHER.addURI(AUTHORITY, "/notes/#", NOTES_ID);
    }

    @Override
    public boolean onCreate() {
        mHelper = new NotesOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        long rowID = db.insert(NOTES_TABLE_NAME, "", values);
        if (rowID > 0) {
            Uri notesUri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(notesUri, null);
            }
            return notesUri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(NOTES_TABLE_NAME);

        switch (URI_MATCHER.match(uri)) {
            case NOTES:
                builder.setProjectionMap(NOTES_PROJECTION_MAP);
                break;
            case NOTES_ID:
                builder.appendWhere(_ID + "=" + uri.getLastPathSegment());
                break;

            default:
        }
        if (sortOrder == null || Objects.equals(sortOrder, "")) {
            sortOrder = NOTE_TITLE;
        }

        Cursor c = builder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        if (getContext() != null) {
            c.setNotificationUri(getContext().getContentResolver(), uri);
            return c;
        }

        throw new IllegalArgumentException("Invalid query" + c);
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        SQLiteDatabase db = mHelper.getWritableDatabase();
        int match = URI_MATCHER.match(uri);
        int count;
        switch (match) {
            case NOTES:
                count = db.delete(NOTES_TABLE_NAME, selection, selectionArgs);
                break;
            case NOTES_ID:
                String id = uri.getLastPathSegment();
                String where = _ID + " = " + id;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                count = db.delete(NOTES_TABLE_NAME, where, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        if (count >= 0 && getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int count;
        switch (URI_MATCHER.match(uri)) {
            case NOTES:
                count = db.update(NOTES_TABLE_NAME, values, selection, selectionArgs);
                break;

            case NOTES_ID:
                String id = uri.getLastPathSegment();
                String where = _ID + " = " + id;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                count = db.update(NOTES_TABLE_NAME, values, where, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (count > 0 && getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;

    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case NOTES:
                return "vnd.android.cursor.dir/com.awiese.notes";
            case NOTES_ID:
                return "vnd.android.cursor.item/com.awiese.notes";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

}
