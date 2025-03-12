package com.mdd.services;


import com.mdd.dto.CommentRequestDTO;
import com.mdd.dto.CommentResponseDTO;

import java.util.List;

public interface CommentService {

    /**
     * This method should return all comments for the article
     * @param articleId
     * @param userEmail
     * @return List<Comment>
     */
    List<CommentResponseDTO> getCommentsByArticleId(Integer articleId, String userEmail);

    /**
     * This method should add a comment to the article
     * @param commentRequest
     * @param userEmail
     */
    void addComment(CommentRequestDTO commentRequest, String userEmail);
}
