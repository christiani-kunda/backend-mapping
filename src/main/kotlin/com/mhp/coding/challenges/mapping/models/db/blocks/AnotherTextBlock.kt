package com.mhp.coding.challenges.mapping.models.db.blocks

class AnotherTextBlock(
    var text: String,
    override var sortIndex: Int = 0,
) : ArticleBlock(sortIndex)
