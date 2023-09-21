package com.meta.verse.debugClutch;

import lombok.Data;

@Data
public class WmsPullDlrInfoDBO {
    private String responseId;
    private String status;
    @Override
    public String toString() {
        return responseId;
    }

}
