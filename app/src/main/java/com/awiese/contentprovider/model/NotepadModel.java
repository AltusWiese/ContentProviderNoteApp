package com.awiese.contentprovider.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NotepadModel implements Parcelable {

    private final String notepadTitleText;
    private final String notepadBodyText;
    private String noteId;

    public NotepadModel(String noteId, String notepadTitleText, String notepadBodyText) {

        this.noteId = noteId;
        this.notepadTitleText = notepadTitleText;
        this.notepadBodyText = notepadBodyText;
    }

    public NotepadModel(String notepadTitleText, String notepadBodyText) {

        this.notepadTitleText = notepadTitleText;
        this.notepadBodyText = notepadBodyText;
    }

    public String getNoteId() {
        return noteId;
    }

    public String getNotepadTitleText() {
        return notepadTitleText;
    }

    public String getNotepadBodyText() {
        return notepadBodyText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.notepadTitleText);
        dest.writeString(this.notepadBodyText);
        dest.writeString(this.noteId);
    }

    protected NotepadModel(Parcel in) {
        this.notepadTitleText = in.readString();
        this.notepadBodyText = in.readString();
        this.noteId = in.readString();
    }

    public static final Parcelable.Creator<NotepadModel> CREATOR = new Parcelable.Creator<NotepadModel>() {
        @Override
        public NotepadModel createFromParcel(Parcel source) {
            return new NotepadModel(source);
        }

        @Override
        public NotepadModel[] newArray(int size) {
            return new NotepadModel[size];
        }
    };
}
