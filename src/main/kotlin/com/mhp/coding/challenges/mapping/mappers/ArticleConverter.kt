package com.mhp.coding.challenges.mapping.mappers

import com.mhp.coding.challenges.mapping.models.db.Article
import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock
import com.mhp.coding.challenges.mapping.models.db.blocks.GalleryBlock
import com.mhp.coding.challenges.mapping.models.db.blocks.ImageBlock
import com.mhp.coding.challenges.mapping.models.db.blocks.TextBlock
import com.mhp.coding.challenges.mapping.models.db.blocks.VideoBlock
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto
import com.mhp.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto
import org.mapstruct.Mapper
import org.mapstruct.ObjectFactory

@Mapper
abstract class ArticleConverter {
    @ObjectFactory
    fun toLib(articleBlock: ArticleBlock): ArticleBlockDto {
        return when (articleBlock) {
            is GalleryBlock -> {
                mapGallery(articleBlock)
            }

            is TextBlock -> {
                map(articleBlock)
            }

            is ImageBlock -> {
                map(articleBlock)
            }

            is VideoBlock -> {
                map(articleBlock)
            }

            else -> {
                map(articleBlock)
            }
        }
    }

    abstract fun map(article: Article?): ArticleDto
//    abstract fun map(article: List<Article>?): List<ArticleDto>
    abstract fun map(articleBlock: ArticleBlock): ArticleBlockDto
    abstract fun map(articleBlock: Collection<ArticleBlock>): Collection<ArticleBlockDto>

    abstract fun mapGallery(galleryBlock: GalleryBlock): GalleryBlockDto
    abstract fun map(textBlock: TextBlock): com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock
    abstract fun map(imageBlock: ImageBlock): com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock
    abstract fun map(videoBlock: VideoBlock): com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock

}