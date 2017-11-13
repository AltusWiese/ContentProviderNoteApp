package com.awiese.contentprovider.model;

public class NotepadModel {

    private final String notepadTitleText;
    private final String notepadBodyText;
    private final String noteId;

    public NotepadModel (String noteId, String notepadTitleText, String notepadBodyText) {

        this.noteId = noteId;
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

}
