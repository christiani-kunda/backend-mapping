package com.mhp.coding.challenges.mapping.services

import com.mhp.coding.challenges.mapping.repositories.ArticleRepository
import com.mhp.coding.challenges.mapping.mappers.ArticleMapper
import com.mhp.coding.challenges.mapping.models.db.Article
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto
import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val mapper: ArticleMapper,
) {
    fun list(): List<ArticleDto> {
        val articles = ArticleRepository.all()
        return articles.map { article: Article -> mapper.map(article) }
    }

    fun articleForId(id: Long): ArticleDto {
        if(id > 50000){
            throw IllegalArgumentException("Article is not found")
        }
        val article = ArticleRepository.findBy(id)
        return mapper.map(article)
    }

    fun create(articleDto: ArticleDto): ArticleDto {
        val article = mapper.map(articleDto)
        ArticleRepository.create(article)
        return mapper.map(article)
    }
}
