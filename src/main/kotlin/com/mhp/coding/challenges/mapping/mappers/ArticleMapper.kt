package com.mhp.coding.challenges.mapping.mappers

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.mhp.coding.challenges.mapping.models.db.Article
import com.mhp.coding.challenges.mapping.models.db.blocks.GalleryBlock
import com.mhp.coding.challenges.mapping.models.db.blocks.ImageBlock
import com.mhp.coding.challenges.mapping.models.db.blocks.TextBlock
import com.mhp.coding.challenges.mapping.models.db.blocks.VideoBlock
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto
import com.mhp.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto
import com.mhp.coding.challenges.mapping.models.dto.blocks.GenericArticleDto
import org.springframework.stereotype.Component
import java.util.*

@Component
class ArticleMapper {
    fun map(article: Article?): ArticleDto {
        if (article != null) {
            val articleBlockDto = ArrayList<ArticleBlockDto>()
            if(article.blocks.isNotEmpty()) {
                val sortedArticleBlock = article.blocks.sortedBy { it.sortIndex }
                sortedArticleBlock.map { articleBlock ->
                    val mapper = ObjectMapper().registerKotlinModule()
                    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    when(articleBlock){
                        is GalleryBlock -> articleBlockDto.add(mapper.convertValue(articleBlock, GalleryBlockDto::class.java))
                        is ImageBlock ->   articleBlockDto.add(mapper.convertValue(articleBlock, com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock::class.java))
                        is TextBlock ->articleBlockDto.add(mapper.convertValue(articleBlock, com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock::class.java))
                        is VideoBlock ->articleBlockDto.add(mapper.convertValue(articleBlock, com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock::class.java))
                        else -> {
                            articleBlockDto.add(mapper.convertValue(articleBlock, GenericArticleDto::class.java))}
                    }
                }
            }
            return ArticleDto(article.id, article.title, article.description.orEmpty(), article.author.orEmpty(), articleBlockDto)
        }
        return ArticleDto(0, "", "", "", emptyList())
    }

    // Not part of the challenge / Nicht Teil dieser Challenge.
    fun map(articleDto: ArticleDto?): Article = Article(
        title = "An Article",
        blocks = emptySet(),
        id = 1,
        lastModified = Date()
    )
}
