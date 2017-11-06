package com.awiese.contentprovider.model;

public class NotepadModel {

    private final String notepadTitleText;
    private final String notepadBodyText;


    public NotepadModel (String notepadTitleText, String notepadBodyText) {

        this.notepadTitleText = notepadTitleText;
        this.notepadBodyText = notepadBodyText;
    }

    public String getNotepadTitleText() {
        return notepadTitleText;
    }

    public String getNotepadBodyText() {
        return notepadBodyText;
    }

}
