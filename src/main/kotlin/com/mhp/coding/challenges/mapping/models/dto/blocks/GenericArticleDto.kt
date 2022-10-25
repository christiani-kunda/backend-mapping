package com.mhp.coding.challenges.mapping.models.dto.blocks

import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock

/**
 * The Class GenericArticleDto.
 *
 * @author Christian Iradukunda
 */
data class GenericArticleDto(
    var details: ArticleBlock,
    override val sortIndex: Int,
) : ArticleBlockDto