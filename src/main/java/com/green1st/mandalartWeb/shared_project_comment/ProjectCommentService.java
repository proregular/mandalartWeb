package com.green1st.mandalartWeb.shared_project_comment;

import com.green1st.mandalartWeb.shared_project_comment.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectCommentService {
    private final ProjectCommentMapper mapper;

    public long postProjectComment(ProjectCommentPostReq p) {
        mapper.postSharedProjectComment(p);
        return p.getCommentId();
    }

    public ProjectCommentGetRes getProjectCommentList(ProjectCommentGetReq p) {
        ProjectCommentGetRes res = new ProjectCommentGetRes();
        if (p.getProjectId() <= 0) {
            res.setContentList(new ArrayList<>());
            return res;
        }
        List<ProjectCommentDto> contentList = mapper.getSharedProjectComment(p);
        res.setContentList(contentList);
        return res;
    }

    public int updateProjectComment(ProjectCommentPatchReq p) {
        return mapper.updateSharedProjectComment(p);
    }

    public int deleteProjectComment(ProjectCommentDelReq p) {
        return mapper.deleteSharedProjectComment(p);
    }
}

