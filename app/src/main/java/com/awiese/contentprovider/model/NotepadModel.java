package com.awiese.contentprovider.model;

public class NotepadModel {

    private final String notepadTitleText;
    private final String notepadBodyText;
    private final int intId;

    public NotepadModel (int intId, String notepadTitleText, String notepadBodyText) {

        this.intId = intId;
        this.notepadTitleText = notepadTitleText;
        this.notepadBodyText = notepadBodyText;
    }

    public int getIntId() {
        return intId;
    }

    public String getNotepadTitleText() {
        return notepadTitleText;
    }

    public String getNotepadBodyText() {
        return notepadBodyText;
    }

}
