package com.example.board;

import com.example.board.dto.ArticleDto;
import com.example.board.entity.Article;
import com.example.board.entity.Board;
import com.example.board.repo.ArticleRepository;
import com.example.board.repo.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    public ArticleDto create(Long boardId, ArticleDto dto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow();
        Article article = new Article();
        article.setBoard(board);
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setPassword(dto.getPassword());
        return ArticleDto.fromEntity(articleRepository.save(article));
    }

    public List<ArticleDto> readALL() {
        List<ArticleDto> articleDtos = new ArrayList<>();
        for (Article article: articleRepository.findAll()){
            articleDtos.add(ArticleDto.fromEntity(article));
        }
        return articleDtos;
    }

    public ArticleDto readArticle(Long id){
        return ArticleDto.fromEntity(articleRepository.findById(id)
                .orElseThrow());
    }

    public ArticleDto update(Long id, ArticleDto articleDto) {
        Article article = articleRepository.findById(id)
                .orElseThrow();
        if (article.getPassword().equals(articleDto.getPassword())) {
            article.setTitle(articleDto.getTitle());
            article.setContent(articleDto.getContent());
        }
        // TODO else에서 throw
        return ArticleDto.fromEntity(articleRepository.save(article));
    }

    public void delete(Long id, String password) {
        Article article = articleRepository.findById(id)
                .orElseThrow();
        if (article.getPassword().equals(password)) {
            articleRepository.delete(article);
        }
        // TODO else에서 throw
    }
}
