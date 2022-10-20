package com.mhp.coding.challenges.mapping.mappers

import com.mhp.coding.challenges.mapping.models.db.Article
import com.mhp.coding.challenges.mapping.models.db.Image
import com.mhp.coding.challenges.mapping.models.db.ImageSize
import com.mhp.coding.challenges.mapping.models.db.blocks.*
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto
import com.mhp.coding.challenges.mapping.models.dto.ImageDto
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto
import com.mhp.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto
import com.mhp.coding.challenges.mapping.models.dto.blocks.GenericArticleDto
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.ArrayList
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

@Component
class ArticleMapper {
    fun map(article: Article?): ArticleDto {
        if (article != null) {
            val articleBlockDto = ArrayList<ArticleBlockDto>()
            if(article.blocks.isNotEmpty()) {
                val sortedArticleBlock = article.blocks.sortedBy { it.sortIndex }
                sortedArticleBlock.map { articleBlock ->
                    when(articleBlock){
                        is GalleryBlock -> articleBlockDto.add(GalleryBlockDto(mapImages(articleBlock.images),articleBlock.sortIndex))
                        is ImageBlock ->   articleBlockDto.add(com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock(
                            ImageDto(articleBlock.image?.id ?: 0, articleBlock.image?.url ?: "",
                                articleBlock.image?.imageSize ?: ImageSize.SMALL
                            ),articleBlock.sortIndex))
                        is TextBlock ->articleBlockDto.add(com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock(articleBlock.text,articleBlock.sortIndex))
                        is VideoBlock ->articleBlockDto.add(com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock(articleBlock.url,articleBlock.type,articleBlock.sortIndex))
                        else -> {
                            val mapper = jacksonObjectMapper()
                            articleBlockDto.add(GenericArticleDto(mapper.writeValueAsString(articleBlock), articleBlock.sortIndex))}
                    }
                }
            }
            return ArticleDto(article.id, article.title, article.description.orEmpty(), article.author.orEmpty(), articleBlockDto)
        }
        return ArticleDto(0, "", "", "", emptyList())
    }

    fun mapImages(images: List<Image?>) : List<ImageDto>{
        val imagesDto = ArrayList<ImageDto>()
        images.map { image: Image? ->  imagesDto.add(ImageDto(image?.id ?: 0, image?.url ?: "", image?.imageSize ?: ImageSize.SMALL))}
        return imagesDto
    }

    // Not part of the challenge / Nicht Teil dieser Challenge.
    fun map(articleDto: ArticleDto?): Article = Article(
        title = "An Article",
        blocks = emptySet(),
        id = 1,
        lastModified = Date()
    )
}
