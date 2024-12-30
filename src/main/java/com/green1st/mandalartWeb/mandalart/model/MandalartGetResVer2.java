package com.green1st.mandalartWeb.mandalart.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MandalartGetResVer2 {
    private long projectId;
    private String nickName;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private int sharedFg;
    List<MandalartGetRes> mandalart;
}
