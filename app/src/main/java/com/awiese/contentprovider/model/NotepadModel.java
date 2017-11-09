package com.awiese.contentprovider.model;

public class NotepadModel {

    private final String notepadTitleText;
    private final String notepadBodyText;
    private final int noteId;

    public NotepadModel (int noteId, String notepadTitleText, String notepadBodyText) {

        this.noteId = noteId;
        this.notepadTitleText = notepadTitleText;
        this.notepadBodyText = notepadBodyText;
    }

    public int getNoteId() {
        return noteId;
    }

    public String getNotepadTitleText() {
        return notepadTitleText;
    }

    public String getNotepadBodyText() {
        return notepadBodyText;
    }

}
