package com.mdd.controllers;

import com.mdd.dto.CommentRequestDTO;
import com.mdd.dto.CommentResponseDTO;
import com.mdd.dto.ResponseDTO;
import com.mdd.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CommentsController {

    @Autowired
    private CommentService commentService;

    /**
     * Retrieves comments for a specific article identified by its ID.
     *
     * @param articleId The ID of the article for which comments are requested.
     * @param authentication The authentication context containing the user's details.
     * @return A ResponseEntity containing a list of comments.
     */
    @GetMapping("/comments/{articleId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByArticleId(@PathVariable Integer articleId, Authentication authentication) {
        String userEmail = authentication.getName();
        List<CommentResponseDTO> comments = commentService.getCommentsByArticleId(articleId, userEmail);
        return ResponseEntity.ok(comments);
    }

    /**
     * Adds a comment to a specific article identified by its ID.
     *
     * @param commentRequest The request payload containing the comment details.
     * @param authentication The authentication context containing the user's details.
     * @return A ResponseEntity containing a message.
     */
    @PostMapping("/comments")
    public ResponseEntity<ResponseDTO> addCommentToArticle(@RequestBody CommentRequestDTO commentRequest, Authentication authentication) {
        String userEmail = authentication.getName();
        commentService.addComment(commentRequest, userEmail);
        ResponseDTO response= new ResponseDTO("Comment added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
