package com.awiese.contentprovider;


import android.view.View;

import com.awiese.contentprovider.model.NotepadModel;

public interface RecycleViewClickListener {
    void onClick(View view, NotepadModel notepadModel);
}
