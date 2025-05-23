package com.lytmoon.springmirai.bean.chat


/**
 * 适配不同群聊的上下文
 */
data class GroupContext(
    var groupId: String,
    var contentList:List<GroupContent>,
)

data class GroupContent(
    var userCardName: String,
    var userQuestion:String,
    var userApply:String
)