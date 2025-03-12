package com.mdd.services;


import com.mdd.dto.CommentRequestDTO;
import com.mdd.dto.CommentResponseDTO;

import java.util.List;

public interface CommentService {


    List<CommentResponseDTO> getCommentsByArticleId(Integer articleId, String userEmail);

    void addComment(CommentRequestDTO commentRequest, String userEmail);
}
