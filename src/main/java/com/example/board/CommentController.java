package com.example.board;

import com.example.board.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("article/{articleId}/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public String createComment(
            @PathVariable("articleId")
            Long articleId,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password
    ) {
        commentService.createComment(articleId, new CommentDto(content, password));
        return String.format("redirect:/article/%d",articleId);
    }

    @PostMapping("{commentId}/delete")
    public String deleteComment(
            Long boardId,
            @PathVariable("articleId")
            Long articleId,
            @PathVariable("commentId")
            Long commentId,
            @RequestParam("password")
            String password
    ) {
        commentService.deleteComment(commentId,password);
        return String.format("redirect:/article/%d",articleId);
    }


}
