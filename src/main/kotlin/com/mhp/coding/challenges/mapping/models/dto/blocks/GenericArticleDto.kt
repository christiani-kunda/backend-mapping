package com.mhp.coding.challenges.mapping.models.dto.blocks

/**
 * The Class GenericArticleDto.
 *
 * @author Christian Iradukunda
 */
data class GenericArticleDto(
    var details: Any,
    override val sortIndex: Int,
) : ArticleBlockDto