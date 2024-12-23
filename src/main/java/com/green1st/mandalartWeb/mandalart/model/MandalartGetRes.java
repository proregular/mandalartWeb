package com.green1st.mandalartWeb.mandalart.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Schema(title = "만다라트 조회 정보")
@Setter
public class MandalartGetRes {
    @Schema(title = "project_id")
    private long projectId;
    @Schema(description = "만다라트 id 리스트")
    private List<MandalartGetDto> mandalartIdGetList;
    private int completedCount;
    private String color;
    private int depth;

    public List<MandalartGetDto> getMandalartIdGetList() {
        return mandalartIdGetList;
    }

    public int getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(int completedCount) {
        this.completedCount = completedCount;
    }

    public String getColor() {
        return color;
    }
    public void setColorCodes(String color) {
        this.color = color;
    }
    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
